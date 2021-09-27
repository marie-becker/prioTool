package se1_projekt.controller;

import se1_projekt.commands.*;
import se1_projekt.entities.UserStory;
import se1_projekt.exceptions.ContainerException;
import se1_projekt.model.Container;
import se1_projekt.model.PersistenceManager;
import se1_projekt.strategies.AnalyzeStrategyA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class EingabeDialog {
	public static String[] strings;
	Invoker inv = new Invoker();

	// Diese Methode realisiert die Eingabe
	public void startEingabe() {

	    //Agiert im Strategy Pattern als Policy/Client
		Analyze.setStrategie(new AnalyzeStrategyA());


		String strInput = null;

		inv.register("load", new Load());
		inv.register("store", new Store());
		inv.register("dump", new Dump());
		inv.register("enter", new Enter());
		inv.register("actors", new Actors());
		inv.register("analyze", new Analyze());
		inv.register("addElement", new AddElement());
		inv.register("status", new Status());
		inv.register("help", new Help());
		inv.register("edit", new Edit());


		System.out.println("Prio-Tool V3.0 - c/o Marie Becker in 2020");
		System.out.println("Based on Prio-Tool V2.0 - c/o Sascha Alda in 2018\n");
		System.out.println("Momentaner Dateipfade für Speicherung: \n " + PersistenceManager.LOCACTOR + "\n " + PersistenceManager.LOCSTORY +
				"\n In Klasse PersistenceManager änderbar (LOCACTOR und LOCSTORY)" + "\n");
		System.out.println("Willkommen!" + "\n" + "Zum Anzeigen aller Befehle, tippen sie 'help' \n");

		BufferedReader input = new BufferedReader( new InputStreamReader(System.in ) );

		while ( true ) {
			try {
				System.out.print("> ");
				strInput = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			strings = strInput.split(" ");
			if(strings[0].equals("undo")) inv.undo();
			else if(strings[0].equals("exit")){
				System.out.println("Auf Wiedersehen!");
				break;
			}
			else inv.execute(strings[0]);
		}
	}

	//Nimmt Angaben für eine neue User Story entgegen
	//Alle Werte für Prio 1-5, Aufwand frei
    public static void enter() {
        Console con = new Console();
        System.out.println("Geben sie die Grunddaten der User Story ein:");

        int id = con.readLineInt("ID der User Story: ");

        String titel = con.readLine("Titel der User Story: ");

        String actor = con.readLine("Akteur der User Story: ");

        String funk = con.readLine("Funktionalität der User Story: ");

        int aufwand = con.readLineInt("Aufwand der User Story: ");

        int risk = con.readLineToFive("Risiko der User Story: ");

        int mehrwert = con.readLineToFive("Mehrwert der User Story: ");

        int strafe = con.readLineToFive("Strafe der User Story: ");

        // Berechnung der Priorisierung
        double prio =  ( ((double)mehrwert + (double)strafe ) / ( (double)aufwand + (double)risk ));
        prio = (int) (prio*100 + 0.5) / 100.0;
        System.out.println("User Story mit ID: " + id + " hat die Prio: " + prio);

        // Neues Objekt vom Typ UserStory
        UserStory us = new UserStory( id, titel, mehrwert, strafe, aufwand, risk , prio, actor, funk);

        // Hinzufügen
        try {
            Container.getInstance().addUserStory( us );
        } catch (ContainerException e) {
            e.printStackTrace();
            System.out.println("Fehler beim Abspeichern der User Story!");
        }

    }
}
