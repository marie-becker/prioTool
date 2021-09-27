package se1_projekt.strategies;

import se1_projekt.entities.UserStory;
import se1_projekt.model.Container;
import java.util.Arrays;
import java.util.List;

public class AnalyzeStrategyA implements IStrategy {
    //Diese Klasse ist eine Konkrete Strategie (Strategy Pattern)

    //Basic Funktion
    public int analyzeUS(int id) {
        UserStory us = Container.getInstance().getUserStory(id);
        if(us == null) return -1;
        int prozent = 100;
        if(us.getFunk() != null | !us.getFunk().equals("")){
            if(!mw(us.getFunk())) prozent -= 30;
            if(!aktiv(us.getFunk())) prozent -= 20;
            if(!complex(us.getFunk())) prozent -= 10;
        }else prozent -= 60;

        if(act(us.getActor())){
            if(!us.getFunk().contains(us.getActor())){
                prozent -= 10;
            }
        }else prozent -= 30;


        if(!title(us.getTitel())) prozent -= 10;
        return prozent;
    }

    //analyze - all
    public void analyzeAll() {
        List<UserStory> list = Container.getInstance().getCurrentList();
        if(list == null || list.isEmpty()){
            System.out.println("Es gibt keine User Stories, die analysiert werden könnten.");
            return;
        }
        double prozent = 0;
        for(UserStory us : list){
            prozent += analyzeUS(us.getId());
        }
        prozent = prozent/list.size();
        System.out.println("Ihre " + list.size() + " User Stories haben durchschnittliche eine Qualität von "
                + prozent + "% (" + getNote(prozent) +")");
    }

    //analyze <ID>
    public boolean analyzeById(int id) {
        int prozent = analyzeUS(id);
        if(prozent != -1){
            System.out.println("Die User Story mit der ID " + id + " hat folgende Qualität: \n" + prozent + "% (" + getNote(prozent) + ")");
            return true;
        }
        return false;
    }

    //analyze <ID> - details
    public boolean analyzeDetail(int id) {
        if(!analyzeById(id)) return false;
        System.out.println("\n" + "Details:");
        UserStory us = Container.getInstance().getUserStory(id);
        if(analyzeUS(id) == 100) System.out.println("Alles ok.");
        else {
            if(!us.getFunk().equals("") ){
                if(!mw(us.getFunk())) System.out.println("Kein schriftlicher Mehrwert zu erkennen (-30%)");
                if(!aktiv(us.getFunk())) System.out.println("Funktionalität nicht im Aktiv formuliert (-20%)");
                if(!complex(us.getFunk())) System.out.println("Anforderung zu komplex für eine User Story (-10%)");
            }else System.out.println("Keine Funktionalität formuliert (-60%)");

            if (act(us.getActor())) {
                if(!us.getFunk().contains(us.getActor())) {
                    System.out.println("Zugewiesener Akteur wird nicht in die Funktionalität eingebunden (-10%)");
                }
            }else System.out.println("Akteur ('" + us.getActor() + "') ist nicht bekannt (-30%)");

            if (!title(us.getTitel())) System.out.println("Kein Titel formuliert (-10%)");
        }
        return true;
    }

    //analyze <ID> - details - hints
    public void analyzeHints(int id){
        if(!analyzeDetail(id)) return;
        System.out.println("\n" + "Hints:");
        UserStory us = Container.getInstance().getUserStory(id);
        if(analyzeUS(id) == 100) System.out.println("Alles ok.");
        else {
            if (!us.getFunk().equals("")){
                if(us.getFunk().equals("")) System.out.println("US IST ' '");
                if(!mw(us.getFunk())) System.out.println("Formulieren sie den Zweck der User Story/einen schriftlichen Mehrwert!");
                if(!aktiv(us.getFunk())) System.out.println("Formulieren sie die Funktionalität im Aktiv!");
                if (!complex(us.getFunk())) System.out.println("Verteilen sie die Anforderungen auf mehrere User Stories!");
            }else System.out.println("Fügen sie eine Funktionalität hinzu!");

            if (act(us.getActor())) {
                if(!us.getFunk().contains(us.getActor())) {
                    System.out.println("Schreiben sie die Funktionalität aus Sicht des Akteurs!");
                }
            }else System.out.println("Registrieren sie einen neuen Akteur '" + us.getActor() + "'!");

            if (!title(us.getTitel())) System.out.println("Fügen sie einen Titel hinzu!");
        }
    }


    //Methoden die der Berechnung der Qualität bzw. der Faktoren dienen:

    //Prüft auf Bandwurmsätze
    public boolean complex(String descr){
        if(descr == null || descr.equals("")) return false;
        int count = (descr.length() - descr.replace("und", "").length()) / "und".length();
        int count2 = (descr.length() - descr.replace(",", "").length()) / ",".length();
        return count <= 1 && count2 <= 1;
    }

    //Prüft auf schriftlichen Mehrwert
    public boolean mw(String descr){
        if(descr != null || !descr.equals("")) {
            List<String> kausal = Arrays.asList("weil", "deswegen", "da", "damit", "dank", "deshalb", "daher", "somit", "um", "wegen",
                    "aufgrund", "auf Grund", "denn", "zumal", "infolge", "mittels", "mit Hilfe", "durch");
            for (String s : kausal) {
                if (descr.contains(s)) return true;
            }
        }
        return false;
    }

    //Prüft auf Akteur
    public boolean act(String actor){
        List<String> actors = Container.getInstance().getActors();
        return actor != null && !actor.equals("") && actors.contains(actor);
    }

    //Prüft auf nichtleeren Titel
    public boolean title(String title){
        return !(title == null || title.equals(""));
    }

    //Prüft, ob Funktionalität im aktiv geschrieben ist
    public boolean aktiv(String descr){
        if(descr == null || descr.equals("")) return false;
        List<String> passiv = Arrays.asList("werde", "wirst", "wird", "werden", "werdet");
        for(String s: passiv){
            if(descr.contains(s)) return false;
        }
        return true;
    }

    //Gibt prozentuale Qualität als Note in Worten aus
    public String getNote(double prozent){
        if(prozent >= 90) return "sehr gut";
        if(prozent >= 70) return "gut";
        if(prozent >= 60) return "befriedigend";
        if(prozent >= 50) return "ausreichend";
        else return "mangelhaft";
    }
}
