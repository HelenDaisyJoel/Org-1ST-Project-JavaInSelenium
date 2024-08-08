package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VideoPlatformDolbyBookingPage extends BasePage{
	protected WebDriver driver;
	protected WebDriverWait wait;
    
    private static final By SELECT_ENCODER = By.xpath("(//p[normalize-space()='Video Encoder'])[1]");
    private static final By ADD_ICON_ENCODER = By.xpath("//button[@title='Add Video Encoder']");
    private static final By ENCODER_NAME = By.id("Encoder-Name");
    private static final By ENCODER_ID = By.id("Encoder-ID");
    
    private static final By VIDEO_DECODER_OPTION = By.xpath("(//p[normalize-space()='Video Decoder'])[1]");
    private static final By ADD_ICON_DECODER = By.xpath("//button[@title='Add Video Decoder']");
    private static final By DECODER_NAME_FIELD = By.xpath("//input[@id='Decoder-Name']");
    private static final By DECODER_ID_FIELD = By.xpath("//input[@id='Decoder-ID']");
   
    
//    Source
    private By Select_Source = By.xpath("//p[normalize-space()='Video Source']");
    private static final By ADD_ICON_SOURCE = By.xpath("//button[@title='Add Video Source']");
    private By Display_Name = By.xpath("//input[@id='Display-Name']");
    private By Angle = By.xpath("(//div[@id='Angle'])[1]");
    private By Encoder = By.xpath("(//div[@id='Video Encoder Display Name'])[1]");
    private By Select_Encoder = By.xpath("//li[@role='option'][1]");
    
    
//	Select Channel
	By Select_Channel=By.xpath("//p[normalize-space()='Video Channel']");
	private static final By ADD_ICON_CHANNEL = By.xpath("//button[@title='Add Video Channel']");
	By Display_Name1=By.xpath("//input[@id='Display-Name']");
	By Select_Vendor=By.xpath("(//div[@id='Vendor'])[1]");
	By Select_Dolby=By.xpath("//div[contains(@class, 'MuiPaper-root')]//ul[@role='listbox']//li[text()='Dolby']");
	By Select_None=By.xpath("//div[contains(@class, 'MuiPaper-root')]//ul[@role='listbox']//li[text()='None']");
	By Decoder=By.xpath("(//div[@id='decoderName'])[1]");
	
	By Select_Booking=By.xpath("//p[normalize-space()='Video Booking']");
    private static final By ADD_ICON_BOOKING = By.xpath("//button[@title='Add Video Booking']");
    
    
    By Angle_Name=By.xpath("//div[@id='Auto-Angle']");
	By Select_Angle=By.xpath("//li[normalize-space()='Backside']");
	By Description1=By.xpath("(//input[@id='Description'])[1]");
	By VendorChannelID1=By.xpath("(//input[@id='Vendor-Channel-Identifier'])[1]");
	By ADD_Button=By.xpath("(//button[normalize-space()='Add'])[1]");

	By Connector_ID=By.xpath("(//input[@id='connectorId'])[1]");
	By Cluster_Name=By.xpath("(//div[@id='clusterName'])[1]");
	By Select_Cluster=By.xpath("(//li[normalize-space()='Ashburn'])[1]");
	By Description_Name=By.xpath("(//input[@id='Description'])[1]");

//	Tooltip
	By Tool_Tip_Channel=By.xpath("(//button[@title='Add Video Channel'])[1]");
  
    private static final By ADD_BUTTON = By.xpath("(//button[normalize-space()='Add'])[1]");
    
    public static final By TOOLTIP_ADD_ENCODER=By.xpath("(//button[@title='Add Video Encoder'])[1]");
    public static final By TOOLTIP_ADD_DECODER = By.xpath("(//button[@title='Add Video Decoder'])[1]");
    public static final By TOOLTIP_ADD_CHANNEL= By.xpath("(//button[@title='Add Video Channel'])[1]");
    public static final By TOOLTIP_ADD_SOURCE= By.xpath("(//button[@title='Add Video Source'])[1]");
    public static final By TOOLTIP_ADD_BOOKING= By.xpath("(//button[@title='Add Video Booking'])[1]");
    public static final By TOOLTIP_EXPORT = By.xpath("(//button[normalize-space()='Export'])[1]");
    public static final By TOOLTIP_COLUMN_SETTINGS = By.xpath("(//button[@title='Column Settings'])[1]");
    private static final By MENU = By.xpath("(//div[@role='menu'])[1]");

    private static final By UPDATE = By.xpath("//li[normalize-space()='Edit']");
    private static final By UPDATE_BUTTON = By.xpath("(//button[normalize-space()='Update'])[1]");
    
    By SourceName=By.xpath("(//div[@id='Source'])[1]");
	By selectSource=By.xpath("//li[@role='option'][1]");
	By ChannelName=By.xpath("//div[@id='Video Booking Channel']");
	By selectChannel=By.xpath("//li[@role='option'][1]");
	By StartTime=By.xpath("//button[@aria-label='Start Time']");
	By EndTime=By.xpath("//button[@aria-label='End Time']");
	By ChangeEndTime=By.xpath("//*[@id=\"add-new\"]/div/div/div/form/div[4]/div/div[2]/button");
	By SelectNextDate=By.xpath("//button[normalize-space()='31']");
	
    public VideoPlatformDolbyBookingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }
 /////////				ENCODER TEST           ////////
    
    public void Select_Encoder() throws InterruptedException {
    	Thread.sleep(2000);
        driver.findElement(SELECT_ENCODER).click();
    }

    public void createEncoder(String encoderName, String encoderID) throws InterruptedException {
    	 // Wait for the ADD_ICON_ENCODER to become enabled
    	
    	ElementUtils.waitForElementToBeEnabled(driver, ADD_ICON_ENCODER, 600, 2000);


        // Proceed with the rest of the method
        WebElement icon = driver.findElement(ADD_ICON_ENCODER);
        icon.click();
        
//        WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_ICON_ENCODER));
//        icon.click();
        driver.findElement(ENCODER_NAME).sendKeys(encoderName);
        driver.findElement(ENCODER_ID).sendKeys(encoderID);
        driver.findElement(ADD_BUTTON).click();
        System.out.println("");
    }

    public void updateEncoder(String updatedName,String updatedID) throws InterruptedException {
    	Thread.sleep(1000);
        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(MENU));
        menu.click();
        WebElement updateOption = wait.until(ExpectedConditions.visibilityOfElementLocated(UPDATE));
        updateOption.click();
        driver.findElement(ENCODER_NAME).clear();
        driver.findElement(ENCODER_NAME).sendKeys(updatedName);
        driver.findElement(ENCODER_ID).clear();
        driver.findElement(ENCODER_ID).sendKeys(updatedID);
        driver.findElement(UPDATE_BUTTON).click();
        System.out.println("");
    }

      
    /////////				DECODER TEST           ////////
    
    public void Select_Decoder() throws InterruptedException {
        driver.findElement(VIDEO_DECODER_OPTION).click();
        System.out.println("");
    }

    public void addDolbyDecoder(String decoderName, String decoderID) throws InterruptedException {
//    	Thread.sleep(4000);
//    	WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_ICON_DECODER));
////    	  driver.findElement(ADD_ICON_DECODER).click();
//    	  Thread.sleep(4000);
//    	  icon.click();
    	ElementUtils.waitForElementToBeEnabled(driver, ADD_ICON_DECODER, 600, 2000);

        // Proceed with the rest of the method
        WebElement icon = driver.findElement(ADD_ICON_DECODER);
        icon.click();
        driver.findElement(DECODER_NAME_FIELD).sendKeys(decoderName);
        Thread.sleep(1000);
        driver.findElement(DECODER_ID_FIELD).sendKeys(decoderID);
        Thread.sleep(1000);
        selectSupportedChannelVendor("Dolby");
        Thread.sleep(1000);
        driver.findElement(ADD_BUTTON).click();
    }
    
    private void selectSupportedChannelVendor(String vendor) throws InterruptedException {
    	Thread.sleep(1000);
        WebElement supportedChannelVendor = driver.findElement(By.xpath("(//div[@id='Supported-Channel-Vendor'])[1]"));
        Thread.sleep(1000);
        supportedChannelVendor.click();
        WebElement vendorOption = driver.findElement(By.xpath("//li[normalize-space()='" + vendor + "']"));
        vendorOption.click();
    }
    
    public void addNoneDecoder(String name, String id) throws InterruptedException {
        driver.findElement(ADD_ICON_DECODER).click();
        driver.findElement(DECODER_NAME_FIELD).sendKeys(name);
        Thread.sleep(1000);
        driver.findElement(DECODER_ID_FIELD).sendKeys(id);
        Thread.sleep(1000);
        selectSupportedChannelVendor("None");
        Thread.sleep(1000);
        driver.findElement(ADD_BUTTON).click();
        System.out.println("");
    }
    
    public void updateDecoder(String updatedName, String updatedID) throws InterruptedException {
    	Thread.sleep(1000);
    	WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(MENU));
        menu.click();
        WebElement updateOption = wait.until(ExpectedConditions.visibilityOfElementLocated(UPDATE));
        updateOption.click();
        driver.findElement(DECODER_NAME_FIELD).clear();
        driver.findElement(DECODER_NAME_FIELD).sendKeys(updatedName);
        driver.findElement(DECODER_ID_FIELD).clear();
        driver.findElement(DECODER_ID_FIELD).sendKeys(updatedID);
        driver.findElement(UPDATE_BUTTON).click();
        System.out.println("");
    }
    
    
    
