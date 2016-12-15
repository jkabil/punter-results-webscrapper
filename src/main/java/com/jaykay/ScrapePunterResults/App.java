package com.jaykay.ScrapePunterResults;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String filename = "_Results .csv";
    	String CURRENT_DATE = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    	String date = CURRENT_DATE;
    	//System.out.println(args.length);
    	if (args.length < 1 || args.length > 2) {
    		System.out.println(("Incorrect usage!" + "\n" + "java -jar ScrapePunterResults.jar <filepath> [<date in YYYY-MM-DD format>]"));
    		System.exit(0);
    	}
    	else if (args.length == 2) {
    		date = args[1];
    	}
    	filename = args[0] + "\\" + date + filename;
    	//System.out.println(filename + "\n" + date);
    	writeToCSVFile(filename, new PunterResults().scrapeResults(date));
    	System.out.println("Scraping for " + date + " Results Completed" + "\n" + "File available in " + filename);
    	
    }
    
    public static void writeToCSVFile (String folderPath, List<PunterResultsWinner> winnersList) {
    	try {
			FileWriter w = new FileWriter(new File(folderPath));
			List<String> headings = new ArrayList<String>( Arrays.asList("Racedate","MeetingName","RaceNo","RaceTime","TrackConditions","WinnerHorseNo","WinnerHorseName","TopToteWin",
					"TopTotePrice","SecondHorseNo","SecondHorseName","SecondToteprice","ThirdHorseNo","ThirdHorseName","ThirdTotePrice"));
			CSVUtils.writeLine(w, headings);
			for (PunterResultsWinner winner : winnersList) {
				for (PunterRace race : winner.Races) {
					List<String> values = new ArrayList<String>();
					values.add(winner.getRaceDate());
					values.add(winner.getMeetingName());
					values.add(race.getRaceNo());
					values.add(race.getRaceTime());
					values.add(race.getTrackConditions());
					values.add(race.getWinnerNo());
					values.add(race.getWinnerHorseName());
					values.add(race.getTopToteWin());
					values.add(race.getTopTotePrice());
					values.add(race.getFirstRunnerUpNo());
					values.add(race.getFirstRunnerUpHorseName());
					values.add(race.getSecondTotePrice());
					values.add(race.getSecondRunnerUpNo());
					values.add(race.getSecondRunnerUpHorseName());
					values.add(race.getThirdTotePrice());
					CSVUtils.writeLine(w, values);
				}
			}
			w.flush();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}    	
    }
}
