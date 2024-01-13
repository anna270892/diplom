package org.example.paymentforthetour.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyCard {
    private String cardNumber;
    private String month;
    private String year;
    private String cardHolder;
    private String cvv;
}
