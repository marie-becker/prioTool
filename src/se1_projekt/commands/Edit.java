package se1_projekt.commands;

import se1_projekt.controller.EingabeDialog;
import se1_projekt.controller.Console;
import se1_projekt.entities.UserStory;
import se1_projekt.model.Container;


public class Edit implements ICommand{

    public void execute() {
        String[] com = EingabeDialog.strings;
        try {
            int id = Integer.parseInt(com[1]);
            if(com[2].equals("-") && com[3].equals("funktionalität")){
                Console console = new Console();
                String input = console.readLine("Geben sie eine neue Funktionalität für die User Story mit der ID " + id +" an:" + "\n");
                UserStory us = Container.getInstance().getUserStory(id);
                us.setFunk(input);
                System.out.println("Funktionalität erfolgreich geändert.");
            }
            else if(com[2].equals("-") && com[3].equals("titel")){
                Console console = new Console();
                String input = console.readLine("Geben sie einen neuen Titel für die User Story mit der ID " + id +" an:" + "\n");
                UserStory us = Container.getInstance().getUserStory(id);
                us.setTitel(input);
                System.out.println("Titel erfolgreich geändert.");
            }
            else System.out.println("Unkown Command!");
        }catch(NumberFormatException e){
            System.out.println("Unkown Command!");
        }
    }
}
