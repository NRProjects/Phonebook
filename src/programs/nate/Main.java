package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        long linearSearchTimeStart = System.currentTimeMillis();
        List<Integer> phoneNumbers = new ArrayList<>();
        List<String> names = new ArrayList<>();

        try (Scanner directoryReader = new Scanner(new File("C:\\Users\\Admin\\Documents\\IntelliJ\\Phone Book\\directory.txt"))) {
            while (directoryReader.hasNextLine()) {
                phoneNumbers.add(directoryReader.nextInt());
                names.add(directoryReader.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File \"directory\" not found");
        }

        // Sorting
        long sortTimeStart = System.currentTimeMillis();
        Collections.sort(names);
        long sortTimeEnd = System.currentTimeMillis();



        // Linear search
        int namesFound = 0;
        int queriedNames = 0;
        try (Scanner findReader = new Scanner(new File("C:\\Users\\Admin\\Documents\\IntelliJ\\Phone Book\\find.txt"))) {
            while (findReader.hasNextLine()) {
                queriedNames++;
                if (names.contains(findReader.nextLine())) {
                    namesFound++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File \"find\" not found");
        }
        long linearSearchTimeEnd = System.currentTimeMillis();



        //Binary search
        long binarySearchTimeStart = System.currentTimeMillis();
        try (Scanner findReader = new Scanner(new File("C:\\Users\\Admin\\Documents\\IntelliJ\\Phone Book\\find.txt"))) {
            List<String> namesToFind = new ArrayList<>();
            while (findReader.hasNextLine()) {
                namesToFind.add(findReader.nextLine());
            }
            Collections.sort(namesToFind);

            List<Integer> indexList = new ArrayList<>();
            for (String s : namesToFind) {
                int index = Collections.binarySearch(names, s);
                indexList.add(index);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File \"find\" not found");
        }
        long binarySearchTimeEnd = System.currentTimeMillis();



//     Total time calculations with print
        long totalTimeTakenLinear = linearSearchTimeEnd - linearSearchTimeStart;
        long totalTimeMinutesLinear = TimeUnit.MILLISECONDS.toMinutes(totalTimeTakenLinear);
        long totaTimeSecondsLinear = TimeUnit.MILLISECONDS.toSeconds(totalTimeTakenLinear);
        long totalTimeMillisecondsLinear = totalTimeTakenLinear % 1000;

////
        // Sorting time calculations with print
        long totalSortTime = sortTimeEnd - sortTimeStart;
        long totalSortTimeMinutes = TimeUnit.MILLISECONDS.toMinutes(totalSortTime);
        long totalSortTimeSeconds = TimeUnit.MILLISECONDS.toSeconds(totalSortTime);
        long totalSortTimeMilliseconds = totalSortTime % 1000;

//
        // Binary search time calculations with print
        long binarySearchTimeTotal = binarySearchTimeEnd - binarySearchTimeStart;
        long binarySearchTimeMinutes = TimeUnit.MILLISECONDS.toMinutes(binarySearchTimeTotal);
        long binarySearchTimeSeconds = TimeUnit.MILLISECONDS.toSeconds(binarySearchTimeTotal);
        long binarySearchTimeMilliseconds = binarySearchTimeTotal % 1000;

        // Total time taken (binary search)
        long totalTimeMinutes = totalSortTimeMinutes + binarySearchTimeMinutes;
        long totalTimeSeconds = totalSortTimeSeconds + binarySearchTimeSeconds;
        long totalTimeMilliseconds = totalSortTimeMilliseconds + binarySearchTimeMilliseconds;



        System.out.println("Starting search (linear search): ");
        System.out.println("Found " + namesFound + " / " + queriedNames + " entries. ");
        System.out.println("Time taken: " + totalTimeMinutes + " min. " + totaTimeSecondsLinear + " sec. " + totalTimeMillisecondsLinear + " ms.");
        System.out.println();
        System.out.println("Start searching (binary sort + jump search)");
        System.out.println("Found " + namesFound + " / " + queriedNames + " entries. ");
        System.out.println("Time taken: " + totalTimeMinutes + " min. " + totalTimeSeconds + " sec. " + totalTimeMilliseconds + " ms. ");
        System.out.println("Sorting time: " + totalSortTimeMinutes  + " min. " + totalSortTimeSeconds + " sec. " + totalSortTimeMilliseconds + " ms.");
        System.out.println("Searching time: " + binarySearchTimeMinutes + " min. " + binarySearchTimeSeconds + " sec. " + binarySearchTimeMilliseconds + " ms.");
    }
}