/////////				SOURCE TEST           ////////
    
    public void Select_Source() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(Select_Source).click();
	
		}
    
    public void createSource(String sourceName, String angleOption, String updatedEncoder) throws InterruptedException {

//    	Thread.sleep(4000);
//    	WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_ICON_SOURCE));
//        icon.click();
    	
    	ElementUtils.waitForElementToBeEnabled(driver, ADD_ICON_SOURCE, 600, 2000);

        // Proceed with the rest of the method
        WebElement icon = driver.findElement(ADD_ICON_SOURCE);
        icon.click();
       
        driver.findElement(Display_Name).sendKeys(sourceName);
        Thread.sleep(1000);        
        // Handle Angle selection
        selectDropdownOption(Angle, Select_Angle);
        // Handle Encoder selection
//        selectDropdownOption(Encoder, Select_Encoder);
        Thread.sleep(1000); 
        driver.findElement(Encoder).click();
	    Thread.sleep(2000);
	    waitForDropdownOptions();
	    Thread.sleep(1000);
	    selectDropdownOption(updatedEncoder);
        // Handle Start Margin selection
        selectTimeOption("10 hours");
        // Handle End Margin selection
        selectTimeOption("10 hours");
        Thread.sleep(2000);
        driver.findElement(ADD_Button).click();
    }
    
    private void selectTimeOption(String timeOption) throws InterruptedException {
        driver.findElement(By.xpath("(//button[@aria-label='Choose time, selected time is 12:00 AM'])[1]")).click(); // Update if different locator for start/end margin
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement timeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@role='option' and @aria-label='" + timeOption + "']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", timeElement);
        Actions actions = new Actions(driver);
        actions.moveToElement(timeElement).click().perform();
        Thread.sleep(1000);
    }
    public void updateSource(String updatedSource) throws InterruptedException {
		Thread.sleep(1000);
		WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(MENU));
        menu.click();
        WebElement updateOption = wait.until(ExpectedConditions.visibilityOfElementLocated(UPDATE));
        updateOption.click();
		Thread.sleep(1000);		
		WebElement update=driver.findElement(Display_Name);
		update.clear();
		update.sendKeys(updatedSource);
		Thread.sleep(1000);
		driver.findElement(UPDATE_BUTTON).click();
		System.out.println("Update functionality is working fine");
		System.out.println(" ");
		Thread.sleep(2000);
	}

    
