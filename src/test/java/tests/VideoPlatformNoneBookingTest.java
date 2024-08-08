package tests;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.VideoPlatformDolbyBookingPage;

public class VideoPlatformNoneBookingTest {
    WebDriver driver;
    VideoPlatformDolbyBookingPage page;

    // Test inputs
    String userName = "admin@1st.com";
    String password = "admin";
    String orgName = "Test_Org";
    String unitName = "Unit-01";
    String randomDisplayName = UUID.randomUUID().toString();
    String encoderName = "EncoderTest-" + randomDisplayName.substring(0, 4);
    String encoderID = "TestID" + randomDisplayName.substring(0, 4);
    String EncoderSearchKeyword = encoderName;
    String decoderName = "DecoderTest-" + randomDisplayName.substring(0, 4);
    String decoderID = decoderName;
    String updatedDecoder = "Update" + decoderName;
    String updatedEncoder="Update" +encoderName;
    String updatedID = "Update" + decoderName;
    String dolbyDecoderName = decoderName;
    String noneDecoderName = decoderName;
    String decoderSearchKeyword = decoderName;
    String sourceName = "SourceTest" + randomDisplayName.substring(0, 4);
    String sourceSearchKeyword = sourceName;
    String updatedSource="UpdatedSourceTest-" +randomDisplayName.substring(0, 4);
    String channelName = "ChannelTest" + randomDisplayName.substring(0, 4);
    String ChannelSearchKeyword = channelName;
    String VendorName = "Dolby";
    String VendorNoneName="None";
    String Description = "Test23";
    String ClusterName = "Ashburn";
    String ConnectorID = "99";
    String Angle = "Backside";
    String sourceAngle="Backside";
    String ForeignIDValue="123";
    String ForeignIDType="CHRIMS Event Code";

    @BeforeClass
    public void setUp() throws InterruptedException {
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
        page.Select_Encoder();
        page.createEncoder(encoderName, encoderID);
        Assert.assertEquals(page.getMessage(), "Video Encoder Successfully Added");

        page.verifyToolTip("Add Video Encoder", VideoPlatformDolbyBookingPage.TOOLTIP_ADD_ENCODER);
        page.verifyToolTip("Export", VideoPlatformDolbyBookingPage.TOOLTIP_EXPORT);
        page.verifyToolTip("Column Settings", VideoPlatformDolbyBookingPage.TOOLTIP_COLUMN_SETTINGS);

        page.displayInactive();
        page.search(EncoderSearchKeyword);

//        page.disableEntity();
//        Assert.assertEquals(page.getMessage(), "Video Encoder Successfully Disabled");

        page.updateEncoder(updatedEncoder,updatedID);
        Assert.assertEquals(page.getMessage(), "Video Encoder Successfully Updated");

//        page.deleteEntity();
//        Assert.assertEquals(page.getMessage(), "Video Encoder Successfully Deleted");
    }

    @Test(priority = 2)
    public void testDecoder() throws InterruptedException {
        page.Select_Decoder();
        Thread.sleep(1000);
        page.verifyToolTip("Add Video Decoder", VideoPlatformDolbyBookingPage.TOOLTIP_ADD_DECODER);
        page.verifyToolTip("Export", VideoPlatformDolbyBookingPage.TOOLTIP_EXPORT);
        page.verifyToolTip("Column Settings", VideoPlatformDolbyBookingPage.TOOLTIP_COLUMN_SETTINGS);

        page.addNoneDecoder(noneDecoderName, decoderID);
        Assert.assertEquals(page.getMessage(), "Video Decoder Successfully Added");

        page.displayInactive();
        page.search(noneDecoderName);
        
        page.updateDecoder(updatedDecoder, updatedID);
        Assert.assertEquals(page.getMessage(), "Video Decoder Successfully Updated");
    }

    @Test(priority = 3)
    public void testSource() throws InterruptedException {
        page.Select_Source();
        page.createSource(sourceName, sourceAngle, updatedEncoder);
        Assert.assertEquals(page.getMessage(), "Video Source Successfully Added");

        page.verifyToolTip("Add Video Source", VideoPlatformDolbyBookingPage.TOOLTIP_ADD_SOURCE);
        page.verifyToolTip("Export", VideoPlatformDolbyBookingPage.TOOLTIP_EXPORT);
        page.verifyToolTip("Column Settings", VideoPlatformDolbyBookingPage.TOOLTIP_COLUMN_SETTINGS);

        page.displayInactive();
        page.search(sourceSearchKeyword);
        
        page.updateSource(updatedSource);
//        Assert.assertEquals(page.getMessage(), "Video Source Successfully Updated");
//
////        page.disableEntity();
//        Assert.assertEquals(page.getMessage(), "Video Source Successfully Disabled");
    }

    @Test(priority = 4)
    public void testChannel() throws InterruptedException {
        page.Select_Channel();
        page.CreateNone_Channel(channelName, VendorNoneName, Angle, updatedDecoder, Description,ForeignIDValue,ForeignIDType);
        Assert.assertEquals(page.getMessage(), "Video Channel Successfully Added");

        page.verifyToolTip("Add Video Channel", VideoPlatformDolbyBookingPage.TOOLTIP_ADD_CHANNEL);
        page.verifyToolTip("Export", VideoPlatformDolbyBookingPage.TOOLTIP_EXPORT);
        page.verifyToolTip("Column Settings", VideoPlatformDolbyBookingPage.TOOLTIP_COLUMN_SETTINGS);

        page.displayInactive();
        page.search(ChannelSearchKeyword);

//        page.disableEntity();
//        Assert.assertEquals(page.getMessage(), "Video Channel Successfully Disabled");

        page.updateNoneChannel();
        Assert.assertEquals(page.getMessage(), "Video Channel Successfully Updated");

//        page.deleteEntity();
//        Assert.assertEquals(page.getMessage(), "Video Channel Successfully Deleted");
    }

   
    @Test(priority = 5)
    public void testBooking() throws InterruptedException {
        page.Select_Booking();
        System.out.println("Source name is"+sourceName);
        String sourceName1=updatedSource+"_"+sourceAngle;
        System.out.println("Channel name is"+channelName);
        String channelName1=channelName+" ("+VendorNoneName+")";
//         String channelName1=channelName+" (VendorName)";
        System.out.println("Channel name is"+channelName);
        page.createBooking(sourceName1, channelName1);
        Assert.assertEquals(page.getMessage(), "Video Booking Successfully Added");

        page.disableEntity();
        Assert.assertEquals(page.getMessage(), "Video Booking Successfully Disabled");

        page.deleteEntity();
        Assert.assertEquals(page.getMessage(), "Video Booking Successfully Deleted");
    }


    @AfterClass
    public void tearDown() throws InterruptedException {
    	page.Select_Decoder();
    	
    	page.search(updatedDecoder); 
    	page.disableEntity();
        Assert.assertEquals(page.getMessage(), "Video Decoder Successfully Disabled");
    	page.Select_Source();
        page.search(updatedSource);
        page.disableEntity();
//
        
        page.Select_Channel();    	
        page.search(ChannelSearchKeyword); 
       page.disableEntity();
       Assert.assertEquals(page.getMessage(), "Video Channel Successfully Disabled");

       page.deleteEntity();
       Assert.assertEquals(page.getMessage(), "Video Channel Successfully Deleted");

        driver.quit();
    }
}
