package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AboutRenter {
    WebDriver driver;
    private final By pageAboutRental = By.className("App_App__15LM-"); // Локатор всей страницы
    private final By firstName = By.xpath("//input[@placeholder = '* Имя']"); // Поле имя
    private final By secondName = By.xpath(".//input[@placeholder= '* Фамилия']"); //Поле фамилия
    private final By address = By.xpath(".//input[@placeholder= '* Адрес: куда привезти заказ']"); //Поле адрес
    private final By metroInput =  By.xpath("//input[@placeholder = '* Станция метро']"); //Поле станция метро
    private final By metroStationList = By.className("select-search__value"); // Список станций
    private final By telephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']"); // Поле номер телефона
    private final By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button"); // Кнопка далее
    public AboutRenter (WebDriver driver) {
        this.driver = driver;
    }
    public void waitForLoadAboutRentalPageLoad() {  //Метод ожидания прогрузки страницы
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfElementLocated(pageAboutRental));
    }
    public AboutRenter inputFirstName (String inputName) { //Вводим имя
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(inputName);
        return this;
    }
    public AboutRenter inputSecondName (String inputSecondName) { //Водим Фамилию
        driver.findElement(secondName).clear();
        driver.findElement(secondName).sendKeys(inputSecondName);
        return this;
    }
    public AboutRenter inputAddress (String newAddress) { //Вводим адрес
        driver.findElement(address).clear();
        driver.findElement(address).sendKeys(newAddress);
        return this;
    }
    public AboutRenter inputAndSelectMetroStation (String newAddress) { //Вводим станцию метро текстом и выбираем
        driver.findElement(metroInput).clear();
        driver.findElement(metroInput).sendKeys(newAddress);
        new WebDriverWait( driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(metroStationList));
        driver.findElement(By.className("select-search__row")).click();
        return this;
    }
    public AboutRenter inputPhoneNumber(String inputNewNumber) { // Вводим номер телефона
        driver.findElement(telephone).clear();
        driver.findElement(telephone).sendKeys(inputNewNumber);
        return this;
    }
    public void clickOnNextButton() { // Нажимаем на кнопку далее
        driver.findElement(nextButton).click();

    }

}
