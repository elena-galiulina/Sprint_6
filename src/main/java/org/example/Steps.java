package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Steps {

    private final WebDriver webDriver;

    public Steps (WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public Steps click(By element) {
        webDriver.findElement(element).click();
        return this;
    }

    public Steps input(By element, String text) {
        webDriver.findElement(element).sendKeys(text);
        return this;
    }

    public boolean isShown(By element) {
        return !webDriver.findElements(element).isEmpty();
    }

    public String getText(By element) {

        return webDriver.findElement(element).getText();
    }

    public Steps closeCookieConsent() {
        // Найдите и кликните на кнопку принятия cookie
        By cookieConsentButton = By.xpath("//button[contains(text(), 'да все привыкли') or contains(text(), 'Принять')]");
        webDriver.findElement(cookieConsentButton).click();

        // Добавьте ожидание, чтобы убедиться, что панель исчезла
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(
                ExpectedConditions.invisibilityOfElementLocated(By.className("App_CookieConsent__1yUIN"))
        );

        return this;
    }

}
