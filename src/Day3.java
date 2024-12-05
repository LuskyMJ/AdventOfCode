import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day3 {
    static int total = 0;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./Files/day3.txt");
        Pattern operationPattern = Pattern.compile("mul[(][0-9]{1,3},[0-9]{1,3}[)]");
        Pattern digitPattern = Pattern.compile("[0-9]{1,3}");
        Scanner fileReader = new Scanner(file);

        Stream<MatchResult> operations = fileReader.findAll( operationPattern );
        operations.forEach(o -> {
            Scanner scanner = new Scanner( o.group() );
            int first = Integer.parseInt( scanner.findInLine( digitPattern ) );
            int second = Integer.parseInt( scanner.findInLine( digitPattern ) );
            total += first * second;
        });

        System.out.println(total);
    }
}