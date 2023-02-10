import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class MyFrame extends JFrame implements ActionListener, KeyListener
{
    private MyPanel mp;
    private MyModel mm;
    private GameManager gm;
    private MediaTracker mt;
    private Timer timer;
    private int timeBuff;
    private JPanel panel1;
    private JTextField text;
    private JButton button;
    private MultiGame mg;
    private boolean multiWait;
    private SendData sendData;

    public MyFrame()
    {
        super();
        //----------------初期化----------------
        this.mt = new MediaTracker(this);
        this.mm = new MyModel();
        this.timer = new Timer(10, this);
        this.timeBuff = 0;
        this.sendData = new SendData();
        this.multiWait = false;
        this.text = new JTextField(10);
        this.button = new JButton("Enter");
        this.button.addActionListener(this);
        this.panel1 = new JPanel();
        BoxLayout box1 = new BoxLayout(panel1, BoxLayout.X_AXIS);
        this.panel1.setLayout(box1);
        this.panel1.add(this.text);
        this.panel1.add(this.button);
        this.panel1.setEnabled(false);
        this.panel1.setVisible(false);
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
        BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(box);
        panel.add(this.mp);
        panel.add(this.panel1);
        super.getContentPane().add(panel);
        //--------------------------------------

    }

    //timerで呼ばれる部分
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.button)
        {
            this.mm.setMultiGame(new MultiGame(this.text.getText()));
            this.mg = this.mm.getMultiGame();
            this.mg.runMultiClient();
            this.mm.multiInit();
            this.gameStart();
            this.mm.setSceneNum(22);
            this.panel1.setEnabled(false);
            this.panel1.setVisible(false);
            this.requestFocus();
        }
        this.timeBuff += 1;
        this.mp.repaint();
        switch(this.mm.getSceneNum())
        {
            case 1:
                if(!this.gm.getIsFall())
                {
                    if(this.gm.isGameOver())
                    {
                        this.mm.setSceneNum(3);
                    }
                    else
                    {
                        this.mm.game(0);
                    }
                }
                else if(this.timeBuff > this.mm.getSpeed())
                {
                    this.gm.fallMino();
                }
                break;
            case 22:
                if(!this.gm.getIsFall())
                {
                    if(this.gm.isGameOver())
                    {
                        this.mm.setSceneNum(3);
                    }
                    else
                    {
                        this.mm.game(0);
                    }
                }
                else if(this.timeBuff > this.mm.getSpeed())
                {
                    this.gm.fallMino();
                    this.sendData.feilds = this.gm.getFeilds();
                    this.sendData.hold = this.gm.getHold();
                    this.sendData.next = this.mm.getRandArray();
                    this.sendData.score = this.mm.getScore();
                    this.mg.dataSend(this.sendData);
                    this.mp.setSendData(this.mg.receiveData());
                }
                break;
            case 23:
                if(this.multiWait && this.mg.getWait())
                {
                    this.multiWait = false;
                    this.mm.multiInit();
                    this.gameStart();
                    this.mm.setSceneNum(22);
                }
                break;
        }
        if(this.timeBuff > this.mm.getSpeed())
        {
            this.timeBuff = 0;
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
                if(this.mm.getSceneNum() == 22) this.mg.closeContact();
                System.exit(0);
                break;
            case KeyEvent.VK_SPACE:
                this.mm.pushedSpace();
                if(this.mm.getSceneNum() == 1)
                {
                    this.gameStart();
                }
                if(this.mm.getSceneNum() == 21)
                {
                    this.panel1.setEnabled(true);
                    this.panel1.setVisible(true);
                }
                if(this.mm.getSceneNum() == 23)
                {
                    this.multiWait = true;
                    this.mg = this.mm.getMultiGame();
                    this.mg.runMultiHost();
                    this.timer.start();
                }
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