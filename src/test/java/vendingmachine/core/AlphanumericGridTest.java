package vendingmachine.core;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class AlphanumericGridTest {

    AlphanumericGrid alphanumericGrid;

    @Before
    public void setUp() throws Exception {
        alphanumericGrid = new AlphanumericGrid(10, 10);
        alphanumericGrid.loadDisplayableItems(Arrays.asList("Classic Coke", "Diary Chocolate", "Lays Original"));
    }

    @Test
    public void testRetrievalOfItemNameWithKey_TenByTenGrid() throws Exception {
        String selectionKey = "a2";
        this.alphanumericGrid.enterSelectionKey(selectionKey);

        Assert.assertThat(alphanumericGrid.getSelectedItem(), Matchers.is("Diary Chocolate"));
    }

    @Test
    public void testRetrievalOfItemNameWithKey_TwoByTenGrid() throws Exception {
        AlphanumericGrid alphanumericGrid = new AlphanumericGrid(2, 10);
        alphanumericGrid.loadDisplayableItems(Arrays.asList("Classic Coke", "Diary Chocolate", "Lays Original"));

        String selectionKey = "b1";
        alphanumericGrid.enterSelectionKey(selectionKey);

        Assert.assertThat(alphanumericGrid.getSelectedItem(), Matchers.is("Lays Original"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRetrievalWithSelectionKeyOfLeadingZeroColumnNumber_PatternShouldFail() throws Exception {
        String selectionKey = "b001";
        alphanumericGrid.enterSelectionKey(selectionKey);

        Assert.assertThat(alphanumericGrid.getSelectedItem(), Matchers.is("Lays Original"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRetrievalWithInvalidKeyFormat() throws Exception {
        AlphanumericGrid alphanumericGrid = new AlphanumericGrid(2, 10);
        alphanumericGrid.loadDisplayableItems(Arrays.asList("Classic Coke", "Diary Chocolate", "Lays Original"));

        String selectionKey = "bb23";
        alphanumericGrid.enterSelectionKey(selectionKey);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRetrievalInvalidKeyPosition() throws Exception {
        AlphanumericGrid alphanumericGrid = new AlphanumericGrid(10, 10);
        alphanumericGrid.loadDisplayableItems(Arrays.asList("Classic Coke", "Diary Chocolate", "Lays Original"));

        String selectionKey = "d11";
        alphanumericGrid.enterSelectionKey(selectionKey);

        Assert.assertThat(alphanumericGrid.getSelectedItem(), Matchers.is("Lays Original"));
    }





}
