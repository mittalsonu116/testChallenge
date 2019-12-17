package core.api.modues;

import core.api.EndPoints;
import core.api.Master;
import core.utils.ReusableMethods;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.List;

public class Album extends Master {

    /**
     * Function to check total number of album available on server
     * @param albumCount
     * @return boolean
     */
    public static boolean checkAlbumCount(String albumCount){
        boolean flag=false;
        List<String> respList=getAlbumApiResponse().jsonPath().getList("$");
        if(respList.size() == Integer.parseInt(albumCount)){
            flag=true;
        }
        return flag;
    }

    /**
     * Function to get album api response
     * @return Response
     */
    public static Response getAlbumApiResponse(){
        Response resp=new ReusableMethods(portalSpec).get(EndPoints.ALBUM);
        resp.then()
                .assertThat().statusCode(HttpStatus.SC_OK);
        return resp;
    }
}
