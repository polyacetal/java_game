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
            case 20:
                drawMultiGame(g);
                break;
        }
    }

    public void setGameManager(GameManager gm)
    {
        this.gm = gm;
    }

    //--------------------------------表示する画面--------------------------------
    public void drawTitle(Graphics g)
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

    public void drawSingleGame(Graphics g)
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

    public void drawPause(Graphics g)
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

    public void drawMultiGame(Graphics g)
    {
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 0, this.mm.WIDTH, this.mm.HEIGHT);
        g.setColor(new Color(0,0,0));
        //print title
        Font f1 = new Font("Dialog", Font.PLAIN, 100);
        g.setFont(f1);
        g.drawString("comming soon", (this.mm.WIDTH / 2) - 350, (this.mm.HEIGHT / 2));
        this.repaint();
    }
    //----------------------------------------------------------------------------
}