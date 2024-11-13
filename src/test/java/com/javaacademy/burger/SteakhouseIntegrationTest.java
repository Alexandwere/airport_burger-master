package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javaacademy.burger.Currency.RUB;
import static com.javaacademy.burger.dish.DishType.BURGER;

public class SteakhouseIntegrationTest {

    @Test
    @DisplayName("Успешная работа всего ресторана")
    public void successWorkRestaurant() {
        Steakhouse steakhouse = new Steakhouse(new Waitress(), new Kitchen(), new PayTerminal());
        Paycheck resultPaycheck = steakhouse.makeOrder(BURGER, RUB);
        Paycheck expectedPaycheck = new Paycheck(BURGER.getPrice(), RUB, BURGER);
        Assertions.assertEquals(resultPaycheck, expectedPaycheck);

        Dish resultDish = steakhouse.takeOrder(resultPaycheck);
        Dish expectedDish = new Dish(BURGER);
        Assertions.assertEquals(resultDish, expectedDish);
    }
}
