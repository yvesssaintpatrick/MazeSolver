/**
 *This code is a Java program that reads from a text file containing a maze,
 * and then solves that maze if possible.
 *
 * @author  Shawn Lartey
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MazeSolver {

    private static final String FILENAME = "src/main/java/maze3.txt";
    static char[][] array;
    static int rows = 0;
    static int cols = 0;

    /*
    The Position class is responsible for holding the values of the rows and 
    columns being traversed by the stack.
    */
    private static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        File myFile = new File(FILENAME);

        try {//file opens should be in try block to catch
            //exceptions, such as when file is not found
            Scanner input = new Scanner(myFile);
            
            //reads the file to determine the number of rows
            //and columns for the array
            while (input.hasNextLine()) {
                String line = input.nextLine();
                rows++;
                cols = line.length();
            }
            input.close();

            array = new char[rows][cols];
            
            // Read maze into a 2-d array
            Scanner scan = new Scanner(myFile);
            int startCol = 0;
            int startRow = 0;
            for (int i = 0; i < rows; i++) {
                String line = scan.nextLine();
                for (int j = 0; j < cols; j++) {
                    array[i][j] = line.charAt(j);
                    if (array[i][0] == ' ') {
                        startCol = 0;
                        startRow = i;
                    }
                }
            }
            scan.close();
            
            //this condition runs only if a solution is found else
            //it prints no solution found 
            if (Play()) {
                System.out.println("Solution found");
                printMaze(array);
            } else {
                System.out.println("No solution found");
            }

        } catch (FileNotFoundException e) {
            System.out.println("ERROR - File not found");
        }

    }

    /*
    This Play method solves the maze and finds one path to the end of the game
    */
    public static boolean Play() {

        int startPosition = findStart();
        int endPosition = findEnd();
        boolean mazeSolved = false;

        //condition to check if the start and end positions are not out of range 
        if (startPosition != -1 && endPosition != -1) {
            Stack<Position> stack = new Stack<>();
        //defines the cordinates of the start position and pushes it onto the stack
            Position startPos = new Position(startPosition, 0);
            stack.push(startPos);
            
            array[startPosition][0] = '.'; //places a dot at the start position
            
            
            while (!stack.isEmpty()) {
                int currentRow = stack.peek().row;
                int currentCol = stack.peek().col;
                
                array[currentRow][currentCol] = '.';//places a dot at the current position
                
                //condition that ends the game after the maze is solved
                if (currentCol == cols - 1) { 
                    mazeSolved = true;
                    return mazeSolved;    
                }
                
                //if there is an availble space to move, the current 
                //row and column are defined and pushed onto the stack
                else if(currentRow > 0 && array[currentRow - 1][currentCol] == ' ') {
                    
                    Position up = new Position(currentRow - 1, currentCol);
                    stack.push(up);//direction of the move
                }

                else if(currentRow < rows - 1 && array[currentRow + 1][currentCol] == ' ') {

                    Position down = new Position(currentRow + 1, currentCol);
                    stack.push(down);//direction of the move
                }

                else if(currentCol < cols - 1 && array[currentRow][currentCol + 1] == ' ') {
                    Position right = new Position(currentRow, currentCol + 1);
                    stack.push(right);//direction of the move
                }

                else if(currentCol > 0 && array[currentRow][currentCol - 1] == ' ') {

                    Position left = new Position(currentRow, currentCol - 1);
                    stack.push(left);//direction of the move
                }
                else{
                //backtracking so that places already visited are spotted
                stack.pop();
                array[currentRow][currentCol] = '?';
                }

            }
        }
        return mazeSolved;

    }

    /*
    returns the end position which is the intersection
    of the row and column that does not have an 'X'
    */
    public static int findEnd() {
        for (int i = 0; i < rows; i++) {
            if (array[i][cols - 1] == ' ') {
                int endCol = i;
                return endCol;
            }
        }
        return -1;

    }

    /*
    returns the start row which is the intersection
    of the row and column that does not have an 'X'
    */
    public static int findStart() {
        for (int i = 0; i < rows; i++) {
            if (array[i][0] == ' ') {
                int startRow = i;
                return startRow;
            }
        }
        return -1;

    }

    /*
    Prints out the maze with the path to the end of the game
    */
    public static void printMaze(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if(array[i][j] == '?'){
                    array[i][j] = ' ';
                }
                System.out.print(array[i][j]);
                
            }
            System.out.println();
        }
    }

}
