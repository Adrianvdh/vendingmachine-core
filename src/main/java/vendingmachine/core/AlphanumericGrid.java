package vendingmachine.core;

import java.util.*;
import java.util.regex.Pattern;

public class AlphanumericGrid {

    private String[][] grid;

    private int columnLength;
    private int rowLength;

    private String selectedItem;

    public AlphanumericGrid(int columnLength, int rowLength) {
        this.columnLength = columnLength;
        this.rowLength = rowLength;
        grid = new String[columnLength][rowLength];
    }

    public void loadDisplayableItems(List<String> itemsToDisplay) {
        Set<String> existingItems = new LinkedHashSet<>();
        //populate grid
        int itemIndex = 0;
        for (int rowLetterIndex = 0; rowLetterIndex < rowLength; rowLetterIndex++) {
            for (int columnNumberIndex = 0; columnNumberIndex < columnLength; columnNumberIndex++) {
                if(itemIndex < itemsToDisplay.size()) {
                    String item = itemsToDisplay.get(itemIndex);
                    if(!existingItems.contains(item)) {
                        setValueOfGridAt(rowLetterIndex, columnNumberIndex, item);
                        existingItems.add(item);
                    }
                }
                else {
                    break;
                }
                itemIndex++;

            }
        }

    }

    public Map<String, String> showItems() {
        Map<String, String> gridItems = new HashMap<>();
        int itemIndex = 0;

        for (int rowLetterIndex = 0; rowLetterIndex < grid.length; rowLetterIndex++) {
            String letter = getLetterFromIndex(rowLetterIndex);

            for (int columnNumberIndex = 0; columnNumberIndex < grid[rowLetterIndex].length; columnNumberIndex++) {
                if (grid[rowLetterIndex][columnNumberIndex] == null) {
                    break;
                }
                gridItems.put(formatAlphanumeic(letter, columnNumberIndex), getValueFromGridAt(rowLetterIndex, columnNumberIndex));
            }
        }
        return gridItems;
    }

    private String formatAlphanumeic(String letter, int indexNumber) {
        return String.format("%s%d", letter, 1+ indexNumber);
    }

    private String getLetterFromIndex(int index) {
        // 'a' starts at 97
        return String.valueOf((char) (index + 97));
    }

    public void enterSelectionKey(String selectionKey) {
        validateSelectionKey(selectionKey);

        int rowNumber = getRowIndexFromAlphabetCharacter(selectionKey);
        int columnNumber = getColumnNumberFromRemainingSelectionKey(selectionKey);

        if(columnNumberIsOutOfBoundsOfGrid(columnNumber))
            throw new IndexOutOfBoundsException("Column selection size is to large for this grid!");
        if(rowNumberIsOutOfBoundsOfGrid(rowNumber))
            throw new IndexOutOfBoundsException("Row position is out of the bounds for this grid!");

        this.selectedItem = getValueFromGridAt(rowNumber, columnNumber);
    }

    public void removeItemByName(String itemName) {
        //search array for item by name
        for(int rowNum = 0; rowNum < rowLength; rowNum++) {
            for(int colNum = 0; colNum < columnLength; colNum++) {

                String itemFromGrid = getValueFromGridAt(rowNum, colNum);
                if(itemFromGrid != null && itemFromGrid.equals(itemName)) {
                    setValueOfGridAt(rowNum, colNum, null);
                    break;
                }
            }
        }

        throw new RuntimeException("Item could not be found!");

    }

    public String getSelectedItem() {
        return this.selectedItem;
    }

    private void setValueOfGridAt(int rowLetterIndex, int columnNumberIndex, String value) {
        grid[rowLetterIndex][columnNumberIndex] = value;
    }

    private boolean rowNumberIsOutOfBoundsOfGrid(int rowNumber) {
        return rowNumber > this.rowLength;
    }

    private boolean columnNumberIsOutOfBoundsOfGrid(int columnNumber) {
        return columnNumber > this.columnLength;
    }

    private String getValueFromGridAt(int rowLetter, int columnNumber) {
        return grid[rowLetter][columnNumber];
    }

    private int getColumnNumberFromRemainingSelectionKey(String selectionKey) {
        return Integer.parseInt(selectionKey.substring(1, selectionKey.length())) - 1;
    }

    private int getRowIndexFromAlphabetCharacter(String selectionKey) {
        // 'a' starts at 97
        return (int) selectionKey.charAt(0) - 97;
    }

    private void validateSelectionKey(String selectionKey) {
        if (!Pattern.matches("^[a-z][1-9]+$", selectionKey)) {
            throw new IllegalArgumentException("selectionKey is in the incorrect format!");
        }
    }
}
