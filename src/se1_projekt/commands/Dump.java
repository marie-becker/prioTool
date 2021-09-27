package se1_projekt.commands;

import se1_projekt.controller.EingabeDialog;
import se1_projekt.entities.UserStory;
import se1_projekt.model.Container;
import se1_projekt.views.Ausgabe;

public class Dump implements ICommand {

    public void execute() {
        String[] com = EingabeDialog.strings;
        Ausgabe dialog = new Ausgabe();
        if(com.length == 1){
            dialog.display(Container.getInstance().currentUSasDTO());
        }
        else if(com.length == 2){
            try {
                int i = Integer.parseInt(com[1]);
                UserStory us = Container.getInstance().getUserStory(i);
                if(us == null) return;
                else Ausgabe.printEverything(us);
            }catch(NumberFormatException e){
                System.out.println("Unknown command");
            }
        }
        else if(com.length == 4 && com[2].equals("status")){
            dialog.displayStatus(Container.getInstance().currentUSasDTO());
        }
        else System.out.println("Unknown command");
    }
}
