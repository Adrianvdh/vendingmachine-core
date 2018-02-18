package vendingmachine.core;

import vendingmachine.core.internal.SoldOutException;
import vendingmachine.core.interaction.Coin;
import vendingmachine.core.internal.NotFullPaidException;
import vendingmachine.core.item.Item;
import vendingmachine.core.interaction.Change;
import vendingmachine.core.interaction.Note;
import vendingmachine.core.interaction.Order;

import java.util.Map;

public interface VendingMachine {

    Map<String, String> listInstockItemsWithId();

    Double selectItemAndGetPrice(Item item) throws SoldOutException;

    void insertCoin(Coin... coins);

    void insertNote(Note... notes);

    Change refundAndReturnChange();

    Order collectItemOrder() throws NotFullPaidException;

    Double getCurrentBalance();
}
