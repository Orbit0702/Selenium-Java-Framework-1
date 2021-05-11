/**********************
*Scenario Description:
*1.	Invoke Browser dynamically.
*2. Navigating to Contact Us section
*3. Fill in details in Contact Us. Show number of projects available and display the selected project.
*4. Navigate to Buy Villas section.
*5. Display Property Details of villas which are completed and have more than 10 units and possession date before December 2021.
*6. Take screenshot of the WebPage.
*7. Close the browser.
*...
*Author: Prithvi Adhikary
*Empid: 905041
*Date of creation: 3/29/2021
*************************/

package MiniProject;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class BuildProjectEnquiry3 {
	
	//Global scope variables
	WebDriver driver=null;
	Properties properties=new Properties();
	
	//Test Scenario 1-
	@Parameters("browser")
	@BeforeMethod
	public void OpenBrowser(String browser) {
		if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver","WebDriversExe files/geckodriver.exe"); //setting system property
			driver=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","WebDriversExe files/chromedriver.exe"); //setting system property
			driver=new ChromeDriver();
		}
		driver.manage().deleteAllCookies();//deleting cookies
		driver.manage().window().maximize();//maximizing browser
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void closeBrowser() {
		//Test Scenario 7-
		driver.quit();
	}
	
	@Test
	public void testIshaHomes() throws Exception {
		loadPropertiesFile();
		openApplication();
		navigateToContactUs();
		fillDetailsInEnquiryForm(properties.getProperty("name"),properties.getProperty("email"),properties.getProperty("phonenumber"));
		selectProjectInEnquiryForm(properties.getProperty("selectedProject"));
		submitForm();
		clickBuyVillas();
		displayProjectsAsPerGivenCriteria();
		takeScreenshot();
	}
	 
	/*********************
	MethodName : loadPropertiesFile()
	Method Description: To load the TestInput.properties file
	Package name : MiniProject
	************************/
	public void loadPropertiesFile() throws Exception {
		FileInputStream fs=new FileInputStream("TestDataInput/TestInput.properties");
		properties.load(fs);
	}
	
	/*********************
	MethodName : openApplication()
	Method Description: To launch the IshaHomes application in browser
	Package name : MiniProject
	************************/
	public void openApplication() {
		driver.get("https://ishahomes.com/");
		
		Assert.assertEquals(driver.getTitle(), "Buy Apartments,Flats&Luxury Villas- Best Builders in Chennai-Ishahomes","Page not invoked sucessfully");//validation if site is invoked correctly
		System.out.println("Application invoked successfully");
	}
	
	/*********************
	MethodName : navigateToContactUs()
	Method Description: To navigate to Contact Us section
	Package name : MiniProject
	************************/
	public void navigateToContactUs() {
		WebElement contactUs=driver.findElement(By.linkText("Contact us"));//locating Contact Us webelement
		
		Assert.assertTrue(contactUs.isDisplayed(),"Contact Us is not displayed"); //validation if contact us is displayed
		System.out.println("Contact Us is displayed");
		
		contactUs.click();
		
		Assert.assertEquals(driver.getTitle(),"Contact Isha Homes, an Award Winning Real Estate firm to build your dream house.","Contact Us Section not opened"); //validation if contact us page is invoked
		System.out.println("Contact Us section opened");
	}
	
	/*********************
	MethodName : fillDetailsInEnquiryForm()
	Method Description: To fill all the required details in the enquiry form
	Package name : MiniProject
	************************/
	public void fillDetailsInEnquiryForm(String name, String email, String phone) {
		((JavascriptExecutor)driver).executeScript("scroll(0,100)"); //scrolling down
		
		WebElement nameField=driver.findElement(By.name("sell_do[form][lead][name]")); //name input field
		WebElement emailField=driver.findElement(By.name("sell_do[form][lead][email]")); //email input field
		WebElement phoneField=driver.findElement(By.name("sell_do[form][lead][phone]")); //phone input field
		
		//Validation of input fields
		Assert.assertTrue(nameField.isEnabled(),"Name Field is available");
		System.out.println("Name field present");
		Assert.assertTrue(emailField.isEnabled(),"email Field is available");
		System.out.println("email field present");
		Assert.assertTrue(phoneField.isEnabled(),"Phone filed is available");
		System.out.println("phone field present");
		
		//clearing default values
		nameField.clear();
		emailField.clear();
		phoneField.clear();
		
		//sending values to input fields
		nameField.sendKeys(name);
		emailField.sendKeys(email);
		phoneField.sendKeys(phone);
		
	}
	
	/*********************
	MethodName : selectProjectEnquiryForm()
	Method Description: To select the given project in the select list
	Package name : MiniProject
	************************/
	public void selectProjectInEnquiryForm(String project) {
		//Selecting Project
			//saving the scroll-list
			Select scrollList=new Select(driver.findElement(By.name("sell_do[form][lead][project_id]")));
			
			List<WebElement> scroll= scrollList.getOptions();
			
			System.out.println("Number of available projects: "+scroll.size());//display no. of projects available
			
			for(WebElement listItem: scroll) {
				if(listItem.getText().equals(project)) {
					WebElement selectedProject=listItem;
					selectedProject.click();
					Assert.assertTrue(selectedProject.isSelected(),"Project not selected");//validation if selected project is clicked
					System.out.println("Project Selected");
					System.out.println("The selected project is: "+selectedProject.getText());
				}
			}
	 }
	
	/*********************
	MethodName : submitForm()
	Method Description: To submit the form successfully
	Package name : MiniProject
	************************/
	public void submitForm() {
		WebElement submitForm=driver.findElement(By.xpath("//input[@value='Submit']")); //submit form button
		
		Assert.assertTrue(submitForm.isDisplayed(),"Submit Form button is not displayed"); //validation for submit form button
		System.out.println("Submit Form button is displayed");
		
		submitForm.click();
		
		/*WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[contains(text(),'Thank you for getting in touch!')]"))));*/
		
		//Thread.sleep(8000);
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[contains(text(),'Thank you for getting in touch!')]")).getText(), "THANK YOU FOR GETTING IN TOUCH!","Form not submitted"); //validation for submission of form
		System.out.println("Form submitted successfully");
		
		WebElement backToHomepage=driver.findElement(By.xpath("//a[@class='menu_btn']"));//back-to-homepage button
		
		Assert.assertTrue(backToHomepage.isDisplayed(),"Back To Homepage is not present"); //validation for homepage button
		System.out.println("Back To Homepage button is present");
		
		backToHomepage.click();
		//Thread.sleep(1500);

	}
	
	/*********************
	MethodName : clickBuyVillas()
	Method Description: To click on Buy Villas section and navigate there
	Package name : 
	************************/
	public void clickBuyVillas() {
		//Test Scenario 4-
				WebElement buyVillas=driver.findElement(By.linkText("Buy Villas"));//buy-villas button
				
				Assert.assertEquals(buyVillas.getText(), "Buy Villas","Buy Villas is not present"); //validation for Buy Villas webelement
				System.out.println("Buy Villas link is present");
				
				buyVillas.click();
	}
	
	/*********************
	MethodName : displayProjectAsPerGivenCriteria()
	Method Description: To display name and location of project having units > 1 and possession date before Dec'21
	Package name : 
	************************/
	public void displayProjectsAsPerGivenCriteria() {
		//Test Scenario 5-
		//Below are the projects with Possession Dates before December 2021 and they have been completed
		
		((JavascriptExecutor)driver).executeScript("scroll(0,500)");//scrolling down
		
		WebElement units1Element=driver.findElement(By.xpath("//p[contains(text(),'95')]")); //units of Isha Signature Villas
		WebElement area1=driver.findElement(By.xpath("//a[contains(text(),'Pallikaranai, Chennai ')]")); //Location of Isha Signature Villas
		WebElement project1=driver.findElement(By.xpath("//a[contains(text(),'Ishas Signature Villas')]")); //Name of project- Isha Signature Villas
		if (Integer.parseInt(units1Element.getText().substring(14))>10) //Condition for display
		{
			System.out.println("Project: "+project1.getText());
			System.out.println("Area: "+ area1.getText());
			System.out.println(units1Element.getText());
		}
		
		WebElement units2Element=driver.findElement(By.xpath("//p[contains(text(),'48')]")); //units of Isha Pallavi
		WebElement area2=driver.findElement(By.xpath("//a[contains(text(),'Pallikaranai, Chennai.')]")); //Location of Isha Pallavi
		WebElement project2=driver.findElement(By.xpath("//a[contains(text(),'Isha Pallavi')]")); //Name of Project-Isha Pallavi
		if(Integer.parseInt(units2Element.getText().substring(14))>10)
		{
			System.out.println("Project: "+project2.getText());
			System.out.println("Area: "+ area2.getText());
			System.out.println(units2Element.getText());
		}
		
		WebElement units3Element=driver.findElement(By.xpath("//p[contains(text(),'106')]")); //units of Isha Mia Villas
		WebElement area3=driver.findElement(By.xpath("//a[contains(text(),'Pudupakkam Village, Chennai.')]")); //Location of Isha Mia
		WebElement project3=driver.findElement(By.xpath("//a[contains(text(),'Isha Mia Villas')]")); //Name of Project- Isha Mia Villas
		if(Integer.parseInt(units3Element.getText().substring(14))>10)
		{
			System.out.println("Project: "+project3.getText());
			System.out.println("Area: "+ area3.getText());
			System.out.println(units3Element.getText());
		}		
	}
	
	/*********************
	MethodName : takeScreenshot()
	Method Description: To take a screenshot of the webpage
	Package name : 
	************************/
	public void takeScreenshot() throws Exception {
		//Test Scenario 6-
		//saving screenshot
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(src, new File("./Screenshots/testIshaHomesVillas2.png"));
		
	}
	 
}
