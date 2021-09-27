package se1_projekt.model;

import se1_projekt.DTOs.UserStoryDTO;
import se1_projekt.entities.UserStory;
import se1_projekt.exceptions.ContainerException;

import java.util.ArrayList;
import java.util.List;


/**
 * Klasse zum Management sowie zur Eingabe unnd Ausgabe von User Stories.
 * (Laut MVC Pattern: Controller)
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 * 
 */

public class Container {
	
	// Interne ArrayList zur Abspeicherung der Objekte
	private List<UserStory> liste;
	private List<String> actors;
	

	private static Container instance = new Container();
	
	public static synchronized Container getInstance() {
	if (instance == null) {
		instance = new Container();
	}
		return instance;
	}
	

	private Container(){
		actors = new ArrayList<>();
		liste = new ArrayList<>();
	}
	

	public void addUserStory ( UserStory r ) throws ContainerException {		
		if ( contains(r)) {
			throw new ContainerException("ID bereits vorhanden!");
		}
		liste.add(r);
	}

	public void addActor(String a){
		if(!actors.contains(a)){
			actors.add(a);
			System.out.println("Akteur '" + a + "' wurde im System registriert.");
		}
		else System.out.println("Akteur '" + a + "' existiert bereits.");
	}
	

	private boolean contains(UserStory r) {
		int id = r.getId();
		if(liste == null) return false;
		for ( UserStory rec : liste) {
			if ( rec.getId() == id ) {
				return true;
			}
		}
		return false;
	}


	public List<UserStory> getCurrentList() {
		return this.liste;
	}

	public List<String> getActors(){
		return this.actors;
	}
	
	/*
	 * Methode zur Auslieferung der UserStory-Objekte.
	 * Es werden keine Referenzen auf die Entity selber übergeben,
	 * sondern nur DTO
	 */
	public List<UserStoryDTO> currentUSasDTO() {
		List<UserStoryDTO> listeDTO = new ArrayList<>();
		
		for ( UserStory us : this.liste ) {
			UserStoryDTO dto = new UserStoryDTO();
			dto.setTitel( us.getTitel() );
			dto.setPrio(us.getPrio());
			dto.setStatus(us.getStatus());
			dto.setId(us.getId());
			listeDTO.add(dto);
		}
		
		return listeDTO;
	}
	

	public UserStory getUserStory(int id) {
		for ( UserStory rec : liste) {
			if (id == rec.getId() ){
				return rec;
			}
		}
		System.out.println("Es existiert keine User Story mit der ID " + id + ".");
		return null;
	}

	public void load() {
		this.liste = PersistenceManager.loadUS();
		this.actors = PersistenceManager.loadActors();
	}

	public void store() throws ContainerException {
		PersistenceManager.storeUS( this.liste );
		PersistenceManager.storeAct(this.actors);
	}

	public void replaceUS(List<UserStory> newList){
		this.liste = newList;
	}

	public void replaceAct(List<String> newList){
		this.actors = newList;
	}

	public void deleteLastUS(){
		this.liste.remove(liste.size()-1);
	}

	public void deleteLastAc(){ this.actors.remove(actors.size()-1); }

	public void deleteUSById(int id){
		UserStory us = getUserStory(id);
		if(us != null) {
			this.liste.remove(us);
			System.out.println("User Story mit der ID " + id + " wurde gelöscht.");
		}
	}

	public boolean deleteAc(String actor){
		if(actors.contains(actor)){
			this.actors.remove(actor);
			return true;
		}
		else{
			System.out.println("Kein Akteur '" + actor + "' im System.");
			return false;
		}
	}

}
