package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int[][] sudoku = new int[9][9];

        makeSudoku(sudoku);

    }


    public static void displaySudoku(int[][] sudoku) {

        for (int[] matrix : sudoku) {

            System.out.println(Arrays.toString(matrix));

        }
    }

    public static void makeSudoku(int[][] sudoku) {

        System.out.println("Enter the horizontal lines starting from the top.\nFrom left to right, " +
                "without spaces between numbers, type zero if square is empty e.g. \"001234080\".");

        for(int i = 0; i < 9; i++) {

            boolean isLengthNotValid;
            boolean isDataTypeNotValid;
            int[] sudokuRow = new int[9];

            do {

                int rowNumber = i + 1;

                isLengthNotValid = false;
                isDataTypeNotValid = false;

                System.out.println("Enter line no. " + rowNumber + ": ");

                var scanner = new Scanner(System.in);
                String row = scanner.nextLine();

                if (row.length() != 9) {

                    System.out.println("Wrong length of line, please type 9 digits");

                    isLengthNotValid = true;

                }

                for(int j = 0; j < row.length(); j++) {

                    try {

                        sudokuRow[j] = Integer.parseInt(String.valueOf(row.charAt(j)));

                    } catch (NumberFormatException e) {

                        System.out.println("Wrong data format");

                        isDataTypeNotValid = true;
                        break;

                    }

                }

            }while(isLengthNotValid || isDataTypeNotValid);

            fillSudokuRow(sudoku, sudokuRow, i);

        }

        displaySudoku(sudoku);

        if(!validateInputSudoku(sudoku)) {

            System.out.println("data validation failed :/, try again");
            System.exit(1);

        }

        System.out.println("Work correct");
    }

    private static void fillSudokuRow(int[][] sudoku, int[] sudokuRow, int row) {

        System.arraycopy(sudokuRow, 0, sudoku[row], 0, sudokuRow.length);

    }

    private static boolean validateInputSudoku(int[][] sudoku) {

        boolean rowValidate;
        boolean columnValidate;
        boolean squareValidate;

        for(int i = 0; i < sudoku.length; i++) {

            rowValidate = checkIfUniqueValuesInRow(sudoku, i);
            columnValidate = checkIfUniqueValuesInColumn(sudoku, i);

            if(!(rowValidate && columnValidate)) return false;
        }

        for(int i = 0; i < 3; i++) {

            for(int j = 0; j < 3; j++) {

               squareValidate = checkIfUniqueValuesInSquare(sudoku, i, j);

               if(!squareValidate) return false;

            }
        }

        return true;

    }


    private static boolean checkIfUniqueValuesInRow(int[][] sudoku, int rowIndex) {

        for (int i = 0; i < sudoku.length; i++) {

            if (sudoku[rowIndex][i] == 0) continue;

            for (int j = 0; j < sudoku.length; j++) {

                if (sudoku[rowIndex][i] == sudoku[rowIndex][j] && i != j) {

                    return false;

                }
            }
        }

        return true;

    }

    private static boolean checkIfUniqueValuesInColumn(int[][] sudoku, int columnIndex) {

        for (int i = 0; i < sudoku.length; i++) {

            if(sudoku[i][columnIndex] == 0) continue;

            for(int j = 0; j < sudoku.length; j++) {

                if(sudoku[i][columnIndex] == sudoku[j][columnIndex] && i != j) {

                    return false;

                }
            }
        }

        return true;

    }

    public static boolean checkIfUniqueValuesInSquare(int[][] sudoku, int x, int y) {

        for (int i = x * 3; i < (x * 3) + 3; i++) {

            for (int j = y * 3; j < (y * 3) + 3; j++) {

                if (sudoku[i][j] == 0) continue;

                for (int k = x * 3; k < (x * 3) + 3; k++) {

                    for (int l = y * 3; l < (y * 3) + 3; l++) {

                        if (sudoku[i][j] == sudoku[k][l] && i != k && j != l) {

                            return false;

                        }
                    }
                }
            }
        }

        return true;

    }
}
