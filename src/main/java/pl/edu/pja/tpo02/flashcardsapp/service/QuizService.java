package pl.edu.pja.tpo02.flashcardsapp.service;

import org.springframework.stereotype.Service;
import pl.edu.pja.tpo02.flashcardsapp.dto.QuizAnswerDTO;
import pl.edu.pja.tpo02.flashcardsapp.dto.QuizQuestionDTO;
import pl.edu.pja.tpo02.flashcardsapp.dto.QuizResultDTO;
import pl.edu.pja.tpo02.flashcardsapp.exception.EntryNotFoundException;
import pl.edu.pja.tpo02.flashcardsapp.model.Entry;
import pl.edu.pja.tpo02.flashcardsapp.repository.EntryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuizService {

    private final EntryRepository entryRepository;
    private final Random random = new Random();

    public QuizService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }


    public QuizQuestionDTO generateRandomQuiz() {
        List<Entry> allEntries = (List<Entry>) entryRepository.findAll();
        if (allEntries.isEmpty()) {
            return null;
        }

        Entry randomEntry = allEntries.get(random.nextInt(allEntries.size()));


        String[] langs = {"polish", "english", "german"};
        String sourceLang = langs[random.nextInt(langs.length)];


        String sourceWord;
        switch (sourceLang) {
            case "english" -> sourceWord = randomEntry.getEnglish();
            case "german" -> sourceWord = randomEntry.getGerman();
            default -> sourceWord = randomEntry.getPolish();
        }


        List<String> targetLangs = new ArrayList<>();
        for (String lang : langs) {
            if (!lang.equals(sourceLang)) {
                targetLangs.add(lang);
            }
        }


        return new QuizQuestionDTO(
                randomEntry.getId(),
                sourceLang,
                sourceWord,
                targetLangs
        );
    }


    public QuizResultDTO validateQuizAnswer(QuizAnswerDTO answerDto) throws EntryNotFoundException {

        Entry entry = entryRepository.findById(answerDto.getEntryId())
                .orElseThrow(EntryNotFoundException::new);


        boolean correctLang1 = false;
        boolean correctLang2 = false;
        String realLang1 = "";
        String realLang2 = "";

        switch (answerDto.getSourceLang()) {
            case "polish" -> {

                correctLang1 = entry.getEnglish().equalsIgnoreCase(answerDto.getAnswerLang1());
                correctLang2 = entry.getGerman().equalsIgnoreCase(answerDto.getAnswerLang2());
                realLang1 = entry.getEnglish();
                realLang2 = entry.getGerman();
            }
            case "english" -> {

                correctLang1 = entry.getPolish().equalsIgnoreCase(answerDto.getAnswerLang1());
                correctLang2 = entry.getGerman().equalsIgnoreCase(answerDto.getAnswerLang2());
                realLang1 = entry.getPolish();
                realLang2 = entry.getGerman();
            }
            case "german" -> {

                correctLang1 = entry.getPolish().equalsIgnoreCase(answerDto.getAnswerLang1());
                correctLang2 = entry.getEnglish().equalsIgnoreCase(answerDto.getAnswerLang2());
                realLang1 = entry.getPolish();
                realLang2 = entry.getEnglish();
            }
        }

        boolean allCorrect = correctLang1 && correctLang2;


        QuizResultDTO result = new QuizResultDTO();
        result.setCorrect(allCorrect);

        result.setCorrectLang1(realLang1);
        result.setCorrectLang2(realLang2);

        return result;
    }
}