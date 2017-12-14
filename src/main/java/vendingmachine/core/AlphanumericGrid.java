package vendingmachine.core;

import java.util.Collection;
import java.util.List;

public class AlphanumericGrid {

    private String[][] grid;

    private int columnLenghth;
    private int rowLength;


    public AlphanumericGrid(int columnLength, int rowLength) {
        this.columnLenghth = columnLength;
        this.rowLength = rowLength;
        grid = new String[columnLength][rowLength];
    }

    public void loadDisplayableItems(List<String> itemsToDisplay) {
        //populate grid

        int itemIndex = 0;
        for (int columnIndex = 0; columnIndex < columnLenghth; columnIndex++) {
            for (int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
                if(itemIndex < itemsToDisplay.size()) {
                    String item = itemsToDisplay.get(itemIndex);
                    grid[columnIndex][rowIndex] = item;
                }
                itemIndex++;

            }
        }

    }

    public void enterSelectionKey(String selectionKey) {
        //select item from grid
    }

    public String getSelectedItem() {
        return "Classic Coke";
    }
}
