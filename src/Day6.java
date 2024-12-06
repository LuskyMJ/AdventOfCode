import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./Files/day6.txt");
        Scanner scanner = new Scanner( file );
        ArrayList<String> data = new ArrayList<String>();
        int guardX = 0;
        int guardY = 0;
        int counter = 0;

        ArrayList<IntVector> collisionPoints = new ArrayList<IntVector>();

        // Parsing data
        while ( scanner.hasNextLine() ) {
            data.add( scanner.nextLine() );
        }

        // Finding position of guard
        for (int i = 0; i < data.size(); i++) {
            int guardPos = data.get(i).indexOf("^");
            if ( guardPos != -1 ) {
                guardX = guardPos;
                guardY = i;
            }
        }
        collisionPoints.add( new IntVector(guardX, guardY) );

        boolean[][] locationsVisited = new boolean[data.size()][data.getFirst().length()];
        locationsVisited[guardY][guardX] = true;
        int directionX = 0;
        int directionY = -1;

        // Travelling
        while ( true ) {
            int nextX = guardX + directionX;
            int nextY = guardY + directionY;

            // Will go out of bounds
            if ( nextX < 0 || nextX >= data.getFirst().length() || nextY < 0 || nextY >= data.getFirst().length() ) {
                collisionPoints.add( new IntVector(guardX, guardY) );
                break;
            }

            // Obstacle in front. Turn
            if ( data.get(nextY).charAt(nextX) == '#') {
                IntVector rotated = new IntVector(directionX, directionY).turn();
                directionX = rotated.x;
                directionY = rotated.y;
                collisionPoints.add( new IntVector(guardX, guardY) );
            }

            // Move the guard
            else {
                guardX = nextX;
                guardY = nextY;
                locationsVisited[guardY][guardX] = true;
            }
        }
    }
}
