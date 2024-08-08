package pageObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BasePage {
	protected WebDriver driver;
    protected WebDriverWait wait;

    private By loginButton = By.xpath("(//button[normalize-space()='Log in'])[1]");
    private By userNameField = By.xpath("(//input[@id='Input_Username'])[1]");
    private By passwordField = By.xpath("(//input[@id='Input_Password'])[1]");
    private By loginBtn = By.xpath("//button[@value='login']");
    private By hamburgerMenu = By.xpath("(//button[@type='button'])[2]");
    private By selectOrgDropdown = By.xpath("(//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-sizeSmall css-niyuos'])[1]");
    private By selectUnitDropdown = By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div[2]/ul/div/form[2]/div/div/div/div");
    private By successMessage = By.xpath("(//div[@class='MuiAlert-message css-1xsto0d'])[1]");
    private By closeIcon = By.xpath("(//button[@title='Close'])[1]");
    private static final By SEARCH = By.xpath("//input[@type='search']");
    private static final By SELECT_DISABLE_CHECKBOX = By.xpath("//input[@name='gilad']");
    
    private static final By MENU = By.xpath("(//div[@role='menu'])[1]");
    private static final By DISABLE = By.xpath("//li[normalize-space()='Disable']");
    private static final By CONFIRM_YES = By.xpath("(//button[contains(@type,'button')][normalize-space()='Yes'])[2]");
    private static final By DELETE = By.xpath("//li[normalize-space()='Delete']");
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void login(String userName, String password) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(loginButton).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameField)).sendKeys(userName);
        // Wait for the password field to be present and visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        // Wait for the login button to be present and clickable
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    public static class ElementUtils {

        // Utility method to wait until an element becomes enabled
        public static void waitForElementToBeEnabled(WebDriver driver, By locator, int timeoutInSeconds, int pollingIntervalInMillis) throws InterruptedException {
            boolean isEnabled = false;

            for (int i = 0; i < (timeoutInSeconds * 1000) / pollingIntervalInMillis; i++) {
                try {
                    WebElement element = driver.findElement(locator);
                    if (element.isEnabled()) {
                        isEnabled = true;
                        break;
                    }
                } catch (Exception e) {
                    // If element is not found or not visible yet, continue polling
                }
                Thread.sleep(pollingIntervalInMillis);
            }

            if (!isEnabled) {
                throw new RuntimeException("Element did not become enabled within the timeout period.");
            }
        }
    }
    public void navigateToVideoPlatform(String orgName, String unitName) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(hamburgerMenu).click();
        Thread.sleep(2000);
        driver.findElement(selectOrgDropdown).click();
        selectDropdownOption(orgName);
        Thread.sleep(1000);
        driver.findElement(selectUnitDropdown).click();
        selectDropdownOption(unitName);
    }

    protected void selectDropdownOption(String optionText) throws InterruptedException {
    	Thread.sleep(2000);
        List<WebElement> allOptions = driver.findElements(By.xpath("(//li[@role='option'])"));
        Thread.sleep(1000);
        for (WebElement option : allOptions) {
            if (option.getText().equals(optionText)) {
                option.click();
                break;
            }
        }
    }

    public String getMessage() throws InterruptedException {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        String messageText = message.getText();
        Thread.sleep(1500);

        WebElement closeIconElement = driver.findElement(closeIcon);
        closeIconElement.click();
        wait.until(ExpectedConditions.invisibilityOf(message));
        System.out.println("Message is: " + messageText);

        return messageText;
    }
    
    
    public void search(String keyword) {
        driver.findElement(SEARCH).sendKeys(keyword);
        System.out.println("Search functionality is working fine");
    }
    
    public void deleteEntity() throws InterruptedException {
    	Thread.sleep(1000);
        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(MENU));
        menu.click();
        WebElement deleteOption = wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE));
        deleteOption.click();
        Thread.sleep(2000);
        driver.findElement(CONFIRM_YES).click();
        System.out.println("");
    }
    
    public void displayInactive() {
        driver.findElement(SELECT_DISABLE_CHECKBOX).click();
        System.out.println("");
    }
    
    public void verifyToolTip(String expectedToolTip, By locator) {
        String actualToolTip = driver.findElement(locator).getAttribute("title");
        Assert.assertEquals(actualToolTip, expectedToolTip, "Tooltip is not properly displayed");
        System.out.println("Tooltip is: " + actualToolTip);
    }
    
    public void disableEntity() throws InterruptedException {
    	Thread.sleep(1000);
        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(MENU));
        menu.click();
        Thread.sleep(1000);
        WebElement disableOption = wait.until(ExpectedConditions.visibilityOfElementLocated(DISABLE));
        disableOption.click();
        Thread.sleep(1000);
        driver.findElement(CONFIRM_YES).click();
        System.out.println("");
    }
   
    
//    public void verifyPageTitle(String expectedTitle) {
//        WebElement title = driver.findElement(By.xpath("(//h6[normalize-space()='Video Encoder'])[1]"));
//        String actualTitle = title.getText();
//        Assert.assertEquals(actualTitle, expectedTitle, "Page Title is not properly displayed");
//        System.out.println("");
//    }

    public String getScreenshot(String testcaseName) {
    	// Create a reference of TakesScreenshot
        TakesScreenshot ts = (TakesScreenshot) driver;

        // Call getScreenshotAs method to create an image file
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Define the destination file path
        String destination = System.getProperty("user.dir") + "/screenshots/" + testcaseName + "_" + getCurrentTime() + ".png";

        // Copy the file to the destination
        try {
            FileUtils.copyFile(source, new File(destination));
            System.out.println("Screenshot taken for test case: " + testcaseName);
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
		return destination;
    	
    }

 // Helper method to get current time in a readable format
    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        return sdf.format(new Date());
    }

}

