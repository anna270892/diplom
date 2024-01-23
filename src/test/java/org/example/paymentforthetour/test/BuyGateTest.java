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


    //Позитивные кейсы:
    //1.Валидная покупка кнопкой «Купить» со статусом APPROVED
    @Test
    void buyPositiveAllFieldValidApproved() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getApprovedCard();
        payment.inputData(approvedCard);
        payment.waitNotificationApproved();
        assertEquals("APPROVED", SqlHelper.getPaymentStatus());
    }

    //2.Валидная покупка кнопкой «Купить в кредит» статусом APPROVED
    @Test
    void creditPositiveAllFieldValidApproved() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard approvedCard = DataHelper.getApprovedCard();
        payment.inputData(approvedCard);
        payment.waitNotificationApproved();
        assertEquals("APPROVED", SqlHelper.getCreditRequestStatus());
    }

    //Негативные кейсы:
    //1.Пустая форма покупки + кнопка «Купить»
    @Test
    void blankPurchaseFormField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getEmptyCard();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //3.Оплата недействительной картой (форма по кнопке "Купить")
    @Test
    void invalidCardField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getNumberInvalidCard();
        payment.inputData(approvedCard);
        payment.errorBankRefusal();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //5.Оплата картой с пустым номером карты (форма по кнопке "Купить")
    @Test
    void emptyCardNumberField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getEmptyCardNumberField();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //7.Оплата картой с не валидным номером карты 15 цифр (форма по кнопке "Купить")
    @Test
    void numberCard15SymbolsField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getNumberCard15Symbols();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //9.Оплата картой с пустым месяцем (форма по кнопке "Купить")
    @Test
    void emptyMonthField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getEmptyMonthField();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //!невозможно сделать просроченный месяц в 24 году! 11.Оплата картой с просроченным месяцем (форма по кнопке "Купить")
    @Test
    void cardWithThePastMonthField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getCardWithThePastMonth();
        payment.inputData(approvedCard);
        payment.getCardHasExpiredError();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //13.Оплата картой с не валидным месяцем два ноля (форма по кнопке "Купить")
    @Test
    void cardMonthOver00Field() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getCardMonthOver00();
        payment.inputData(approvedCard);
        payment.getCardExpirationDateError();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //15.Оплата картой с не валидным месяцем одна цифра (форма по кнопке "Купить")
    @Test
    void cardMonth1SymbolField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getCardMonth1Symbol();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //17.Оплата картой с не валидным месяцем который больше 12 (форма по кнопке "Купить")
    @Test
    void cardMonthOver12Field() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getCardMonthOver12();
        payment.inputData(approvedCard);
        payment.getCardExpirationDateError();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //19.Оплата картой с пустым годом (форма по кнопке "Купить")
    @Test
    void emptyYearField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getEmptyYearField();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //21.Оплата картой с просроченным годом (форма по кнопке "Купить")
    @Test
    void overdueYearField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getOverdueYear();
        payment.inputData(approvedCard);
        payment.getCardHasExpiredError();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //23.Оплата картой с не валидным годом от текущего года +6 лет (форма по кнопке "Купить")
    @Test
    void cardYearOverThisYearOn6Field() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getCardYearOverThisYearOn6();
        payment.inputData(approvedCard);
        payment.getCardExpirationDateError();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //25.Оплата картой с не валидным годом одна цифра(форма по кнопке "Купить")
    @Test
    void cardYear1SymbolField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getCardYear1Symbol();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //27.Оплата картой с не валидным годом два ноля(форма по кнопке "Купить")
    @Test
    void cardYearOver00Field() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getCardYearOver00();
        payment.inputData(approvedCard);
        payment.getCardHasExpiredError();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //29.Оплата картой с пустым владельцем карты (форма по кнопке "Купить")
    @Test
    void emptyCardOwnerField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getEmptyCardOwnerField();
        payment.inputData(approvedCard);
        payment.getRequiredFieldError();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //+БАГ 31.Оплата картой с не валидным владельцем карты(из одного слова, например только имя) (форма по кнопке "Купить")
    @Test
    void cardHolder1WordField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getCardHolder1Word();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //+БАГ 33.Оплата картой с не валидным владельцем карты(русские буквы) (форма по кнопке "Купить")
    @Test
    void cardHolderRussianLettersField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getCardHolderRussianLetters();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //+БАГ 35.Оплата картой с не валидным владельцем карты(цифры) (форма по кнопке "Купить")
    @Test
    void cardHolderNumbersField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getCardHolderNumbers();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //+БАГ 37.Оплата картой с не валидным владельцем карты(спецсимволы, кроме пробела и дефиса) (форма по кнопке "Купить")
    @Test
    void cardSpecialSymbolsField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getCardSpecialSymbols();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //+БАГ ошибкой подсв и поле "Владелец карты" 39.Оплата картой с пустым CVV (форма по кнопке "Купить")
    @Test
    void emptyCVVField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getEmptyCVVField();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //+БАГ пропускает с нулями 41.Оплата картой с не валидным CVV три ноля(форма по кнопке "Купить")
    @Test
    void emptyCVV000Field() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getEmptyCVV000();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //43.Оплата картой с не валидным CVV две цифры(форма по кнопке "Купить")
    @Test
    void emptyCVV2SymbolField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getEmptyCVV2Symbol();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //45.Оплата картой с не валидным CVV одна цифра(форма по кнопке "Купить")
    @Test
    void emptyCVV1SymbolField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard approvedCard = DataHelper.getEmptyCVV1Symbol();
        payment.inputData(approvedCard);
        payment.getWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }
}