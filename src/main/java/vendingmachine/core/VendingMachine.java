package vendingmachine.core;

import vendingmachine.core.interaction.item.exception.SoldOutException;
import vendingmachine.core.interaction.money.Coin;
import vendingmachine.core.interaction.money.exception.NotFullPaidException;
import vendingmachine.core.item.Item;
import vendingmachine.core.interaction.money.Change;
import vendingmachine.core.interaction.money.Note;
import vendingmachine.core.interaction.Order;

import java.util.Collection;
import java.util.Map;

public interface VendingMachine {

    Map<String, String> listInstockItemsWithId();

    Collection<Item> getInstockItems();

    Double selectItemAndGetPrice(Item item) throws SoldOutException;

    void insertCoin(Coin... coins);

    void insertNote(Note... notes);

    Change refundAndReturnChange();

    Order collectItemOrder() throws NotFullPaidException;

    Double getCurrentBalance();
}
