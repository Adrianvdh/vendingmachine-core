package vendingmachine.core;

import java.util.Arrays;
import java.util.List;

public class AlphanumericGrid {

    private String[][] grid;

    private int columnLenghth;
    private int rowLength;

    String selectedItem;

    public static void main(String[] args) {
        System.out.println((int)'a');

        System.out.println("hello".substring(1, 3));
    }

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
                    grid[rowIndex][columnIndex] = item;
                }
                itemIndex++;

            }
        }

    }

    public void enterSelectionKey(String selectionKey) {

        //parse key to index
        int rowLetter = selectionKey.charAt(0) - 97;
        int columnNumber = Integer.parseInt(selectionKey.substring(1, selectionKey.length())) - 1;

        //get item from index
        this.selectedItem = grid[columnNumber][rowLetter];
    }

    public String getSelectedItem() {
        return this.selectedItem;
    }
}
