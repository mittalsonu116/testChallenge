package core.api.modues;

import core.api.EndPoints;
import core.api.Master;
import core.utils.ReusableMethods;
import core.utils.response_POJO.GetPostsPOJO;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.*;

public class Post extends Master {

    /**
     * Function to fetch post id's corresponding to user id from user json list
     * @param userId
     * @return Set<String>
     */
    public static Set<String> getPostIDs(String userId){
        Set<String> post_id=new LinkedHashSet<>();
        Response resp=getPostApiResponse();
        List<String> respList=resp.jsonPath().getList("$");
        for(int i=0;i<respList.size();i++){
            String id = resp.jsonPath().getString("userId["+i+"]");
            if(id.equals(userId)){
                post_id.add(resp.jsonPath().getString("id["+i+"]"));
            }
        }
        return post_id;
    }

    /**
     * Function to check total number of post available on server
     * @param postCount
     * @return boolean
     */
    public static boolean checkPostCount(String postCount){
        boolean flag=false;
        List<String> respList=getPostApiResponse().jsonPath().getList("$");
        if(respList.size() == Integer.parseInt(postCount)){
            flag=true;
        }
        return flag;
    }

    /**
     * Function to get post api response
     * @return Response
     */
    public static Response getPostApiResponse(){
        Response resp=new ReusableMethods(portalSpec).get(EndPoints.POST);
        resp.then()
                .assertThat().statusCode(HttpStatus.SC_OK);
        return resp;
    }

    /**
     * Function to fetch response through POJO classes
     */
    public static GetPostsPOJO[] getPostResponse(){
        return new ReusableMethods(portalSpec).getByPOJO(EndPoints.POST)
                .as(GetPostsPOJO[].class);
    }

    /**
     * Function to check uniqueness of post id
     */
    public static boolean checkPostIDUniqueness(GetPostsPOJO[] posts){
        boolean flag=false;
        List<String> postId= new ArrayList();
        for (GetPostsPOJO post:posts)
            postId.add(post.getId());
        Set<String> idSet=new HashSet<>(postId);
        if(postId.size()==idSet.size())
            flag=true;
        return flag;
    }

    /**
     * Function to check user id in post response
     */
    public static boolean compareUserIDInPostResponse(GetPostsPOJO[] posts,List<String> userID){
        boolean flag=false;
        Set<String> userId=new HashSet<>();
        for (GetPostsPOJO post:posts)
            userId.add(post.getUserId());
        List<String> userIdList=new ArrayList<>(userId);
        if(userIdList.equals(userID));
            flag=true;
        return flag;
    }

    /**
     * Function to fetch all post id from post json list
     * @return List<String>
     */
    public static List<String> getPostIDList(){
        List<String> idList=new ArrayList<>();
        Response resp=getPostApiResponse();
        List<String> respList=resp.jsonPath().getList("$");
        for(int i=0;i<respList.size();i++){
            idList.add(resp.jsonPath().getString("id["+i+"]"));
        }
        return idList;
    }
}
