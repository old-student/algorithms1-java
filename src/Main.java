import inversionsCounter.InversionsCounter;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileName = "inputData/inversionsCounter/IntegerArray.txt";
        InversionsCounter invCounter = new InversionsCounter("dsd");
        //invCounter.printArray();
        System.out.println(invCounter.getNumberOfInversions());
        //invCounter.printArray();
    }

}
