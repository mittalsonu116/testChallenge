package core.api.modues;

import core.api.EndPoints;
import core.api.Master;
import core.utils.ReusableMethods;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.List;

public class Todo extends Master {

    /**
     * Function to check total number of to-do available on server
     * @param todoCount
     * @return boolean
     */
    public static boolean checkTodoCount(String todoCount){
        boolean flag=false;
        List<String> respList=getTodoApiResponse().jsonPath().getList("$");
        if(respList.size() == Integer.parseInt(todoCount)){
            flag=true;
        }
        return flag;
    }

    /**
     * Function to get to-do api response
     * @return Response
     */
    public static Response getTodoApiResponse(){
        Response resp=new ReusableMethods(portalSpec).get(EndPoints.TODO);
        resp.then()
                .assertThat().statusCode(HttpStatus.SC_OK);
        return resp;
    }
}
