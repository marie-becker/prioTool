package se1_projekt.commands;

import se1_projekt.views.Ausgabe;

public class Help implements ICommand{

    public void execute() {
        Ausgabe.help();
    }
}
