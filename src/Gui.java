import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener {
    JPanel panel;
    JButton[][] button = new JButton[8][8];
    Game game;
    JLabel label;

    public Gui() {
        panel = new JPanel();
        game = new Game();
        label=new JLabel();
        setSize(500, 500);
        GridLayout gridLayout = new GridLayout(8, 8);
        gridLayout.setHgap(0);
        gridLayout.setVgap(0);
        panel.setLayout(gridLayout);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                button[i][j] = new JButton();
                button[i][j].setText((i+1)+","+(j+1));
                if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
                    button[i][j].setOpaque(true);
                    button[i][j].setBorderPainted(false);
                    button[i][j].setBackground(Color.WHITE);
                } else if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
                    button[i][j].setOpaque(true);
                    button[i][j].setBorderPainted(false);
                    button[i][j].setBackground(Color.BLACK);
                }
                button[i][j].addActionListener(this);
                panel.add(button[i][j]);
            }
        }
        setLayout(new BorderLayout());
        setTitle("OTHELLO");
        panel.setBackground(new Color(20, 66, 20));
        add(panel,BorderLayout.CENTER);
        add(label,BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton b = (JButton) ae.getSource();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (button[i][j] == b) {
                    game.turn = game.changeTurn();
                    game.putPiece(i + 1, j + 1, game.turn);
                    game.change(i + 1, j + 1, game.turn);
                    update();
                    game.checkScore(game.turn);
                    if(game.finished()) {
                        if (game.turn.equals("white")) {
                            JOptionPane.showMessageDialog(null, "Game finished!!\nPlayer WHITE won with " + game.whiteScore + "score./",
                                    "End of game", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                    else{
                        String string="Turn:"+game.changeColor(game.turn)+"  ||||  "+"Player white score : "+ game.whiteScore+"  ||||  " +
                                "Player black score : "+game.blackScore;
                       label.setText(string);

                    }
                }
            }
        }
    }

    public void update() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameBoard.board[i][j].getPiece() != null) {
                    if (GameBoard.board[i][j].getPiece().getColor().equals("white")) {
                        button[i][j].setOpaque(true);
                        button[i][j].setBorderPainted(false);
                        button[i][j].setBackground(Color.WHITE);
                        button[i][j].setText((i+1)+","+(j+1));
                    } else {
                        button[i][j].setOpaque(true);
                        button[i][j].setBorderPainted(false);
                        button[i][j].setBackground(Color.BLACK);
                        button[i][j].setText((i+1)+","+(j+1));
                    }
                }
            }
        }

    }
}
