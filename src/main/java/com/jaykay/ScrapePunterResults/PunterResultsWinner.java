package com.jaykay.ScrapePunterResults;

import java.util.List;

public class PunterResultsWinner {
	
	public String getRaceDate() {
		return raceDate;
	}
	public void setRaceDate(String raceDate) {
		this.raceDate = raceDate;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	
	private String raceDate;
	private String meetingName;
	public List<PunterRace> Races;

}
