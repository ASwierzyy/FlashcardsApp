package pl.edu.pja.tpo02.flashcardsapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.tpo02.flashcardsapp.displayStrategy.DisplayWords;
import pl.edu.pja.tpo02.flashcardsapp.dto.QuizAnswerDTO;
import pl.edu.pja.tpo02.flashcardsapp.dto.QuizQuestionDTO;
import pl.edu.pja.tpo02.flashcardsapp.dto.QuizResultDTO;
import pl.edu.pja.tpo02.flashcardsapp.exception.EntryNotFoundException;
import pl.edu.pja.tpo02.flashcardsapp.service.QuizService;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;
    private final DisplayWords displayWords;

    public QuizController(QuizService quizService, DisplayWords displayWords) {
        this.quizService = quizService;
        this.displayWords = displayWords;
    }

    
    @GetMapping("/new")
    public ResponseEntity<QuizQuestionDTO> getRandomQuizQuestion() {
        QuizQuestionDTO question = quizService.generateRandomQuiz();
        if (question == null) {
            return ResponseEntity.noContent().build();
        }

        
        String displayedSource = displayWords.display(question.getSourceWord());
        question.setSourceWord(displayedSource);

        
        

        return ResponseEntity.ok(question);
    }

    
    @PostMapping("/validate")
    public ResponseEntity<QuizResultDTO> validateQuizAnswer(@RequestBody QuizAnswerDTO answerDto) {
        try {
            QuizResultDTO result = quizService.validateQuizAnswer(answerDto);

            
            String transformedLang1 = displayWords.display(result.getCorrectLang1());
            String transformedLang2 = displayWords.display(result.getCorrectLang2());
            result.setCorrectLang1(transformedLang1);
            result.setCorrectLang2(transformedLang2);

            return ResponseEntity.ok(result);
        } catch (EntryNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}