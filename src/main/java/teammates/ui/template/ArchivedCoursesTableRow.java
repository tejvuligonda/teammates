package teammates.ui.template;

import java.util.List;

public class ArchivedCoursesTableRow {
    private String courseId;
    private String courseName;
    private List<ElementTag> actions;
    
    public ArchivedCoursesTableRow(final String courseIdParam, final String courseNameParam, final List<ElementTag> actionsParam) {
        this.courseId = courseIdParam;
        this.courseName = courseNameParam;
        this.actions = actionsParam;
    }
    
    public String getCourseId() {
        return courseId;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public List<ElementTag> getActions() {
        return actions;
    }
}
