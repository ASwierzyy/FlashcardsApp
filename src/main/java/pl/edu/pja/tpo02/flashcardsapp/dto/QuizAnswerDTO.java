package pl.edu.pja.tpo02.flashcardsapp.dto;

public class QuizAnswerDTO {
    private Long entryId;
    private String sourceLang;           

    
    private String answerLang1;          
    private String answerLang2;          

    public QuizAnswerDTO() {}

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

    public String getAnswerLang1() {
        return answerLang1;
    }
    public void setAnswerLang1(String answerLang1) {
        this.answerLang1 = answerLang1;
    }

    public String getAnswerLang2() {
        return answerLang2;
    }
    public void setAnswerLang2(String answerLang2) {
        this.answerLang2 = answerLang2;
    }
}