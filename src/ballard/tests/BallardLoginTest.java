package ballard.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class BallardLoginTest {
private WebDriver driver;

@BeforeMethod

public void setUP() {
	// إعدادات لتجاوز حماية الموقع ومنع تعليق التحميل
    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
    options.addArguments("--disable-blink-features=AutomationControlled");
    
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.get("https://www.ballarddesigns.com/");
}

@Test
public void ballardLogin() throws InterruptedException {
	WebElement logo = driver.findElement(By.cssSelector("a[title='Ballard Designs LOGO']"));
	Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed");
	System.out.println("The logo is displayed successfully");
	
	
	
	WebElement accountLink = driver.findElement(By.cssSelector("a[title='Account']"));
	accountLink.click();
	System.out.println("Clicked on Account link successfully");
	Thread.sleep(3000);
	
	
	WebElement welcomeText = driver.findElement(By.cssSelector("#BDLoginMessageNormal p"));
	String actualText = welcomeText.getText();
	String expectedText = "Welcome back! To access your account, please enter your email address and password and click Sign In.";
	Assert.assertEquals(actualText, expectedText, "The welcome text does not match");
	System.out.println("Verified navigation text successfully.");
	
	
	WebElement emailField = driver.findElement(By.id("email"));
	Assert.assertTrue(emailField.isDisplayed(), "Email field is not displayed");
	String emailLabel = emailField.getAttribute("label");
	Assert.assertEquals(emailLabel, "Email", "Email label text does not match");
	System.out.println("Email label verified successfully: " + emailLabel);
	
	
	WebElement passwordField = driver.findElement(By.id("password"));
	Assert.assertTrue(passwordField.isDisplayed(), "Password field is not displayed");
	String passwordLabel = passwordField.getAttribute("label");
	Assert.assertEquals(passwordLabel, "Password", "Password label text does not match");
	System.out.println("Password label verified successfully: " + passwordLabel);
	
	
	WebElement signInBtn = driver.findElement(By.cssSelector("button.login-button"));
	Assert.assertTrue(signInBtn.isDisplayed(), "Sign In button is not displayed");

	String signInText = signInBtn.getText(); 
	Assert.assertEquals(signInText, "Sign In", "Sign In button text does not match");
	System.out.println("Sign In button verified successfully with text: " + signInText);
	
	

	emailField.sendKeys("mohammadshatha121@gmail.com");
	passwordField.sendKeys("ss@Zyadeh@52");
	signInBtn.click();
	System.out.println("Clicked Sign In button successfully.");
	Thread.sleep(3000);

	
	
	WebElement accountLinkAfterLogin = driver.findElement(By.cssSelector("a[title='Account']"));
	Assert.assertTrue(accountLinkAfterLogin.isDisplayed(), "Account link is not displayed after login");
	accountLinkAfterLogin.click();
	System.out.println("Account link is displayed and clicked successfully");
	Thread.sleep(2000);
	String currentUrl = driver.getCurrentUrl();
	System.out.println("Current URL is: " + currentUrl);
	Assert.assertTrue(currentUrl.contains("AccountOverView"), "User is not navigated to Account Overview page");
	System.out.println("Verified navigation to Account Overview page successfully with URL: " + currentUrl);
	
	
	WebElement accountWelcomeMsg = driver.findElement(By.cssSelector("div.WelcomeAccountPane p"));
    String welcomeAccountText = accountWelcomeMsg.getText();
    System.out.println("Account Welcome Message: " + welcomeAccountText);
    Assert.assertTrue(welcomeAccountText.contains("Welcome"), "Welcome message text does not match");
    System.out.println("Welcome message verified successfully");
}

}
