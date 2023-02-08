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

    public MyPanel(MyFrame mf, MyModel mm, Image[] blocks)
    {
        this.mf = mf;
        this.mm = mm;
        this.gm = this.mm.getGameManager();
        this.blocks = blocks;

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

    public void drawTitle(Graphics g)
    {
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 0, this.mm.WIDTH, this.mm.HEIGHT);
        g.setColor(new Color(0,0,0));
        //print title
        Font f1 = new Font("Dialog", Font.PLAIN, 100);
        g.setFont(f1);
        g.drawString("TETRIS", (this.mm.WIDTH / 2) - 170, (this.mm.HEIGHT / 4));
        //print playstyle
        Font f2 = new Font("Dialog", Font.PLAIN, 30);
        int charPosi = this.mm.getCharPosition();
        g.setFont(f2);
        g.drawString("1人でプレイ", (this.mm.WIDTH / 2) - 70, (this.mm.HEIGHT / 2));
        g.drawString("2人でプレイ", (this.mm.WIDTH / 2) - 70, (this.mm.HEIGHT / 2) + 100);
        g.drawString("▶", (this.mm.WIDTH / 2) - 120, (this.mm.HEIGHT / 2) + (100 * charPosi));
        this.repaint();
    }

    public void drawSingleGame(Graphics g)
    {
        //--------------------clear----------------------
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 0, this.mm.WIDTH, this.mm.HEIGHT);
        g.setColor(new Color(0,0,0));
        //-----------------------------------------------

        int[][] feilds = this.gm.getFeilds();
        for(int y = 0; y < 20; y++)
        {
            for(int x = 0; x < 10; x++)
            {
                g.drawImage(this.blocks[feilds[y][x]], (x * 20) + 20 , (y * 20) + 20, this);
            }
        }
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
                drawMultiGame(g);
                break;
        }
    }
}