package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;

    //окно заказа форма 1
    private By orderWindowFirst = By.xpath(".//div[@class='Order_Header__BZXOb' and text()='Для кого самокат']"); //By.className("Order_Content__bmtHS");
    //поле имя
    private By inputName = By.cssSelector("input[placeholder='* Имя']");
    //поле фамилия
    private By inputLastName = By.cssSelector("input[placeholder='* Фамилия']");
    //поле адрес
    private By inputAdress = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    //поле станция метро
    private By inputMetroStation = By.xpath(".//input[@class='select-search__input' and  @placeholder='* Станция метро']");
    //элемент выпадающего списка метро
    private By selectMetroStation = By.xpath(".//div[@class='select-search__select']");
    //телефон
    private By inputPhoneNumber = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");
    //кнопка далее
    private By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");
    //окно заказа форма 2
    private By orderWindowSecond = By.xpath(".//div[@class='Order_Header__BZXOb' and text()='Про аренду']");  //By.className("Order_Content__bmtHS")
    //поле дата
    private By inputDateOfOrder = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    //поле срок аренды
    private By rentDuration = By.cssSelector(".Dropdown-control");
    //элемент срока аренды на сутки
    private By rentDurationOneDay = By.xpath(".//div[text()='сутки']");
    //чек-бокс черный цвет самоката
    private By blackColorScooter = By.id("black");
    //поле комментарий курьеру
    private By textFoCourier = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and  @placeholder='Комментарий для курьера']");
    //окно подтверждения заказа
    private By orderWindowAccept = By.className("Order_Modal__YZ-d3");
    //кнопка подтверждения заказа "Заказать"
    private By buttonNextAcceptOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //кнопка подтверждения заказа "Да"
    private By buttonNextAcceptYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //окно успешного заказа
    //private By orderWindowSucces = By.className("Order_Modal__YZ-d3");
    //заголовк окна успешного заказа
    private By headerOrderWindowSucces = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver webDriver) {
        this.driver = webDriver;
    }
    public By getOrderWindowFirst() {
        return orderWindowFirst;
    }

    public By getInputName() {
        return inputName;
    }

    public By getInputLastName() {
        return inputLastName;
    }

    public By getInputAdress() {
        return inputAdress;
    }

    public By getInputMetroStation() {
        return inputMetroStation;
    }

    public By getInputPhoneNumber() {
        return inputPhoneNumber;
    }

    public By getButtonNext() {
        return buttonNext;
    }

    public By getInputDateOfOrder() {
        return inputDateOfOrder;
    }

    public By getRentDuration() {
        return rentDuration;
    }

    public By getRentDurationOneDay() {
        return rentDurationOneDay;
    }

    public By getBlackColorScooter() {
        return blackColorScooter;
    }

    public By getTextFoCourier() {
        return textFoCourier;
    }

    public By getOrderWindowAccept() {
        return orderWindowAccept;
    }

    public By getButtonNextAcceptYes() {
        return buttonNextAcceptYes;
    }

    public By getHeaderOrderWindowSucces() {
        return headerOrderWindowSucces;
    }

    public By getOrderWindowSecond() {
        return orderWindowSecond;
    }

    public By getButtonNextAcceptOrder() {
        return buttonNextAcceptOrder;
    }

    public By getSelectMetroStation() {
        return selectMetroStation;
    }
}
