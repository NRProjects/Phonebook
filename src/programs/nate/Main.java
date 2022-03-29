package programs.nate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static final String DIR_PATH = "C:\\Users\\Admin\\Documents\\IntelliJ\\Phone Book\\directory.txt";
    private static final String FIND_PATH = "C:\\Users\\Admin\\Documents\\IntelliJ\\Phone Book\\find.txt";

    public static void main(String[] args) {
        long sortStartTime = System.currentTimeMillis();
        List<String> listDir = createStream(DIR_PATH).map(str -> str.split(" ", 2)[1]).sorted().toList();
        List<String> listFind = createStream(FIND_PATH).toList();
        long sortEndTime = System.currentTimeMillis();
        long duration = sortEndTime - sortStartTime;

        //Linear search
        System.out.println("Starting search (linear search): ");
        long linearStartTime = System.currentTimeMillis();
        long namesFound = listFind.stream().filter(listDir::contains).count();
        long linearEndTime = System.currentTimeMillis();
        System.out.printf("Found %d / %d entries.\n", namesFound, listDir.size());
        printTime("Time taken", linearStartTime, linearEndTime);
        System.out.println();

        //Binary search
        System.out.println("Start searching (binary sort)");
        long binaryStartTime = System.currentTimeMillis();
        List<Integer> indexes = listFind.stream().map(s -> Collections.binarySearch(listDir, s)).toList();
        long binaryEndTime = System.currentTimeMillis();
        System.out.println("Found " + indexes.size() + " / " + listDir.size() + " entries. ");
        printTime("Sorting Time", sortStartTime, sortEndTime);
        printTime("Time taken", binaryStartTime, binaryEndTime);
    }

    public static void printTime(String prefix, long startTime, long endTime) {
        long duration = endTime - startTime;
        long milliseconds = duration % 1000;
        duration /= 1000;
        long seconds = duration % 60;
        duration /= 60;
        long minutes = duration % 60;

        System.out.printf(prefix + ": %d min. %d sec. %d ms.\n", minutes, seconds, milliseconds);
    }

    public static Stream<String> createStream(String str) {
        try {
            return Files.lines(Paths.get(str));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}

