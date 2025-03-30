package pl.edu.pja.tpo02.flashcardsapp.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Entry {

    @Id
    private Long idOfEntry;
    private String polish;
    private String english;
    private String german;

    public Entry() {}

    public Entry(Long id, String polish, String english, String german){
        this.idOfEntry = id;
        this.polish = polish;
        this.english = english;
        this.german = german;
    }

    public Long getId() {
        return idOfEntry;
    }

    public void setId(Long idOfEntry) {
        this.idOfEntry = idOfEntry;
    }

    public String getPolish() {
        return polish;
    }

    public String setPolish(String s){
        polish = s;
        return polish;
    }

    public String getEnglish() {
        return english;
    }

    public String setEnglish(String s){
        english = s;
        return english;
    }

    public String getGerman() {
        return german;
    }

    public String setGerman(String s){
        german = s;
        return german;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | PL: %s | EN: %s | DE: %s" , idOfEntry ,polish , english , german);
    }
}
