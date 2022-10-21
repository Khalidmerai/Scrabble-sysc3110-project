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
        for (int i = 0; i < numRows; i++){
            System.out.print(i + " ");
            for (int j = 0; j < numColumns; j++){ //prints value of square
                System.out.print(squares[i][j] + " ");
            }
            System.out.println();
            System.out.println("------|---|---|---|---|---|---|---|---|---|---|---|---|---|---");
        }
    }

    /** TODO
     * Populates the board with squares of Scrabble such as the
     * DLS, TLS, DWS, TWS, and regular squares.
     */
    private void buildSquares() {
        //Triple Word Squares
        for (int i = 0; i < numRows; i++){
            if (i == 0 || i == 14){
                squares[i][0] = new TripleLetterSquare(i, 0);
                squares[i][7] = new TripleLetterSquare(i, 7);
                squares[i][14] = new TripleLetterSquare(i, 14);
            }
            if (i == 7){
                squares[i][0] = new TripleLetterSquare(i, 0);
                squares[i][14] = new TripleLetterSquare(i, 14);
            }
        }

        //Triple Letter Squares
        for (int i = 0; i < numRows; i++){
            if (i == 1 || i == 13){
                squares[i][0] = new TripleLetterSquare(i, 5);
                squares[i][7] = new TripleLetterSquare(i, 9);
            }
            if (i == 5 || i == 9){
                squares[i][0] = new TripleLetterSquare(i, 1);
                squares[i][14] = new TripleLetterSquare(i, 5);
                squares[i][0] = new TripleLetterSquare(i, 9);
                squares[i][7] = new TripleLetterSquare(i, 13);
            }
        }

    }

    public static void main(String[] args) {
        GameBoard game = new GameBoard();
        game.printGameBoard();
        int[][] squares = new int[3][3];
        squares[0][0] = 1;
        squares[0][1] = 1;
        squares[0][2] = 1;
        for(int i = 0; i<3;i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(squares[i][j]);
            }
        }
    }
}
