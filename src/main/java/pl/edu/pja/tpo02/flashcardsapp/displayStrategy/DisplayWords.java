package pl.edu.pja.tpo02.flashcardsapp.displayStrategy;

import pl.edu.pja.tpo02.flashcardsapp.model.Entry;

public interface DisplayWords {

    String display(Entry entry);
    String display(String word);

}
