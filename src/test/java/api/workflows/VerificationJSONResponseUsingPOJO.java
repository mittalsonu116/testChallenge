package api.workflows;

import core.api.Master;
import core.utils.response_POJO.*;
import org.testng.annotations.Test;

import static core.api.modues.Comment.getCommentResponse;
import static core.api.modues.Post.getPostResponse;
import static core.api.modues.User.getUserResponse;

public class VerificationJSONResponseUsingPOJO extends Master {

    @Test(testName = "ValidationThroughPostPOJO",description = "verify post's attributes by using POJO classes", priority = 1,enabled = false)
    public void validationOnPostResponse(){
        GetPostsPOJO[] posts = getPostResponse();
        for (GetPostsPOJO post:posts)
            System.out.println(post.getId()+" : "+post.getTitle()+" : "+post.getUserId()+" : "+post.getBody());
    }

    /**
     * Apply validations on user's attributes
     */
    @Test(testName = "ValidationThroughUserPOJO",description = "verify user's attributes by using POJO classes", priority = 0,enabled = true)
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

    @Test(testName = "ValidationThroughCommentPOJO",description = "verify comment's attributes by using POJO classes", priority = 2,enabled = false)
    public void validationOnCommentResponse(){
        GetCommentsPOJO[] comments = getCommentResponse();
        for (GetCommentsPOJO comment:comments)
            System.out.println(comment.getId()+" : "+comment.getPostId()+" : "+comment.getEmail()+" : "+comment.getName()+" : "+comment.getBody());
    }
}
