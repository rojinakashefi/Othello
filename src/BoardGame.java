import javax.swing.*;
import java.awt.*;

public class BoardGame extends JFrame {
    JPanel panel;
    public BoardGame(){
        panel=new JPanel();
        setSize(500,500);
        GridLayout gridLayout=new GridLayout(8,8);
        gridLayout.setHgap(0);
        gridLayout.setVgap(0);
        panel.setLayout(gridLayout);
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                JButton button=new JButton();
                button.setMargin(new Insets(0,0,0,0));
                panel.add(button);
                if((i==3 && j==3) || (i==4 && j==4)){
                    button.setOpaque(true);
                    button.setBorderPainted(false);
                    button.setBackground(Color.WHITE);
                    continue;
                }
                if((i==3 && j==4) || (i==4 && j==3) ){
                    button.setOpaque(true);
                    button.setBorderPainted(false);
                    button.setBackground(Color.BLACK);
                    panel.add(button);
                    continue;
                }
            }
        }
        setTitle("OTHELLO");
        setContentPane(panel);
        getContentPane().setBackground(Color.GREEN);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
