package com.javaacademy.burger;

//4. Написать тесты, которые проверяют работу терминала оплаты:
//Ситуация №1: На оплату поступил бургер, оплата в рублях. Вернулся чек с оплатой в котором указано: 300 рублей, валюта - рубль, товар - бургер.
//Ситуация №2: На оплату поступил бургер, оплата в мозамбикских долларах, вылетела ошибка NotAcceptedCurrencyException

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
