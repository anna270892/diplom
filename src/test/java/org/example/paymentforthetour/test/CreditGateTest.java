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

public class CreditGateTest {
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
    //3.Валидная покупка кнопкой «Купить» со статусом DECLINED
    @Test
    void buyPositiveAllFieldValidDeclined() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        BuyGate payment = startPage.goToBuyPage();
        MyCard declinedCard = DataHelper.getDeclinedCard();
        payment.inputData(declinedCard);
        payment.waitNotificationApproved();
        assertEquals("DECLINED", SqlHelper.getPaymentStatus());
    }

    //4.Валидная покупка кнопкой «Купить в кредит» статусом DECLINED
    @Test
    void creditPositiveAllFieldValidDeclined() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getDeclinedCard();
        payment.inputData(declinedCard);
        payment.waitNotificationApproved();
        assertEquals("DECLINED", SqlHelper.getCreditRequestStatus());
    }


    //Негативные кейсы:
    //2.Пустая форма покупки + кнопка «Купить в кредит»
    @Test
    void blankPurchaseFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getEmptyCard();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    //4.Оплата недействительной картой (форма по кнопке "Купить в кредит")
    @Test
    void invalidCardFieldFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getNumberInvalidCard();
        payment.inputData(declinedCard);
        payment.errorBankRefusal();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    //6.Оплата картой с пустым номером карты (форма по кнопке "Купить в кредит")
    @Test
    void emptyCardNumberFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getEmptyCardNumberField();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //8.Оплата картой с не валидным номером карты 15 цифр (форма по кнопке "Купить в кредит")
    @Test
    void numberCard15SymbolsFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getNumberCard15Symbols();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //10.Оплата картой с пустым месяцем (форма по кнопке "Купить в кредит")
    @Test
    void emptyMonthFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getEmptyMonthField();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //!невозможно сделать просроченный месяц в 24 году! 12.Оплата картой с просроченным месяцем (форма по кнопке "Купить в кредит")
    @Test
    void cardWithThePastMonthFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getCardWithThePastMonth();
        payment.inputData(declinedCard);
        payment.errorCardHasExpired();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //14.Оплата картой с не валидным месяцем два ноля(форма по кнопке "Купить в кредит")
    @Test
    void cardMonthOver00FormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getCardMonthOver00();
        payment.inputData(declinedCard);
        payment.errorTheCardExpirationDateIsIncorrect();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //16.Оплата картой с не валидным месяцем одна цифра(форма по кнопке "Купить в кредит")
    @Test
    void cardMonth1SymbolFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getCardMonth1Symbol();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //18.Оплата картой с не валидным месяцем который больше 12 (форма по кнопке "Купить")
    @Test
    void cardMonthOver12FormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getCardMonthOver12();
        payment.inputData(declinedCard);
        payment.errorTheCardExpirationDateIsIncorrect();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //20.Оплата картой с пустым годом (форма по кнопке "Купить")
    @Test
    void emptyYearFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getEmptyYearField();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //22.Оплата картой с просроченным годом (форма по кнопке "Купить в кредит")
    @Test
    void overdueYearFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getOverdueYear();
        payment.inputData(declinedCard);
        payment.errorCardHasExpired();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //24.Оплата картой с не валидным годом от текущего года +6 лет (форма по кнопке "Купить в кредит")
    @Test
    void cardYearOverThisYearOn6FormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getCardYearOverThisYearOn6();
        payment.inputData(declinedCard);
        payment.errorTheCardExpirationDateIsIncorrect();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //26.Оплата картой с не валидным годом одна цифра(форма по кнопке "Купить в кредит")
    @Test
    void cardYear1SymbolFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getCardYear1Symbol();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //28.Оплата картой с не валидным годом два ноля(форма по кнопке "Купить в кредит")
    @Test
    void cardYearOver00FormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getCardYearOver00();
        payment.inputData(declinedCard);
        payment.errorCardHasExpired();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //30.Оплата картой с пустым владельцем карты (форма по кнопке "Купить в кредит")
    @Test
    void emptyCardOwnerFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getEmptyCardOwnerField();
        payment.inputData(declinedCard);
        payment.errorRequiredField();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //+БАГ 32.Оплата картой с не валидным владельцем карты(из одного слова, например только имя) (форма по кнопке "Купить в кредит")
    @Test
    void cardHolder1WordFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getCardHolder1Word();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //+БАГ 34.Оплата картой с не валидным владельцем карты(русские буквы) (форма по кнопке "Купить в кредит")
    @Test
    void cardHolderRussianLettersFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getCardHolderRussianLetters();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //+БАГ 36.Оплата картой с не валидным владельцем карты(цифры) (форма по кнопке "Купить в кредит")
    @Test
    void cardHolderNumbersFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getCardHolderNumbers();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //+БАГ 38.Оплата картой с не валидным владельцем карты(спецсимволы, кроме пробела и дефиса) (форма по кнопке "Купить в кредит")
    @Test
    void cardSpecialSymbolsFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getCardSpecialSymbols();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //+БАГ ошибкой подсв и поле "Владелец карты" 40.Оплата картой с пустым CVV (форма по кнопке "Купить в кредит")
    @Test
    void emptyCVVFieldFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getEmptyCVVField();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //+БАГ 42.Оплата картой с не валидным CVV три ноля(форма по кнопке "Купить в кредит")
    @Test
    void emptyCVV000FormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getEmptyCVV000();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //44.Оплата картой с не валидным CVV две цифры(форма по кнопке "Купить в кредит")
    @Test
    void emptyCVV2SymbolFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getEmptyCVV2Symbol();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }


    //46.Оплата картой с не валидным CVV одна цифра(форма по кнопке "Купить в кредит")
    @Test
    void emptyCVV1SymbolFormCreditField() {
        PaymentMethod startPage = open(url, PaymentMethod.class);
        CreditGate payment = startPage.goToCreditPage();
        MyCard declinedCard = DataHelper.getEmptyCVV1Symbol();
        payment.inputData(declinedCard);
        payment.errorWaitingInvalidFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }
}
