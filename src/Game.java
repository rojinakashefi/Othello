
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Player black;
    Player white;
    GameBoard gameBoard;
    Scanner scanner;
    String turn = "";

    public Game() {
        gameBoard = new GameBoard();
        scanner = new Scanner(System.in);
        gameBoard.initializingGameBoard();
        start();
    }

    public void start() {
        gettingInfo();
        //shart payan kar
        while (true) {
            GameBoard.printingGameBoard();
            turn = changeTurn();
            if (turn.equals("white")) {
                System.out.println("\nPlayer " + white.getName() + "(" + white.getColor() + ")" + ":\nThese are your available moves\n");
            } else {
                System.out.println("Player " + black.getName() + " (" + black.getColor() + ")");
            }
            showRecommendingCells(showRecommend(turn));
            System.out.println("\nplease enter your next move:");
            System.out.println("x:");
            int x = scanner.nextInt();
            System.out.println("y:");
            int y = scanner.nextInt();
            putPiece(x, y, turn);
            change(x, y, turn);
            if (finished()) {
                //score elam kone
                break;
            }
        }
    }

    public void gettingInfo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter white player name:");
        String player1name = scan.nextLine();
        white = new Player(player1name, "white");
        System.out.println("Please enter black player name: ");
        String player2name = scan.nextLine();
        black = new Player(player2name, "black");
    }


    public void showRecommendingCells(ArrayList<Cell> cells) {
        for (Cell cell : cells) {
            int x = cell.getX() + 1;
            int y = cell.getY() + 1;
            System.out.println("(" + x + "," + y + ")");
        }
    }

    public String changeTurn() {
        if (turn.isEmpty())
            return "white";
        if (turn.equalsIgnoreCase("white")) {
            return "black";
        } else return "white";
    }

    public ArrayList<Cell> showRecommend(String turn) {
        ArrayList<Cell> recommend = new ArrayList<>();
        for (int i = 0; i < GameBoard.board.length; i++) {
            for (int j = 0; j < GameBoard.board.length; j++) {
                if (GameBoard.board[i][j].getPiece() != null) {
                    if (GameBoard.board[i][j].getPiece().getColor().equals(turn)) {
                        String reversedColor = changeColor(turn);
                        //payin
                        if (GameBoard.board[i + 1][j].getPiece() != null &&
                                GameBoard.board[i + 1][j].getPiece().getColor().equals(reversedColor)) {
                            for (int k = i + 2; k < GameBoard.board.length; k++) {
                                if (GameBoard.board[k][j].getPiece() == null) {
                                    recommend.add(GameBoard.board[k][j]);
                                    break;
                                } else if (GameBoard.board[k][j].getPiece().getColor().equals(turn)) {
                                    break;
                                }
                            }
                        }
                        //bala
                        if (GameBoard.board[i - 1][j].getPiece() != null &&
                                GameBoard.board[i - 1][j].getPiece().getColor().equals(reversedColor)) {
                            for (int k = i - 1; k >= 0; k--) {
                                if (GameBoard.board[k][j].getPiece() == null) {
                                    recommend.add(GameBoard.board[k][j]);
                                    break;
                                } else if (GameBoard.board[k][j].getPiece().getColor().equals(turn)) {
                                    break;
                                }
                            }
                        }
                        //rast
                        if (GameBoard.board[i][j + 1].getPiece() != null &&
                                GameBoard.board[i][j + 1].getPiece().getColor().equals(reversedColor)) {
                            for (int k = j + 2; k < GameBoard.board.length; k++) {
                                if (GameBoard.board[i][k].getPiece() == null) {
                                    recommend.add(GameBoard.board[i][k]);
                                    break;
                                } else if (GameBoard.board[i][k].getPiece().getColor().equals(turn)) {
                                    break;
                                }
                            }
                        }
                        //chap
                        if (GameBoard.board[i][j - 1].getPiece() != null &&
                                GameBoard.board[i][j - 1].getPiece().getColor().equals(reversedColor)) {
                            for (int k = j - 1; k >= 0; k--) {
                                if (GameBoard.board[i][k].getPiece() == null) {
                                    recommend.add(GameBoard.board[i][k]);
                                    break;
                                } else if (GameBoard.board[i][k].getPiece().getColor().equals(turn)) {
                                    break;
                                }
                            }
                        }
                        //payin rast
                        if (GameBoard.board[i + 1][j + 1].getPiece() != null &&
                                GameBoard.board[i + 1][j + 1].getPiece().getColor().equals(reversedColor)) {
                            int ki = i + 1;
                            int kj = j + 1;
                            while (ki != GameBoard.board.length || kj != GameBoard.board.length) {
                                if (GameBoard.board[ki][kj].getPiece() == null) {
                                    recommend.add(GameBoard.board[ki][kj]);
                                    break;
                                } else if (GameBoard.board[ki][kj].getPiece().getColor().equals(turn)) {
                                    break;
                                }
                                ki++;
                                kj++;
                            }
                        }
                        //bala rast
                        if (GameBoard.board[i - 1][j + 1].getPiece() != null &&
                                GameBoard.board[i - 1][j + 1].getPiece().getColor().equals(reversedColor)) {
                            int ki = i - 2;
                            int kj = j + 2;
                            while (ki != -1 || kj != GameBoard.board.length) {
                                if (GameBoard.board[ki][kj].getPiece() == null) {
                                    recommend.add(GameBoard.board[ki][kj]);
                                    break;
                                } else if (GameBoard.board[ki][kj].getPiece().getColor().equals(turn)) {
                                    break;
                                }
                                ki--;
                                kj++;
                            }
                        }
                        //bala chap
                        if (GameBoard.board[i - 1][j - 1].getPiece() != null &&
                                GameBoard.board[i - 1][j - 1].getPiece().getColor().equals(reversedColor)) {
                            int ki = i - 2;
                            int kj = j - 2;
                            while (ki != -1 || kj != -1) {
                                if (GameBoard.board[ki][kj].getPiece() == null) {
                                    recommend.add(GameBoard.board[ki][kj]);
                                    break;
                                } else if (GameBoard.board[ki][kj].getPiece().getColor().equals(turn)) {
                                    break;
                                }
                                ki--;
                                kj--;
                            }
                        }
                        //payin chap
                        if (GameBoard.board[i + 1][j - 1].getPiece() != null &&
                                GameBoard.board[i + 1][j - 1].getPiece().getColor().equals(reversedColor)) {
                            int ki = i + 2;
                            int kj = j - 2;
                            while (ki != GameBoard.board.length || kj != -1) {
                                if (GameBoard.board[ki][kj].getPiece() == null) {
                                    recommend.add(GameBoard.board[ki][kj]);
                                    break;
                                } else if (GameBoard.board[ki][kj].getPiece().getColor().equals(turn)) {
                                    break;
                                }
                                ki++;
                                kj--;
                            }
                        }
                    }
                }
            }
        }
        return recommend;
    }


    public void putPiece(int x, int y, String color) {
        System.out.println("\n");
        GameBoard.board[x - 1][y - 1].setPiece(new Piece(color));
        GameBoard.printingGameBoard();
    }

    public void change(int x, int y, String color) {
        String reversed = changeColor(color);
        for (int i = x - 2; i >= 0; i--) {
            //ertefa ro be bala
            if (GameBoard.board[i][y - 1].getPiece() != null && GameBoard.board[i][y - 1].getPiece().getColor().equals(reversed)) {
                for (int k = i - 1; k >= 0; k--) {
                    if (GameBoard.board[k][y - 1].getPiece() != null && GameBoard.board[k][y - 1].getPiece().getColor().equals(turn)) {
                        for (int p = k; p < i + 1; p++) {
                            GameBoard.board[p][y - 1].getPiece().setColor(turn);
                        }
                    }
                }
            }
        }
        //ofoghi ro be chap
        for (int j = y - 2; j >= 0; j--) {
            if (GameBoard.board[x - 1][j].getPiece() != null && GameBoard.board[x - 1][j].getPiece().getColor().equals(reversed)) {
                for (int k = j - 1; k >= 0; k--) {
                    if (GameBoard.board[x - 1][k].getPiece() != null && GameBoard.board[x - 1][k].getPiece().getColor().equals(turn)) {
                        for (int p = k; p <= j + 1; p++) {
                            GameBoard.board[x - 1][p].getPiece().setColor(turn);
                        }
                    }
                }
            }
        }
        //ofoghi ro be rast
        for (int j = y; j < GameBoard.board.length; j++) {
            if (GameBoard.board[x - 1][j].getPiece() != null && GameBoard.board[x - 1][j].getPiece().getColor().equals(reversed)) {
                for (int k = j + 1; k < GameBoard.board.length; k++) {
                    if (GameBoard.board[x - 1][k].getPiece() != null && GameBoard.board[x - 1][k].getPiece().getColor().equals(turn)) {
                        for (int p = k; p >= j; p--) {
                            GameBoard.board[x - 1][p].getPiece().setColor(turn);
                        }
                    }
                }
            }

        }
        for (int i = x; i < GameBoard.board.length; i++) {
            //ertefa ro be payin
            if (GameBoard.board[i][y - 1].getPiece() != null && GameBoard.board[i][y - 1].getPiece().getColor().equals(reversed)) {
                for (int k = i + 1; k < GameBoard.board.length; k++) {
                    if (GameBoard.board[k][y - 1].getPiece() != null && GameBoard.board[k][y - 1].getPiece().getColor().equals(turn)) {
                        for (int p = k; p >= i; p--) {
                            GameBoard.board[p][y - 1].getPiece().setColor(turn);
                        }
                    }
                }
            }
        }
        //ghotri \>(az bala chap be payin rast
        if (x != GameBoard.board.length && y != GameBoard.board.length) {
            if (GameBoard.board[x][y].getPiece() != null && GameBoard.board[x][y].getPiece().getColor().equals(reversed)) {
                int ki = x + 1;
                int kj = y + 1;
                while (ki != GameBoard.board.length && kj != GameBoard.board.length) {
                    if (GameBoard.board[ki][kj].getPiece() != null && GameBoard.board[ki][kj].getPiece().getColor().equals(turn)) {
                        int k = x;
                        int p = y;
                        while (k != GameBoard.board.length && k <= ki && p != GameBoard.board.length && p <= kj) {
                            GameBoard.board[k][p].getPiece().setColor(turn);
                            k++;
                            p++;
                        }
                    }
                    ki++;
                    kj++;
                }
            }
        }
        //<\(az payin rast be bala chap)
        if (x - 2 != GameBoard.board.length && y - 2 != GameBoard.board.length) {
            if (GameBoard.board[x - 2][y - 2].getPiece() != null && GameBoard.board[x - 2][y - 2].getPiece().getColor().equals(reversed)) {
                int ki = x - 3;
                int kj = y - 3;
                while (ki > -1 && kj > -1) {
                    if (GameBoard.board[ki][kj].getPiece() != null && GameBoard.board[ki][kj].getPiece().getColor().equals(turn)) {
                        int k = x - 1;
                        int p = y - 1;
                        while (k > -1 && k >= ki && p > -1 && p >= kj) {
                            GameBoard.board[k][p].getPiece().setColor(turn);
                            k--;
                            p--;
                        }
                    }
                    ki--;
                    kj--;
                }
            }
        }
        //ghortir/>(az payin chap be bala rast)
        //x-2=>x-1
        if (x - 2 > -1) {
            if (GameBoard.board[x - 2][y].getPiece() != null && GameBoard.board[x - 2][y].getPiece().getColor().equals(reversed)) {
                int ki = x - 3;
                int kj = y + 1;
                while (ki > -1 && kj < GameBoard.board.length) {
                    if (GameBoard.board[ki][kj].getPiece() != null && GameBoard.board[ki][kj].getPiece().getColor().equals(turn)) {
                        int k = x - 1;
                        int p = y - 1;
                        while (k > -1 && k >= ki && p > -1 && p <= kj) {
                            GameBoard.board[k][p].getPiece().setColor(turn);
                            k--;
                            p++;
                        }
                    }
                    ki--;
                    kj++;
                }
            }
        }
        if (y - 2 > -1) {
            if (GameBoard.board[x][y - 2].getPiece() != null && GameBoard.board[x][y - 2].getPiece().getColor().equals(reversed)) {
                int ki = x + 1;
                int kj = y - 3;
                while (ki != GameBoard.board.length && kj > -1) {
                    if (GameBoard.board[ki][kj].getPiece() != null && GameBoard.board[ki][kj].getPiece().getColor().equals(turn)) {
                        int k = x - 1;
                        int p = y - 1;
                        while (k != GameBoard.board.length && k <= ki && p > -1 && p >= kj) {
                            GameBoard.board[k][p].getPiece().setColor(turn);
                            k++;
                            p--;
                        }
                    }
                    ki++;
                    kj--;
                }
            }
        }
        System.out.println("\n\nChanged\n");
        GameBoard.printingGameBoard();
    }

    public String changeColor(String color) {
        if (color.equals("white")) {
            return "black";
        }
        return "white";
    }

    public boolean finished() {
        int whiteScore = 0;
        int blackScore = 0;
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                if (GameBoard.board[i][k].getPiece() == null) {
                    return false;
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameBoard.board[i][j].getPiece().getColor().equals("white")) {
                    whiteScore++;
                } else {
                    blackScore++;
                }
            }
        }
        System.out.println(whiteScore);
        System.out.println(blackScore);
        return true;
    }
}

