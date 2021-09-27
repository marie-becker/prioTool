package se1_projekt.commands;

public interface Undoable {
    //Wird von Commands implementiert, die Undoable sein sollen. Hier: AddElement, Enter, Load
    void undo();
}
