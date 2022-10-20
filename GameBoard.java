import java.lang.reflect.Array;

public class GameBoard {
    /**
     * The number of rows in the board.
     */
    private static final int numRows = 15;
    /**
     * The number of columns in the board.
     */
    private static final int numColumns = 15;
    /** The squares on the board. */
    private Square[][] squares;

    public GameBoard(){
        squares = new Square[numRows][numColumns];
        buildGameBoard();
    }

    /** TODO:
     * Populates the board with squares numbered 1, 2, 3, ..., numRows and numColumns.
     * The square numbered "n" is stored in squares[n-1].
     */
    private void buildGameBoard() {

    }
    private char[][] gameboard = {};
    public void printGameBoard(){
        System.out.println("\tA | B | C | D | E | F | G | H | I | J | K | L | M | N | O");
        System.out.println("------|---|---|---|---|---|---|---|---|---|---|---|---|---|---");
        for (int i = 0; i <= 15; i++){
            if (i > 10){
                System.out.println(i + "    +   +   +   +   +   +   +   +   +   +   +   +   +   +   ");
                System.out.println("------|---|---|---|---|---|---|---|---|---|---|---|---|---|---");
            }else {
                System.out.println(i + "     +   +   +   +   +   +   +   +   +   +   +   +   +   +   ");
                System.out.println("------|---|---|---|---|---|---|---|---|---|---|---|---|---|---");
            }
        }
    }

    public static void main(String[] args) {
        //GameBoard game = new GameBoard();
        //game.printGameBoard();
        int[][] squares = new int[3][3];
        squares[0][0] = 1;
        squares[0][1] = 1;
        squares[0][2] = 1;
        for(int i = 0; i<3;i++){
            for (int j = 0; j < 3; j++){
                System.out.println(squares[i][j]);
            }
        }
    }
}
