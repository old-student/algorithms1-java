package quickSort;

import helpClasses.Pair;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.Collections.sort;

public class QuickSort {

    /**
     * This field tells how to choose pivot element for partitioning.
     * Default value is the first element of array.
     */
    private PivotType pivotType = PivotType.FIRST;

    /**
     * This field stores input data for further processing
     */
    private int[] data;


    public QuickSort() {
    }

    /**
     * @param fileName String which contains name of input file
     * @throws java.io.IOException if there are problems with input file
     */
    public QuickSort(String fileName) throws IOException {
        setData(fileName);
    }

    /**
     * @param inputData An object which contains input array
     */
    public QuickSort(int[] inputData) {
        setData(inputData);
    }

    public void setPivotType(PivotType pivotType) {
        this.pivotType = pivotType;
    }

    public PivotType getPivotType() {
        return this.pivotType;
    }

    /**
     * @param inputData An object which contains input array
     */
    public void setData(int[] inputData) {
        data = inputData.clone();
    }

    /**
     * @param fileName A string which contains name of input file
     * @throws java.io.IOException if there are problems with input file
     */
    public void setData(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        try (Scanner scanner =  new Scanner(path)) {
            data = new int[ Integer.parseInt(scanner.nextLine()) ];
            for(int i=0; i < data.length; ++i) {
                data[i] = Integer.parseInt(scanner.nextLine());
            }
        }
    }

    public int[] getData() {
        return data;
    }

    /**
     * Method prints out data array
     */
    public void printData() {
        System.out.println("Array of size: " + data.length);
        for(int element: data) {
            System.out.println(element);
        }
    }

    /**
     * Method calls main method to sort data array and counts number of comparisons
     * @return The total number of comparisons used by sorting data array
     */
    public long sortData() {
        return recursiveQuickSort(0, data.length - 1);
    }

    /**
     * This method recursively sorts data array and
     * counts the number of comparisons used for partitioning
     * and in the left and the right halves
     *
     * @param left index of left boundary of origin array
     * @param right index of right boundary of origin array
     * @return The number of comparisons used so far
     */
    private long recursiveQuickSort(int left, int right) {
        if(left >= right)
            return 0;
        int pivotIndex = partitionDataAroundPivot(left, right, getPivotIndex(left, right));
        return ( (right - left)
                 + recursiveQuickSort(left, pivotIndex - 1)
                 + recursiveQuickSort(pivotIndex + 1, right) );
    }

    /**
     * Method swaps ith and jth elements of data array
     * @param i first index
     * @param j second index
     */
    public void swapData (int i, int j) {
        int buf  = data[i];
        data[i] = data[j];
        data[j] = buf;
    }

    /**
     * Method does partitioning of input array bounded
     * with left and right indices around pivot element
     * @param left index of left boundary of origin array
     * @param right index of right boundary of origin array
     * @param pivotIndex index of pivot element for partitioning
     * @return index of pivot element in already partitioned array
     */
    private int partitionDataAroundPivot(int left, int right, int pivotIndex) {
        //pivot element is always the first element of array
        swapData(left, pivotIndex);

        int pivotValue = data[left];
        int i = left + 1;
        for(int j = left + 1; j <= right; j++) {
            if(data[j] < pivotValue) {
                swapData(j, i);
                ++i;
            }
        }
        //insert pivot element in appropriate position
        swapData(left, i - 1);
        return (i-1); //return index of pivot element of partitioned array
    }

    /**
     * Method determines index of pivot element for partitioning
     * @param left index of left boundary of origin array
     * @param right index of right boundary of origin array
     * @return index of pivot element for partitioning of
     *         input array between left and right indices
     */
    private int getPivotIndex(int left, int right) {
        int pivotIndex = left;
        switch (pivotType) {
            case FIRST:
                pivotIndex = left;
                break;
            case FINAL:
                pivotIndex = right;
                break;
            case MEDIAN:
                int median = (left + right) / 2;
                List<Pair<Integer,Integer>> pairs = new ArrayList<>();
                pairs.add(new Pair<>(data[left], left));
                pairs.add(new Pair<>(data[median], median));
                pairs.add(new Pair<>(data[right], right));
                sort(pairs);
                pivotIndex = pairs.get(1).getSecond();
                break;
        }
        return pivotIndex;
    }

}