/////////				CHANNEL TEST           ////////
    
    public void Select_Channel() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(Select_Channel).click();
	
		}

//Create a new Video Channel -Testcase 1
	public void CreateDolby_Channel(String DisplayName, String Vendor, String Angle,String ConnectorID, String ClusterName, String Description ) throws InterruptedException {
		
//		Thread.sleep(5000);	
//		WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_ICON_CHANNEL));
//        icon.click();
		
		ElementUtils.waitForElementToBeEnabled(driver, ADD_ICON_CHANNEL, 600, 2000);

        // Proceed with the rest of the method
        WebElement icon = driver.findElement(ADD_ICON_CHANNEL);
        icon.click();
				
		driver.findElement(Display_Name1).sendKeys(DisplayName);
		Thread.sleep(2000);
		selectDropdownOption(Select_Vendor, Select_Dolby);
		Thread.sleep(2000);
		selectDropdownOption(Angle_Name, Select_Angle);
		Thread.sleep(2000);
		driver.findElement(Connector_ID).sendKeys(ConnectorID);
		Thread.sleep(1000);			
		selectDropdownOption(Cluster_Name,Select_Cluster);
		Thread.sleep(2000);
		driver.findElement(Description_Name).sendKeys(Description);
		Thread.sleep(2000);
		scrollDown();
		driver.findElement(ADD_Button).click();
		
	}
	
