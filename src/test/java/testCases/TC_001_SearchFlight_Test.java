package testCases;

import org.testng.annotations.Test;

import org.testng.Assert;
import baseClass.BaseClass;
import pageObjects.SearchFlightPage;

public class TC_001_SearchFlight_Test extends BaseClass {

	@Test(groups = { "regression", "master" })
	public void searchFlight() throws InterruptedException {

		try {

			logger.info("*****  Starting TC_001_SearchFlight_Test ***** ");

			SearchFlightPage sr = new SearchFlightPage(driver);
			
			sr.closeCommon();

			sr.selectFlightsOption();
			logger.info("----Select the flight option----");

			sr.selectTrip();
			sr.fromCityClick();
			sr.fromCity(prop.getProperty("fromCity"));
			sr.selectCityCode(prop.getProperty("fromCityCode"));

			logger.info("----Selected from city option----");

			sr.toCityClick();
			sr.toCity(prop.getProperty("toCity"));
			sr.selectCityCode(prop.getProperty("toCityCode"));

			logger.info("----Selected To city option----");

			sr.selectMonthAndDate(prop.getProperty("monthAndYear"));

			logger.info("----Selected the month and year----");

			sr.selectDate(prop.getProperty("date"));

			logger.info("----Selected the date----");

			sr.selectClass();
			sr.selectAdults(prop.getProperty("adults"));
			sr.selectChildrens(prop.getProperty("childrens"));
			sr.selectInfants(prop.getProperty("infants"));
			sr.chooseClass();
			sr.apply();
			sr.selectFareType();

			logger.info("----Clicking the search flight button----");
			sr.searchFlight();

			logger.info("----Clicked to search flight button----");

			sr.okGotItNotification();
			Assert.assertEquals(sr.pageTtitle(), "MakeMyTrip");

			logger.info("----Verified title page----");

		}

		catch (Exception e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			Assert.fail();
		}

		logger.info("*****  Finished TC_001_SearchFlight_Test ***** ");

	}

}
