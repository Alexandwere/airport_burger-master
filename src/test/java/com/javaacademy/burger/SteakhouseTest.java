package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static com.javaacademy.burger.Currency.MOZAMBICAN_DOLLARS;
import static com.javaacademy.burger.Currency.RUB;
import static com.javaacademy.burger.Currency.USD;
import static com.javaacademy.burger.dish.DishType.FRIED_POTATO;
import static com.javaacademy.burger.dish.DishType.RIBS;

public class SteakhouseTest {

    @Test
    @DisplayName("Работа ресторана в обход терминала")
    public void successWork() {
        PayTerminal spyTerminal = Mockito.spy(PayTerminal.class);
        Steakhouse steakhouse = new Steakhouse(new Waitress(), new Kitchen(), spyTerminal);

        Paycheck resultPaycheck = steakhouse.makeOrder(RIBS, RUB);
        Paycheck expectedPaycheck = new Paycheck(RIBS.getPrice(), RUB, RIBS);
        Assertions.assertEquals(expectedPaycheck, resultPaycheck);

        Dish resultDish = steakhouse.takeOrder(resultPaycheck);
        Dish expectedDish = new Dish(RIBS);
        Assertions.assertEquals(expectedDish, resultDish);
    }

    @Test
    @DisplayName("Проверка прохождения заказов через терминал")
    public void terminalWork() {
        Kitchen kitchen = Mockito.mock(Kitchen.class);
        Waitress waitress = Mockito.spy(Waitress.class);
        PayTerminal payTerminal = new PayTerminal();

        Mockito.doReturn(true).when(waitress).giveOrderToKitchen(Mockito.any(), Mockito.any());
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminal);
        Steakhouse spySteakhouse = Mockito.spy(steakhouse);

        Paycheck paycheck1 = spySteakhouse.makeOrder(RIBS, RUB);
        Paycheck expected1 = new Paycheck(RIBS.getPrice(), RUB, RIBS);
        Assertions.assertEquals(expected1, paycheck1);

        Mockito.doReturn(new Paycheck(BigDecimal.ONE, USD, FRIED_POTATO))
                .when(spySteakhouse).makeOrder(FRIED_POTATO, USD);
        Paycheck paycheck2 = spySteakhouse.makeOrder(FRIED_POTATO, USD);
        Paycheck expected2 = new Paycheck(BigDecimal.ONE, USD, FRIED_POTATO);
        Assertions.assertEquals(expected2, paycheck2);

        Mockito.doReturn(new Paycheck(BigDecimal.ONE, MOZAMBICAN_DOLLARS, FRIED_POTATO))
                .when(spySteakhouse).makeOrder(FRIED_POTATO, MOZAMBICAN_DOLLARS);
        Paycheck paycheck3 = spySteakhouse.makeOrder(FRIED_POTATO, MOZAMBICAN_DOLLARS);
        Paycheck expected3 = new Paycheck(BigDecimal.ONE, MOZAMBICAN_DOLLARS, FRIED_POTATO);
        Assertions.assertEquals(expected3, paycheck3);
    }
}
