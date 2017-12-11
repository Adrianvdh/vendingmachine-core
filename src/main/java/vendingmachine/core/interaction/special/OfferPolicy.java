package vendingmachine.core.interaction.special;

import vendingmachine.core.item.Item;

public interface OfferPolicy {

    void registerEligibleItemsFromSystem(Item... items);

    Item claimSpecial();
}
