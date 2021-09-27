package se1_projekt.views;

import se1_projekt.DTOs.UserStoryDTO;
import se1_projekt.controller.EingabeDialog;
import se1_projekt.entities.UserStory;
import se1_projekt.model.Container;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Ausgabe {

    String testEdit = "";

    public void display(List<UserStoryDTO> list) {
        // Falls Liste leer kurze Ausgabe und raus.
        if ( list.isEmpty() ) {
            System.out.println("Keine User Stories vorhanden");
            return;
        }
        Ausgabe.printTable(list);
    }

    public void displayStatus(List<UserStoryDTO> liste) {
        String[] com = EingabeDialog.strings;

        if(liste.isEmpty()) {
            System.out.println("Keine User Stories vorhanden");
            return;
        }

        List<UserStoryDTO> list = liste.stream()
                .filter(s -> s.getStatus().equals(com[3]))
                .collect(Collectors.toList());

        if(list.isEmpty()) {
            System.out.println("Keine User Stories mit Status '" + com[3] + "' vorhanden");
            return;
        }
        printTable(list);
    }

    static void printTable(List<UserStoryDTO> list) {
        Collections.sort( list );

        String leftAlignFormat = "| %-3d | %-4s | %-8s | %-50s |%n";

        System.out.format("+-----+------+----------+----------------------------------------------------+%n");
        System.out.format("| ID  | Prio |  Status  | Titel                                              |%n");
        System.out.format("+-----+------+----------+----------------------------------------------------+%n");
        list.forEach(UserStoryDTO -> System.out.format(leftAlignFormat, UserStoryDTO.getId(), UserStoryDTO.getPrio(),
                UserStoryDTO.getStatus(), UserStoryDTO.getTitel()));
        System.out.format("+-----+------+----------+----------------------------------------------------+%n");
    }

    public static void help(){
        System.out.println(
                        "actors                             Ausgabe der registrierten Akteure" + "\n" +
                        "addElement - actor <actor>         Hinzufügen von Akteuren" + "\n" +
                        "analyze <ID>                       Analysieren einer User Story mittels ID" + "\n" +
                        "analyze - All                      Analysieren aller User Stories" + "\n" +
                        "analyze <ID> - details             Analysieren einer User Story mit Ausgabe der Defizite" + "\n" +
                        "analyze <ID> - details - hints     Analysieren einer User Story mit Ausgabe der Defizite und Hinweisen zum Beheben dieser" + "\n" +
                        "dump                               Ausgabe aller User Stories" + "\n" +
                        "dump <ID>                          Detaillierte Ausgabe der User Story mit der ID <ID>" + "\n" +
                        "dump - status <Status>             Ausgabe aller User Stories mit Status <Status>" + "\n" +
                        "edit <ID> - funktionalität         Editieren der Funktionalität einer User Story mit der ID <ID>" + "\n" +
                        "edit <ID> - titel                  Editieren des Titels einer User Story mit der ID <ID>" + "\n" +
                        "enter                              Hinzufügen von User Stories" + "\n" +
                        "exit                               Beenden des Programms" + "\n" +
                        "help                               Anzeigen aller Befehle" + "\n" +
                        "load                               Laden und Vereinen der gespeicherten User Stories und Akteure mit den aktuellen (sofern keine IDs kollidieren)" + "\n" +
                        "status <ID> <Status>               Setzt den Status einer User Story mit der ID <ID> auf den Status <Status>" + "\n" +
                        "store                              Speicherung der aktuellen User Stories und Akteure" + "\n" +
                        "undo                               Rückgängigmachen des letzten Commands");
    }

    public static void actors(){
        List<String> actors = Container.getInstance().getActors();
        if(actors.isEmpty()) {
            System.out.println("Keine Akteure im System registriert.");
            System.out.println("Registrieren sie einen Akteur mit 'addElement - actor <Akteurname>'");
        }
        else {
            for (String s : actors) System.out.println(s);
        }
    }

    public static void printEverything(UserStory us){
        System.out.println(
                "_______________|_____________________________________________________________________________________________________________" + "\n" +
                "ID             | " + us.getId() + "\n" +
                "Titel          | " + us.getTitel() + "\n" +
                "Priorität      | " + us.getPrio() + "\n" +
                "Akteur         | " + us.getActor() + "\n" +
                "Funktionalität | " + us.getFunk()
        );
    }
}
