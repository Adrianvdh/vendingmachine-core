package interaction.cli;

import vendingmachine.item.Item;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CLI {

    List<String> items = new LinkedList<>();

    public CLI listItemSelection(Collection<Item> itemsToList, Function<Item, String> stringFormat) {
        itemsToList.forEach((item) -> items.add(stringFormat.apply(item)));
        return this;
    }

    public Set<String> show() {
        return items.stream().collect(Collectors.toSet());
    }
}
