package org.example.paymentforthetour.data;

import com.github.javafaker.Faker;

import java.util.Locale;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {

    //валидная карта APPROVED
    public static MyCard getApprovedCard() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", month, year, holder, cvv);
    }

    //валидная карта DECLINED
    public static MyCard getDeclinedCard() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444442", month, year, holder, cvv);
    }

    //генерация случайного месяца от 0 до 9
    public static String getShiftedMonth() {
        int shift = (int) (Math.random() * 10);
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }


    //пустое заполнение формы
    public static MyCard getEmptyCard() {
        return new MyCard("", "", "", "", "");
    }

    //"Номер карты" недействительный
    public static MyCard getNumberInvalidCard() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new MyCard("4444 4444 4444 4445", month, year, holder, cvv);
    }

    //"Номер карты" пустое
    public static MyCard getEmptyCardNumberField() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new MyCard("", month, year, holder, cvv);
    }

    //"Номер карты" 15 цифр
    public static MyCard getNumberCard15Symbols() {
        Faker faker = new Faker();
        String number = faker.number().digits(15);
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new MyCard(number, month, year, holder, cvv);
    }


    //"Месяц" пустое
    public static MyCard getEmptyMonthField() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", "", year, holder, cvv);
    }

    //"Месяц" прошедший (на 1 месяц назад)
    public static MyCard getCardWithThePastMonth() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = String.valueOf(LocalDate.now().minusMonths(1).getMonthValue());
        String year = getShiftedYearMinus(-1);
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", month, year, holder, cvv);
    }

    //"Месяц" 00
    public static MyCard getCardMonthOver00() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", "00", year, holder, cvv);
    }

    //"Месяц" 1 цифра
    public static MyCard getCardMonth1Symbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = faker.number().digit();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", month, year, holder, cvv);
    }

    //"Месяц" 13 (более 12 месяцев)
    public static MyCard getCardMonthOver12() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", "13", year, holder, cvv);
    }


    //"Год" метод сдвига года вперед
    public static String getShiftedYear(int yearCount) {
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("YY"));
    }

    //"Год" метод сдвига года назад
    public static String getShiftedYearMinus(int yearCount) {
        return LocalDate.now().minusYears(yearCount).format(DateTimeFormatter.ofPattern("YY"));
    }

    //"Год" пустое
    public static MyCard getEmptyYearField() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", month, "", holder, cvv);
    }

    //"Год" просроченный (предыдущий год)
    public static MyCard getOverdueYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = String.valueOf(LocalDate.now().minusYears(1).getYear());
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", month, year, holder, cvv);
    }

    //"Год" текущий год + 6 лет
    public static MyCard getCardYearOverThisYearOn6() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(6);
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", month, year, holder, cvv);
    }

    //"Год" одна цифра
    public static MyCard getCardYear1Symbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = faker.number().digit();
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", month, year, holder, cvv);
    }

    //"Год" 00
    public static MyCard getCardYearOver00() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", month, "00", holder, cvv);
    }


    //"Владелец карты" пустое
    public static MyCard getEmptyCardOwnerField() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", month, year, "", cvv);
    }

    //"Владелец карты" одно слово
    public static MyCard getCardHolder1Word() {
        Faker faker = new Faker();
        String holder = faker.name().firstName();
        String month = getShiftedMonth();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", month, year, holder, cvv);
    }

    //"Владелец карты" русские буквы
    public static MyCard getCardHolderRussianLetters() {
        Faker faker = new Faker(new Locale("ru"));
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", month, year, holder, cvv);
    }

    //"Владелец карты" цифры
    public static MyCard getCardHolderNumbers() {
        Faker faker = new Faker();
        String holder = faker.number().digits(7) + " " + faker.number().digits(7);
        String month = getShiftedMonth();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", month, year, holder, cvv);
    }

    //"Владелец карты" спецсимволы, кроме пробела и дефиса
    public static MyCard getCardSpecialSymbols() {
        Faker faker = new Faker();
        String month = getShiftedMonth();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(3);
        return new MyCard("4444444444444441", month, year, "*&$%", cvv);
    }


    //"CVV" пустое
    public static MyCard getEmptyCVVField() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(0);
        return new MyCard("4444444444444441", month, year, holder, "");
    }

    //"CVV" 000
    public static MyCard getEmptyCVV000() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(0);
        return new MyCard("4444444444444441", month, year, holder, "000");
    }

    //"CVV" две цифры +
    public static MyCard getEmptyCVV2Symbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(2);
        return new MyCard("4444444444444441", month, year, holder, cvv);
    }

    //"CVV" одна цифра +
    public static MyCard getEmptyCVV1Symbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(0);
        String cvv = faker.number().digits(1);
        return new MyCard("4444444444444441", month, year, holder, cvv);
    }
}
