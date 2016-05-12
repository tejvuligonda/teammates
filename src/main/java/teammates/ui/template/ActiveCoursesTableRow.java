package teammates.ui.template;

import java.util.List;

public class ActiveCoursesTableRow {
    private String courseId;
    private String courseName;
    private String href;
    private List<ElementTag> actions;
    
    public ActiveCoursesTableRow(final String courseIdParam, final String courseNameParam, 
        final String href, final List<ElementTag> actionsParam) {
        this.courseId = courseIdParam;
        this.courseName = courseNameParam;
        this.href = href;
        this.actions = actionsParam;
    }
    
    public String getCourseId() {
        return courseId;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public String getHref() {
        return href;
    }
    
    public List<ElementTag> getActions() {
        return actions;
    }
    
}
