package inversionsCounter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InversionsCounter {

    /**
     * This field stores the input data for further processing
     */
    private List<Integer> data = new ArrayList<Integer>();

    public InversionsCounter() {
    }

    /**
     * Constructor gets as input a name of file with data
     * and calls setData method to read it
     *
     * @param fileName A string which contains name of input file
     * @throws java.io.IOException if there are problems with input file
     */
    public InversionsCounter(String fileName) throws IOException {
        setData(fileName);
    }

    /**
     * Constructor gets as input an object which implements interface
     * Iterable and calls setData to set inner data
     *
     * @param inputData An object which contains input elements
     */
    public InversionsCounter(Iterable<Integer> inputData) {
        setData(inputData);
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
     * Method gets as input a name of file with data
     * and calls setData method to read it
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
     * This method is used to call the private method that sorts the
     * ArrayList data and returns the number of inversions in it
     *
     * @return The number of inversions in ArrayList data
     */
    public long getNumberOfInversions() {
        return (countInversions(0, data.size() - 1));
    }

    /**
     * This method recursively counts the number of inversions in
     * the left half, in the right half and number of split inversions
     * of origin array bounded with left and right indices respectively.
     *
     * @param left index of left boundary of origin array
     * @param right index of right boundary of origin array
     * @return The number of inversions in origin array bounded with left and right indices
     */
    private long countInversions(int left, int right) {
        if(left >= right)
            return (0);

        int middle = (left + right) / 2;
        long leftInv  = countInversions(left, middle);
        long rightInv = countInversions(middle + 1, right);
        long splitInv = countSplitInversions(left, middle, middle + 1, right);
        return (leftInv + rightInv + splitInv);
    }

    /**
     * This method returns the number of split inversions of part of origin array
     * which consist of left and right halves
     *
     * @param beginLeft start index of the left half of the treated part of origin array
     * @param endLeft end index of the left half of the treated part of origin array
     * @param beginRight start index of the right half of the treated part of origin array
     * @param endRight end index of the right half of the treated part of origin array
     * @return The number of split inversions in origin array bounded with beginLeft and endRight indices
     */
    private long countSplitInversions(int beginLeft, int endLeft, int beginRight, int endRight) {
        int totalNumElements = endRight - beginLeft + 1;
        int[] bufArray = new int[totalNumElements];
        long splitInv = 0;

        for(int iLeft = beginLeft, iRight = beginRight, iBuf = 0; iBuf < totalNumElements; ++iBuf) {
            if(iLeft > endLeft)
                bufArray[iBuf] = data.get(iRight++);
            else if(iRight > endRight)
                bufArray[iBuf] = data.get(iLeft++);
            else if(data.get(iLeft) < data.get(iRight))
                bufArray[iBuf] = data.get(iLeft++);
            else {
                bufArray[iBuf] = data.get(iRight++);
                splitInv += endLeft - iLeft + 1;
            }
        }

        for(int iBuf = 0; iBuf < totalNumElements; ++iBuf) {
            data.set(iBuf + beginLeft, bufArray[iBuf]);
        }

        return (splitInv);
    }

}
