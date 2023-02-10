import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class MyPanel extends JPanel
{
    private BufferedImage buff;
    private MyFrame mf;
    private MyModel mm;
    private GameManager gm;
    private SendData sd;
    private Image[] blocks;
    private Image[] minos;

    //-----------------------------Panelの設定-----------------------------
    public MyPanel(MyFrame mf, MyModel mm, Image[] blocks, Image[] minos)
    {
        this.mf = mf;
        this.mm = mm;
        this.blocks = blocks;
        this.minos = minos;

        super.setBackground(Color.white);
        super.setPreferredSize(new Dimension(this.mm.WIDTH, this.mm.HEIGHT));
        this.buff = new BufferedImage(this.mm.WIDTH, this.mm.HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.mf.addKeyListener(this.mf);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics bg = this.buff.getGraphics();
        g.drawImage(this.buff,0,0,this);
        this.drawScene(bg);
    }
    //---------------------------------------------------------------------

    public void drawScene(Graphics g)
    {
        switch(this.mm.getSceneNum())
        {
            case 0:
                drawTitle(g);
                break;
            case 1:
                drawSingleGame(g);
                break;
            case 2:
                drawPause(g);
                break;
            case 3:
                drawResult(g);
                break;
            case 20:
                drawMultiSetting(g);
                break;
            case 21:
                drawMultiAddress(g);
                break;
            case 22:
                drawMultiGame(g);
                break;
            case 23:
                drawMultiWait(g);
                break;
        }
    }

    public void setGameManager(GameManager gm)
    {
        this.gm = gm;
    }

    public void setSendData(SendData sd)
    {
        this.sd = sd;
    }

    //--------------------------------表示する画面--------------------------------
    public void drawTitle(Graphics g)   //0
    {
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 0, this.mm.WIDTH, this.mm.HEIGHT);
        g.setColor(new Color(0,0,0));
        //print title
        Font f1 = new Font("SanSerif", Font.PLAIN, 100);
        g.setFont(f1);
        g.drawString("TETRIS", (this.mm.WIDTH / 2) - 170, (this.mm.HEIGHT / 4));
        //print playstyle
        Font f2 = new Font("SanSerif", Font.PLAIN, 30);
        int charPosi = this.mm.getCharPosition();
        g.setFont(f2);
        g.drawString("1人でプレイ", (this.mm.WIDTH / 2) - 70, (this.mm.HEIGHT / 2));
        g.drawString("2人でプレイ", (this.mm.WIDTH / 2) - 70, (this.mm.HEIGHT / 2) + 100);
        g.drawString("▶", (this.mm.WIDTH / 2) - 120, (this.mm.HEIGHT / 2) + (100 * charPosi));
        this.repaint();
    }

    public void drawSingleGame(Graphics g)  //1
    {
        //-------------------初期化----------------------
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 0, this.mm.WIDTH, this.mm.HEIGHT);
        g.setColor(new Color(0,0,0));
        int[] randArray = this.mm.getRandArray();
        Font f = new Font("SanSerif", Font.PLAIN, 20);
        g.setFont(f);
        //-----------------------------------------------

        //-----------------------------GameFeild---------------------------
        int[][] feilds = this.gm.getFeilds();
        for(int y = 2; y < 22; y++)
        {
            for(int x = 0; x < 10; x++)
            {
                g.drawImage(this.blocks[feilds[y][x]], (x * 20) + 100 , (y * 20) - 20 , this);
            }
        }
        g.drawString("HOLD", 40, 40);
        g.drawImage(this.minos[this.gm.getHold()], 42, 50, this);
        g.drawString("NEXT", 310, 40);
        for(int i = 0; i < 4; i++)
        {
            g.drawImage(this.minos[randArray[i]], 310, 60 * i + 50, this);
        }
        //-----------------------------------------------------------------
        //----------------------------------score----------------------------------
        String score = "score :" + this.mm.getScore();
        g.drawString(score, 310, 418);
        //-------------------------------------------------------------------------
        this.repaint();
    }

    public void drawPause(Graphics g)   //2
    {
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 0, this.mm.WIDTH, this.mm.HEIGHT);
        g.setColor(new Color(0,0,0));
        //print title
        Font f1 = new Font("Dialog", Font.PLAIN, 100);
        g.setFont(f1);
        g.drawString("Pause", (this.mm.WIDTH / 2) - 150, (this.mm.HEIGHT / 4));
        //print playstyle
        Font f2 = new Font("Dialog", Font.PLAIN, 30);
        int charPosi = this.mm.getCharPosition();
        g.setFont(f2);
        g.drawString("ゲームに戻る", (this.mm.WIDTH / 2) - 80, (this.mm.HEIGHT / 2));
        g.drawString("タイトルに戻る", (this.mm.WIDTH / 2) - 80, (this.mm.HEIGHT / 2) + 100);
        g.drawString("▶", (this.mm.WIDTH / 2) - 130, (this.mm.HEIGHT / 2) + (100 * charPosi));
        this.repaint();
    }

    public void drawResult(Graphics g)   //3
    {
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 0, this.mm.WIDTH, this.mm.HEIGHT);
        g.setColor(new Color(0,0,0));
        //print title
        Font f1 = new Font("SanSerif", Font.PLAIN, 100);
        g.setFont(f1);
        g.drawString("RESULT", (this.mm.WIDTH / 2) - 170, (this.mm.HEIGHT / 4));
        //print playstyle
        Font f2 = new Font("SanSerif", Font.PLAIN, 30);
        int charPosi = this.mm.getCharPosition();
        g.setFont(f2);
        g.drawString("タイトルに戻る: Push Space", (this.mm.WIDTH / 2) - 170, (this.mm.HEIGHT / 2));
        g.drawString("ゲームをやめる: Push ESC", (this.mm.WIDTH / 2) - 170, (this.mm.HEIGHT / 2) + 100);
        this.repaint();
    }

    public void drawMultiSetting(Graphics g)    //20
    {
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 0, this.mm.WIDTH, this.mm.HEIGHT);
        g.setColor(new Color(0,0,0));
        //print title
        Font f1 = new Font("Dialog", Font.PLAIN, 50);
        g.setFont(f1);
        g.drawString("どちらか選んでください", (this.mm.WIDTH / 2) - 300, (this.mm.HEIGHT / 4));
        //print playstyle
        Font f2 = new Font("Dialog", Font.PLAIN, 30);
        int charPosi = this.mm.getCharPosition();
        g.setFont(f2);
        g.drawString("Host", (this.mm.WIDTH / 2) - 80, (this.mm.HEIGHT / 2));
        g.drawString("Client", (this.mm.WIDTH / 2) - 80, (this.mm.HEIGHT / 2) + 100);
        g.drawString("▶", (this.mm.WIDTH / 2) - 130, (this.mm.HEIGHT / 2) + (100 * charPosi));
        this.repaint();
    }

    public void drawMultiAddress(Graphics g)   //21
    {
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 0, this.mm.WIDTH, this.mm.HEIGHT);
        g.setColor(new Color(0,0,0));
        //print title
        Font f1 = new Font("SanSerif", Font.PLAIN, 40);
        g.setFont(f1);
        g.drawString("接続先アドレスを入力してください", (this.mm.WIDTH / 2) - 300, (this.mm.HEIGHT / 2));
        //print playstyle
        this.repaint();
    }

    public void drawMultiGame(Graphics g)   //22
    {
        //-------------------初期化----------------------
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 0, this.mm.WIDTH, this.mm.HEIGHT);
        g.setColor(new Color(0,0,0));
        int[] randArray = this.mm.getRandArray();
        Font f = new Font("SanSerif", Font.PLAIN, 20);
        g.setFont(f);
        //-----------------------------------------------

        //-----------------------------1PGameFeild---------------------------
        int[][] feilds = this.gm.getFeilds();
        for(int y = 2; y < 22; y++)
        {
            for(int x = 0; x < 10; x++)
            {
                g.drawImage(this.blocks[feilds[y][x]], (x * 20) + 100 , (y * 20) - 20 , this);
            }
        }
        g.drawString("HOLD", 40, 40);
        g.drawImage(this.minos[this.gm.getHold()], 42, 50, this);
        g.drawString("NEXT", 310, 40);
        for(int i = 0; i < 4; i++)
        {
            g.drawImage(this.minos[randArray[i]], 310, 60 * i + 50, this);
        }
        String score = "score :" + this.mm.getScore();
        g.drawString(score, 310, 418);
        //-------------------------------------------------------------------------
        //-----------------------------2PGameFeild---------------------------
        int[][] feilds2P = this.sd.feilds;
        for(int y = 2; y < 22; y++)
        {
            for(int x = 0; x < 10; x++)
            {
                g.drawImage(this.blocks[feilds2P[y][x]], (x * 20) + 600 , (y * 20) - 20 , this);
            }
        }
        g.drawString("HOLD", 540, 40);
        g.drawImage(this.minos[this.sd.hold], 542, 50, this);
        g.drawString("NEXT", 810, 40);
        for(int i = 0; i < 4; i++)
        {
            g.drawImage(this.minos[this.sd.next[i]], 810, 60 * i + 50, this);
        }
        String score2P = "score :" + this.sd.score;
        g.drawString(score, 810, 418);
        //-------------------------------------------------------------------
        this.repaint();
    }

    public void drawMultiWait(Graphics g)   //23
    {
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 0, this.mm.WIDTH, this.mm.HEIGHT);
        g.setColor(new Color(0,0,0));
        //print title
        Font f1 = new Font("SanSerif", Font.PLAIN, 40);
        g.setFont(f1);
        g.drawString("少々お待ちください...", (this.mm.WIDTH / 2) - 300, (this.mm.HEIGHT / 2));
        this.repaint();
    }
    //----------------------------------------------------------------------------
}