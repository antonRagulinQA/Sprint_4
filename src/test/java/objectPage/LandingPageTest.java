package objectPage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.LandingPage;

import static org.junit.Assert.assertEquals;
import static pageObject.Constants.LandingPageConstants.*;


@RunWith(Parameterized.class)
public class LandingPageTest {
    private WebDriver driver;
    private final String webSite = "https://qa-scooter.praktikum-services.ru/";
    private final By accordionHeading;
    private final By accordionAnswer;
    private final String expected;

public LandingPageTest (By accordionHeading,By accordionAnswer, String expected) {
    this.accordionHeading = accordionHeading;
    this.accordionAnswer = accordionAnswer;
    this.expected = expected;

}
@Before
public void setUp() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Professional\\WebDriver\\bin\\chromedriver.exe");
//    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.get(webSite);
}

@Parameterized.Parameters
    public static Object [][] getParameters() {
    return new Object[][] {
            {ACCORDION_HEADING_1,ACCORDION_ITEM_ANSWER_1,ACCORDION_TEXT_ANSWER_1},
            {ACCORDION_HEADING_2,ACCORDION_ITEM_ANSWER_2,ACCORDION_TEXT_ANSWER_2},
            {ACCORDION_HEADING_3,ACCORDION_ITEM_ANSWER_3,ACCORDION_TEXT_ANSWER_3},
            {ACCORDION_HEADING_4,ACCORDION_ITEM_ANSWER_4,ACCORDION_TEXT_ANSWER_4},
            {ACCORDION_HEADING_5,ACCORDION_ITEM_ANSWER_5,ACCORDION_TEXT_ANSWER_5},
            {ACCORDION_HEADING_6,ACCORDION_ITEM_ANSWER_6,ACCORDION_TEXT_ANSWER_6},
            {ACCORDION_HEADING_7,ACCORDION_ITEM_ANSWER_7,ACCORDION_TEXT_ANSWER_7},
            {ACCORDION_HEADING_8,ACCORDION_ITEM_ANSWER_8,ACCORDION_TEXT_ANSWER_8},
    };
}
@Test
public void accordionAnswersQuestionsTest () {

    LandingPage yandexScooterPage  = new LandingPage(driver);
    yandexScooterPage.waitForLoadLendingPage();
    yandexScooterPage.scrollToFAQ();
    yandexScooterPage.clickOnAccordionHeader(accordionHeading);
    yandexScooterPage.waitForLoadAnswerBlockAfterClick(accordionAnswer);

    String result = driver.findElement(accordionAnswer).getText();
    assertEquals (expected,result);

}
}
