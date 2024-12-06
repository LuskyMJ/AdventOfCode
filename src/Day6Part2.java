import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./Files/day6.txt");
        Scanner scanner = new Scanner( file );
        ArrayList<String> data = new ArrayList<String>();
        IntVector guardPos = new IntVector(0, 0);
        IntVector direction = new IntVector(0, -1);
        ArrayList<IntVector> collisionPoints = new ArrayList<IntVector>();
        int obstacleCount = 0;

        // Parsing data
        while ( scanner.hasNextLine() ) {
            data.add( scanner.nextLine() );
        }
        int length = data.getFirst().length();

        // Finding position of guard
        for (int i = 0; i < data.size(); i++) {
            int guardPosIndex = data.get(i).indexOf("^");
            if ( guardPosIndex != -1 ) {
                guardPos.x = guardPosIndex;
                guardPos.y = i;
            }
        }
        collisionPoints.add( guardPos );

        // Travelling
        while ( true ) {
            IntVector next = guardPos.add( direction );

            // Will go out of bounds
            if ( next.x < 0 || next.x >= length || next.y < 0 || next.y >= length ) {
                break;
            }

            // Obstacle in front. Turn
            if ( data.get(next.y).charAt(next.x) == '#') {
                direction.turn(1);
                collisionPoints.add( guardPos );
            }

            // Create obstacle in front. Finish with actually moving
            else {
                System.out.println("Create obstacle");
                IntVector vObj = next;
                IntVector vDirection = direction.turn(1);
                IntVector vPos = guardPos;

                // Keep moving virtual guard
                while ( true ) {
                    IntVector vNext = vPos.add( vDirection );

                    if ( vNext.x < 0 || vNext.x >= length || vNext.y < 0 || vNext.y >= length ) {
                        break;
                    }

                    // Real obstacle in front
                    if ( data.get(next.y).charAt(next.x) == '#' ) {
                        if ( collisionPoints.contains( vPos ) ) {
                            System.out.println("Found loop!");
                            System.out.println( vObj );
                            vDirection.turn(1);
                            obstacleCount++;
                            break;
                        }
                        else collisionPoints.add( vPos );
                        direction.turn(1);
                    }

                    // Hit virtual wall
                    else if ( vNext.equals( vObj ) ) {
                        System.out.println("Found loop!");
                        System.out.println( vObj );
                        vDirection.turn(1);
                        obstacleCount++;
                        break;
                    }

                    vPos = vNext;
                }

                // Actually move the guard
                guardPos = next;
            }
        }
    }
}
