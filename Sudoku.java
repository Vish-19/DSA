package Placements.BackTracking;
public class Sudoku{
    public static boolean isSafe(char[][] board, int row, int col, int number) {
       //column
       for(int i=0; i<board.length; i++) {
           if(board[i][col] == (char)(number+'0')) {
               return false;
           }
       }
      
       //row
       for(int j=0; j<board.length; j++) {
           if(board[row][j] == (char)(number+'0')) {
               return false;
           }
       }
      
       //grid
       int sr = 3 * (row/3);
       int sc = 3 * (col/3);
      
       for(int i=sr; i<sr+3; i++) {
           for(int j=sc; j<sc+3; j++) {
               if(board[i][j] == (char)(number+'0')) {
                   return false;
               }
           }
       }


        return true;
    }
    public static boolean sudoku(char[][] board, int row, int col) {
       if(row == board.length) {
           return true; //if row reaches 9 we have our solution
       }
      
       int nrow = 0; //next row
       int ncol = 0; //next column
      
       if(col == board.length-1) { //If we reach the end of the board column wise
           nrow = row + 1; // We go to the next row
           ncol = 0;       // We start from column 0 again
       } else {  //If we don't reach the end of the board column wise
           nrow = row;     // We stay in the same row
           ncol = col + 1; // We move to the next column
       }
      
       if(board[row][col] != '.') { //If the (row, col) is not empty, '.' implies empty
           if(sudoku(board, nrow, ncol)) {
               return true;
           }
       } else {
          
           //fill the place
           for(int i=1; i<=9; i++) {
               if(isSafe(board, row, col, i)) {
                   board[row][col] = (char)(i+'0');
                   if(sudoku(board, nrow, ncol))
                       return true;
                   else
                        board[row][col] = '.';
               }
           }
       }
                     
       return false;
   }
    public static void main(String[] args) {
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '.', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '9'}
        };
        if(sudoku(board, 0, 0)){
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    System.out.print(board[i][j] + " ");
                }
            System.out.println();
            }
        }
        else{
            System.out.println("No Solution");
        }
        
    }
}