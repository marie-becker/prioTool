package se1_projekt.DTOs;

public class UserStoryDTO implements Comparable<UserStoryDTO> {

	private String titel;
	private double prio;
	private int id;
	private String status;
	
	public String getTitel() {
		return this.titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}
	public double getPrio() {
		return prio;
	}
	public void setPrio(double prio) {
		this.prio = prio;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/*
	 * Methode zum Vergleich zweier UserStories.
	 * Vergleich ist implementiert auf Basis des Vergleichs
	 * von zwei Prio-Werten. 
	 */
	public int compareTo(UserStoryDTO input) {
		if ( input.getPrio() == this.getPrio() ) {
			return 0;
		}
		
		if ( input.getPrio() > this.getPrio() ) {
			return 1;
		}
		else return -1;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
