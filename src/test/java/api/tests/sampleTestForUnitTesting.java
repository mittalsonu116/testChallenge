package api.tests;

import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static core.api.modues.Comment.checkEmailFormat;
import static core.utils.CommonUtils.printInvalidEmail;

/**
 * this class created for unit test purpose. please don't refer this.
 */

public class sampleTestForUnitTesting {
    Map<String,String> map=new LinkedHashMap<>();

    @Test
    public void test(){
        map.put("1","ejhsdsdfcds");
        map.put("2","sonu@gmail.com");

        printInvalidEmail(checkEmailFormat(map));
    }
}
