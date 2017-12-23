package vendingmachine.core;

import java.util.List;
import java.util.regex.Pattern;

public class AlphanumericGrid {

    private String[][] grid;

    private int columnLength;
    private int rowLength;

    String selectedItem;

    public AlphanumericGrid(int columnLength, int rowLength) {
        this.columnLength = columnLength;
        this.rowLength = rowLength;
        grid = new String[columnLength][rowLength];
    }

    public void loadDisplayableItems(List<String> itemsToDisplay) {
        //populate grid

        int itemIndex = 0;
        for (int rowLetterIndex = 0; rowLetterIndex < rowLength; rowLetterIndex++) {
            for (int columnNumberIndex = 0; columnNumberIndex < columnLength; columnNumberIndex++) {

                if(itemIndex < itemsToDisplay.size()) {
                    String item = itemsToDisplay.get(itemIndex);
                    grid[rowLetterIndex][columnNumberIndex] = item;
                }
                itemIndex++;

            }
        }

    }

    public void enterSelectionKey(String selectionKey) {
        //validate selectionKey
        if (!Pattern.matches("^[a-z][1-9]+$", selectionKey)) {
            throw new IllegalArgumentException("selectionKey is in the incorrect format!");
        }

        //parse key to index
        int rowLetter = selectionKey.charAt(0) - 97;
        int columnNumber = Integer.parseInt(selectionKey.substring(1, selectionKey.length())) - 1;
        if(columnNumber > this.columnLength)
            throw new IndexOutOfBoundsException("Column selection size is to large for this grid!");

        if(rowLetter > this.rowLength)
            throw new IndexOutOfBoundsException("Row position is out of the bounds for this grid!");

        //get item from index
        this.selectedItem = grid[rowLetter][columnNumber];
    }

    public String getSelectedItem() {
        return this.selectedItem;
    }
}
