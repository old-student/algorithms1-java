package quickSort;

import helpClasses.Pair;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.Collections.sort;
import static java.util.Collections.swap;

public class QuickSort {

    /**
     * This field tells how to choose pivot element for partitioning
     * default value is first element of array
     */
    private PivotType pivotType = PivotType.FIRST;

    /**
     * This field stores the input data for further processing
     */
    private List<Integer> data = new ArrayList<Integer>();


    public QuickSort() {
    }

    /**
     * Constructor gets as input a name of file with data
     * and calls setData method to read it
     *
     * @param fileName A string which contains name of input file
     * @throws java.io.IOException if there are problems with input file
     */
    public QuickSort(String fileName) throws IOException {
        setData(fileName);
    }

    /**
     * Constructor gets as input an object which implements interface
     * Iterable and calls setData to set inner data
     *
     * @param inputData An object which contains input elements
     */
    public QuickSort(Iterable<Integer> inputData) {
        setData(inputData);
    }

    public void setPivotType(PivotType pivotType) {
        this.pivotType = pivotType;
    }

    public PivotType getPivotType() {
        return (this.pivotType);
    }

    /**
     * Method gets an object which implements interface Iterable
     * and copies its elements to inner ArrayList
     *
     * @param inputData An object which contains input elements
     */
    public void setData(Iterable<Integer> inputData) {
        data.clear();
        for(Integer elem: inputData) {
            data.add(elem);
        }
    }

    /**
     * Method gets as input a name of file with data and read it
     *
     * @param fileName A string which contains name of input file
     * @throws java.io.IOException if there are problems with input file
     */
    public void setData(String fileName) throws IOException {
        //clear data before reading data from file
        data.clear();

        Path path = Paths.get(fileName);
        try (Scanner scanner =  new Scanner(path)){
            while (scanner.hasNextLine()){
                data.add(Integer.parseInt(scanner.nextLine()));
            }
        }
    }

    /**
     * Method prints inner List to standard output stream
     */
    public void printData() {
        System.out.println("Array of size: " + data.size());
        for(int element: data) {
            System.out.println(element);
        }
    }

    /**
     * Method calls main method to sort data array and counts number of comparisons
     * @return The total number of comparisons used to sort data array
     */
    public long sortData() {
        return (recursiveQuickSort(0, data.size() - 1));
    }

    /**
     * This method recursively sorts the data array and
     * counts the number of comparisons used for partitioning
     * and in the left and the right halves
     *
     * @param left index of left boundary of origin array
     * @param right index of right boundary of origin array
     * @return The number of comparisons used so far
     */
    private long recursiveQuickSort(int left, int right) {
        if(left >= right)
            return (0);
        int pivotIndex = partitionDataAroundPivot(left, right, getPivotIndex(left, right));
        return ( (right - left)
                 + recursiveQuickSort(left, pivotIndex - 1)
                 + recursiveQuickSort(pivotIndex + 1, right) );
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
        //pivot element is always a first element of array
        swap(data, left, pivotIndex);

        int pivotValue = data.get(left);
        int i = left + 1;
        for(int j = left + 1; j <= right; j++) {
            if(data.get(j) < pivotValue) {
                swap(data, j, i);
                ++i;
            }
        }
        //insert pivot element in appropriate position
        swap(data, left, i-1);

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
                List<Pair<Integer, Integer>> pairs = new ArrayList<>();
                pairs.add(new Pair(data.get(left), left));
                pairs.add(new Pair(data.get(median), median));
                pairs.add(new Pair(data.get(right), right));
                sort(pairs);
                //get index of middle-stayed value [0,1,2]
                pivotIndex = pairs.get(1).getSecond();
                break;
        }
        return (pivotIndex);
    }

    /**
     * Method creates deep copy of data array
     * @return deep copy of data array
     */
    public List<Integer> getDataCopy() {
        List<Integer> copyList = new ArrayList<Integer>();
        for(Integer elem : data) {
            copyList.add(new Integer(elem));
        }
        return(copyList);
    }
}
