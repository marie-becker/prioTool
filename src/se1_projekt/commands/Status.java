package se1_projekt.commands;

import se1_projekt.controller.EingabeDialog;
import se1_projekt.entities.UserStory;
import se1_projekt.model.Container;

public class Status implements ICommand {

    public void execute() {
        String[] com = EingabeDialog.strings;
        if(com.length == 3 && (com[2].equals("todo") || com[2].equals("progress") || com[2].equals("done"))){
            try {
                int id = Integer.parseInt(com[1]);
                UserStory us = Container.getInstance().getUserStory(id);
                if(us == null) return;
                us.setStatus(com[2]);
                System.out.println("Die User Story mit der ID " + us.getId() + " wurde auf den Status " + com[2] + " gesetzt.");
            }catch(NumberFormatException e) {
                System.out.println("Unknown Command");
            }
        }else{
            System.out.println("Unknown command");
        }
    }
}
