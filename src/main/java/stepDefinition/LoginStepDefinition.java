package stepDefinition;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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
		driver.manage().window().maximize();
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
	public void user_fills_the_form_and_saves() throws InterruptedException{
		Select title = new Select(driver.findElement(By.name("title")));
		title.selectByVisibleText("Mr.");
		
		String firstname = "first name";
		driver.findElement(By.id("first_name")).sendKeys(firstname);
		String lastname = "surname";
		driver.findElement(By.id("surname")).sendKeys(lastname);
		String name = firstname+lastname;
		
		Select suffix = new Select(driver.findElement(By.name("suffix")));
		suffix.selectByVisibleText("II");		
		
		driver.findElement(By.xpath("//input[@name='contact_lookup_ref']//following-sibling::input[@value='Lookup']")).click();
		
		Set<String> windows = driver.getWindowHandles();
		
		Iterator<String> it = windows.iterator();
		String parentwindow = it.next();
		
		while(it.hasNext()) {
			String childwindow = it.next();
			driver.switchTo().window(childwindow);
			driver.manage().window().maximize();
			driver.findElement(By.id("search")).sendKeys("test");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			driver.findElement(By.xpath("//a[contains(text(),'test1 test1')]")).click();
		}
		
		
		Thread.sleep(3000);
		driver.switchTo().window(parentwindow);
		System.out.println(driver.getCurrentUrl());
		driver.findElement(By.xpath("//input[@name='receive_email' and @value='N']")).click();
		driver.findElement(By.id("fieldId_birthday")).sendKeys("02-Jan-1992");
		
		driver.findElement(By.xpath("//input[@value='Load From Company']//following-sibling::input[@type='submit' and @value='Save']")).submit();
	}

	
	@Then("^user verifies the contact$")
	public void user_verifies_the_contact() {
		user_is_on_Contacts_page();
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"vContactsForm\"]/table/tbody/tr/td[2]/a"));
		Iterator<WebElement> itr = rows.iterator();
		int rowcount = 0;
		while(itr.hasNext()) {
			rowcount++;
		}
		System.out.println(rowcount);
		
		
		
		
		//*[@id="vContactsForm"]/table/tbody/tr[3]/td[2]/strong
		//*[@id="vContactsForm"]/table/tbody/tr[4]/td[2]/a
		
		//*[@id="vContactsForm"]/table/tbody/tr[11]/td[2]/a
		
	}
	
	
	@Then("^close the browser$")
	public void close_the_browser(){
		driver.close();
	}

}
