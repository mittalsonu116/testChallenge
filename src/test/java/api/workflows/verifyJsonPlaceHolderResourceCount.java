package api.workflows;

import core.api.Master;
import org.testng.Assert;
import org.testng.annotations.Test;

import static core.api.modues.Album.checkAlbumCount;
import static core.api.modues.Comment.checkCommentCount;
import static core.api.modues.Photo.checkPhotoCount;
import static core.api.modues.Post.checkPostCount;
import static core.api.modues.Todo.checkTodoCount;
import static core.api.modues.User.checkUserCount;

public class verifyJsonPlaceHolderResourceCount extends Master {

    /**
     * Using default constructor to parse test script xml file test data into hash-map reference
     */
    public verifyJsonPlaceHolderResourceCount(){
        super();
        testdata=getTestDataFromXML(this.getClass().getSimpleName());
    }

    @Test(testName = "verificationOfResourceCount", description = "verify count of json place holder resources like users, posts, comments, albums, photos and todos")
    public void resourceCountVerification() throws Exception {

        /**
         * Check user count on json place holder at server
         */
        Assert.assertTrue(checkUserCount(testdata.get("UserCount")),"user count mismatched");

        /**
         * Check post count on json place holder at server
         */
        Assert.assertTrue(checkPostCount(testdata.get("PostCount")),"post count mismatched");

        /**
         * Check comment count on json place holder at server
         */
        Assert.assertTrue(checkCommentCount(testdata.get("CommentCount")),"comment count mismatched");

        /**
         * Check album count on json place holder at server
         */
        Assert.assertTrue(checkAlbumCount(testdata.get("AlbumCount")),"album count mismatched");

        /**
         * Check photo count on json place holder at server
         */
        Assert.assertTrue(checkPhotoCount(testdata.get("PhotoCount")),"photo count mismatched");

        /**
         * Check to-do count on json place holder at server
         */
        Assert.assertTrue(checkTodoCount(testdata.get("TodoCount")),"todo count mismatched");
    }
}
