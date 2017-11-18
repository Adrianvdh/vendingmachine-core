package vendingmachine;

import vendingmachine.interaction.item.exception.SoldOutException;
import vendingmachine.interaction.money.Coin;
import vendingmachine.interaction.money.exception.NotFullPaidException;
import vendingmachine.item.Item;
import vendingmachine.interaction.money.Change;
import vendingmachine.interaction.money.Note;
import vendingmachine.interaction.Order;
import vendingmachine.money.Money;

import java.util.Collection;

public interface VendingMachine {

    Collection<Item> getInstockItems();

    Double selectItemAndGetPrice(Item item) throws SoldOutException;

    void insertCoin(Coin... coins);

    void insertNote(Note... notes);

    Change refundAndReturnChange();

    Order collectItemOrder() throws NotFullPaidException;

    Double getCurrentBalance();

    void reset();
}
