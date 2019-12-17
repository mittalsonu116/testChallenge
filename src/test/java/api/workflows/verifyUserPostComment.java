package api.workflows;

import core.api.Master;
import static core.api.modues.Comment.*;
import static core.api.modues.Post.*;
import static core.api.modues.User.*;
import static core.utils.CommonUtils.*;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class verifyUserPostComment extends Master {

    private String user_id;
    private Set<String> post_ids;
    private Map<String,String> comment_data;
    private List<String> email_format;

    /**
     * Using default constructor to parse test script xml file test data into hash-map reference
     */
    public verifyUserPostComment(){
        super();
        testdata=getTestDataFromXML(this.getClass().getSimpleName());
    }

    @Test(testName = "verificationOfPostComment", description = "verify post comment of a user named as 'Samantha'")
    public void postCommentVerification() throws Exception {

        /**
         * Get user id corresponding to user name
         */
        user_id=getUserID(testdata.get("UserName"));
        System.out.println(user_id);

        /**
         * Get all post id corresponding to user id
         */
        post_ids=getPostIDs(user_id);
        System.out.println(post_ids);

        /**
         * Get all comment id and email address corresponding to post id
         */
        comment_data=getCommentIDAndEmail(post_ids);
        System.out.println(comment_data);

        /**
         * Validate correctness of email format
         */
        email_format=checkEmailFormat(comment_data);
        printInvalidEmail(email_format);
    }
}
