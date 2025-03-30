package pl.edu.pja.tpo02.flashcardsapp.exception;

public class EntryNotFoundException extends Exception{
    public EntryNotFoundException() {
        super("Entry not found");
    }
}
