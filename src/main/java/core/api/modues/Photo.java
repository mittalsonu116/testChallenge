package core.api.modues;

import core.api.EndPoints;
import core.api.Master;
import core.utils.ReusableMethods;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.List;

public class Photo extends Master {

    /**
     * Function to check total number of photo available on server
     * @param photoCount
     * @return boolean
     */
    public static boolean checkPhotoCount(String photoCount){
        boolean flag=false;
        List<String> respList=getPhotoApiResponse().jsonPath().getList("$");
        if(respList.size() == Integer.parseInt(photoCount)){
            flag=true;
        }
        return flag;
    }

    /**
     * Function to get photo api response
     * @return Response
     */
    public static Response getPhotoApiResponse(){
        Response resp=new ReusableMethods(portalSpec).get(EndPoints.PHOTO);
        resp.then()
                .assertThat().statusCode(HttpStatus.SC_OK);
        return resp;
    }
}
