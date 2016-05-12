package teammates.ui.controller;

import java.util.ArrayList;
import java.util.List;

import teammates.common.datatransfer.AccountAttributes;
import teammates.common.datatransfer.CourseAttributes;
import teammates.ui.datatransfer.InstructorStudentListPageCourseData;
import teammates.ui.template.InstructorStudentListFilterBox;
import teammates.ui.template.InstructorStudentListFilterCourse;
import teammates.ui.template.InstructorStudentListSearchBox;
import teammates.ui.template.InstructorStudentListStudentsTableCourse;

public class InstructorStudentListPageData extends PageData {

    private InstructorStudentListSearchBox searchBox;
    private InstructorStudentListFilterBox filterBox;
    private List<InstructorStudentListStudentsTableCourse> studentsTable;
    private int numOfCourses;
    
    public InstructorStudentListPageData(final AccountAttributes account, final String searchKey,
                                         final boolean displayArchive,
                                         final List<InstructorStudentListPageCourseData> coursesToDisplay) {
        super(account);
        this.searchBox = new InstructorStudentListSearchBox(getInstructorSearchLink(), searchKey, account.googleId);
        List<InstructorStudentListFilterCourse> coursesForFilter =
                                        new ArrayList<InstructorStudentListFilterCourse>();
        List<InstructorStudentListStudentsTableCourse> coursesForStudentsTable =
                                        new ArrayList<InstructorStudentListStudentsTableCourse>();
        for (InstructorStudentListPageCourseData islpcData : coursesToDisplay) {
            CourseAttributes course = islpcData.course;
            coursesForFilter.add(new InstructorStudentListFilterCourse(course.id, course.name));
            coursesForStudentsTable.add(
                                            new InstructorStudentListStudentsTableCourse(islpcData.isCourseArchived,
                                                                            course.id, course.name, account.googleId,
                                                                            getInstructorCourseEnrollLink(course.id),
                                                                            islpcData.isInstructorAllowedToModify));
        }
        this.filterBox = new InstructorStudentListFilterBox(coursesForFilter, displayArchive);
        this.studentsTable = coursesForStudentsTable;
        this.numOfCourses = coursesForFilter.size();
    }
    
    public InstructorStudentListSearchBox getSearchBox() {
        return searchBox;
    }

    public InstructorStudentListFilterBox getFilterBox() {
        return filterBox;
    }
    
    public List<InstructorStudentListStudentsTableCourse> getStudentsTable() {
        return studentsTable;
    }

    public int getNumOfCourses() {
        return numOfCourses;
    }

}
