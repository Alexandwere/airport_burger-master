package com.javaacademy.burger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.javaacademy.burger.dish.DishType.BURGER;
import static com.javaacademy.burger.dish.DishType.FUAGRA;

public class WaitressTest {

    private final Kitchen mockKitchen = Mockito.mock(Kitchen.class);
    private final Waitress waitress = new Waitress();

    @Test
    @DisplayName("Успешный заказ бургера")
    public void acceptOrder() {
        boolean result = waitress.giveOrderToKitchen(BURGER, mockKitchen);
        boolean expected = true;
        Assertions.assertEquals(result, expected);
    }

    @Test
    @DisplayName("Заказ фуагры - отказ")
    public void refuseOrder() {
        boolean result = waitress.giveOrderToKitchen(FUAGRA, mockKitchen);
        boolean expected = false;
        Assertions.assertEquals(result, expected);
    }
}
