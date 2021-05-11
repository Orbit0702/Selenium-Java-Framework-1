package MiniProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BuildingProjectEnquiry1 {

	@Test
	public void ProjectEnquiry() throws Exception {
		
		System.setProperty("webdriver.gecko.driver","C://Users//prith//eclipse-jee-2020-12-R-win32-x86_64//geckodriver.exe"); //setting system property
		WebDriver driver=new FirefoxDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://ishahomes.com/");
		
		Assert.assertEquals(driver.getTitle(), "Buy Apartments,Flats&Luxury Villas- Best Builders in Chennai-Ishahomes");//validation if site is invoked correctly
		
		WebElement contactUs=driver.findElement(By.linkText("Contact us"));//locating Contact Us webelement
		
		Assert.assertTrue(contactUs.isDisplayed()); //validation if contact us is displayed
		
		contactUs.click();
		
		Assert.assertEquals(driver.getTitle(),"Contact Isha Homes, an Award Winning Real Estate firm to build your dream house."); //validation if contact us page is invoked
		
		((JavascriptExecutor)driver).executeScript("scroll(0,100)"); //scrolling down
		
		WebElement nameField=driver.findElement(By.name("sell_do[form][lead][name]")); //name input field
		WebElement emailField=driver.findElement((By.name("sell_do[form][lead][email]"))); //email input field
		WebElement phoneField=driver.findElement(By.name("sell_do[form][lead][phone]")); //phone input field
		
		nameField.sendKeys("ABCD");
		emailField.sendKeys("abcd@gmail.com");
		phoneField.sendKeys("9873830922");
		
		WebElement selectedProject=driver.findElement(By.cssSelector("#contactPage > div:nth-child(3) > div:nth-child(1) > "
				+ "div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > "
				+ "form:nth-child(1) > div:nth-child(6) > div:nth-child(1) > div:nth-child(1) > select:nth-child(2) > option:nth-child(4)"));
		
		selectedProject.click();
		
		Select scrollList=new Select(driver.findElement(By.cssSelector("#contactPage > div:nth-child(3) > "
				+ "div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > "
				+ "div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(6) >"
				+ "div:nth-child(1) > div:nth-child(1) > select:nth-child(2)")));
		
		List<WebElement> scroll= scrollList.getOptions();
		
		System.out.println("Number of available projects: "+scroll.size());
		
		System.out.println("Selected project: "+selectedProject.getText());
		
		WebElement submitForm=driver.findElement(By.xpath("//input[@value='Submit']"));
		
		submitForm.click();
		
		Thread.sleep(10000);
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[contains(text(),'Thank you for getting in touch!')]")).getText(), "THANK YOU FOR GETTING IN TOUCH!"); //validation for submission of form
		
		WebElement backToHomepage=driver.findElement(By.xpath("//a[@class='menu_btn']"));
		
		backToHomepage.click();
		Thread.sleep(3000);
		
		WebElement buyVillas=driver.findElement(By.linkText("Buy Villas"));
		
		Assert.assertEquals(buyVillas.getText(), "Buy Villas"); //validation for Buy Villas webelement
		
		buyVillas.click();
		
		((JavascriptExecutor)driver).executeScript("scroll(0,100)");//scrolling down
		
		WebElement viewDetails1=driver.findElement(By.xpath("//a[@class='viewDetails' and @href='https://ishahomes.com/isha-signature-villa/']"));
		
		viewDetails1.click();
		
		WebElement areaCity1=driver.findElement(By.xpath("//span[@class='icflocation']"));
		
		String area1=areaCity1.getText();
		
		((JavascriptExecutor)driver).executeScript("scroll(0,100)");
		
		WebElement noOfUnits1=driver.findElement(By.xpath("//h5[contains(text(),'95 Villas')]"));
		System.out.println(noOfUnits1.getText());
		WebElement possessionDate1=driver.findElement(By.xpath("//h5[contains(text(),\"Jun'09\")]"));
		int units1=Integer.parseInt(noOfUnits1.getText().substring(0,2));
		if((possessionDate1.getText().compareTo("Dec'21")>0)&&(units1>=10)) {
			System.out.println(area1);
		}
		Thread.sleep(3000);
		
		driver.findElement(By.linkText("Buy Villas")).click();
		Thread.sleep(3000);
		((JavascriptExecutor)driver).executeScript("scroll(0,100)");//scrolling down
		
		WebElement noOfUnits2=driver.findElement(By.xpath("//p[contains(text(),'48')]"));
		
		WebElement viewDetails2=driver.findElement(By.xpath("//a[@class='viewDetails' and @href='https://ishahomes.com/isha-pallavi/']"));
		
		viewDetails1.click();
		
		WebElement areaCity2=driver.findElement(By.xpath("//span[@class='icflocation']"));
		
		String area2=areaCity1.getText();
		
		((JavascriptExecutor)driver).executeScript("scroll(0,100)");
		
		WebElement possessionDate2=driver.findElement(By.xpath("//h5[contains(text(),\"Jun'09\")]"));
		int units2=Integer.parseInt(noOfUnits2.getText());
		System.out.println(noOfUnits2);
		if((possessionDate2.getText().compareTo("Dec'21")>0)&&(units2>=10)) {
			System.out.println(area2);
		}
		
		
		driver.quit();
		
		
		
	}

}
