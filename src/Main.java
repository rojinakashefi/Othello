
public class Main {

    public static void main(String[] args) {
        Game game=new Game();
        GameBoard gameBoard=new GameBoard();
        gameBoard.initializingGameBoard();
        game.showRecommendingCells(game.showRecommend());
        game.putPiece(4,6,"white");
        GameBoard.printingGameBoard();
        System.out.println("\n\n");
        game.change(4,6,"white");
        GameBoard.printingGameBoard();
        System.out.println("\n\n");
        game.showRecommendingCells(game.showRecommend());
        game.putPiece(5,6,"black");
        System.out.println("\n\n");
        GameBoard.printingGameBoard();
        System.out.println("\n\n");
        game.showRecommendingCells(game.showRecommend());

    }
}
