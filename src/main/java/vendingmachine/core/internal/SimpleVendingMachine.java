package vendingmachine.core.internal;

import vendingmachine.core.VendingMachine;
import vendingmachine.core.interaction.*;
import vendingmachine.core.item.Item;
import vendingmachine.core.store.Inventory;
import vendingmachine.core.store.MoneyHolder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleVendingMachine implements VendingMachine {

    private Inventory<Item> machineGoodsInventory;
    private MoneyHolder machineMoneyHolder;
    private AlphanumericGrid alphanumericGrid;

    private Item selectedItem;
    private double currentBalance;

    public SimpleVendingMachine(Inventory<Item> machineGoodsInventory, MoneyHolder machineMoneyHolder) {
        this.machineGoodsInventory = machineGoodsInventory;
        this.machineMoneyHolder = machineMoneyHolder;

        this.alphanumericGrid = contructAlphanumericGrid(machineGoodsInventory);
    }

    private AlphanumericGrid contructAlphanumericGrid(Inventory<Item> itemInventory) {
        double goodsSquareRootSize = Math.sqrt((double) itemInventory.getAvailableItems().size());
        int squareRootSizeRoundedUp = Double.valueOf(Math.ceil(goodsSquareRootSize)).intValue();
        int rows = squareRootSizeRoundedUp;
        int columns = squareRootSizeRoundedUp;

        AlphanumericGrid alphanumericGrid = new AlphanumericGrid(columns, rows);
        alphanumericGrid.loadDisplayableItems(machineGoodsInventory.getAvailableItems().stream().map(item -> item.getName()).collect(Collectors.toList()));
        return alphanumericGrid;
    }

    @Override
    public Map<String, String> listInstockItemsWithId() {
        return this.alphanumericGrid.showItems();
    }

    @Override
    public Double selectItemAndGetPrice(Item item) throws SoldOutException {
        if(machineGoodsInventory.hasItem(item)) {
            this.selectedItem = item;
            return selectedItem.getPrice();
        }
        throw new SoldOutException(String.format("%s is sold out! Select something else.", item.getName()));
    }

    @Override
    public void insertCoin(Coin... coins) {
        addMoneyToMachine(coins);
    }

    @Override
    public void insertNote(Note... notes) {
        addMoneyToMachine(notes);
    }

    @Override
    public Change refundAndReturnChange() {
        Collection<Money> refund = tryCollectChangeAmountInMoney(currentBalance);
        deductChangeFromMachineMoneyHolder(refund);
        clearSelectionAndCurrentBalance();

        return new Change().addAll(refund);
    }

    @Override
    public Order collectItemOrder() throws NotFullPaidException {
        if(hasPaidInFull()) {
            double changeToReturn = getReturningChangeAmount();

            if(hasNoChangeToReturn(changeToReturn)) {
                return justItemAndNoChange();
            }
            try {
                Collection<Money> changeInMoney = tryCollectChangeAmountInMoney(changeToReturn);
                return itemAndChange(changeInMoney);
            }
            catch(NotSufficientChangeException e) {
                throw new NotSufficientChangeException("The machine unfortunately has not enough store for your change.");
            }
        }
        else {
            throw new NotFullPaidException("Item price has not been paid in full! Please insert " + remainingBalanceToBePaidForItem());
        }
    }


    @Override
    public Double getCurrentBalance() {
        return this.currentBalance;
    }

    private void addMoneyToMachine(Money... money) {
        Arrays.stream(money).forEach(m -> {
            updateCurrentBalanceFromMoneyAmount(m);
            machineMoneyHolder.add(m);
        });
    }

    private Collection<Money> tryCollectChangeAmountInMoney(double changeToReturn) {
        return MoneyUtil.collectAmountWorthOfMoneyFromMachine(changeToReturn, machineMoneyHolder);
    }

    private void deductChangeFromMachineMoneyHolder(Collection<Money> moneyToDeduct) {
        moneyToDeduct.forEach(machineMoneyHolder::deduct);
    }

    private void clearSelectionAndCurrentBalance() {
        currentBalance = 0;
        selectedItem = null;
    }

    private boolean hasPaidInFull() {
        return currentBalance >= selectedItem.getPrice();
    }

    private boolean hasNoChangeToReturn(double changeToReturn) {
        return changeToReturn == 0;
    }

    private Order justItemAndNoChange() {
        Item item = collectItem();
        Change change = new Change();
        return new Order(item, change);
    }

    private Order itemAndChange(Collection<Money> changeInMoney) {
        Item item = collectItem();
        Change change = collectChange(changeInMoney);
        return new Order(item, change);
    }

    private double remainingBalanceToBePaidForItem() {
        return selectedItem.getPrice() - currentBalance;
    }

    private double getReturningChangeAmount() {
        return currentBalance - selectedItem.getPrice();
    }

    private Item collectItem() {
        machineGoodsInventory.deduct(selectedItem);
        return selectedItem;
    }

    private Change collectChange(Collection<Money> changeInMoney) {
        deductChangeFromMachineMoneyHolder(changeInMoney);
        clearSelectionAndCurrentBalance();

        return new Change().addAll(changeInMoney);
    }

    private void updateCurrentBalanceFromMoneyAmount(Money money) {
        currentBalance += money.getMonetaryAmount();
    }
}
