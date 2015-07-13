import inversionsCounter.InversionsCounter;
import quickSort.PivotType;
import quickSort.QuickSort;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileName = "testData/quickSort/QuickSort.txt";
        QuickSort quickSort = new QuickSort(fileName);

        long startTime = System.currentTimeMillis();
        quickSort.setPivotType(PivotType.MEDIAN);
        long comparisonsNumber = quickSort.sortData();
        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println("Elapsed time = " + estimatedTime + " ms");
        System.out.println("Comparisons number = " + comparisonsNumber);
    }
}
