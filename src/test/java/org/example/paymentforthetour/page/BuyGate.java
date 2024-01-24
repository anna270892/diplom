package org.example.paymentforthetour.page;

import com.codeborne.selenide.SelenideElement;
import org.example.paymentforthetour.data.MyCard;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BuyGate {
    private SelenideElement heading = $$("h3").find(exactText("Оплата по карте")); //+
    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$("[class=\"input__control\"]"); //+
    private SelenideElement monthField = $(byText("Месяц")).parent().$("[class=\"input__control\"]"); //+
    private SelenideElement yearField = $(byText("Год")).parent().$("[class=\"input__control\"]"); //+
    private SelenideElement cardHolderField = $(byText("Владелец")).parent().$("[class=\"input__control\"]"); //+
    private SelenideElement cvvField = $(byText("CVC/CVV")).parent().$("[class=\"input__control\"]"); //+
    private SelenideElement approvedOperation = $(byText("Операция одобрена Банком.")); //+
    private SelenideElement failureOperation = $(byText("Ошибка! Банк отказал в проведении операции.")); //+
    private SelenideElement wrongFormatError = $(byText("Неверный формат")); //+
    private SelenideElement cardExpirationDateError = $(byText("Неверно указан срок действия карты")); //+
    private SelenideElement cardExpiredError = $(byText("Истёк срок действия карты")); //+
    private SelenideElement requiredFieldError = $(byText("Поле обязательно для заполнения")); //+
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить")); //+

    public BuyGate() {
        heading.shouldBe(visible);
    }

    public void inputData(MyCard card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        cardHolderField.setValue(card.getCardHolder());
        cvvField.setValue(card.getCvv());
        continueButton.click();
    }

    //ожидание элемента "Операция одобрена Банком." +
    public void waitNotificationApproved() {
        approvedOperation.shouldBe(visible, Duration.ofSeconds(15));
    }

    //элемент "Неверный формат" +
    public void getWaitingInvalidFormat() {
        wrongFormatError.shouldBe(visible);
    }

    //элемент "Поле обязательно для заполнения" +
    public void getRequiredFieldError() {
        requiredFieldError.shouldBe(visible);
    }


    //элемент "Неверно указан срок действия карты" +
    public void getCardExpirationDateError() {
        cardExpirationDateError.shouldBe(visible);
    }

    //элемент "Истёк срок действия карты" +
    public void getCardHasExpiredError() {
        cardExpiredError.shouldBe(visible);
    }

    //ожидание элемента "Ошибка! Банк отказал в проведении операции." +
    public void errorBankRefusal() {
        failureOperation.shouldBe(visible, Duration.ofSeconds(15));
    }
}