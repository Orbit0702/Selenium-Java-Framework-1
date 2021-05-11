********************************************READ ME****************************************************************

Hi User. Please consider going through the description and some specific instructions before executing the test.


DESCRIPTION: -
* This test is to fill an enquiry form in the Builder's website and then find some specific projects. 
* Test Scenario Descriptions-
	1. Invoke Browser dynamically.
	2. Navigating to Contact Us section
	3. Fill in details in Contact Us. Show number of projects available and display the selected project.
	4. Navigate to Buy Villas section.
	5. Display Property Details of villas which are completed and have more than 10 units and possession date before December 2021.
	6. Take screenshot of the WebPage.
	7. Close the browser.
* Project Explorer layout- 
	905041_PrithviAdhikary_MiniProject_IshaHomes
		src(source folder)
			MiniProject(package)
				BuildingProjectEnquiry1.java
				BuildProjectEnquiry2.java 
				BuildProjectEnquiry3.java(Test Class) 
		JRE System Libraries(Java system jars)
		Referenced Libraries(Selenium jars)
		TestNG(testNG jars)
		Screenshots(folder to save screenshot)
		TestDataInput(Data Input folder)
			TestInput.properties(DDT properties file)
			TestInput.txt
		test-output(auto-generated TestNG output folder)
		WebDriversExe files(folder containing necessary webdrivers)
			chromedriver.exe(Chrome Driver)
			geckodriver.exe(Firefox Driver)
		testng.xml(xml file through which to run the test cases)
		READ_ME.txt(read_me text file)
* This project uses TestNG.

Instructions: -
* Use testng.xml file to execute the test cases. Rightclick testng.xml>run as>TestNG Suite. 
* Only two drivers are provided, Google Chrome and Firefox drivers are implemented. To use any of them, change the 'value'(values are "firefox" or "chrome") attribute in 'parameter' tag 
  in testng.xml to the desired driver. Execution in chrome is faster than firefox.
* For user desired input in contact us input fields, change the values of keys in TestInput.properties file. User can change the key values as per his/her own wish in the properties file.
* Site may be changed by the time of evaluation. Lest it happens, please do make the required changes in the webelement paths.
* Do not use BuildingProjectEnquiry1.java or BuildProjectEnquiry2.java classes. Use only BuildProjectEnquiry3.java (Test class). 
* Do delete any/all pre-existing screenshot saved in the Screenshots folder before executing.
* Explicit Wait at line 149 is not working. Implementation of implicit wait is done in method OpenBrowser().
* Project Possession Date Extraction is difficult. To find possession date, click on the View Details. This part of requirement is not automated properly since StaleElementException is 
  thrown after navigating back to Buy Villas page. Through manual observation, only those projects are displayed in console who fulfill the required criteria. 


*********************************************************************THE END*******************************************************************

