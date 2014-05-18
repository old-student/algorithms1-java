package quickSort;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.sort;
import static org.junit.Assert.assertArrayEquals;

public class TestQuickSort {

    private List<Integer> readDataFromFile(String fileName) throws IOException {
        List<Integer> data = new ArrayList<Integer>();
        Path path = Paths.get(fileName);
        try (Scanner scanner =  new Scanner(path)){
            while (scanner.hasNextLine()){
                data.add(Integer.parseInt(scanner.nextLine()));
            }
        }
        return (data);
    }

    @Test
    public void testSortSmallArray() {
        List<Integer> inputData = Arrays.asList(new Integer[]{5, 6, 1, 4, 11, 22, 12, 29, 3});
        QuickSort quickSort = new QuickSort(inputData);
        quickSort.sortData();
        sort(inputData);
        assertArrayEquals(inputData.toArray(), quickSort.getDataCopy().toArray());
    }

    @Test
    public void testSortLargeArray() throws IOException {
        String fileName = "inputData/quickSort/QuickSort.txt";
        List<Integer> inputData = readDataFromFile(fileName);
        QuickSort quickSort = new QuickSort(inputData);
        quickSort.sortData();
        sort(inputData);
        assertArrayEquals(inputData.toArray(), quickSort.getDataCopy().toArray());
    }

}
