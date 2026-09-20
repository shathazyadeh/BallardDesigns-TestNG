package ballard.tests;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BallardLoginTest {
private WebDriver driver;
private WebDriverWait wait; // هو اللي بيعمل الانتظار

@BeforeMethod
public void setUP() {
	// إعدادات لتجاوز حماية الموقع ومنع تعليق التحميل
    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
    options.addArguments("--disable-blink-features=AutomationControlled");
    
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.get("https://www.ballarddesigns.com/");
    wait = new WebDriverWait(driver, Duration.ofSeconds(15)); //**
}

@Test
public void ballardLogin() throws InterruptedException {
	
	
	//Verify that the website logo is displayed
	WebElement logo = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Ballard Designs LOGO']"))
	);
	Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed");
	System.out.println("The logo is displayed successfully");
	
	//Click on the "Sign In/Register" (Account) link to open the login view
	
	WebElement accountLink = driver.findElement(By.cssSelector("a[title='Account']"));
	accountLink.click();
	System.out.println("Clicked on Account link successfully");
	wait.until(ExpectedConditions.urlContains("UserLogonView"));	
	//Verify the welcome text message on the login page
	
	WebElement welcomeText = wait.until(
		    ExpectedConditions.visibilityOfElementLocated(
		        By.cssSelector("#BDLoginMessageNormal p")
		    )
		);	
	String actualText = welcomeText.getText();
	String expectedText = "Welcome back! To access your account, please enter your email address and password and click Sign In.";
	Assert.assertEquals(actualText, expectedText, "The welcome text does not match");
	System.out.println("Verified navigation text successfully.");
	
	//Verify the email input field is displayed and check its label attribute
	
	WebElement emailField = driver.findElement(By.id("email"));
	Assert.assertTrue(emailField.isDisplayed(), "Email field is not displayed");
	String emailLabel = emailField.getAttribute("label");
	Assert.assertEquals(emailLabel, "Email", "Email label text does not match");
	System.out.println("Email label verified successfully: " + emailLabel);
	
	//Verify the password input field is displayed and check its label attribute
	
	WebElement passwordField = driver.findElement(By.id("password"));
	Assert.assertTrue(passwordField.isDisplayed(), "Password field is not displayed");
	String passwordLabel = passwordField.getAttribute("label");
	Assert.assertEquals(passwordLabel, "Password", "Password label text does not match");
	System.out.println("Password label verified successfully: " + passwordLabel);
	
	//Verify the "Sign In" button is displayed and check its text
	WebElement signInBtn = driver.findElement(By.cssSelector("button.login-button"));
	Assert.assertTrue(signInBtn.isDisplayed(), "Sign In button is not displayed");

	String signInText = signInBtn.getText(); 
	Assert.assertEquals(signInText, "Sign In", "Sign In button text does not match");
	System.out.println("Sign In button verified successfully with text: " + signInText);
	
//Enter valid email and password, then click the Sign In button
	
	emailField.sendKeys("mohammadshatha121@gmail.com");
	passwordField.sendKeys("ss@Zyadeh@52");
	signInBtn.click();
	System.out.println("Clicked Sign In button successfully.");
	Thread.sleep(5000);
	
	// Verify the user is logged in successfully
	WebElement signOutLink = wait.until(
		    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[aria-label='Sign Out']"))
		);
		Assert.assertTrue(signOutLink.isDisplayed(), "Sign Out link is not displayed after login");
		System.out.println("Verified 'Sign Out' link is displayed - user is logged in successfully.");
		
		
	
	//Verify successful navigation to the Account Overview page
	// before login -> Sign In/Register page, after login -> Account Overview page.
	WebElement accountLinkAfterLogin = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(
	                By.cssSelector("a[title='Account']")
	        )
	);
	Assert.assertTrue(accountLinkAfterLogin.isDisplayed(), "Account link is not displayed after login");
	accountLinkAfterLogin.click();
	System.out.println("Account link is displayed and clicked successfully");

	wait.until(ExpectedConditions.urlContains("AccountOverView"));

	
	
	String currentUrl = driver.getCurrentUrl();
	System.out.println("Current URL is: " + currentUrl);
	Assert.assertTrue(currentUrl.contains("AccountOverView"), "Account link still redirected to the Sign In page instead of Account Overview.");
	System.out.println("Login verified successfully: the 'Account' link now redirects to "
			 + currentUrl + " instead of the Sign In/Register page");
	Thread.sleep(2000); 
	//Get the text of the welcome message inside the account page and verify it

	WebElement accountWelcomeMsg = driver.findElement(By.cssSelector("div.WelcomeAccountPane p"));
    String welcomeAccountText = accountWelcomeMsg.getText();
    System.out.println("Account Welcome Message: " + welcomeAccountText);
    Assert.assertEquals(welcomeAccountText,"Welcome to your account at Ballard Designs." ,"Welcome message text does not match");
    System.out.println("Welcome message verified successfully");
    
    
    
}

}
