import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    private static boolean isDay2Safe(ArrayList<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            ArrayList<Integer> clone = (ArrayList<Integer>) report.clone();
            clone.remove(i);
            if ( isSafe(clone) ) return true;
        }
        return false;
    }

    private static boolean isSafe(ArrayList<Integer> report) {
        boolean isAscending = true;

        for (int i = 0; i < report.size()-1; i++) {
            int first = report.get(i);
            int second = report.get(i+1);

            // Use first iteration to check for ascending/descending order
            if ( i == 0 ) isAscending = first < second;

            int diff = Math.abs( first - second );
            if ( diff < 1 || diff > 3 ) return false;
            if ( first > second && isAscending ) return false;
            if ( first < second && !isAscending ) return false;
        }

        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./Files/Day2.txt");
        Scanner fileParser = new Scanner(file);
        int safeReports = 0;

        // Looping through reports
        while (fileParser.hasNextLine()) {
            String line = fileParser.nextLine();
            Scanner lineParser = new Scanner(line);

            // Parsing report
            ArrayList<Integer> report = new ArrayList<>();
            while (lineParser.hasNextInt()) {
                report.add(lineParser.nextInt());
            }

            if ( isDay2Safe(report) ) safeReports++;
        }

        System.out.println(safeReports);
    }
}