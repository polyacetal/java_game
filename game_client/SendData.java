import java.io.Serializable;
public class SendData implements Serializable
{
    public int[][] feilds;
    public int hold;
    public int[] next;
    public int score;

    public SendData()
    {
        this.feilds = new int[22][10];
        this.hold = 0;
        this.next = new int[4];
        this.score = 0;
    }
}