package quickSort;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertArrayEquals;

public class TestQuickSort {

    private int[] readDataFromFile(String fileName) throws IOException {
        int[] data = null;
        Path path = Paths.get(fileName);
        try (Scanner scanner =  new Scanner(path)) {
            data = new int[ Integer.parseInt(scanner.nextLine()) ];
            for(int i = 0; i < data.length; ++i) {
                data[i] = Integer.parseInt(scanner.nextLine());
            }
        }
        return data;
    }

    private void testSortArray(int[] testData) {
        QuickSort quickSort = new QuickSort(testData);
        quickSort.sortData();
        Arrays.sort(testData);
        assertArrayEquals(testData, quickSort.getData());
    }

    @Test
    public void testSortEmptyArray() {
        int[] data = {};
        testSortArray(data);
    }

    @Test
    public void testSortГтшеArray() {
        int[] data = {100};
        testSortArray(data);
    }

    @Test
    public void testSortSmallArray() {
        int[] data = {5, 6, 1, 4, 11, 22, 12, 29, 3};
        testSortArray(data);
    }

    @Test
    public void testSortLargeArray() throws IOException {
        String fileName = "testData/quickSort/QuickSort.txt";
        int[] data = readDataFromFile(fileName);
        testSortArray(data);
    }

}