public void CreateNone_Channel(String DisplayName, String Vendor, String Angle,String decoderName, String Description, String ForeignIDValue, String ForeignIDType ) throws InterruptedException {
		
		Thread.sleep(4000);	
		WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_ICON_CHANNEL));
        icon.click();
				
		driver.findElement(Display_Name1).sendKeys(DisplayName);
		Thread.sleep(2000);
		selectDropdownOption(Select_Vendor, Select_None);
		Thread.sleep(2000);
		selectDropdownOption(Angle_Name, Select_Angle);
		Thread.sleep(2000);
		 driver.findElement(Decoder).click();
		waitForDropdownOptions();
		Thread.sleep(2000);
		selectDropdownOption(decoderName);
		driver.findElement(Description_Name).sendKeys(Description);
		Thread.sleep(2000);
		scrollDown();
		driver.findElement(ADD_Button).click();
		
	}
	private void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
    }
	
	private void selectDropdownOption(By dropdownLocator, By optionLocator) throws InterruptedException {
        driver.findElement(dropdownLocator).click();
        Thread.sleep(1000);
        driver.findElement(optionLocator).click();
        Thread.sleep(1000);
    }
	
	public void updateDolbyChannel() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(MENU).click();
		driver.findElement(UPDATE).click();
		Thread.sleep(1000);
		driver.findElement(Description_Name).sendKeys("111111");
		driver.findElement(Connector_ID).sendKeys("8");
		Thread.sleep(1000);
		driver.findElement(UPDATE_BUTTON).click();
		System.out.println("Update functionality is working fine");
		System.out.println(" ");
		
	}
	
	public void updateNoneChannel() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(MENU).click();
		driver.findElement(UPDATE).click();
		Thread.sleep(1000);
		driver.findElement(Description_Name).sendKeys("111111");
		
		Thread.sleep(1000);
		driver.findElement(UPDATE_BUTTON).click();
		System.out.println("Update functionality is working fine");
		System.out.println(" ");
		
	}
    
    
/////////				BOOKING TEST           ////////
	
	public void Select_Booking() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(Select_Booking).click();
	
		}

	
	public void createBooking(String sourceName, String channelName) throws InterruptedException {
//	    Thread.sleep(3000);    
//	    driver.findElement(ADD_ICON_BOOKING).click();
		ElementUtils.waitForElementToBeEnabled(driver, ADD_ICON_BOOKING, 600, 2000);

        // Proceed with the rest of the method
        WebElement icon = driver.findElement(ADD_ICON_BOOKING);
        icon.click();
	    driver.findElement(SourceName).click();
	    Thread.sleep(1000);
	    waitForDropdownOptions();
	    Thread.sleep(1000);
	    selectDropdownOption(sourceName);
	    Thread.sleep(1000);
	    driver.findElement(ChannelName).click();
	    Thread.sleep(1000);
	    // Select source
	    waitForDropdownOptions();
	    Thread.sleep(1000);
	    selectDropdownOption(channelName);
	    // Select start and end times
	    Thread.sleep(1000);
	    driver.findElement(StartTime).click();
	    Thread.sleep(1000);
	    driver.findElement(EndTime).click();
	    Thread.sleep(1000);
	    driver.findElement(ChangeEndTime).click();
	    driver.findElement(SelectNextDate).click();
	    
	    Thread.sleep(2000);
	    driver.findElement(ADD_Button).click();
	}
	
	private void waitForDropdownOptions() {
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@role='option']")));
	}


    
}
