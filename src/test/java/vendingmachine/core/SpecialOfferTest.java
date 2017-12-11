package vendingmachine.core;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import vendingmachine.core.interaction.SpecialOffer;
import vendingmachine.core.interaction.special.BuyOneGetOneFree;
import vendingmachine.core.interaction.special.CheapestItemFree;
import vendingmachine.core.interaction.special.OfferPolicy;
import vendingmachine.core.item.Item;
import vendingmachine.core.item.selection.Chocolate;
import vendingmachine.core.item.selection.Coke;

import java.util.Collection;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class SpecialOfferTest {

    @Test
    public void testSpecialOfferTotalAmount_CheapestItemFree() throws Exception {

        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.withItems(new Coke(), new Chocolate());

        specialOffer.setOfferPolicy(new CheapestItemFree());

        Item cheapestItem = specialOffer.collectOrder().getItem();
        assertThat(new Coke(), is(cheapestItem));
    }

    @Test
    public void cheapestOneFreeStrategy() throws Exception {
        OfferPolicy cheapestItemFree = new CheapestItemFree();

        cheapestItemFree.registerEligibleItemsFromSystem(new Coke(), new Chocolate());

        Item cheapestItem = cheapestItemFree.claimSpecial();
        assertThat(new Coke(), is(cheapestItem));
    }


    @Ignore
    @Test
    public void buyOneGetOneFreeStrategy() throws Exception {
        BuyOneGetOneFree buyOneGetOneFree = new BuyOneGetOneFree();
        buyOneGetOneFree.registerEligibleItemsFromSystem(new Coke(), new Chocolate());

        buyOneGetOneFree.selectItem(new Coke(), new Coke());

        Collection<Item> items = buyOneGetOneFree.claimSpecial();
        assertThat(items, Matchers.<Collection<Item>>allOf(
                hasItem(is( new Coke() )),
                hasSize(4)
        ));
//        assertThat(items, Matchers.<Collection<Item>>allOf(
//                hasItem(is( new Chocolate() )),
//                hasSize(2)
//        ));
    }

}
