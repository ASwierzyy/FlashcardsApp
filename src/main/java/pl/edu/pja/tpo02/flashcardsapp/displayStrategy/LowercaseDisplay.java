package pl.edu.pja.tpo02.flashcardsapp.displayStrategy;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.edu.pja.tpo02.flashcardsapp.model.Entry;

@Component
@Profile("lowercase")
public class LowercaseDisplay implements DisplayWords {

    @Override
    public String display(Entry entry) {
        return entry.toString().toLowerCase();
    }

    @Override
    public String display(String word) {
        return word.toLowerCase();
    }
}
