package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage {
    public final String URL = "https://qa-scooter.praktikum-services.ru/";

    private WebDriver driver;

    //вопрос
    private By importantQuestions = By.xpath(".//div[@class='accordion__button']");
    //ответ
    private By importantAnswers = By.xpath(".//div[@class='accordion__panel']");
    //верхняя кнопка заказать
    private By topButtonOrder = By.xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']");
    //нижняя кнопка заказать
    private By bottomButtonOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    public MainPage(WebDriver webDriver) {

        this.driver = webDriver;
    }


    public WebElement getQuestion(int item) {
        List<WebElement> questions = driver.findElements(importantQuestions);
        return questions.get(item);
    }

    public void clickQueston(WebElement question) {
        question.click();
    }

    public WebElement getAnswer(int item) {
        List<WebElement> answers = driver.findElements(importantAnswers);
        return answers.get(item);
    }

    public String getAnswersText(WebElement answers) {
        return answers.getText();
    }

    public By getTopButtonOrder() {
        return topButtonOrder;
    }

    public By getButtonByType(String buttonType) {
        if (buttonType.equals("top")) {
            return topButtonOrder;
        } else  {
            return bottomButtonOrder;
        }
    }

    public ExpectedCondition<Boolean> isAnswerOpened(WebElement question) {
        return ExpectedConditions.attributeToBe(question, "aria-expanded", "true");
    }

}
