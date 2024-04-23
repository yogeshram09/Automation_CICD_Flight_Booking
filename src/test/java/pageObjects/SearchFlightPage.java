package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SearchFlightPage extends BasePage {

	public SearchFlightPage(WebDriver driver) {
		super(driver);

	}
	
	@FindBy(xpath = "//span[@class='commonModal__close']")
	private WebElement close;


	@FindBy(xpath = "//span[@class='chNavIcon appendBottom2 chSprite chFlights active']")
	private WebElement selFlights;

	@FindBy(xpath = "//li[@data-cy='oneWayTrip']")
	private WebElement trip;

	@FindBy(xpath = "//input[@id='fromCity']")
	private WebElement fromCityClick;

	@FindBy(xpath = "//input[@placeholder='From']")
	private WebElement fromCity;

	@FindBy(xpath = "//*[contains(@class,'font14 lightGreyText latoBold')]")
	private List<WebElement> elements;

	@FindBy(xpath = "//input[@id='toCity']")
	private WebElement toCityClick;

	@FindBy(xpath = "//input[@placeholder='To']")
	private WebElement toCity;

	@FindBy(xpath = "(//div[@role='heading'])")
	private WebElement selectFutureMonYr;

	@FindBy(xpath = "//span[@aria-label='Next Month']")
	private WebElement nextMonth;

	@FindBy(xpath = "//span[@aria-label='Previous Month']")
	private WebElement previousMonth;

	@FindBy(xpath = "//div[@class='DayPicker-Month']//p[1]")
	private List<WebElement> dates;

	@FindBy(xpath = "//span[@class='lbl_input appendBottom5']")
	private WebElement travellersAndClass;

	@FindBy(xpath = "(//ul[@class='guestCounter font12 darkText gbCounter'])[1]/li")
	private List<WebElement> adults;

	@FindBy(xpath = "(//ul[@class='guestCounter font12 darkText gbCounter'])[2]/li")
	private List<WebElement> childrens;

	@FindBy(xpath = "(//ul[@class='guestCounter font12 darkText gbCounter'])[3]/li")
	private List<WebElement> infants;

	@FindBy(xpath = "//li[contains(text(),'Business')]")
	private WebElement chooseClass;

	@FindBy(xpath = "//button[normalize-space()='APPLY']")
	private WebElement applyBtn;

	@FindBy(xpath = "//div[contains(text(),'Armed Forces')]")
	private WebElement selFare;

	@FindBy(xpath = "//a[normalize-space()='Search']")
	private WebElement search;

	@FindBy(xpath = "//button[normalize-space()='OKAY, GOT IT!']")
	private WebElement okButton;

	public void closeCommon() {

		close.click();
	}
	public void selectFlightsOption() {

		selFlights.click();
	}

	public void selectTrip() {
		trip.click();
	}

	public void fromCityClick() {

		fromCityClick.click();
	}

	public void fromCity(String city) {

		fromCity.clear();
		fromCity.sendKeys(city);

	}

	public void selectCityCode(String code) throws InterruptedException {

		for (int i = 0; i < elements.size(); i++) {

			waitWebElementsAppear(elements.get(i));
			String ele = elements.get(i).getText();
			
			if (ele.equalsIgnoreCase(code)) {

				elements.get(i).click();
				break;

			}

		}

	}

	public void toCityClick() {

		toCityClick.click();
	}

	public void toCity(String city) {

		toCity.clear();
		toCity.sendKeys(city);

	}

	public void selectMonthAndDate(String monthYear) {
		String my;
		while (true) {

			my = selectFutureMonYr.getText();
			if (my.equalsIgnoreCase(monthYear)) {
				break;
			}

			//previousMonth.click();
			nextMonth.click();

		}

	}

	public void selectDate(String date) {

		try {
			for (int i = 0; i < dates.size(); i++) {

				String text = dates.get(i).getText();
				if (text.equalsIgnoreCase(date)) {

					System.out.println("Selected date is: " + text);
					dates.get(i).click();
					break;
				}

			}

			if (Integer.parseInt(date) > 31) {
				System.out.println(date + " is an invalid date..!!");

				return;
			}

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void selectClass() {

		travellersAndClass.click();
	}

	public void selectAdults(String num) throws InterruptedException {

		for (WebElement ad : adults) {

			if (ad.getText().equalsIgnoreCase(num)) {

				ad.click();
				// waitWebElementApplear(ad);
				break;
			}

		}

		if (Integer.parseInt(num) > 9) {
			Assert.assertTrue(false, "You can't select more than 10 adults..!!");
		}

	}

	public void selectChildrens(String num) throws InterruptedException {

		for (WebElement ad : childrens) {

			String tx = ad.getText();
			if (tx.equalsIgnoreCase(num)) {
				ad.click();
				// waitWebElementApplear(ad);
				break;
			}
		}
		if (Integer.parseInt(num) > 6) {
			Assert.assertTrue(false, "You can't select more than 6 childrens..!!");
		}

	}

	public void selectInfants(String num) {

		for (WebElement ad : infants) {

			String tx = ad.getText();
			if (tx.equalsIgnoreCase(num)) {
				ad.click();
				break;
			}
		}

		if (Integer.parseInt(num) > 6) {
			Assert.assertTrue(false, "You can't select more than 6 infants..!!");
		}
	}

	public void chooseClass() {

		chooseClass.click();
	}

	public void apply() {

		applyBtn.click();
	}

	public void selectFareType() throws InterruptedException {

		selFare.click();

	}

	public void searchFlight() throws InterruptedException {

		waitWebElement(search);;
		search.click();

		Thread.sleep(10000);

	}

	public String pageTtitle() throws InterruptedException {

		return driver.getTitle();
	}

	public void okGotItNotification() {

		okButton.click();
	}

}
