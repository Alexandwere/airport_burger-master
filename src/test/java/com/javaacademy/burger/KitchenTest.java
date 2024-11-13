package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.exception.KitchenHasNoGasException;
import com.javaacademy.burger.exception.UnsupportedDishTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javaacademy.burger.dish.DishType.BURGER;
import static com.javaacademy.burger.dish.DishType.FUAGRA;

public class KitchenTest {

    @Test
    @DisplayName("Бургер - успешное приготовление")
    public void makeBurgerSuccess() {
        Kitchen kitchen = new Kitchen();
        kitchen.cook(BURGER);
        Dish result = kitchen.getCompletedDishes().get(BURGER).element();
        Dish expected = new Dish(BURGER);
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Отсутствие газа - ошибка")
    public void throwsNoGasException() {
        Kitchen kitchen = new Kitchen();
        kitchen.setHasGas(false);
        Assertions.assertThrows(KitchenHasNoGasException.class, () -> kitchen.cook(BURGER));
    }

    @Test
    @DisplayName("Фуагра - ошибка запроса")
    public void throwsUnsupportedDish() {
        Kitchen kitchen = new Kitchen();
        kitchen.setHasGas(true);
        Assertions.assertThrows(UnsupportedDishTypeException.class, () -> kitchen.cook(FUAGRA));
    }
}
