package api.tests;

import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        String numbers = "1,2,3,64:49,69";
        List<String> nums= Arrays.asList(numbers.split("[^0-9]"));
        System.out.println(nums);
    }
}
