import org.example.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTests {
    private WebDriver driver;
    private MainPage mainPage;
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        mainPage = new MainPage(driver);
        driver.get(mainPage.URL);
    }

    @ParameterizedTest
    @MethodSource("credentialsProvider")
    void accordanceQuestionsAnswers(int item, String answerText) {

        //получили элемент-вопрос
        WebElement question = mainPage.getQuestion(item);
        //прокрутили до элемента-вопроса
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", question);
        //кликнули по вопросу
        mainPage.clickQueston(question);
        //получили элемент-ответ
        WebElement answer = mainPage.getAnswer(item);
        //подождали открытие элемента-ответа
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(mainPage.isAnswerOpened(question));
        //получили текст ответа
        String actualResult = mainPage.getAnswersText(answer);
        assertEquals(answerText, actualResult);
    }
    private static Stream<Arguments> credentialsProvider() {
        return Stream.of(
                Arguments.of(0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                Arguments.of(1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                Arguments.of(2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                Arguments.of(3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                Arguments.of(4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                Arguments.of(5, "Самокат приезжает к вам с полной зарядкой! Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                Arguments.of(6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                Arguments.of(7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        );
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
