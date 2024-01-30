Maze Solver
This Java program reads a maze from a text file and attempts to solve it. The maze is represented as a 2D array, and the solution is found using a stack-based algorithm.

Algorithm Overview

The solution is found using a stack-based algorithm. The program starts from the designated start position and explores possible moves until it reaches the end position or exhausts all possibilities. If a solution is found, the solved maze is printed; otherwise, a message indicating no solution is printed.

Classes and Methods

Position Class
A nested class Position is used to represent coordinates in the maze.

Play() Method
This method implements the maze-solving algorithm. It utilizes a stack to explore and backtrack through the maze until a solution is found.

findStart() and findEnd() Methods
These methods locate the starting and ending positions in the maze, respectively.

printMaze() Method
This method prints the maze with the solution path.

Error Handling
The program handles the case where the specified file is not found, printing an error message in such instances.
