# 🧠 Java Sudoku Solver using Backtracking

This repository contains a simple yet powerful console-based Sudoku solver written in Java. It uses a classic backtracking algorithm to find the solution for any valid 9x9 Sudoku puzzle.

## ✨ How It Works

The core of this program is a recursive strategy known as **backtracking**. Think of it as a smart trial-and-error process. Here’s the basic idea:

1. **Find an Empty Cell:** The solver scans the board to find the next empty spot (represented by a `.`).

2. **Try a Number:** It tries placing the number `1` in that cell.

3. **Check for Safety:** Before placing the number, it checks if the move is valid according to Sudoku rules. The `isSafe()` method ensures there are no duplicates in the current row, column, or 3x3 subgrid.

4. **Recurse:** If the move is safe, the solver calls itself to move on to the next empty cell and solve the rest of the puzzle.

5. **Backtrack:** If it reaches a point where no numbers can be safely placed in a cell, it means a previous placement was wrong. The solver "backtracks" to the previous cell, erases the number it placed, and tries the next higher number.

This process continues until a complete and valid solution is found.

## 🚀 How to Run the Code

1. **Clone the repository** or download the `SudokuSolver.java` file.

2. **Compile the Java file:**

   ```
   javac SudokuSolver.java
   ```

3. **Run the compiled code:**

   ```
   java SudokuSolver
   ```

4. The program will automatically use the hard-coded puzzle in the `main` method and print the solved board to the console.

## 🛠️ Customizing the Puzzle

To solve your own puzzle, simply edit the `char[][] board` array inside the `main` method in `SudokuSolver.java`. Replace the existing numbers with your puzzle's configuration, using a `.` for any empty cells.
