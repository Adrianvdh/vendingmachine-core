package vendingmachine.core;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import vendingmachine.core.interaction.SpecialOffer;
import vendingmachine.core.interaction.special.CheapestItemFree;
import vendingmachine.core.item.Item;
import vendingmachine.core.item.selection.Chocolate;
import vendingmachine.core.item.selection.Coke;

public class SpecialOfferTest {

    @Test
    public void cheapestOneFreeStrategy() throws Exception {
        CheapestItemFree cheapestItemFree = new CheapestItemFree();

        cheapestItemFree.withEligibleItems(new Coke(), new Chocolate());

        Item cheapestItem = cheapestItemFree.claimSpecial();
        Assert.assertThat(cheapestItem, Matchers.is(cheapestItem));
    }


    @Test
    public void testSpecialOfferTotalAmount_CheapestItemFree() throws Exception {

        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.withItems(new Coke(), new Chocolate());

        specialOffer.setOfferPolicy(new CheapestItemFree());

        Assert.assertThat(new Coke(), Matchers.is(specialOffer.collectOrder().getItem()));
    }
}
