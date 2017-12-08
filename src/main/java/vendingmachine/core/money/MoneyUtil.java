package vendingmachine.core.money;

import vendingmachine.core.interaction.money.exception.NotSufficientChangeException;
import vendingmachine.core.interaction.money.Coin;
import vendingmachine.core.interaction.money.Note;

import java.util.*;

public class MoneyUtil {

    public static Collection<Money> collectAmountWorthOfMoneyFromMachine(double amount, MoneyHolder moneyHolder) throws NotSufficientChangeException {
        if (amountToSmallToConvertToMoney(amount)) return noMoney();

        Double balance = amount;
        Collection<Money> amountWorthOfMoney = new ArrayList<>();

        MoneyReducer moneyReducer = new MoneyReducer();
        moneyReducer.ofMachineMoney(moneyHolder);
        moneyReducer.ofAmountToReduce(balance);
        moneyReducer.ofAmountOfMoneyCollection(amountWorthOfMoney);

        moneyReducer.reduceBy(Note.values());
        moneyReducer.reduceBy(Coin.values());

        if(machineLacksMoneyToReduce(moneyReducer))
            throw new NotSufficientChangeException("The machine lacks the money you wish to convert to coins and notes.");

        return moneyReducer.getAmountWorthOfMoney();
    }


    private static boolean amountToSmallToConvertToMoney(double amount) {
        return amount < 0;
    }

    private static Collection<Money> noMoney() {
        return Collections.EMPTY_LIST;
    }

    private static boolean machineLacksMoneyToReduce(MoneyReducer moneyReducer) {
        return moneyReducer.getBalance() > 0;
    }

    private static class MoneyReducer {
        private Double balance;
        private Collection<Money> amountWorthOfMoney;

        private MoneyHolder moneyHolder;

        public void reduceBy(Money[] monetaryItem) {
            Arrays.stream(monetaryItem).forEach(money -> {
                if(canBeReducedByMoneyAmount(money) && moneyHolder.hasItem(money)) {
                    deductMoneyFromMachine(money);
                    deductFromBalanceBeingReduced(money);
                    amountWorthOfMoney.add(money);
                }
            });
        }

        private boolean canBeReducedByMoneyAmount(Money money) {
            return balance >= money.getMonetaryAmount();
        }

        private void deductMoneyFromMachine(Money money) {
            moneyHolder.deduct(money);
        }

        private void deductFromBalanceBeingReduced(Money money) {
            balance -= money.getMonetaryAmount();
        }

        public void ofAmountToReduce(Double balance) {
            this.balance = balance;
        }

        public void ofAmountOfMoneyCollection(Collection<Money> moneyCollection) {
            this.amountWorthOfMoney = moneyCollection;
        }

        public void ofMachineMoney(MoneyHolder moneyHolder) {
            this.moneyHolder = moneyHolder;
        }

        public Double getBalance() {
            return balance;
        }

        public Collection<Money> getAmountWorthOfMoney() {return amountWorthOfMoney;}
    }
}
