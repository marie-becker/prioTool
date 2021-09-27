package se1_projekt.model;


import se1_projekt.controller.Console;
import se1_projekt.entities.UserStory;
import se1_projekt.exceptions.ContainerException;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersistenceManager {

	private PersistenceManager(){}

	// URLs der Dateien, in denen die Objekte gespeichert werden
	public static final  String LOCSTORY= "\\Users\\Marie\\Documents\\SE1 Projekt\\userstories.ser";
	public static final  String LOCACTOR= "\\Users\\Marie\\Documents\\SE1 Projekt\\actors.ser";


	public static void storeUS(List<UserStory> list ) throws ContainerException {
		try(FileOutputStream fos = new FileOutputStream( PersistenceManager.LOCSTORY ); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			if(list.isEmpty()){
				System.out.println("Es gibt keine User Story zu speichern.");
				return;
			}
		   	oos.writeObject( list );
			System.out.println(  list.size() + " User Stories wurden erfolgreich gespeichert!");
		}
		catch (IOException e) {
		  throw new ContainerException("Fehler beim Laden der Datei!");
		}
	}

	public static void storeAct( List<String> list ) throws ContainerException {
		try(FileOutputStream fos = new FileOutputStream( PersistenceManager.LOCACTOR ); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			if(list.isEmpty()){
				System.out.println("Es gibt keine Akteure zu speichern.");
				return;
			}
			oos.writeObject( list );
			System.out.println(  list.size() + " Akteuren wurden erfolgreich gespeichert!");
		}
		catch (IOException e) {
			throw new ContainerException("Fehler beim Laden der Datei!");
		}
	}


	public static List<UserStory> loadUS() {
		List<UserStory> list = null;
		List<UserStory> currentUS = Container.getInstance().getCurrentList();

		try(FileInputStream fis = new FileInputStream(LOCSTORY); ObjectInputStream ois = new ObjectInputStream(fis)) {

		  // Auslesen der Liste
		  Object obj = ois.readObject();
		  if (obj instanceof List<?>) {
			  list = (List) obj;
		  }
		  for(UserStory one : list){
		  	for(UserStory two : currentUS){
		  		if(one.getId() == two.getId()) {
		  			System.out.println("Mindestens eine ID der gespeicherten User Stories deckt sich mit einer der aktuellen!");
		  			Console console = new Console();
		  			while(true) {
		  				System.out.println("Möchten Sie die aktuellen User Stories mit den gespeicherten überschreiben? [y/n]");
		  				String input = console.readLineDecision("> ");
		  				if (input.equals("y")){
		  					System.out.println("Aktuelle User Stories wurden mit den gespeicherten überschrieben.");
		  					return list;
						}
		  				if (input.equals("n")){
		  					System.out.println("Die aktuellen User Stories wurden beibehalten.");
		  					return Container.getInstance().getCurrentList();
						}
		  			}
		  		}
		  	}
		  }
		  System.out.println("Es wurden " + list.size() + " User Stories erfolgreich ins System geladen!");
		  return Stream.concat(Container.getInstance().getCurrentList().stream(), list.stream())
					  .collect(Collectors.toList());
		}
		catch (IOException | ClassNotFoundException e) {
			System.out.println("Es existieren keine User Stories, die ins System geladen werden können.");
			return Container.getInstance().getCurrentList();
		}
	}

	public static List<String> loadActors(){
		List<String> list = null;

		try(FileInputStream fis = new FileInputStream(LOCACTOR); ObjectInputStream ois = new ObjectInputStream(fis)) {
			Object obj = ois.readObject();
			if (obj instanceof List<?>) {
				list = (List) obj;
			}
			List<String> currentActors = Container.getInstance().getActors();
			List<String> newActors = Stream
					.concat(currentActors.stream(), list.stream().filter(ac -> currentActors.indexOf(ac) < 0))
					.collect(Collectors.toList());
			System.out.println("Es wurden " + list.size() + " Akteure erfolgreich ins System geladen!");
			return newActors;
		}catch (IOException | ClassNotFoundException e) {
			System.out.println("Es existieren keine Akteure, die ins System geladen werden können.");
			return Container.getInstance().getActors();
		}
	}
}
