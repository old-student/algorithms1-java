import inversionsCounter.InversionsCounter;
import quickSort.PivotType;
import quickSort.QuickSort;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileName = "testData/inversionsCounter/IntegerArray.txt";
        long invNumber = new InversionsCounter(fileName).getNumberOfInversions();
        System.out.println("Number of inversions = " + invNumber);
    }
}
