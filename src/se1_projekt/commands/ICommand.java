package se1_projekt.commands;

public interface ICommand {
    //Interface für alle Commands. Undoable commands implementieren zusätzlich Undoable
    void execute();
}
