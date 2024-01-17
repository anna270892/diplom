package org.example.paymentforthetour.test;

import com.codeborne.selenide.logevents.SelenideLogger;


import io.qameta.allure.selenide.AllureSelenide;
import org.example.paymentforthetour.data.DataHelper;
import org.example.paymentforthetour.data.MyCard;
import org.example.paymentforthetour.data.SqlHelper;
import org.example.paymentforthetour.page.BuyGate;
import org.example.paymentforthetour.page.CreditGate;
import org.example.paymentforthetour.page.PaymentMethod;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyGateTest {
    public static String url = System.getProperty("sut.url");

    @BeforeEach
    public void openPage() {
        open(url);
    }

    @AfterEach
    public void cleanBase() {
        SqlHelper.clearDB();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    void buyPositiveAllFieldValidApproved() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getApprovedCard();
        payment.inputData(approvedCard);
        payment.waitNotificationApproved();
        assertEquals("APPROVED", SqlHelper.getPaymentStatus());
    }

    @Test
    void buyPositiveAllFieldValidDeclined() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getDeclinedCard();
        payment.inputData(declinedCard);
        payment.waitNotificationApproved();
        assertEquals("DECLINED", SqlHelper.getCreditRequestStatus());
    }
}