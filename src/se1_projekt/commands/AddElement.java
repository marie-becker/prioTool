package se1_projekt.commands;

import se1_projekt.controller.EingabeDialog;
import se1_projekt.model.Container;

public class AddElement implements ICommand, Undoable {

    public void execute() {
        String[] com = EingabeDialog.strings;
        if(com.length == 4 && com[2].equals("actor")){
            Container.getInstance().addActor(com[3]);
        }
        else System.out.println("Unknown command");
    }

    public void undo() {
        Container.getInstance().deleteLastAc();
        System.out.println("Zuletzt hinzugefügter Akteur wurde gelöscht.");
    }
}
