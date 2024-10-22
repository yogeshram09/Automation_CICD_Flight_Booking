package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected static WebDriver driver;
	FileInputStream fis;
	protected Properties prop;
	ChromeOptions option;
	protected Logger logger;

	@BeforeClass(groups = { "regression", "sanity", "master" })
	public void setUp() throws IOException {
          
               //for testing cicd webhook to add new comment.

		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		prop = new Properties();
		prop.load(fis);

		logger = LogManager.getLogger(this.getClass());

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.get(prop.getProperty("siteUrl"));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

//		WebElement frame = driver.findElement(By.name("webklipper-publisher-widget-container-notification-frame"));
//
//		driver.switchTo().frame(frame);
//
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		WebElement ele = wait
//				.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='wewidgeticon we_close']")));
//		ele.click();
//
//		driver.switchTo().defaultContent();

	}

	public String captureScreenShot(String testName) throws IOException {

		String timeStamp = new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + timeStamp + ".png";
		File target = new File(path);
		FileUtils.copyFile(source, target);
		return path;

	}

	@AfterClass(groups = { "regression", "sanity", "master" })
	public void tearDown() throws InterruptedException {

		if (driver != null) {

			// driver.close();
		}

	}

}
