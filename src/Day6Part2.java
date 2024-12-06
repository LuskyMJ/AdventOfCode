import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        IntVector ogGuardPos = new IntVector(0, 0);
        int obstacleCount = 0;

        // Parsing data
        File file = new File("./Files/day6.txt");
        Scanner scanner = new Scanner( file );
        ArrayList<String> data = new ArrayList<String>();
        while ( scanner.hasNextLine() ) {
            data.add( scanner.nextLine() );
        }
        int length = data.getFirst().length();

        // Finding position of guard
        for (int i = 0; i < data.size(); i++) {
            int guardPosIndex = data.get(i).indexOf("^");
            if ( guardPosIndex != -1 ) {
                ogGuardPos.x = guardPosIndex;
                ogGuardPos.y = i;
            }
        }

        // Go through every combination
        for (int y = 0; y < data.size(); y++) {
            for (int x = 0; x < data.getFirst().length(); x++) {
                if (data.get(y).charAt(x) == '#') continue;

                // For tracking collisions
                ArrayList<IntVector> collisions = new ArrayList<IntVector>();
                ArrayList<IntVector> collisionDirections = new ArrayList<IntVector>();

                // Setup
                IntVector addedObj = new IntVector(x, y);
                IntVector direction = new IntVector(0, -1);
                IntVector guardPos = new IntVector(ogGuardPos.x, ogGuardPos.y);

                // Walk through
                while ( true ) {
                    IntVector next = guardPos.add( direction );

                    // Will go out of bounds
                    if ( next.x < 0 || next.x >= length || next.y < 0 || next.y >= length )
                    {
                        break;
                    }

                    // Obstacle in front. Check if we have looped. Note collision. Turn
                    if ( data.get(next.y).charAt(next.x) == '#' || ( next.equals(addedObj)) ) {

                        // Check if we have looped
                        boolean duplicateCollision = false;
                        ArrayList<Integer> indexes = new ArrayList<Integer>();
                        // // Check for all collisions on position
                        for (int i = 0; i < collisions.size(); i++) {
                            if (collisions.get(i).equals( guardPos ) ) indexes.add(i);
                        }
                        // // Check if we were facing the same way last time
                        for (Integer number: indexes) {
                            if ( collisionDirections.get(number).equals( direction ) ) {
                                duplicateCollision = true;
                                break;
                            }
                        }
                        // // We've looped
                        if ( duplicateCollision ) {
                            obstacleCount++;
                            break;
                        }

                        // Note new collision
                        else {
                            collisions.add( guardPos );
                            collisionDirections.add( direction );
                            direction = direction.turn();
                        }
                    }
                    else guardPos = next;
                }
            }
        }
        System.out.println(obstacleCount);
    }
}
