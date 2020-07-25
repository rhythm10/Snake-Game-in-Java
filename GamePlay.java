import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

    private int[] snakexLength = new int[750];
    private int[] snakeyLength = new int[750];

    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon snakeimage;
    private ImageIcon titleImage;

    private int[] enemyxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};

    private ImageIcon enemyImage;
    private Random random = new Random();
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);

    private Timer timer;
    private int delay = 100;

    private int lengthOfSnake = 3;
    private int moves=0;
    private int score=0;

    public GamePlay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer = new Timer(delay,this);
        timer.start();
    }
    public void paint(Graphics g)
    {
        if(moves == 0)
        {
            snakexLength[0] = 100;
            snakexLength[1] = 75;
            snakexLength[2] = 50;

            snakeyLength[0] = 100;
            snakeyLength[1] = 100;
            snakeyLength[2] = 100;

        }
        //border of title
        g.setColor(Color.WHITE);
        g.drawRect(24,10,851,55);

        titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, g,25, 11);

        //border of gameplay
        g.setColor(Color.WHITE);
        g.drawRect(24, 74,851,577);
        g.setColor(Color.BLACK);
        g.fillRect(25,75,850,575);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Scores : " + score,780,30);

        g.drawString("Length : "+lengthOfSnake,780,50);

        rightmouth = new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this,g,snakexLength[0],snakeyLength[0]);

        for(int i=0;i<lengthOfSnake;i++)
        {
            if(i == 0 && right)
            {
                rightmouth = new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this,g,snakexLength[i],snakeyLength[i]);
            }
            if(i == 0 && left)
            {
                leftmouth = new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this,g,snakexLength[i],snakeyLength[i]);
            }
            if(i == 0 && up)
            {
                upmouth = new ImageIcon("upmouth.png");
                upmouth.paintIcon(this,g,snakexLength[i],snakeyLength[i]);
            }
            if(i == 0 && right)
            {
                downmouth = new ImageIcon("downmouth.png");
                downmouth.paintIcon(this,g,snakexLength[i],snakeyLength[i]);
            }
            if(i != 0)
            {
                snakeimage = new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this,g,snakexLength[i],snakeyLength[i]);
            }
        }

        enemyImage = new ImageIcon("enemy.png");

        if(enemyxpos[xpos] == snakexLength[0] && enemyypos[ypos] == snakeyLength[0])
        {
            score++;
            lengthOfSnake++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }
        enemyImage.paintIcon(this,g,enemyxpos[xpos],enemyypos[ypos]);

        for(int i=1;i<lengthOfSnake;i++)
        {
            if(snakexLength[i] == snakexLength[0] && snakeyLength[i] == snakeyLength[0])
            {
                right=false;
                left=false;
                up=false;
                down=false;

                g.setColor(Color.WHITE);
                g.setFont(new Font("arial",Font.BOLD,50));
                g.drawString("Game Over",300,300);

                g.setFont(new Font("arial",Font.BOLD,20));
                g.drawString("Space to RESTART",350,340);
            }
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right)
        {
            for(int i=lengthOfSnake-1;i>=0;i--)
            {
                snakeyLength[i+1] = snakeyLength[i];
            }
            for(int i=lengthOfSnake;i>=0;i--)
            {
                if(i==0)
                {
                    snakexLength[i] = snakexLength[i] + 25;
                }
                else
                {
                    snakexLength[i] = snakexLength[i-1];
                }
                if(snakexLength[i] > 850)
                {
                    snakexLength[i] = 25;
                }
            }
            repaint();
        }
        if(left)
        {
            for(int i=lengthOfSnake-1;i>=0;i--)
            {
                snakeyLength[i+1] = snakeyLength[i];
            }
            for(int i=lengthOfSnake;i>=0;i--)
            {
                if(i==0)
                {
                    snakexLength[i] = snakexLength[i] - 25;
                }
                else
                {
                    snakexLength[i] = snakexLength[i-1];
                }
                if(snakexLength[i] < 25)
                {
                    snakexLength[i] = 850;
                }
            }
            repaint();
        }
        if(up)
        {
            for(int i=lengthOfSnake-1;i>=0;i--)
            {
                snakexLength[i+1] = snakexLength[i];
            }
            for(int i=lengthOfSnake;i>=0;i--)
            {
                if(i==0)
                {
                    snakeyLength[i] = snakeyLength[i] - 25;
                }
                else
                {
                    snakeyLength[i] = snakeyLength[i-1];
                }
                if(snakeyLength[i] < 75)
                {
                    snakeyLength[i] = 625;
                }
            }
            repaint();
        }
        if(down)
        {
            for(int i=lengthOfSnake-1;i>=0;i--)
            {
                snakexLength[i+1] = snakexLength[i];
            }
            for(int i=lengthOfSnake;i>=0;i--)
            {
                if(i==0)
                {
                    snakeyLength[i] = snakeyLength[i] + 25;
                }
                else
                {
                    snakeyLength[i] = snakeyLength[i-1];
                }
                if(snakeyLength[i] > 625)
                {
                    snakeyLength[i] = 75;
                }
            }
            repaint();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            moves++;
            if(!left)
            {
                right = true;
            }
            else
            {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            moves++;
            if(!right)
            {
                left = true;
            }
            else
            {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            moves++;
            if(!down)
            {
                up = true;
            }
            else
            {
                up = false;
                down = true;
            }
            left = false;
            right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            moves++;
            if(!up)
            {
                down = true;
            }
            else
            {
                down = false;
                up = true;
            }
            left = false;
            right = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE)
        {
            score=0;
            moves=0;
            lengthOfSnake=3;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
