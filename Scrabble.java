public class Scrabble {
    private Player player;
    private Bag bag;
    private int turn = 0;
    private GameBoard board;
    private Dictionary dictionary;


    public Scrabble(){
        dictionary = new Dictionary();
        bag = new Bag();
        board =new GameBoard();

    }

    public static void main(String[] args){
        Scrabble game = new Scrabble();
    }


}
