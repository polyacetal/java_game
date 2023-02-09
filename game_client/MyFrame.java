import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class MyFrame extends JFrame implements ActionListener, KeyListener
{
    private MyPanel mp;
    private MyModel mm;
    private GameManager gm;
    private MediaTracker mt;
    private Timer timer;
    private int timeBuff;

    public MyFrame()
    {
        //----------------初期化----------------
        this.mt = new MediaTracker(this);
        this.mm = new MyModel();
        this.timer = new Timer(10, this);
        this.timeBuff = 0;
        //--------------------------------------

        //----------------image-----------------
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image[] blocks = new Image[8];
        Image[] minos = new Image[8];

        for(int i = 0; i < 8; i++)
        {
            URL url = this.getClass().getResource("block" + i + ".png");
            blocks[i] = tk.getImage(url);
            this.mt.addImage(blocks[i], i);
        }

        for(int i = 0; i < 8; i++)
        {
            URL url = this.getClass().getResource("mino" + i + ".png");
            minos[i] = tk.getImage(url);
            this.mt.addImage(minos[i], i + 10);
        }

        try
        {
            this.mt.waitForAll();
        }
        catch(Exception e){}
        //--------------------------------------

        //----------------Panel-----------------
        this.mp = new MyPanel(this, this.mm, blocks, minos);
        JPanel panel = new JPanel();
        panel.add(this.mp);
        super.getContentPane().add(panel);
        //--------------------------------------

    }

    //timerで呼ばれる部分
    public void actionPerformed(ActionEvent e)
    {
        this.timeBuff += 1;
        this.mp.repaint();
        switch(this.mm.getSceneNum())
        {
            case 1:
                if(!this.gm.getIsFall())
                {
                    this.mm.game(0);
                }
                else if(this.timeBuff > this.mm.getSpeed())
                {
                    this.timeBuff = 0;
                    this.gm.fallMino();
                }
                break;

        }
    }

    //-----------------キー入力系-----------------------
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_W:
                this.mm.pushedW();
                break;
            case KeyEvent.VK_A:
                this.mm.pushedA();
                break;
            case KeyEvent.VK_S:
                this.mm.pushedS();
                break;
            case KeyEvent.VK_D:
                this.mm.pushedD();
                break;
            case KeyEvent.VK_Q:
                this.mm.pushedQ();
                break;
            case KeyEvent.VK_E:
                this.mm.pushedE();
                break;
            case KeyEvent.VK_P:
                this.mm.pushedP();
                break;
            case KeyEvent.VK_F:
                this.mm.pushedF();
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_SPACE:
                this.mm.pushedSpace();
                this.gameStart();
                break;
        }
    }
    public void keyReleased(KeyEvent e){}
    //--------------------------------------------------

    public void gameStart()
    {
        //----------------timer-----------------
        this.timer.start();
        //--------------------------------------
        this.gm = this.mm.getGameManager();
        this.mp.setGameManager(this.gm);
    }

    public static void main(String[] args)
    {
        MyFrame frame = new MyFrame();
        frame.setSize(frame.mm.WIDTH, frame.mm.HEIGHT);
        frame.setLocation(100,100);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}