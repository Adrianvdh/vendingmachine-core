package vendingmachine.core.interaction;

import vendingmachine.core.interaction.money.Change;
import vendingmachine.core.interaction.special.OfferPolicy;
import vendingmachine.core.item.Item;

public class SpecialOffer {

    OfferPolicy offerPolicy;

    public void withItems(Item...items) {
        offerPolicy.registerEligibleItemsFromSystem(items);
    }

    public void setOfferPolicy(OfferPolicy offerPolicy) {
        this.offerPolicy = offerPolicy;
    }

    public Order collectOrder() {
        return new Order(offerPolicy.claimSpecial(), new Change());
    }
}
