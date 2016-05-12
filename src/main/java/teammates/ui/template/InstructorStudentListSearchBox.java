package teammates.ui.template;

import teammates.common.util.Sanitizer;

public class InstructorStudentListSearchBox {

    private String instructorSearchLink;
    private String searchKey;
    private String googleId;

    public InstructorStudentListSearchBox(final String instructorSearchLink, final String searchKey, final String googleId) {
        this.instructorSearchLink = instructorSearchLink;
        this.searchKey = searchKey == null ? "" : Sanitizer.sanitizeForHtml(searchKey);
        this.googleId = googleId;
    }

    public String getInstructorSearchLink() {
        return instructorSearchLink;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public String getGoogleId() {
        return googleId;
    }

}
