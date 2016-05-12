package teammates.ui.template;

public class FeedbackResultsQuestionDetails {
    private String questionIndex;
    private String questionText;
    private String additionalInfo;
    private String questionResultStatistics;
    private boolean isIndividualResponsesShownToStudents;
    
    public FeedbackResultsQuestionDetails(final String questionIndex, final String questionText, final String additionalInfo,
                                          final String questionResultStatistics,
                                          final boolean isIndividualResponsesShownToStudents) {        
        this.questionIndex = questionIndex;
        this.questionText = questionText;
        this.additionalInfo = additionalInfo;
        this.questionResultStatistics = questionResultStatistics;
        this.isIndividualResponsesShownToStudents = isIndividualResponsesShownToStudents;
    }

    public String getQuestionIndex() {
        return questionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
   
    public String getQuestionResultStatistics() {
        return questionResultStatistics;
    }
    
    public boolean isIndividualResponsesShownToStudents() {
        return isIndividualResponsesShownToStudents;
    }
    
    
}
