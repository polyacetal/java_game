import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener
{
    private MyPanel mp;
    private JPanel titlePanel, singleGamePanel, multiGamePanel, cardPanel;
    private CardLayout cardLayout;
    private JButton single, multi;

    public MyFrame()
    {
        //---------------前処理-----------------
        this.mp = new MyPanel();
        //--------------------------------------

        //---------------title------------------
        this.titlePanel = new JPanel();
        this.single = new JButton("1人でプレイ");
        this.multi = new JButton("2人でプレイ");
        this.single.addActionListener(this);
        this.multi.addActionListener(this);
        this.titlePanel.add(this.single);
        this.titlePanel.add(this.multi);
        //--------------------------------------

        //-------------singleGame---------------
        this.singleGamePanel = new JPanel();
        JLabel hoge = new JLabel("hoge");
        this.singleGamePanel.add(hoge);
        //--------------------------------------

        //--------------multiGame---------------
        this.multiGamePanel = new JPanel();
        JLabel huga = new JLabel("huga");
        this.multiGamePanel.add(huga);
        //--------------------------------------

        //--------------cardPanel---------------
        this.cardPanel = new JPanel();
        this.cardLayout = new CardLayout();
        this.cardPanel.setLayout(this.cardLayout);
        this.cardPanel.add(this.titlePanel);
        this.cardPanel.add(this.singleGamePanel, "single");
        this.cardPanel.add(this.multiGamePanel, "multi");
        //--------------------------------------

        //----------------全体------------------
        JPanel panel = new JPanel();
        //panel.add(this.mp);
        super.getContentPane().add(this.cardPanel);
        //--------------------------------------
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.single)
        {
            this.cardLayout.show(cardPanel, "single");
        }
        if(e.getSource() == this.multi)
        {
            this.cardLayout.show(cardPanel, "multi");
        }
    }

    public static void main(String[] args)
    {
        MyFrame frame = new MyFrame();
        frame.setSize(640,480);
        frame.setLocation(100,100);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}