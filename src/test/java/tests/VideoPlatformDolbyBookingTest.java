package tests;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import pageObjects.VideoPlatformDolbyBookingPage;
import org.testng.annotations.Listeners;

@Listeners(listeners.Listeners.class)
public class VideoPlatformDolbyBookingTest {
    WebDriver driver;
    VideoPlatformDolbyBookingPage page;
    ExtentReports extent;
    ExtentTest test;

    // Test inputs
    String userName = "admin@1st.com";
    String password = "admin";
    String orgName = "Test_Org";
    String unitName = "Unit-01";
    String randomDisplayName = UUID.randomUUID().toString();
    String encoderName = "EncoderTest-" + randomDisplayName.substring(0, 4);
    String encoderID = "TestID" + randomDisplayName.substring(0, 4);
    String EncoderSearchKeyword = encoderName;
    String decoderDisplayName = "DecoderTest-" + randomDisplayName.substring(0, 4);
    String decoderID = decoderDisplayName;
    String updatedEncoder = "Update" + encoderName;
    String updatedName = "Update" + decoderDisplayName;
    String updatedID = "Update" + decoderDisplayName;
    String dolbyDecoderName = decoderDisplayName;
    String noneDecoderName = decoderDisplayName;
    String decoderSearchKeyword = decoderDisplayName;
    String sourceName = "SourceTest" + randomDisplayName.substring(0, 4);
    String sourceSearchKeyword = sourceName;
    String updatedSource = "UpdatedSourceTest-" + randomDisplayName.substring(0, 4);
    String channelName = "ChannelTest" + randomDisplayName.substring(0, 4);
    String ChannelSearchKeyword = channelName;
    String VendorName = "Dolby";
    String Description = "Test23";
    String ClusterName = "Ashburn";
    String ConnectorID = "99";
    String Angle = "Backside";
    String sourceAngle = "Backside";

    @BeforeClass
    public void setUp() throws InterruptedException {
       
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chrome\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://prism-web.preprod.1stbet.com/");
        driver.manage().window().maximize();
        page = new VideoPlatformDolbyBookingPage(driver);
        page.login(userName, password);
        page.navigateToVideoPlatform(orgName, unitName);
        
    }

    @Test(priority = 1)
    public void testEncoder() throws InterruptedException {
//        test = extent.createTest("Test Encoder Creation and Update");
        page.Select_Encoder();
        page.createEncoder(encoderName, encoderID);
        Assert.assertEquals(page.getMessage(), "Video Encoder Successfully Added");

        page.verifyToolTip("Add Video Encoder", VideoPlatformDolbyBookingPage.TOOLTIP_ADD_ENCODER);
        page.verifyToolTip("Export", VideoPlatformDolbyBookingPage.TOOLTIP_EXPORT);
        page.verifyToolTip("Column Settings", VideoPlatformDolbyBookingPage.TOOLTIP_COLUMN_SETTINGS);

        page.displayInactive();
        page.search(EncoderSearchKeyword);

        page.updateEncoder(updatedEncoder, updatedID);
        Assert.assertEquals(page.getMessage(), "Video Encoder Successfully Updated");
    }

    @Test(priority = 2)
    public void testDecoder() throws InterruptedException {
//        test = extent.createTest("Test Decoder Creation and Update");
        page.Select_Decoder();
        page.verifyToolTip("Add Video Decoder", VideoPlatformDolbyBookingPage.TOOLTIP_ADD_DECODER);
        page.verifyToolTip("Export", VideoPlatformDolbyBookingPage.TOOLTIP_EXPORT);
        page.verifyToolTip("Column Settings", VideoPlatformDolbyBookingPage.TOOLTIP_COLUMN_SETTINGS);

        page.addDolbyDecoder(dolbyDecoderName, decoderID);
        Assert.assertEquals(page.getMessage(), "Video Decoder Successfully Added");

        page.displayInactive();
        page.search(decoderSearchKeyword);

        page.disableEntity();
        Assert.assertEquals(page.getMessage(), "Video Decoder Successfully Disabled");

        page.updateDecoder(updatedName, updatedID);
        Assert.assertEquals(page.getMessage(), "Video Decoder Successfully Updated");

        page.deleteEntity();
        Assert.assertEquals(page.getMessage(), "Video Decoder Successfully Deleted");
    }

    @Test(priority = 3)
    public void testSource() throws InterruptedException {
//        test = extent.createTest("Test Source Creation and Update");
        page.Select_Source();
        page.createSource(sourceName, sourceAngle, updatedEncoder);
        Assert.assertEquals(page.getMessage(), "Video Source Successfully Added");

        page.verifyToolTip("Add Video Source", VideoPlatformDolbyBookingPage.TOOLTIP_ADD_SOURCE);
        page.verifyToolTip("Export", VideoPlatformDolbyBookingPage.TOOLTIP_EXPORT);
        page.verifyToolTip("Column Settings", VideoPlatformDolbyBookingPage.TOOLTIP_COLUMN_SETTINGS);

        page.displayInactive();
        page.search(sourceSearchKeyword);

        page.updateSource(updatedSource);
        // Assert.assertEquals(page.getMessage(), "Video Source Successfully Updated");

        // page.disableEntity();
        // Assert.assertEquals(page.getMessage(), "Video Source Successfully Disabled");
    }

    @Test(priority = 4)
    public void testChannel() throws InterruptedException {
//        test = extent.createTest("Test Channel Creation and Update");
        page.Select_Channel();
        page.CreateDolby_Channel(channelName, VendorName, Angle, ConnectorID, ClusterName, Description);
        Assert.assertEquals(page.getMessage(), "Video Channel Successfully Added");

        page.verifyToolTip("Add Video Channel", VideoPlatformDolbyBookingPage.TOOLTIP_ADD_CHANNEL);
        page.verifyToolTip("Export", VideoPlatformDolbyBookingPage.TOOLTIP_EXPORT);
        page.verifyToolTip("Column Settings", VideoPlatformDolbyBookingPage.TOOLTIP_COLUMN_SETTINGS);

        page.displayInactive();
        page.search(ChannelSearchKeyword);

        // page.disableEntity();
        // Assert.assertEquals(page.getMessage(), "Video Channel Successfully Disabled");

        page.updateDolbyChannel();
        Assert.assertEquals(page.getMessage(), "Video Channel Successfully Updated");

        // page.deleteEntity();
        // Assert.assertEquals(page.getMessage(), "Video Channel Successfully Deleted");
    }

    @Test(priority = 5)
    public void testBooking() throws InterruptedException {
//        test = extent.createTest("Test Booking Creation and Update");
        page.Select_Booking();
        System.out.println("Source name is" + sourceName);
        String sourceName1 = updatedSource + "_" + sourceAngle;
        System.out.println("Channel name is" + channelName);
        String channelName1 = channelName + " (Dolby)";
        // String channelName1=channelName+" (VendorName)";
        System.out.println("Channel name is" + channelName);
        page.createBooking(sourceName1, channelName1);
        Assert.assertEquals(page.getMessage(), "Video Booking Successfully Added");

        page.disableEntity();
        Assert.assertEquals(page.getMessage(), "Video Booking Successfully Disabled");

        page.deleteEntity();
        Assert.assertEquals(page.getMessage(), "Video Booking Successfully Deleted");
    }



    @AfterClass
    public void tearDown() throws InterruptedException {
        // Close the browser and end the session
        driver.quit();

        // Write all test information to the report
//        extent.flush();
    }
}
