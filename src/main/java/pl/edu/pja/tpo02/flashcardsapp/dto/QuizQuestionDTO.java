package pl.edu.pja.tpo02.flashcardsapp.dto;

import java.util.List;

public class QuizQuestionDTO {
    private Long entryId;
    private String sourceLang;      
    private String sourceWord;      
    private List<String> targetLangs; 

    public QuizQuestionDTO() {}

    public QuizQuestionDTO(Long entryId, String sourceLang, String sourceWord, List<String> targetLangs) {
        this.entryId = entryId;
        this.sourceLang = sourceLang;
        this.sourceWord = sourceWord;
        this.targetLangs = targetLangs;
    }

    public Long getEntryId() {
        return entryId;
    }
    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public String getSourceLang() {
        return sourceLang;
    }
    public void setSourceLang(String sourceLang) {
        this.sourceLang = sourceLang;
    }

    public String getSourceWord() {
        return sourceWord;
    }
    public void setSourceWord(String sourceWord) {
        this.sourceWord = sourceWord;
    }

    public List<String> getTargetLangs() {
        return targetLangs;
    }
    public void setTargetLangs(List<String> targetLangs) {
        this.targetLangs = targetLangs;
    }
}