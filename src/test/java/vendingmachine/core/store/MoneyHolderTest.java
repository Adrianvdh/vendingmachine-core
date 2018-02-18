package vendingmachine.core.store;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static vendingmachine.core.interaction.Coin.FIVE;
import static vendingmachine.core.interaction.Coin.TWO;
import static vendingmachine.core.interaction.Note.FIFTY;
import static vendingmachine.core.interaction.Note.TEN;

public class MoneyHolderTest {

    @Test
    public void addSomeMoney() throws Exception {
        MoneyHolder moneyHolder = new MoneyHolder();

        moneyHolder.addMoney(TWO, FIVE, TEN, FIFTY);

        Assert.assertThat(moneyHolder.getTotalAmount(), CoreMatchers.is(67.0));
    }

}
