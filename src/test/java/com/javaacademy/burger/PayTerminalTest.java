package com.javaacademy.burger;

import com.javaacademy.burger.exception.NotAcceptedCurrencyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javaacademy.burger.Currency.MOZAMBICAN_DOLLARS;
import static com.javaacademy.burger.Currency.RUB;
import static com.javaacademy.burger.dish.DishType.BURGER;

public class PayTerminalTest {

    @Test
    @DisplayName("Успешная оплата бургера рублями")
    public void payRublesSuccess() {
        PayTerminal payTerminal = new PayTerminal();
        Paycheck result = payTerminal.pay(BURGER, RUB);
        Paycheck expected = new Paycheck(BURGER.getPrice(), RUB, BURGER);
        Assertions.assertEquals(result, expected);
    }

    @Test
    @DisplayName("Оплата мозамбикскими долларами - ошибка")
    public void payMozambicanDollars() {
        PayTerminal payTerminal = new PayTerminal();
        Assertions.assertThrows(NotAcceptedCurrencyException.class, () -> payTerminal.pay(BURGER, MOZAMBICAN_DOLLARS));
    }
}
