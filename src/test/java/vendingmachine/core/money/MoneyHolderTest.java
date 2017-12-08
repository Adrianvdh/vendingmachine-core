package vendingmachine.core.money;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static vendingmachine.core.interaction.money.Coin.FIVE;
import static vendingmachine.core.interaction.money.Coin.TWO;
import static vendingmachine.core.interaction.money.Note.FIFTY;
import static vendingmachine.core.interaction.money.Note.TEN;

public class MoneyHolderTest {

    @Test
    public void addSomeMoney() throws Exception {
        MoneyHolder moneyHolder = new MoneyHolder();

        moneyHolder.addMoney(TWO, FIVE, TEN, FIFTY);

        Assert.assertThat(moneyHolder.getTotalAmount(), CoreMatchers.is(67.0));
    }

}
