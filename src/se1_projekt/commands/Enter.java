package se1_projekt.commands;

import se1_projekt.controller.EingabeDialog;
import se1_projekt.model.Container;


public class Enter implements ICommand, Undoable {

    public void execute() {
        EingabeDialog.enter();
    }

    public void undo() {
        Container.getInstance().deleteLastUS();
        System.out.println("Die zuletzt hinzugefügte User Story wurde gelöscht.");
    }
}
