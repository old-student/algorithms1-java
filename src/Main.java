import quickSort.PivotType;
import quickSort.QuickSort;

import java.io.IOException;

import static java.util.Collections.sort;

public class Main {

    public static void main(String[] args) throws IOException {
        //String fileName = "inputData/quickSort/SmallIntegerArray.txt";
        String fileName = "inputData/quickSort/QuickSort.txt";
        QuickSort quickSort = new QuickSort(fileName);
        quickSort.setPivotType(PivotType.MEDIAN);
        System.out.println("Number of comparisons = " + quickSort.sortData());
    }

}
