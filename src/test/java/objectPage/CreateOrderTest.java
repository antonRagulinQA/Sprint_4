package objectPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObject.AboutRenter;
import pageObject.AboutScooter;
import pageObject.LandingPage;
import pageObject.OrderCreatedPopUp;

import static org.junit.Assert.assertTrue;
import static pageObject.Constants.CreateOrderButtons.*;
import static pageObject.Constants.ColourOfScooters.*;
@RunWith(Parameterized.class)
public class CreateOrderTest {

    private WebDriver driver;

private final String webSite = "https://qa-scooter.praktikum-services.ru/";
private final Enum button;
private final String name;
private final String secondName;
private final String address;
private final String metroStation;
private final String telephoneNumber;
private final String date;
private final String rentalPeriod;
private final Enum color;
private final String comment;
private final String afterCreateOrderExpectedHeader = "Заказ оформлен";
public CreateOrderTest(Enum button, String name, String secondName, String address, String metroStation, String telephoneNumber, String date, String rentalPeriod, Enum color, String comment) {

    this.button = button;
    this.name = name;
    this.secondName = secondName;
    this.address = address;
    this.metroStation = metroStation;
    this.telephoneNumber = telephoneNumber;
    this.date = date;
    this.rentalPeriod = rentalPeriod;
    this.color = color;
    this.comment = comment;
}
@Parameterized.Parameters
    public static Object[][] getParameters() {
            return new Object[][] {
                    {HEADER_CREATE_ORDER_BUTTON, "Кристофер","Робен","Адрес1","Новокузнецкая","79111111111","15.05.2024","четверо суток",GREY,"Это комментарий из теста 1"},
                    {MIDDLE_CREATE_ORDER_BUTTON, "Винни","Пух","Адрес2","Электрозаводская","79222222222","16.05.2024",BLACK,"сутки","Это комментарий из теста 2"}
            };

    }
    @Before
    public void setUp() {
   WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    driver.get(webSite);
    }
    @After
    public void tearDown() {
    driver.quit();
    }
    @Test
    public void testOfOrderCreation(){
    LandingPage yandexScooterPage = new LandingPage(driver);
            yandexScooterPage.clickOnCookieAcceptButton();
            yandexScooterPage.waitForLoadLendingPage();
            yandexScooterPage.CreateOrderDependsButton(button);


            AboutRenter aboutRenterPage = new AboutRenter(driver);

            aboutRenterPage.waitForLoadAboutRentalPageLoad();
            aboutRenterPage.inputFirstName(name);
            aboutRenterPage.inputSecondName(secondName);
            aboutRenterPage.inputAddress(address);
            aboutRenterPage.inputAndSelectMetroStation(metroStation);
            aboutRenterPage.inputPhoneNumber(telephoneNumber);
            aboutRenterPage.clickOnNextButton();

        AboutScooter aboutScooterPage = new AboutScooter(driver);

        aboutScooterPage.waitAboutScooterPageLoad();
        aboutScooterPage.inputDeliveryDate(date);
        aboutScooterPage.inputRentalPeriod(rentalPeriod);
        aboutScooterPage.colourSelect(color);
        aboutScooterPage.inputCourierComment(comment);
        aboutScooterPage.clickOnNextButton();

        OrderCreatedPopUp orderCreatedPopUp = new OrderCreatedPopUp(driver);
        orderCreatedPopUp.clickOnButtonYes();

        assertTrue(orderCreatedPopUp.orderCreatedHeaderText().contains(afterCreateOrderExpectedHeader));
    }
}
