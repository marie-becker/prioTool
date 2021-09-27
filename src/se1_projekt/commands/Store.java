package se1_projekt.commands;

import se1_projekt.exceptions.ContainerException;
import se1_projekt.model.Container;

public class Store implements ICommand{


    public void execute() {
        try {
            Container.getInstance().store();
        } catch (ContainerException e) {
            System.out.println("Fehler beim Abspeichern!");
        }
    }
}
