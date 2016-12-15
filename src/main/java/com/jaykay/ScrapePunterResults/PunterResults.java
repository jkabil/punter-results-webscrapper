package com.jaykay.ScrapePunterResults;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PunterResults {
	
	private final String url = "https://www.punters.com.au/racing-results/";
	
	public List<PunterResultsWinner> scrapeResults(String date){
		List<PunterResultsWinner> winnersList = new ArrayList<PunterResultsWinner>();
		
		try {
			Document doc = Jsoup.connect(url+date).get();
			
			Elements tracks = doc.select("div[class=results-meeting]");
			
			for (Element track : tracks){
				PunterResultsWinner winner = new PunterResultsWinner();
				winner.setRaceDate(date);
				winner.setMeetingName(track.select("div[class=results-meeting]  h3 > a").text().replace(" Results", ""));
				winner.Races = new ArrayList<PunterRace>();
				Elements races = track.select("table[class=results-table ]");
				for (Element race : races){
					PunterRace r = new PunterRace();					
					r.setRaceNo(race.select("table[class=results-table] > thead > tr > th:nth-child(1) > b").text());
					r.setRaceTime(new SimpleDateFormat("h:mm a").format(new Date(Long.parseLong(race.select("abbr[class~=timestamp").attr("data-utime")) *1000L)));
					r.setTrackConditions(race.select("thead > tr > th:nth-child(1) > div:nth-child(2) > span:nth-child(3)").text());					
					r.setWinnerNo(race.select("td[class=result-value-1]").first().ownText());
					r.setWinnerHorseName(race.select("td[class=result-value-1] > a").text());
					r.setTopToteWin(race.select("table[class=results-table] > thead > tr > th:nth-child(1) > div:nth-child(2) > span:nth-child(2)").text());
					r.setTopTotePrice(race.select("td[class=result-value-1]").first().siblingElements().get(1).text());
					if (!race.select("td[class=result-value-2]").isEmpty()) {
						r.setFirstRunnerUpNo(race.select("td[class=result-value-2]").first().ownText());
						r.setFirstRunnerUpHorseName(race.select("td[class=result-value-2] > a").text());
						r.setSecondTotePrice(race.select("td[class=result-value-2]").first().siblingElements().get(1).text());
					}					
					if (!race.select("td[class=result-value-3]").isEmpty()) {
						r.setSecondRunnerUpNo(race.select("td[class=result-value-3]").first().ownText());
						r.setSecondRunnerUpHorseName(race.select("td[class=result-value-3] > a").text());
						r.setThirdTotePrice(race.select("td[class=result-value-3]").first().siblingElements().get(1).text());						
					}
					/*System.out.println(winner.getMeetingName() + "," + r.getRaceNo() + "," 
							+ r.getRaceTime() + "," + r.getTrackConditions() +  "," + r.getWinnerNo() + "," 
							+ r.getWinnerHorseName() + "," + r.getTopToteWin() + "," + r.getTopTotePrice() + ","
							+ r.getFirstRunnerUpNo() + "," + r.getFirstRunnerUpHorseName() + "," + r.getSecondTotePrice() + ","
							+ r.getSecondRunnerUpNo() + "," + r.getSecondRunnerUpHorseName() + "," + r.getThirdTotePrice());*/
					winner.Races.add(r);
				}
				winnersList.add(winner);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return winnersList;
	}

}
