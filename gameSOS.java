public class gameSOS {
    public enum Gamestage {LOGIC, OVERALL}
    private String winner;
    private char[][] board;
    private Gamestage stage;
    private char currentP;


    public gameSOS(int size) {
        board = new char[size][size];
        currentP = 'b';
    }

    public void makeCell (int row, int col, char value) {
        board[row][col] = value;
    }

    public boolean sosCheck(int row, int col) {
        char charAtCell = board[row][col];
        char oppChar = (charAtCell == 'S') ? 'O': 'S';

        //Vert Check
        if (row >= 2 && board[row - 2][col] == charAtCell &&
            board[row - 1][col] == oppChar &&
            board[row][col] == charAtCell) {
            return true;
        }

        //HorizCheck
        if (col >= 2 && board[row][col - 2] == charAtCell &&
            board[row][col - 1] == oppChar &&
            board[row][col] == charAtCell) {
            return true;
        }

        //left to right diagonal
        if (row >= 2 && col >= 2 &&
            board[row - 2][col - 2] == charAtCell &&
            board[row - 1][col - 1] == oppChar &&
            board[row][col] == charAtCell) {
            return true;
        }

        if (row <= board.length - 3 && col >= 2 &&
            board[row + 2][col - 2] == charAtCell &&
            board[row + 1][col - 1] == oppChar &&
            board[row][col] == charAtCell) {
            return true;
        }

        return false; 

    }    

    public void setGame (Gamestage mode){
        this.stage = mode;
    }

    public String getWinner(){
        return winner;
    }

    public char getCurrentPlayer(){
        return currentP;
    }

    public void flipP() {
        currentP = (currentP == 'b') ? 'r' : 'b';
    }    

}