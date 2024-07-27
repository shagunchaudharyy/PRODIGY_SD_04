import java.util.Scanner;

public class SudokuSolver {

    private static final int SIZE = 9;
    private static final int SUBGRID_SIZE = 3;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int[][] grid = new int[SIZE][SIZE];
            
            System.out.println("Enter the Sudoku grid (use 0 for empty cells):");
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    grid[i][j] = scanner.nextInt();
                }
            }

            if (solveSudoku(grid)) {
                System.out.println("Solved Sudoku:");
                printGrid(grid);
            } else {
                System.out.println("No solution exists.");
            }
        }
    }

    private static boolean solveSudoku(int[][] grid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(grid, row, col, num)) {
                            grid[row][col] = num;
                            if (solveSudoku(grid)) {
                                return true;
                            }
                            grid[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // No valid number found
                }
            }
        }
        return true; // Puzzle solved
    }

    private static boolean isValid(int[][] grid, int row, int col, int num) {
        // Check row
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == num) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = (row / SUBGRID_SIZE) * SUBGRID_SIZE;
        int startCol = (col / SUBGRID_SIZE) * SUBGRID_SIZE;
        for (int i = 0; i < SUBGRID_SIZE; i++) {
            for (int j = 0; j < SUBGRID_SIZE; j++) {
                if (grid[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printGrid(int[][] grid) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
                if ((j + 1) % SUBGRID_SIZE == 0 && j < SIZE - 1) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((i + 1) % SUBGRID_SIZE == 0 && i < SIZE - 1) {
                System.out.println("---------------------");
            }
        }
    }
}
