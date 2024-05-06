import java.util.Arrays;
import java.util.Scanner;

public class SearchComplexity {
    // Linear Search Method
    public static int linearSearch(int[] array, int target) {
        int iterations = 0;
        for (int i = 0; i < array.length; i++) {
            iterations++;
            if (array[i] == target) {
                System.out.println("Linear search iterations: " + iterations);
                return i;  // Returns index of found element
            }
        }
        System.out.println("Linear search iterations: " + iterations);
        return -1;  // Target not found
    }

    // Binary Search Method
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        int iterations = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            iterations++;

            if (array[mid] == target) {
                System.out.println("Binary search iterations: " + iterations);
                return mid;
            }

            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println("Binary search iterations: " + iterations);
        return -1;  // Target not found
    }

    public static int recBinarySearch(int[] array, int target) { //overloading the function so it calls the one that will be used

        return recBinarySearch(array, target, 0, array.length - 1); // setting it to start the recursive
    }

    public static int recBinarySearch(int[] array, int target, int left , int right) {

        if (left > right) {
            return -1; // if its higher than the right then it was not found
        }

        int mid = left + (right - left) / 2;

        if (array[mid] == target) {
            return mid; //best case scenrio if its the one in the middle
        } else if (array[mid] < target) {
            return recBinarySearch(array, target, mid + 1, right); // checks right side of the split
        } else {
            return recBinarySearch(array, target, left, mid - 1); // checks left side of the split
        }
    }


    public static void main(String[] args) {

        int n = 0;
        while (n <=0 ) { // have it restart if something is entered incorrectly
            System.out.println("Enter number of elements in array:");
            Scanner scanner = new Scanner(System.in);
            n = scanner.nextInt();

            if (n <= 0) {
                System.out.println("Enter valid array size");
            } else {
                int[] array = new int[n];

                System.out.println("Enter elements:");
                for (int i = 0; i < n; i++) {
                    array[i] = scanner.nextInt();
                }

                System.out.println("Enter target number to search:");
                int target = scanner.nextInt();

                long startTime, endTime;

                // Linear Search
                startTime = System.nanoTime(); // start timer for method (every method is set the same)
                int linearResult = linearSearch(array, target);
                endTime = System.nanoTime(); // stop timer (every method is set the same)
                long linearSearchTime = endTime - startTime; // total time spent on running method (every method is set the same)
                System.out.println((linearResult == -1) ? "Target not found by linear search." :
                        "Target found by linear search at index: " + linearResult);
                System.out.println("Linear search time: " + linearSearchTime + " nanoseconds");

                // Binary Search (Array must be sorted)
                Arrays.sort(array);
                startTime = System.nanoTime();
                int binaryResult = binarySearch(array, target);
                endTime = System.nanoTime();
                long binarySearchTime = endTime - startTime;
                System.out.println((binaryResult == -1) ? "Target not found by binary search." :
                        "Target found by binary search at index: " + binaryResult);
                System.out.println("Binary search time: " + binarySearchTime + " nanoseconds");


                // recursive binary search
                Arrays.sort(array);
                startTime = System.nanoTime();
                int recBinaryResult = recBinarySearch(array, target);
                System.out.println((recBinaryResult == -1) ? "Target not found by recursive binary search." :
                        "Target found by recursive binary search at index: " + recBinaryResult);
                endTime = System.nanoTime();
                long recBinarySearchTime = endTime - startTime;
                System.out.println("Recursive Binary search time: " + recBinarySearchTime + " nanoseconds");

                scanner.close();
            }
        }
    }
}


