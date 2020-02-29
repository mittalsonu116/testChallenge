package api.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Test1 {
    public static WebDriver driver;
    public static void main(String[] args) {
        Map<String, Integer> map=new HashMap<>();
        System.setProperty("webdriver.chrome.driver","E:\\TestChallenge\\Drivers\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://nssdc.gsfc.nasa.gov/planetary/factsheet/");
        driver.manage().window().maximize();

        for(int i=1;i<=10;i++){
           WebElement ele1=  driver.findElement(By.xpath("(//table[@role='presentation']//tr[last()]/td[@align='center'])["+i+"]"));
            WebElement ele2= driver.findElement(By.xpath("(//a[text()='Number of Moons']/ancestor::td/following-sibling::td)["+i+"]"));
            map.put(ele1.getText(),Integer.parseInt(ele2.getText()));
        }

        map.entrySet().stream().filter(e->e.getValue()>0).forEach(System.out::println);
        driver.quit();
    }
}
