import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
        this.gm = this.mm.getGameManager();
        this.timer = new Timer(10, this);
        this.timeBuff = 0;
        //--------------------------------------

        //----------------image-----------------
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image[] blocks = new Image[8];

        for(int i = 0; i < 8; i++)
        {
            blocks[i] = tk.getImage("./images/image" + i + ".png");
            this.mt.addImage(blocks[i], i);
        }

        try
        {
            this.mt.waitForAll();
        }
        catch(Exception e){}
        //--------------------------------------

        //----------------Panel-----------------
        this.mp = new MyPanel(this, this.mm, blocks);
        JPanel panel = new JPanel();
        panel.add(this.mp);
        super.getContentPane().add(panel);
        //--------------------------------------

        //----------------timer-----------------
        this.timer.start();
        //--------------------------------------
    }

    //timerで呼ばれる部分
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("timer");
        this.timeBuff += 1;
        this.mp.repaint();
        switch(this.mm.getSceneNum())
        {
            case 1:
                System.out.println(this.gm.getIsFall());
                if(!this.gm.getIsFall())
                {
                    this.gm.game();
                }
                else if(this.timeBuff > 100)
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
                System.out.println("w");
                break;
            case KeyEvent.VK_A:
                this.mm.pushedA();
                System.out.println("a");
                break;
            case KeyEvent.VK_S:
                this.mm.pushedS();
                System.out.println("s");
                break;
            case KeyEvent.VK_D:
                this.mm.pushedD();
                System.out.println("d");
                break;
            case KeyEvent.VK_Q:
                this.mm.pushedQ();
                System.out.println("q");
                break;
            case KeyEvent.VK_E:
                this.mm.pushedE();
                System.out.println("e");
                break;
            case KeyEvent.VK_SPACE:
                this.mm.pushedSpace();
                System.out.println("space");
                break;
        }
    }
    public void keyReleased(KeyEvent e){}
    //--------------------------------------------------

    public static void main(String[] args)
    {
        MyFrame frame = new MyFrame();
        frame.setSize(frame.mm.WIDTH, frame.mm.HEIGHT);
        frame.setLocation(100,100);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}