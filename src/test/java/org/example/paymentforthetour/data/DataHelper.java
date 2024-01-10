package org.example.paymentforthetour.data;

import com.github.javafaker.Faker;

public class DataHelper {

    //карта APPROVED
    public static Card getApprovedCard() {
        return new Card("4444444444444441", "01", "25", "Anna Kochergina", "555");
    }

    //карта DECLINED
    public static Card getDeclinedCard() {
        return new Card("4444444444444442", "01", "25", "Anna Kochergina", "555");
    }

    //пустое заполнение формы
    public static Card emptyCard() {
        return new Card("", "", "", "", "");
    }

    //пустое поле "Номер карты" empty card number field
    public static Card emptyCardNumberField() {
        Faker faker = new Faker();

    }
}
