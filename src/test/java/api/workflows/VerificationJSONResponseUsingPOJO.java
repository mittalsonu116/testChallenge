package api.workflows;

import com.relevantcodes.extentreports.LogStatus;
import core.api.Master;
import core.utils.response_POJO.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static core.api.modues.Comment.*;
import static core.api.modues.Post.*;
import static core.api.modues.User.*;

public class VerificationJSONResponseUsingPOJO extends Master {

    @BeforeTest(alwaysRun = true)
    public void beforeTest(){
        extentTest=extentReports.startTest(this.getClass().getSimpleName());
        extentTest.setDescription("Workflow to validate attribute property of user, post and comment");
    }

    @Test(testName = "ValidationOnPostAndCommentFields",description = "verify field property of user, post and comment")
    public void test() throws Exception {
        //validationOnUserResponse();
        validationOnPostResponse();
        validationOnCommentResponse();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(){
        extentReports.endTest(extentTest);
    }

    /**
     * Apply validations on post's attributes by using POJO classes
     */
    private void validationOnPostResponse(){
        GetPostsPOJO[] posts = getPostResponse();
        Assert.assertTrue(checkPostIDUniqueness(posts),"Posts id attribute is not unique");
        extentTest.log(LogStatus.INFO,"User's post ids are unique");
        Assert.assertTrue(compareUserIDInPostResponse(posts,getUserIDList()),"User id mismatch with post response");
        extentTest.log(LogStatus.INFO,"User id matched in user and post api response");
/*        for (GetPostsPOJO post:posts)
            System.out.println(post.getId()+" : "+post.getTitle()+" : "+post.getUserId()+" : "+post.getBody());*/
    }

    /**
     * Apply validations on comment's attributes by using POJO classes
     */
    private void validationOnCommentResponse(){
        GetCommentsPOJO[] comments = getCommentResponse();
        Assert.assertTrue(checkCommentIDUniqueness(comments),"Comments id attribute is not unique");
        extentTest.log(LogStatus.INFO,"Comment ids are unique, posted by user");
        Assert.assertTrue(comparePostIDInCommentResponse(comments,getPostIDList()),"User id mismatch with post response");
        extentTest.log(LogStatus.INFO,"Comment id matched in post and comment api response");
        /*for (GetCommentsPOJO comment:comments)
            System.out.println(comment.getId()+" : "+comment.getPostId()+" : "+comment.getEmail()+" : "+comment.getName()+" : "+comment.getBody());*/
    }

    /**
     * Apply validations on user's attributes by using POJO classes
     */
    private void validationOnUserResponse() {
        GetUsersPOJO[] users=getUserResponse();

        for(GetUsersPOJO u:users) {
            for (GetAddressPOJO user : u.getAddress()) {
                for (GetGeoPOJO geo : user.getGeo()) {
                    System.out.println(geo.getLat());
                }
            }
        }
    }
}
