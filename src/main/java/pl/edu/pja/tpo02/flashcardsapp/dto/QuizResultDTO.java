package pl.edu.pja.tpo02.flashcardsapp.dto;

public class QuizResultDTO {
    private boolean correct;
    private String correctLang1;
    private String correctLang2;

    public QuizResultDTO() {}

    public boolean isCorrect() {
        return correct;
    }
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getCorrectLang1() {
        return correctLang1;
    }
    public void setCorrectLang1(String correctLang1) {
        this.correctLang1 = correctLang1;
    }

    public String getCorrectLang2() {
        return correctLang2;
    }
    public void setCorrectLang2(String correctLang2) {
        this.correctLang2 = correctLang2;
    }
}