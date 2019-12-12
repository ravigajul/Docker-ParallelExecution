# ClarityPPM_QASanityAutomation
# QASanityAutomation
# Docker_Selenium
# Docker_Selenium
To Run the sript on Linux 
#!/bin/bash
Build_pid=$(sudo netstat -tpln|grep :8000|awk -F ' ' '{print $7}'|cut -d '/' -f1)
build_maven_fn() {
    cd /automation/QASanityAutomation/UISanityHealthCheck/
    sudo python -m SimpleHTTPServer 8000 2>&1 /dev/null &
    echo "Processing the Build"
    cd /automation/QASanityAutomation/UISanityHealthCheck
    sudo docker-compose down
    sudo docker-compose up -d
    sleep 10
    sudo mvn test
    if [ $? -eq 0 ];then
      echo "Build is successful. Clearing Docker containers"
      sudo docker-compose down
    else
      echo "Build Failed. Destorying docker instances"
      sudo docker-compose down
    fi
    }
    
if [ -z "$Build_pid" ];then
   build_maven_fn
else
   sudo kill -9 $Build_pid
   build_maven_fn
fi

#Things to Know
To find the list of all elements
@FindAll(@FindBy(how = How.CSS, using = "span.ppm_read_only_value"))
	private List<WebElement> build_version;
	
ITestContext 

To store object and retrieve in other class (itestcontext is active through out the session)

https://automationtalks.com/2017/07/06/what-is-itestcontext-in-testng-seleniu/

SimpleDateFormat
String sLatestRun = nikuhomepage.getTablerows().get(1).findElements(By.tagName("td")).get(10).getText();
Date dLatestRun = new SimpleDateFormat("MM/dd/yy hh:mm a").parse(sLastRun);

TestNG
@Test(dependsOnMethods = { "ValidateAboutDialog" }, priority = 4)

Extent Reports

Headless 

WebDriverManager.chromedriver().setup();
ChromeOptions options= new ChromeOptions();
options.addArguments("headless");
driver = new ChromeDriver(options);	

To wait for the elements that are loaded at runtime

AjaxElementLocatorFactory factory= new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(factory, this);

