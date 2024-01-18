package org.example.paymentforthetour.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentMethod {
    private SelenideElement heading = $$("h2").find(Condition.text("Путешествие дня"));
    private SelenideElement buyButton = $$("button").find(exactText("Купить"));
    private SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

    public PaymentMethod() {
        heading.shouldBe(visible);
    }

    //кнопка "Купить"
    public BuyGate goToBuyPage() {
        buyButton.click();
        return new BuyGate();
    }

    //кнопка "Купить в кредит"
    public CreditGate goToCreditPage() {
        creditButton.click();
        return new CreditGate();
    }
}