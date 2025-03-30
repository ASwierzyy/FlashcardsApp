package pl.edu.pja.tpo02.flashcardsapp.displayStrategy;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.edu.pja.tpo02.flashcardsapp.model.Entry;

@Component
@Profile("uppercase")
public class UppercaseDisplay implements DisplayWords {

    @Override
    public String display(Entry entry) {
        return entry.toString().toUpperCase();
    }

    @Override
    public String display(String word) {
        return word.toUpperCase();
    }
}
