package org.example.paymentforthetour.data;

import com.github.javafaker.Faker;

import java.util.Locale;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {

    //валидная карта APPROVED
    public static MyCard getApprovedCard() {
        return new MyCard("4444444444444441", "01", "24", "Anna Kochergina", "555");
    }

    //валидная карта DECLINED
    public static MyCard getDeclinedCard() {
        return new MyCard("4444444444444442", "01", "24", "Anna Kochergina", "555");
    }

    //пустое заполнение формы
    public static MyCard getEmptyCard() {
        return new MyCard("", "", "", "", "");
    }

    //"Номер карты" недействительный
    public static MyCard getNumberInvalidCard() {
        return new MyCard("4444 4444 4444 4445", "01", "24", "Anna Kochergina", "555");
    }

    //"Номер карты" пустое
    public static MyCard getEmptyCardNumberField() {
        return new MyCard("", "01", "24", "Anna Kochergina", "555");
    }

    //"Номер карты" 15 цифр
    public static MyCard getNumberCard15Symbols() {
        Faker faker = new Faker();
        String number = faker.number().digits(15);
        return new MyCard(number, "01", "24", "Anna Kochergina", "555");
    }




    //"Месяц" пустое
    public static MyCard getEmptyMonthField() {
        return new MyCard("4444444444444441", "", "24", "Anna Kochergina", "555");
    }

    //"Месяц" прошедший (на 1 месяц назад)
    public static MyCard getCardWithThePastMonth() {
        Faker faker = new Faker();
        String month = String.valueOf(LocalDate.now().minusMonths(1).getMonthValue());
        return new MyCard("4444444444444441", month, "24", "Anna Kochergina", "555");
    }

    //"Месяц" 00
    public static MyCard getCardMonthOver00() {
        return new MyCard("4444444444444441", "00", "24", "Anna Kochergina", "555");
    }

    //"Месяц" 1 цифра
    public static MyCard getCardMonth1Symbol() {
        Faker faker = new Faker();
        String month = faker.number().digit();
        return new MyCard("4444444444444441", month, "24", "Anna Kochergina", "555");
    }

    //"Месяц" 13 (более 12 месяцев)
    public static MyCard getCardMonthOver12() {
        return new MyCard("4444444444444441", "13", "24", "Anna Kochergina", "555");
    }


    //"Год" метод сдвига года
    public static String getShiftedYear(int yearCount) {
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("YY"));
    }

    //"Год" пустое
    public static MyCard getEmptyYearField() {
        return new MyCard("4444444444444441", "01", "", "Anna Kochergina", "555");
    }

    //"Год" просроченный (предыдущий год)
    public static MyCard getOverdueYear() {
        Faker faker = new Faker();
        String year = String.valueOf(LocalDate.now().minusYears(1).getYear());
        return new MyCard("4444444444444441", "01", year, "Anna Kochergina", "555");
    }

    //"Год" текущий год + 6 лет
    public static MyCard getCardYearOverThisYearOn6() {
        String year = getShiftedYear(6);
        return new MyCard("4444444444444441", "01", year, "Anna Kochergina", "555");
    }

    //"Год" одна цифра
    public static MyCard getCardYear1Symbol() {
        Faker faker = new Faker();
        String year = faker.number().digit();
        return new MyCard("4444444444444441", "01", year, "Anna Kochergina", "555");
    }

    //"Год" 00
    public static MyCard getCardYearOver00() {
        return new MyCard("4444444444444441", "01", "00", "Anna Kochergina", "555");
    }


    //"Владелец карты" пустое
    public static MyCard getEmptyCardOwnerField() {
        return new MyCard("4444444444444441", "01", "24", "", "555");
    }

    //"Владелец карты" одно слово
    public static MyCard getCardHolder1Word() {
        Faker faker = new Faker();
        String holder = faker.name().firstName();
        return new MyCard("4444444444444441", "01", "24", holder, "555");
    }

    //"Владелец карты" русские буквы
    public static MyCard getCardHolderRussianLetters() {
        Faker faker = new Faker(new Locale("ru"));
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        return new MyCard("4444444444444441", "01", "24", holder, "555");
    }

    //"Владелец карты" цифры
    public static MyCard getCardHolderNumbers() {
        Faker faker = new Faker();
        String holder = faker.number().digits(7) + " " + faker.number().digits(7);
        return new MyCard("4444444444444441", "01", "24", holder, "555");
    }

    //"Владелец карты" спецсимволы, кроме пробела и дефиса
    public static MyCard getCardSpecialSymbols() {
        return new MyCard("4444444444444441", "01", "24", "*&$%", "555");
    }


    //"CVV" пустое
    public static MyCard getEmptyCVVField() {
        return new MyCard("4444444444444441", "01", "24", "Anna Kochergina", "");
    }

    //"CVV" 000
    public static MyCard getEmptyCVV000() {
        return new MyCard("4444444444444441", "01", "24", "Anna Kochergina", "000");
    }

    //"CVV" две цифры
    public static MyCard getEmptyCVV2Symbol() {
        Faker faker = new Faker();
        String cvv = faker.number().digits(2);
        return new MyCard("4444444444444441", "01", "24", "Anna Kochergina", cvv);
    }

    //"CVV" одна цифра
    public static MyCard getEmptyCVV1Symbol() {
        Faker faker = new Faker();
        String cvv = faker.number().digit();
        return new MyCard/**/("4444444444444441", "01", "24", "Anna Kochergina", cvv);
    }
}
