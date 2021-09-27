package se1_projekt.commands;

import se1_projekt.views.Ausgabe;

public class Actors implements ICommand{

    public void execute(){
        Ausgabe.actors();
    }
}
