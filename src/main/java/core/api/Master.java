package core.api;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import core.utils.PropertiesFileController;
import core.utils.Reporter.ExtentReporter;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.config.RestAssuredConfig.config;

public class Master {

    //Util data
    protected HashMap<String,String> testdata=null;
    protected static String curDir;
    protected static Map<String,String> configData;
    protected static RequestSpecification portalSpec;

    //Extent Test
    protected static ExtentReports extentReports;
    protected ExtentTest extentTest;

    /**
     * Set up data required before execution of suite
     */
    @BeforeSuite
    public void suiteSetup() throws FileNotFoundException {
        ExtentReporter.deleteExtentReport();
        curDir = System.getProperty("user.dir");
        configData = new PropertiesFileController().readConfigFileData();
        portalSpec = new RequestSpecBuilder().setBaseUri(configData.get("PORTAL_BASE_HOST")).build();
        extentReports= ExtentReporter.initReporter();
        RestAssured.config = config().logConfig(new LogConfig().defaultStream(new PrintStream(new File("RestAssuredLogs.log"))));
    }

    /**
     * Set up data required after execution of suite
     */
    @AfterSuite
    public void tearDownSuite(){
        ExtentReporter.cleanExtentReport();
    }

    /**
     * Function to parse test data of a particular test script from XML file to hash-map
     * @param fileName
     * @return
     */
    public HashMap<String,String> getTestDataFromXML(String fileName){
        HashMap<String,String> hMap=new HashMap<>();
        String testDataKey=null;
        String testDataValue=null;
        File f=new File(System.getProperty("user.dir")+File.separator+"TestData"+File.separator+fileName+".xml");
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            NodeList elementNodeList=doc.getElementsByTagName("TestData");
            for(int i=0;i<elementNodeList.getLength();i++){
                Node elementNode=elementNodeList.item(i);
                NodeList paramNodeList=elementNode.getChildNodes();
                for(int j=0;j<paramNodeList.getLength();j++){
                    Element ele;
                    Node paramNode=paramNodeList.item(j);
                    if(paramNodeList.item(j).getNodeType()==Node.ELEMENT_NODE){
                        ele = (Element)paramNode;
                        testDataKey = ele.getAttribute("key");
                        testDataValue = ele.getAttribute("value");
                        hMap.put(testDataKey, testDataValue);
                    }
                }
            }
        } catch (ParserConfigurationException pce){
            pce.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return hMap;
    }
}
