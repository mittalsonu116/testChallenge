package core.api.modues;

import core.api.EndPoints;
import core.api.Master;
import core.utils.ReusableMethods;
import core.utils.response_POJO.GetCommentsPOJO;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Comment extends Master {

    /**
     * Function to fetch user's id and email address from user json list
     * @param postids
     * @return Map<String,String>
     */
    public static Map<String,String> getCommentIDAndEmail(Set<String> postids){
        Map<String,String> comment_ids=new LinkedHashMap<>();
        Response resp=getCommentApiResponse();
        List<String> respList=resp.jsonPath().getList("$");
        for (String pid:postids) {
            for (int i = 0; i < respList.size(); i++) {
                String id = resp.jsonPath().getString("postId[" + i + "]");
                if(pid.equals(id)){
                    comment_ids.put(resp.jsonPath().getString("id["+i+"]"),resp.jsonPath().getString("email["+i+"]"));
                }
            }
        }
        return comment_ids;
    }

    /**
     * Function to validate email address format
     * @param commentdata
     * @return List<String>
     */
    public static List<String> checkEmailFormat(Map<String,String> commentdata){
        List<String> emailFormat=new LinkedList<>();
        List<String> emails=commentdata.values().stream().collect(Collectors.toList());

        //Pattern p=Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-z]+)+");
        Pattern p=Pattern.compile("^[a-zA-Z._]+@[a-z]+([.][a-zA-Z]+)+$");
        for (String email:emails){
            Matcher m = p.matcher(email);
            if(m.find()){
                //emailFormat.put(entry.getValue(),": email format valid");
            } else {
                emailFormat.add(email);
            }
        }
        return emailFormat;
    }

    /**
     * Function to check total number of comment available on server
     * @param commentCount
     * @return boolean
     */
    public static boolean checkCommentCount(String commentCount){
        boolean flag=false;
        List<String> respList=getCommentApiResponse().jsonPath().getList("$");
        if(respList.size() == Integer.parseInt(commentCount)){
            flag=true;
        }
        return flag;
    }

    /**
     * Function to get comment api response
     * @return Response
     */
    public static Response getCommentApiResponse(){
        Response resp=new ReusableMethods(portalSpec).get(EndPoints.COMMENT);
        resp.then()
                .assertThat().statusCode(HttpStatus.SC_OK);
        return resp;
    }

    /**
     * Function to fetch response through POJO classes
     */
    public static GetCommentsPOJO[] getCommentResponse(){
        return new ReusableMethods(portalSpec).getByPOJO(EndPoints.COMMENT)
                .as(GetCommentsPOJO[].class);
    }

    /**
     * Function to check uniqueness of comment id
     */
    public static boolean checkCommentIDUniqueness(GetCommentsPOJO[] comments){
        boolean flag=false;
        List<String> commentId= new ArrayList();
        for (GetCommentsPOJO comment:comments)
            commentId.add(comment.getId());
        Set<String> idSet=new HashSet<>(commentId);
        if(commentId.size()==idSet.size())
            flag=true;
        return flag;
    }

    /**
     *
     */
    public static boolean comparePostIDInCommentResponse(GetCommentsPOJO[] comments,List<String> postID){
        boolean flag=false;
        Set<String> postId=new HashSet<>();
        for (GetCommentsPOJO comment:comments)
            postId.add(comment.getPostId());
        List<String> postIdList=new ArrayList<>(postId);
        if(postIdList.equals(postID));
        flag=true;
        return flag;
    }
}
