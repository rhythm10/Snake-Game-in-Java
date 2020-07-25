import javax.swing.JFrame;
import java.awt.Color;

public class Main{
    public static void main(String args[]){

        JFrame f = new JFrame();
        GamePlay game=new GamePlay();
        f.getContentPane().add(game);
        f.setBounds(10,10,905,700);
        f.setBackground(Color.DARK_GRAY);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}