package org.example.paymentforthetour.data;

import com.github.javafaker.Faker;
import java.util.Locale;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {

    //валидная карта APPROVED
    public static Card getApprovedCard() {
        return new Card("4444444444444441", "01", "24", "Anna Kochergina", "555");
    }

    //валидная карта DECLINED
    public static Card getDeclinedCard() {
        return new Card("4444444444444442", "01", "24", "Anna Kochergina", "555");
    }

    //пустое заполнение формы
    public static Card getEmptyCard() {
        return new Card("", "", "", "", "");
    }



    //"Номер карты" пустое
    public static Card getEmptyCardNumberField() {
        return new Card("", "01", "24", "Anna Kochergina", "555");
    }

    //"Номер карты" 15 цифр
    public static Card getNumberCard15Symbols() {
        Faker faker = new Faker();
        String number = faker.number().digits(15);
        return new Card(number, "01", "24", "Anna Kochergina", "555");
    }

    //"Номер карты" недействительный
    public static Card getNumberInvalidCard() {
        return new Card("4444 4444 4444 4445", "01", "24", "Anna Kochergina", "555");
    }




    //"Месяц" метод сдвига года
    public static String getShiftedMonth(){
        int shift = (int) (Math.random() * 10);
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    //"Месяц" пустое
    public static Card getEmptyMonthField() {
        return new Card("4444444444444441", "", "24", "Anna Kochergina", "555");
    }

    //"Месяц" прошедший (на 1 месяц назад)
    public static Card getCardWithThePastMonth() {
        Faker faker = new Faker();
        String month = String.valueOf(LocalDate.now().minusMonths(1).getMonthValue());
        return new Card("4444444444444441", month, "24", "Anna Kochergina", "555");
    }

    //"Месяц" 00
    public static Card getCardMonthOver00() {
        return new Card("4444444444444441", "00", "24", "Anna Kochergina", "555");
    }

    //"Месяц" 1 цифра
    public static Card getCardMonth1Symbol() {
        Faker faker = new Faker();
        String month = faker.number().digit();
        return new Card("4444444444444441", month, "24", "Anna Kochergina", "555");
    }

    //"Месяц" 13 (более 12 месяцев)
    public static Card getCardMonthOver12() {
        return new Card("4444444444444441", "13", "24", "Anna Kochergina", "555");
    }




    //"Год" метод сдвига года
    public static String getShiftedYear(int yearCount){
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("YY"));
    }

    //"Год" пустое
    public static Card getEmptyYearField() {
        return new Card("4444444444444441", "01", "", "Anna Kochergina", "555");
    }

    //"Год" просроченный (предыдущий год)
    public static Card getOverdueYear() {
        Faker faker = new Faker();
        String year = String.valueOf(LocalDate.now().minusYears(1).getYear());
        return new Card("4444444444444441", "01", year, "Anna Kochergina", "555");
    }

    //"Год" текущий год + 6 лет
    public static Card getCardYearOverThisYearOn6() {
        String year = getShiftedYear(6);
        return new Card("4444444444444441", "01", year, "Anna Kochergina", "555");
    }

    //"Год" одна цифра
    public static Card getCardYear1Symbol() {
        Faker faker = new Faker();
        String year = faker.number().digit();
        return new Card("4444444444444441", "01", year, "Anna Kochergina", "555");
    }

    //"Год" 00
    public static Card getCardYearOver00() {
        return new Card("4444444444444441", "01", "00", "Anna Kochergina", "555");
    }



    //"Владелец карты" пустое
    public static Card getEmptyCardOwnerField() {
        return new Card("4444444444444441", "01", "24", "", "555");
    }

    //"Владелец карты" одно слово
    public static Card getCardHolder1Word() {
        Faker faker = new Faker();
        String holder = faker.name().firstName();
        return new Card("4444444444444441", "01", "24", holder, "555");
    }

    //"Владелец карты" русские буквы
    public static Card getCardHolderRussianLetters() {
        Faker faker = new Faker(new Locale("ru"));
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        return new Card("4444444444444441", "01", "24", holder, "555");
    }

    //"Владелец карты" цифры
    public static Card getCardHolderNumbers() {
        Faker faker = new Faker();
        String holder = faker.number().digits(7) + " " + faker.number().digits(7);
        return new Card("4444444444444441", "01", "24", holder, "555");
    }

    //"Владелец карты" спецсимволы, кроме пробела и дефиса
    public static Card getCardSpecialSymbols() {
        return new Card("4444444444444441", "01", "24", "*&$%", "555");
    }



    //"CVV" пустое
    public static Card getEmptyCVVField() {
        return new Card("4444444444444441", "01", "24", "Anna Kochergina", "");
    }

    //"CVV" 000
    public static Card getEmptyCVV000() {
        return new Card("4444444444444441", "01", "24", "Anna Kochergina", "000");
    }

    //"CVV" две цифры
    public static Card getEmptyCVV2Symbol() {
        Faker faker = new Faker();
        String cvv = faker.number().digits(2);
        return new Card("4444444444444441", "01", "24", "Anna Kochergina", cvv);
    }

    //"CVV" одна цифра
    public static Card getEmptyCVV1Symbol() {
        Faker faker = new Faker();
        String cvv = faker.number().digit();
        return new Card("4444444444444441", "01", "24", "Anna Kochergina", cvv);
    }



}
