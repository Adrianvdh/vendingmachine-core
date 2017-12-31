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
        validateSelectionKey(selectionKey);

        int rowNumber = getRowIndexFromAlphabetCharacter(selectionKey);
        int columnNumber = getColumnNumberFromRemainingSelectionKey(selectionKey);

        if(columnNumberIsOutOfBoundsOfGrid(columnNumber))
            throw new IndexOutOfBoundsException("Column selection size is to large for this grid!");
        if(rowNumberIsOutOfBoundsOfGrid(rowNumber))
            throw new IndexOutOfBoundsException("Row position is out of the bounds for this grid!");

        this.selectedItem = getItemFromIndexes(rowNumber, columnNumber);
    }

    private boolean rowNumberIsOutOfBoundsOfGrid(int rowNumber) {
        return rowNumber > this.rowLength;
    }

    private boolean columnNumberIsOutOfBoundsOfGrid(int columnNumber) {
        return columnNumber > this.columnLength;
    }

    private String getItemFromIndexes(int rowLetter, int columnNumber) {
        return grid[rowLetter][columnNumber];
    }

    private int getColumnNumberFromRemainingSelectionKey(String selectionKey) {
        return Integer.parseInt(selectionKey.substring(1, selectionKey.length())) - 1;
    }

    private int getRowIndexFromAlphabetCharacter(String selectionKey) {
        return (int) selectionKey.charAt(0) - 97;
    }

    private void validateSelectionKey(String selectionKey) {
        if (!Pattern.matches("^[a-z][1-9]+$", selectionKey)) {
            throw new IllegalArgumentException("selectionKey is in the incorrect format!");
        }
    }

    public String getSelectedItem() {
        return this.selectedItem;
    }
}
