package pl.edu.pja.tpo02.flashcardsapp.displayStrategy;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.edu.pja.tpo02.flashcardsapp.model.Entry;

@Component
@Profile("original")
public class OriginalDisplay implements DisplayWords {

    @Override
    public String display(Entry entry) {
        return entry.toString();
    }

    @Override
    public String display(String word) {
        return word;
    }
}
