package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pageObject.Constants.CreateOrderButtons.HEADER_CREATE_ORDER_BUTTON;
import static pageObject.Constants.CreateOrderButtons.MIDDLE_CREATE_ORDER_BUTTON;

public class    LandingPage {
        WebDriver driver;

        private final By landingHomePage = By.className("App_App__15LM-"); //Локатор всей страницы лендинга
        private final By faqBlock = By.className("Home_FAQ__3uVm4"); //Блок вопросов (аккордеон)
        private final By headerOrderButton = By.xpath("//button[@class='Button_Button__ra12g']"); //Кнопка заказа в шапке
        private final By middleOrderButton = By.xpath("//div[@class = 'Home_FinishButton__1_cWm']/button[text()='Заказать']"); //Кнопка заказа в нижней части
        private final By cookieAcceptButton =  By.id("rcc-confirm-button");

        public LandingPage (WebDriver driver) {
                this.driver = driver;
        }
        public void waitForLoadLendingPage() {  //Метод ожидания прогрузки страницы
                new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfElementLocated(landingHomePage));
        }

        public LandingPage scrollToFAQ (){             //Метод прокрутки страницы до блока вопросов (аккордеон) //
                WebElement element  = driver.findElement(faqBlock);
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
                return this;
        }
        public LandingPage clickOnAccordionHeader(By accordionHeader) {
                new WebDriverWait(driver, Duration.ofSeconds(3))
                        .until(ExpectedConditions.elementToBeClickable(accordionHeader)).click();
                return this;
        }

        public void waitForLoadAnswerBlockAfterClick (By accordionItemAnswer) { // Метод для ожидания прогрузки раскрывающегося элемента аккодеона
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(accordionItemAnswer).getText() != null
                        && !driver.findElement(accordionItemAnswer).getText().isEmpty()));
        }
        public LandingPage clickOnHeaderOrderButton() { // Нажатие на кнопку "Заказать" в шапке
                driver.findElement(headerOrderButton).click();
                return this;
        }
        public LandingPage clickOnMiddleOrderButton() { //Нажатие на кнопку "Заказать" в середине страницы
                driver.findElement(middleOrderButton).click();
                return this;
        }
        public void clickOnCookieAcceptButton() {
                driver.findElement(cookieAcceptButton).click();
        }
        public void CreateOrderDependsButton (Enum button) {     //Выбор в зависимости от местоположения кнопки создания заказа
                if (button.equals(HEADER_CREATE_ORDER_BUTTON)) {
                        clickOnHeaderOrderButton();
                }
                else if (button.equals(MIDDLE_CREATE_ORDER_BUTTON)) {
                        scrollToFAQ();
                        clickOnMiddleOrderButton();
                }
        }

}
