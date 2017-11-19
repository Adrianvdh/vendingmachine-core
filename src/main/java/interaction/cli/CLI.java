package interaction.cli;

import vendingmachine.item.Item;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

public class CLI {



    public <T> CLI listItemSelection(Collection<Item> itemsToList, Function<T, String> stringFormat) {
        //store items to list
        return this;
    }

    public Set<String> show() {
        //return the items list
        //clear list
        return null;
    }
}
