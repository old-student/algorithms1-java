package inversionsCounter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestInversionsCounter {

    private static int ELEMENTS_NUMBER = 100;

    private void checkOnArray(int[] testData, long expectedInvNumber) {
        long actualInvNumber = new InversionsCounter(testData).getNumberOfInversions();
        assertEquals(expectedInvNumber, actualInvNumber);
    }

    @Test
    public void testIncreasingOrderedArray() {
        int[] testData = new int[ELEMENTS_NUMBER];
        for(int i = 0; i < ELEMENTS_NUMBER; ++i) {
            testData[i] = i;
        }
        checkOnArray(testData, 0);
    }

    @Test
    public void testDecreasingOrderedArray() {
        int[] testData = new int[ELEMENTS_NUMBER];
        for(int i = 0; i < ELEMENTS_NUMBER; ++i) {
            testData[ELEMENTS_NUMBER - 1 - i] = i;
        }
        checkOnArray(testData, ELEMENTS_NUMBER * (ELEMENTS_NUMBER - 1) / 2);
    }

    @Test
    public void testEmptyArray() {
        checkOnArray(new int[0], 0);
    }

    @Test
    public void testSomeSmallArray() {
        int[] testData = {1, 3, 5, 2, 4, 6};
        checkOnArray(testData, 3);
    }

}
