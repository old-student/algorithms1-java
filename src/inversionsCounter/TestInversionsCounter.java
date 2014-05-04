package inversionsCounter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestInversionsCounter {

    private static int numberOfElements = 100;

    @Test
    public void testIncreasingOrderedArray() {
        List<Integer> inputData = new ArrayList<Integer>();
        for(int i = 1; i <= numberOfElements; ++i)
            inputData.add(i);
        InversionsCounter invCounter = new InversionsCounter(inputData);
        long returnedNumberInv = invCounter.getNumberOfInversions();
        long expectedNumberInv = 0;
        assertEquals(expectedNumberInv, returnedNumberInv);
    }

    @Test
    public void testDecreasingOrderedArray() {
        List<Integer> inputData = new ArrayList<Integer>();
        for(int i = numberOfElements; i > 0; --i)
            inputData.add(i);
        InversionsCounter invCounter = new InversionsCounter(inputData);
        long returnedNumberInv = invCounter.getNumberOfInversions();
        long expectedNumberInv = numberOfElements * (numberOfElements - 1) / 2;
        assertEquals(expectedNumberInv, returnedNumberInv);
    }

    @Test
    public void testEmptyArray() {
        List<Integer> inputData = new ArrayList<Integer>();
        InversionsCounter invCounter = new InversionsCounter(inputData);
        long returnedNumberInv = invCounter.getNumberOfInversions();
        long expectedNumberInv = 0;
        assertEquals(expectedNumberInv, returnedNumberInv);
    }

}
