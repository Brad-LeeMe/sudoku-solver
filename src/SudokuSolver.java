import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SudokuSolver {
    private static final int GRID_SIZE = 9;
    private static final int MIN = 1;
    private static final int MAX = 9;
    private static final String FILE = "sudoku puzzle.txt";
    

    public static void main(String[] args) throws Exception {
        int[][] board = readInputFromFile();

        printBoard(board);

        if(solveBoard(board)){
            System.out.println("Solved Successfully");
        }else{
            System.out.println("Not Possible");
        }

        printBoard(board);


    }

    private static boolean isNumberInRow(int row, int number, int[][] board){
        for(int i=0; i<GRID_SIZE; i++){
            if(board[row][i] == number){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int column, int number, int[][] board){
        for(int i=0; i<GRID_SIZE; i++){
            if(board[i][column] == number){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int row, int column, int number, int[][] board){
        int boxRow = row - row % 3;
        int boxColumn = column - column % 3;
        for(int i = boxRow; i < boxRow+3; i++){
            for(int j = boxColumn; j < boxColumn+3; j++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int row, int column, int number, int[][] board){
        if(isNumberInRow(row, number, board) || 
            isNumberInColumn(column, number, board) ||
            isNumberInBox(row, column, number, board)){
                return false;
            }
        return true;
    }

   
    private static boolean solveBoard(int[][] board){
        for(int row = 0; row < GRID_SIZE; row++){
            for(int column = 0; column < GRID_SIZE; column++){
                if(board[row][column] != 0){
                    continue;
                }

                for(int number = MIN; number <= MAX; number++){
                    if(!isValidPlacement(row, column, number, board)){
                        continue;
                    }

                    board[row][column] = number;

                    if(solveBoard(board)){
                        return true;
                    }else{
                        board[row][column] = 0;
                    }
                }
                return false;

            }
        }

        return true;
    }

    private static void printBoard(int[][] board){
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                System.out.print(board[i][j]);
                if(j%3 == 2 && j < GRID_SIZE-1){
                    System.out.print("|");
                }
            }
            System.out.println();
            if(i%3 == 2 && i < GRID_SIZE-1){
                System.out.println("-----------");
            }
        }
    }

    private static int[][] readInputFromFile() throws IOException{
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        FileReader fReader = new FileReader(FILE);
        BufferedReader reader = new BufferedReader(fReader);

        String[] currentRow = new String[GRID_SIZE];

        for(int i=0; i<GRID_SIZE; i++){
            currentRow = reader.readLine().split("");
            
            for(int j=0; j<currentRow.length; j++){
                board[i][j] = Integer.parseInt(currentRow[j]);
            }
        }

        reader.close();

        return board;
    }

    /*
     private static boolean solveBoard(int[][] board){
        for(int row = 0; row < GRID_SIZE; row++){
            for(int column = 0; column<GRID_SIZE; column++){
                if(board[row][column] == 0){
                    for(int number = MIN; number <= MAX; number++){
                        if(isValidPlacement(row, column, number, board)){
                            board[row][column] = number;

                            if(solveBoard(board)){
                                return true;
                            }else{
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    */
}
