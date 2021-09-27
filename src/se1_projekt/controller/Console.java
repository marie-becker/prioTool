package se1_projekt.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
	private BufferedReader input;

	public Console(){
		input = new BufferedReader( new InputStreamReader(System.in ) );
	}

	public String readLine(String prompt){
		String strInput = null;

		System.out.print( prompt );
		try {
			strInput = input.readLine();
		} catch (IOException e) {
			System.out.println("Fehler beim Einlesen des Commands!");
		}
		return strInput;
	}


	public int readLineInt(String prompt){
		String strInput = null;
		System.out.print(prompt);

		// Eingabe des Wertes
		try {
			strInput = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Umwandlung nach Integer --> int
		int id;
		try {
			id = Integer.parseInt(strInput);
			if(id <= 0){
				System.out.println("Zahl muss größer als 0 sein!");
				return this.readLineInt(prompt);
			}
		} catch (java.lang.NumberFormatException e){
			System.out.println("Das ist keine Zahl!");
			return this.readLineInt(prompt);
		}
		return id;
	}

	public int readLineToFive(String prompt){
		String strInput = null;
		System.out.print(prompt);

		// Eingabe des Wertes
		try {
			strInput = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Umwandlung nach Integer --> int
		int id;
		try {
			id = Integer.parseInt(strInput);
			if(id <= 0 || id > 5){
				System.out.println("Zahl muss zwischen 1 und 5 liegen!");
				return this.readLineToFive(prompt);
			}
		} catch (java.lang.NumberFormatException e){
			System.out.println("Das ist keine Zahl!");
			return this.readLineInt(prompt);
		}
		return id;
	}

	public String readLineDecision(String prompt) throws IOException {
		String strInput;
		System.out.print(prompt);
			strInput = input.readLine();
			if(!(strInput.equals("y") || strInput.equals("n"))){
				System.out.println("Ungültige Eingabe!");
			}
			return strInput;
	}


}
