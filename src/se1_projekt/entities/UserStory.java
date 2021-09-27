package se1_projekt.entities;

import java.io.Serializable;

/*
 * Klasse UserStory, repräsentiert ein langlebiges Entity, welches persistiert wird
 */

public class UserStory implements Comparable<UserStory>, Serializable {
	
	@Override
	public String toString() {
		return "UserStory [titel=" + titel + ", aufwand=" + aufwand + ", id=" + id + ", mehrwert=" + mehrwert
				+ ", risk=" + risk + ", strafe=" + strafe + ", prio=" + prio + "]";
	}

	private String titel;
	private int aufwand;
	private int id;
	private int mehrwert;
	private int risk;
	private int strafe;
	private double prio;
	private  String actor;
	private String funk;
	private String status;
	
	
	// Konstruktor zur Erzeugung (Beschreibung ausgelassen)
	public UserStory(int id, String titel, int mehrwert, int strafe, int aufwand, int risk, double prio, String actor, String funk) {
		this.id = id;
		this.titel = titel;
		this.mehrwert = mehrwert;
		this.strafe = strafe;
		this.aufwand = aufwand;
		this.risk = risk;
		this.prio = prio;
		this.actor = actor;
		this.funk = funk;
		this.status = "todo";
	}
	
	public double getPrio() {
		return prio;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public int getId() {
		return id;
	}
	public String getActor() {
		return actor;
	}
	public String getFunk() {
		return funk;
	}
	public void setFunk(String funk) {
		this.funk = funk;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	/*
	 * Methode zum Vergleich zweier UserStories.
	 * Vergleich ist implementiert auf Basis des Vergleichs
	 * von zwei Prio-Werten. 
	 */
	public int compareTo(UserStory input) {
		if ( input.getPrio() == this.getPrio() ) {
			return 0;
		}
		
		if ( input.getPrio() > this.getPrio() ) {
			return 1;
		}
		else return -1;
	}



}
