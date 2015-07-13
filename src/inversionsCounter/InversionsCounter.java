package inversionsCounter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class InversionsCounter {
    /**
     * This field stores the input data for further processing
     */
    private int[] data;

    public InversionsCounter() { }

    /**
     * Constructor gets as input a name of file with
     * data and calls setData method to read it
     *
     * @param fileName A string which contains name of input file
     * @throws java.io.IOException if there are problems with input file
     */
    public InversionsCounter(String fileName) throws IOException {
        setData(fileName);
    }

    /**
     * Constructor gets as input an array if integers
     * and calls setData to set inner field
     *
     * @param inputData An array which contains input elements
     */
    public InversionsCounter(int[] inputData) {
        setData(inputData);
    }

    /**
     * Method gets an array and copies its elements to inner field
     *
     * @param inputData An array which contains input elements
     */
    public void setData(int[] inputData) {
        data = inputData.clone();
    }

    /**
     * Method reads data from file and stores it
     *
     * @param fileName A string which contains name of input file
     * @throws java.io.IOException if there are problems with input file
     */
    public void setData(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        try (Scanner scanner =  new Scanner(path)) {
            data = new int[ Integer.parseInt(scanner.nextLine()) ];
            for(int i = 0; i < data.length; ++i) {
                data[i] = Integer.parseInt(scanner.nextLine());
            }
        }
    }

    /**
     * Method prints array to standard output stream
     */
    public void printData() {
        System.out.println("Array size: " + data.length);
        for(int element: data) {
            System.out.println(element);
        }
    }

    /**
     * This method is used to call the private method that sorts the
     * data and returns the number of inversions in it
     *
     * @return The number of inversions in inner array
     */
    public long getNumberOfInversions() {
        return countInversions(0, data.length - 1);
    }

    /**
     * This method recursively counts the number of inversions in
     * the left half, in the right half and the number of split inversions
     * of the origin array bounded with left and right indices respectively.
     *
     * @param left index of left boundary of origin array
     * @param right index of right boundary of origin array
     * @return The number of inversions in origin array bounded with left and right indices
     */
    private long countInversions(int left, int right) {
        if(left >= right)
            return 0;

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
        int totalElemNumber = endRight - beginLeft + 1;
        int[] bufArray = new int[totalElemNumber];
        long splitInv = 0;
        for(int iLeft = beginLeft, iRight = beginRight, iBuf = 0; iBuf < totalElemNumber; ++iBuf) {
            if(iLeft > endLeft)
                bufArray[iBuf] = data[iRight++];
            else if(iRight > endRight)
                bufArray[iBuf] = data[iLeft++];
            else if(data[iLeft] <= data[iRight])
                bufArray[iBuf] = data[iLeft++];
            else {
                bufArray[iBuf] = data[iRight++];
                splitInv += endLeft - iLeft + 1;
            }
        }
        System.arraycopy(bufArray, 0, data, beginLeft, bufArray.length);
        return splitInv;
    }
}
