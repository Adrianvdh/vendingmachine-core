package vendingmachine.money;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import vendingmachine.money.MoneyHolder;

import static vendingmachine.interaction.money.Coin.FIVE;
import static vendingmachine.interaction.money.Coin.TWO;
import static vendingmachine.interaction.money.Note.FIFTY;
import static vendingmachine.interaction.money.Note.TEN;

public class MoneyHolderTest {

    @Test
    public void addSomeMoney() throws Exception {
        MoneyHolder moneyHolder = new MoneyHolder();

        moneyHolder.addMoney(TWO, FIVE, TEN, FIFTY);

        Assert.assertThat(moneyHolder.getTotalAmount(), CoreMatchers.is(67.0));
    }
}
