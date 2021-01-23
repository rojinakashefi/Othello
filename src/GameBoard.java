
public class GameBoard {
    protected static Cell[][] board;

    public GameBoard() {
        board = new Cell[8][8];
    }

    public void initializingGameBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 3 && j == 3) {
                    board[i][j] = new Cell(i, j, new Piece(("white")));
                } else if (i == 3 && j ==4) {
                    board[i][j] = new Cell(i, j, new Piece(("black")));
                } else if (i == 4 && j == 3) {
                    board[i][j] = new Cell(i, j, new Piece(("black")));
                } else if (i == 4 && j == 4) {
                    board[i][j] = new Cell(i, j, new Piece(("white")));
                } else {
                    board[i][j] = new Cell(i, j, null);
                }
            }
        }
    }

    public static void printingGameBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.printf("--- ");
            }
            System.out.printf("\n");
            for (int k=0;k<8;k++){
                if(board[i][k].getPiece()==null){
                    System.out.printf("(%d%d)",i+1,k+1);
                }
                else if(board[i][k].getPiece().getColor().equals("black")){
                    System.out.print(" B  ");
                }
                else if(board[i][k].getPiece().getColor().equals("white")){
                    System.out.print(" W  ");
                }
            }
            System.out.print("\n");
        }
    }
    public Cell[][] getBoard() {
        return board;
    }
}
