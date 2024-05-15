package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pageObject.Constants.ColourOfScooters.BLACK;
import static pageObject.Constants.ColourOfScooters.GREY;

public class AboutScooter {
WebDriver driver;

private final By pageAboutScooter = By.className("App_App__15LM-"); //Заголовок страницы
private final By deliveryDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']"); // Поле когда привезти самокат
private final By rentalPeriod = By.xpath(".//span[@class='Dropdown-arrow']");// Поле срок аренды
private final By rentalPeriodDropDown = By.className("Dropdown-menu");//Выпадающий список поля срок аренды
private final By blackScooter = By.id("black"); // черный цвет скутера
private final By greyScooter = By.id("grey"); // серый цвет скутера
private final By courierComment = By.xpath(".//input[@placeholder='Комментарий для курьера']"); //комментарий для курьера
private final By nextButton = By.xpath("//div[@class = 'Order_Buttons__1xGrp']/button[text()='Заказать']"); // Кнопка заказать

public AboutScooter (WebDriver driver) {
    this.driver = driver;
}

public void waitAboutScooterPageLoad() {  //Ожидание загрузки страницы
    new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfElementLocated(pageAboutScooter));
}
public AboutScooter inputDeliveryDate(String newDate) {   // Ввод даты доставки
        driver.findElement(deliveryDate).clear();
        driver.findElement(deliveryDate).sendKeys(newDate);
        return this;
}
public AboutScooter inputRentalPeriod(String newPeriod) { //Выбор количество суток
        driver.findElement(rentalPeriod).click();
            new WebDriverWait(driver, Duration.ofSeconds(3))
            .until(ExpectedConditions.elementToBeClickable((rentalPeriodDropDown))).click();

//    WebElement numberOfDays = driver.findElement(By.xpath(String.format("//div[@role='option' and text()='%s']", newPeriod)));
//    numberOfDays.click();
    return this;
}
    public AboutScooter colourSelect(Enum colour) {
        if ( colour.equals(BLACK) ) {
            driver.findElement(blackScooter).click();
        } else if ( colour.equals(GREY) ) {
            driver.findElement(greyScooter).click();
        }
        return this;
    }

public AboutScooter inputCourierComment(String newCourierComment) {
    driver.findElement(courierComment).sendKeys(newCourierComment);
    return this;
}
public void clickOnNextButton(){
    driver.findElement(nextButton).click();
}

}
