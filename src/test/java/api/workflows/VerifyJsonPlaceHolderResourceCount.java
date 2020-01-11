package api.workflows;

import com.relevantcodes.extentreports.LogStatus;
import core.api.Master;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static core.api.modues.Album.checkAlbumCount;
import static core.api.modues.Comment.checkCommentCount;
import static core.api.modues.Photo.checkPhotoCount;
import static core.api.modues.Post.checkPostCount;
import static core.api.modues.Todo.checkTodoCount;
import static core.api.modues.User.checkUserCount;

public class VerifyJsonPlaceHolderResourceCount extends Master {

    /**
     * Using default constructor to parse test script xml file test data into hash-map reference
     */
    public VerifyJsonPlaceHolderResourceCount(){
        super();
        testdata=getTestDataFromXML(this.getClass().getSimpleName());
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest(){
        extentTest=extentReports.startTest(this.getClass().getSimpleName());
        extentTest.setDescription("Workflow to test various resources count like user count, post count, comment count etc.");
    }

    @Test(testName = "verificationOfResourceCount", description = "verify count of json place holder resources like users, posts, comments, albums, photos and todos")
    public void resourceCountVerification() throws Exception {

        /**
         * Check user count on json place holder at server
         */
        Assert.assertTrue(checkUserCount(testdata.get("UserCount")),"user count mismatched");
        extentTest.log(LogStatus.INFO,"User Count matched");

        /**
         * Check post count on json place holder at server
         */
        Assert.assertTrue(checkPostCount(testdata.get("PostCount")),"post count mismatched");
        extentTest.log(LogStatus.INFO,"User's post Count matched");

        /**
         * Check comment count on json place holder at server
         */
        Assert.assertTrue(checkCommentCount(testdata.get("CommentCount")),"comment count mismatched");
        extentTest.log(LogStatus.INFO,"User's comment Count matched");

        /**
         * Check album count on json place holder at server
         */
        Assert.assertTrue(checkAlbumCount(testdata.get("AlbumCount")),"album count mismatched");
        extentTest.log(LogStatus.INFO,"Album Count matched");

        /**
         * Check photo count on json place holder at server
         */
        Assert.assertTrue(checkPhotoCount(testdata.get("PhotoCount")),"photo count mismatched");
        extentTest.log(LogStatus.INFO,"Album's photo Count matched");

        /**
         * Check to-do count on json place holder at server
         */
        Assert.assertTrue(checkTodoCount(testdata.get("TodoCount")),"todo count mismatched");
        extentTest.log(LogStatus.INFO,"To Do Count matched");
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(){
        extentReports.endTest(extentTest);
    }
}
