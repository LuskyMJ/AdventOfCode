import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        char[] toSearch = new char[] {'X','M','A','S'};
        int searchLength = toSearch.length;
        int lineLength;

        // Parsing file
        ArrayList<String> lines = new ArrayList<String>();
        Scanner scanner = new Scanner( new File("./src/day4.txt") );
        while ( scanner.hasNextLine() ) {
            lines.add( scanner.nextLine() );
        }

        lineLength = lines.getFirst().length();

        for (int lineY = 0; lineY < lines.size(); lineY++) {
            String line = lines.get( lineY );
            for (int lineX = 0; lineX < lineLength; lineX++) {
                // Inside specific character in set
                if ( !line.charAt(lineX).equals('X') ) continue;

                // Loop through all directions where we can check for wordmatch
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        if (x == 0 && y == 0) continue; // Invalid direction
                        if ( lineX - searchLength-1 < 0 || lineX + searchLength-1 > lineLength-1) continue; // Travelling through direction will go out of bounds

                    }
                }

            }
        }
    }
}