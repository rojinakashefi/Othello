
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Player black;
    Player white;
    String turn = "";

    public Game() {
    }

    public void gettingInfo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter black player name:");
        String player1name = scan.nextLine();
        black = new Player(player1name, "white");
        System.out.println("Please enter White player name: ");
        String player2name = scan.nextLine();
        white = new Player(player2name, "black");
    }


    public void showRecommendingCells(ArrayList<Cell> cells) {
        GameBoard.printingGameBoard();
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

    public ArrayList<Cell> showRecommend() {
        ArrayList<Cell> recommend = new ArrayList<>();
        turn = changeTurn();
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
                        if (GameBoard.board[i -1][j - 1].getPiece() != null &&
                                GameBoard.board[i -1][j - 1].getPiece().getColor().equals(reversedColor)) {
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
                        if (GameBoard.board[i + 1 ][j - 1].getPiece() != null &&
                                GameBoard.board[i +1 ][j - 1].getPiece().getColor().equals(reversedColor)) {
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
        GameBoard.board[x - 1][y - 1].setPiece(new Piece(color));
    }

    public void change(int x, int y, String color) {
        for (int i = x - 2; i >= 0; i--) {
            //ertefa//taghir rang sefid
            if (GameBoard.board[i][y - 1].getPiece() != null && GameBoard.board[i][y - 1].getPiece().getColor().equals("black")) {
                for (int k = i - 1; k >= 0; k--) {
                    if (GameBoard.board[k][y - 1].getPiece() != null && GameBoard.board[k][y - 1].getPiece().getColor().equals("white")) {
                        for (int p = k; p < i + 1; p++) {
                            GameBoard.board[p][y - 1].getPiece().setColor("white");
                        }
                    }
                }
            }
        }
        for (int j = y - 2; j >= 0; j--) {
            if (GameBoard.board[x - 1][j].getPiece() != null && GameBoard.board[x - 1][j].getPiece().getColor().equals("black")) {
                for (int k = j - 1; k >= 0; k--) {
                    if (GameBoard.board[x - 1][k].getPiece() != null && GameBoard.board[x - 1][k].getPiece().getColor().equals("white")) {
                        for (int p = k; p <= j + 1; p++) {
                            GameBoard.board[x - 1][p].getPiece().setColor("white");
                        }
                    }
                }
            }
        }
    }

    public String changeColor(String color) {
        if (color.equals("white")) {
            return "black";
        }
        return "white";
    }
}

