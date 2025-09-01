import org.example.MainPage;
import org.example.OrderPage;
import org.example.Steps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class OrderPageTests {

    private WebDriver driver;
    private Steps step;
    private OrderPage orderPage;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
       // driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        mainPage = new MainPage(driver);
        driver.get(mainPage.URL);

        step = new Steps(driver);
        step.closeCookieConsent();
        orderPage = new OrderPage(driver);
        mainPage = new MainPage(driver);
    }

    @ParameterizedTest
    @MethodSource("orderButtons")
    void acsessibilityButtonOrder(String buttonType) {
        // получаем селектор кнопки
        By buttonSelector = mainPage.getButtonByType(buttonType);
        // переходим к кнопке
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView();", driver.findElement(buttonSelector));
        // кликаем на кнопку
        step.click(buttonSelector);
        // ожидаем
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(
                webDriver -> step.isShown(orderPage.getOrderWindowFirst())
        );
        // проверяем открылось ли окно
        assertTrue(step.isShown(orderPage.getOrderWindowFirst()),
                "Окно заказа должно отображаться после клика на кнопку");
    }

    private static Stream<Arguments> orderButtons() {
        return Stream.of(
                Arguments.of("top"),
                Arguments.of("bottom"));
    }

    @ParameterizedTest
    @MethodSource("userData")
    void confirmOrder(String name, String lastName, String adress, String phone,
                        String date, String textForCourier) {
        step.click(mainPage.getTopButtonOrder());
        step.input(orderPage.getInputName(), name);
        step.input(orderPage.getInputLastName(), lastName);
        step.input(orderPage.getInputAdress(), adress);
        step.click(orderPage.getInputMetroStation());

        step.click(orderPage.getSelectMetroStation());
        step.input(orderPage.getInputPhoneNumber(), phone);
        step.click(orderPage.getButtonNext());
        assertTrue(step.isShown(orderPage.getOrderWindowSecond()),
                "Окно заказа 2 должно отображаться после клика на кнопку");
        step.input(orderPage.getInputDateOfOrder(), date);
        step.click(orderPage.getOrderWindowSecond());
        step.click(orderPage.getRentDuration());
        step.click(orderPage.getRentDurationOneDay());
        step.click(orderPage.getBlackColorScooter());
        step.input(orderPage.getTextFoCourier(), textForCourier);
        step.click(orderPage.getButtonNextAcceptOrder());
        assertTrue(step.isShown(orderPage.getOrderWindowAccept()),
                "Окно подтверждения заказа должно отображаться после клика на кнопку");
        step.click(orderPage.getButtonNextAcceptYes());
        assertTrue(step.getText(orderPage.getHeaderOrderWindowSucces()).contains("Заказ оформлен"), "Сообщение 'Заказ оформлен' не получено");


    }

    private static Stream<Arguments> userData() {
        return Stream.of(
                Arguments.of("Иван", "Тестовый","г.Москва", "89824806001", "07.09.2025\n", "Позвоните за 30 минут до доставки"),
                Arguments.of("Петя", "Василюк","г.Москва", "89519496660", "10.09.2025\n", "Слава Богу-Императору"));
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
