package raugh;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Raugh {

	static WebDriver driver;
	static WebDriverWait wait;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.makemytrip.com/");

		driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement ele = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='wewidgeticon we_close']")));
		ele.click();

		driver.switchTo().defaultContent();

		driver.findElement(By.cssSelector("label[for='departure']")).click();

		WebElement web = driver.findElement(By.xpath("(//div[@role='heading'])[2]"));
		wait.until(ExpectedConditions.visibilityOf(web));
		String text = driver.findElement(By.xpath("(//div[@role='heading'])[2]")).getText();
		System.out.println("Before Split: " + text);

		String ex = text.split("2024")[0].trim();
		System.out.println("After Split: " + ex);

		// driver.findElement(By.cssSelector("li[data-cy='adults-4']")).click();
		// driver.findElement(By.cssSelector("li[data-cy='children-6']")).click();

		driver.close();
	}

}
