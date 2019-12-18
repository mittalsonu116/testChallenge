package api.workflows;

import core.api.Master;
import core.utils.response_POJO.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static core.api.modues.Comment.*;
import static core.api.modues.Post.*;
import static core.api.modues.User.*;

public class VerificationJSONResponseUsingPOJO extends Master {

    /**
     * Apply validations on post's attributes
     */
    @Test(testName = "ValidationThroughPostPOJO",description = "verify post's attributes by using POJO classes", priority = 1,enabled = true)
    public void validationOnPostResponse(){
        GetPostsPOJO[] posts = getPostResponse();
        Assert.assertTrue(checkPostIDUniqueness(posts),"Posts id attribute is not unique");
        Assert.assertTrue(compareUserIDInPostResponse(posts,getUserIDList()),"User id mismatch with post response");
/*        for (GetPostsPOJO post:posts)
            System.out.println(post.getId()+" : "+post.getTitle()+" : "+post.getUserId()+" : "+post.getBody());*/
    }

    /**
     * Apply validations on comment's attributes
     */
    @Test(testName = "ValidationThroughCommentPOJO",description = "verify comment's attributes by using POJO classes", priority = 2,enabled = true)
    public void validationOnCommentResponse(){
        GetCommentsPOJO[] comments = getCommentResponse();
        Assert.assertTrue(checkCommentIDUniqueness(comments),"Comments id attribute is not unique");
        Assert.assertTrue(comparePostIDInCommentResponse(comments,getPostIDList()),"User id mismatch with post response");
        /*for (GetCommentsPOJO comment:comments)
            System.out.println(comment.getId()+" : "+comment.getPostId()+" : "+comment.getEmail()+" : "+comment.getName()+" : "+comment.getBody());*/
    }

    /**
     * Apply validations on user's attributes
     */
    @Test(testName = "ValidationThroughUserPOJO",description = "verify user's attributes by using POJO classes", priority = 3,enabled = false)
    public void validationOnUserResponse()throws Exception {
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
