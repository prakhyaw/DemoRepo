package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class LoginStepDefinition {
	
	WebDriver driver;
	
	@Given("^user is already on login page$")
	public void user_is_already_on_login_page() {
		
		System.setProperty("webdriver.chrome.driver", "D:/Softwares/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.freecrm.com");		
	}
	
	@When("^title of login page is Free CRM$")
	public void title_of_login_page_is_Free_CRM() {
		String title = driver.getTitle();
		Assert.assertEquals(title,"#1 Free CRM software in the cloud for sales and service");
	}
	
	@Then("^user enters username and password$")
	public void user_enters_username_and_password(){
		driver.findElement(By.name("username")).sendKeys("prakhya_w");
		driver.findElement(By.name("password")).sendKeys("9849929323");
	}

	@Then("^user clicks on login button$")
	public void user_clicks_on_login_button() {
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div/input")).click();;
	}

	@Then("^user is on home page$")
	public void user_is_on_home_page() {
		String title = driver.getTitle();
		Assert.assertEquals(title, "CRMPRO");
	}
	
	@Given("^user is on Contacts page$")
	public void user_is_on_Contacts_page(){
		driver.switchTo().frame("mainpanel");
		System.out.println("frame");
		//driver.findElement(By.linkText("Contacts")).isDisplayed(); - not working
		driver.findElement(By.xpath("//a[contains(text(),'Contacts')]")).isDisplayed();
		//driver.findElement(By.xpath("//*[@id=\"navmenu\"]/ul/li[4]/a")).click();
		System.out.println("Contacts link is displayed");
	}

	@Then("^user clicks on New Contacts link$")
	public void user_clicks_on_New_Contacts_link(){
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"))).build().perform();
		driver.findElement(By.xpath("//a[contains(text(),'New Contact')]")).click();
	}

	@Then("^user fills the form and saves$")
	public void user_fills_the_form_and_saves(){
	}

	@Then("^close the browser$")
	public void close_the_browser(){
	}

}
