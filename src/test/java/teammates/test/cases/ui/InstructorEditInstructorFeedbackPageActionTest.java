package teammates.test.cases.ui;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import teammates.common.datatransfer.DataBundle;
import teammates.common.datatransfer.InstructorAttributes;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.exception.UnauthorizedAccessException;
import teammates.common.util.Const;
import teammates.test.driver.AssertHelper;
import teammates.ui.controller.InstructorEditInstructorFeedbackPageAction;
import teammates.ui.controller.ShowPageResult;

public class InstructorEditInstructorFeedbackPageActionTest extends BaseActionTest {

    private static DataBundle dataBundle;
    
    @BeforeClass
    public static void classSetUp() throws Exception {
        printTestClassHeader();
        dataBundle = loadDataBundle("/InstructorEditInstructorFeedbackPageTest.json");
        removeAndRestoreDatastoreFromJson("/InstructorEditInstructorFeedbackPageTest.json");
        
        uri = Const.ActionURIs.INSTRUCTOR_EDIT_INSTRUCTOR_FEEDBACK_PAGE;
    }
    
    @Test
    public void testExecuteAndPostProcess() throws Exception {
        InstructorAttributes instructor = dataBundle.instructors.get("IEIFPTCourseinstr");
        InstructorAttributes moderatedInstructor = dataBundle.instructors.get("IEIFPTCoursehelper1");
        InstructorEditInstructorFeedbackPageAction editInstructorFPAction;
        ShowPageResult showPageResult;
        
        String courseId = moderatedInstructor.courseId;
        String feedbackSessionName = "";
        String moderatedInstructorEmail = "IEIFPTCoursehelper1@gmail.tmt";
        String[] submissionParams;

        gaeSimulation.loginAsInstructor(instructor.googleId);

        ______TS("typical success case");
        feedbackSessionName = "First feedback session";
        submissionParams = new String[]{
                Const.ParamsNames.COURSE_ID, courseId,
                Const.ParamsNames.FEEDBACK_SESSION_NAME, feedbackSessionName,
                Const.ParamsNames.FEEDBACK_SESSION_MODERATED_PERSON, moderatedInstructorEmail
        };
        
        editInstructorFPAction = getAction(submissionParams);
        showPageResult = (ShowPageResult) editInstructorFPAction.executeAndPostProcess();

        assertEquals(Const.ViewURIs.INSTRUCTOR_FEEDBACK_SUBMISSION_EDIT + "?error=false&user=" + instructor.googleId,
                     showPageResult.getDestinationWithParams());
        assertEquals("", showPageResult.getStatusMessage());
        AssertHelper.assertLogMessageEquals("TEAMMATESLOG|||instructorEditInstructorFeedbackPage|||instructorEditInstructorFeedbackPage" +
                "|||true|||Instructor|||IEIFPTCourseinstr|||IEIFPTCourseinstr|||IEIFPTCourseintr@course1.tmt|||" +
                "Moderating feedback session for instructor (" + moderatedInstructor.email + ")<br>" +
                "Session Name: First feedback session<br>Course ID: IEIFPTCourse|||" +
                "/page/instructorEditInstructorFeedbackPage",
                editInstructorFPAction.getLogMessage());
        
        ______TS("success: another feedback");
        feedbackSessionName = "Another feedback session";
        submissionParams = new String[]{
                Const.ParamsNames.COURSE_ID, courseId,
                Const.ParamsNames.FEEDBACK_SESSION_NAME, feedbackSessionName,
                Const.ParamsNames.FEEDBACK_SESSION_MODERATED_PERSON, moderatedInstructorEmail
        };
        
        editInstructorFPAction = getAction(submissionParams);
        showPageResult = (ShowPageResult) editInstructorFPAction.executeAndPostProcess();

        assertEquals(Const.ViewURIs.INSTRUCTOR_FEEDBACK_SUBMISSION_EDIT + "?error=false&user=" + instructor.googleId,
                     showPageResult.getDestinationWithParams());
        assertEquals("", showPageResult.getStatusMessage());
        AssertHelper.assertLogMessageEquals("TEAMMATESLOG|||instructorEditInstructorFeedbackPage|||instructorEditInstructorFeedbackPage" +
                "|||true|||Instructor|||IEIFPTCourseinstr|||IEIFPTCourseinstr|||IEIFPTCourseintr@course1.tmt|||" +
                "Moderating feedback session for instructor (" + moderatedInstructor.email + ")<br>" +
                "Session Name: Another feedback session<br>Course ID: IEIFPTCourse|||" +
                "/page/instructorEditInstructorFeedbackPage",
                editInstructorFPAction.getLogMessage());
        
        ______TS("failure: does not have privilege (helper can't moderate instructor)");
        gaeSimulation.loginAsInstructor(moderatedInstructor.googleId);
        feedbackSessionName = "First feedback session";
        submissionParams = new String[]{
                Const.ParamsNames.COURSE_ID, courseId,
                Const.ParamsNames.FEEDBACK_SESSION_NAME, feedbackSessionName,
                Const.ParamsNames.FEEDBACK_SESSION_MODERATED_PERSON, moderatedInstructorEmail
        };
        
        try {
            editInstructorFPAction = getAction(submissionParams);
            editInstructorFPAction.executeAndPostProcess();
            signalFailureToDetectException();
        } catch (UnauthorizedAccessException e) {
            assertEquals("Feedback session [First feedback session] is not accessible " +
                         "to instructor [" + moderatedInstructor.email + "] " +
                         "for privilege [canmodifysession]", e.getMessage());
        }

        ______TS("failure: accessing non-existent moderatedinstructor email");
        gaeSimulation.loginAsInstructor(instructor.googleId);
        moderatedInstructorEmail = "non-exIstentEmail@gsail.tmt";
        submissionParams = new String[]{
                Const.ParamsNames.COURSE_ID, courseId,
                Const.ParamsNames.FEEDBACK_SESSION_NAME, feedbackSessionName,
                Const.ParamsNames.FEEDBACK_SESSION_MODERATED_PERSON, moderatedInstructorEmail
        };
        
        try {
            editInstructorFPAction = getAction(submissionParams);
            editInstructorFPAction.executeAndPostProcess();
            signalFailureToDetectException();
        } catch (EntityDoesNotExistException edne) {
            assertEquals("Instructor Email " + moderatedInstructorEmail + 
                         " does not exist in " + courseId + ".", edne.getMessage());
        }
    }

    private InstructorEditInstructorFeedbackPageAction getAction(String... params) throws Exception {
        return (InstructorEditInstructorFeedbackPageAction) (gaeSimulation.getActionObject(uri, params));
    }
}
