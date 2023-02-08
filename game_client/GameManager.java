import java.util.Random;
public class GameManager
{
    private int[][] baseFeilds; //確定したfeild
    private int[][] feilds; //進行中のfeild
    private int charX;  //minoのx座標
    private int charY;  //minoのy座標
    private TetMino mino;   //TetMinoのクラス
    private int[] minoRange;    //minoの範囲
    private int[][] minoBuff;   //TetMinoクラスから受け取ったminoの情報
    private boolean isFall; //0:静止, 1:落下中
    private Random rando;

    public GameManager()
    {
        this.baseFeilds = new int[20][10];
        this.feilds = new int[20][10];
        //出現位置
        this.charX = 4;
        this.charY = 1;
        this.rando = new Random();
    }

    //------------------入力系-------------------
    public void fallMino()
    {
        if(this.mino.minoRange()[2] + this.charY < 19 && this.check(0,1))
        {
            this.charY += 1;
            this.putMino();
        }
        else
        {
            this.confirmMino();
        }
    }

    public void moveRight()
    {
        if(this.mino.minoRange()[0] + this.charX < 9 && this.isFall && this.check(1,0))
        {
            this.charX += 1;
            this.putMino();
        }
    }

    public void moveLeft()
    {
        if(this.mino.minoRange()[1] + this.charX > 0 && this.isFall && this.check(-1,0))
        {
            this.charX -= 1;
            this.putMino();
        }
    }
    
    public void rRotateMino()
    {
        if(this.mino.getMinoType() != 1 || this.charY < 17)
        {
            try
            {
                this.mino.rRotateMino();
                if(!onCheck())
                {
                    this.mino.lRotateMino();
                }
                this.putMino();
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                if(this.charX > 5)
                {
                    this.charX -= 1;
                }
                else
                {
                    this.charX += 1;
                }
                if(this.mino.getMinoType() == 1)
                {
                    if(this.charX > 5)
                    {
                        this.charX -= 1;
                    }
                    else
                    {
                        this.charX += 1;
                    }
                }
                this.putMino();
            }
        }
    }

    public void lRotateMino()
    {
        if(this.mino.getMinoType() != 1 || this.charY < 17)
        {
            try
            {
                this.mino.lRotateMino();
                if(!onCheck())
                {
                    this.mino.rRotateMino();
                }
                this.putMino();
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                if(this.charX > 5)
                {
                    this.charX -= 1;
                }
                else
                {
                    this.charX += 1;
                }
                if(this.mino.getMinoType() == 1)
                {
                    if(this.charX > 5)
                    {
                        this.charX -= 1;
                    }
                    else
                    {
                        this.charX += 1;
                    }
                }
                this.putMino();
            }
        }
    }
    //-------------------------------------------

    //--------------------geter------------------
    public int[][] getFeilds()
    {
        return this.feilds;
    }

    public int getFeildBlock(int x, int y)
    {
        return this.feilds[y][x];
    }

    public TetMino getTetMino()
    {
        return this.mino;
    }

    public boolean getIsFall()
    {
        return this.isFall;
    }
    //-------------------------------------------

    //------------------処理---------------------
    public void game()
    {
        //初期化
        this.isFall = true;
        this.charX = 4;
        this.charY = 1;
        //処理
        this.mino = new TetMino(this.rando.nextInt(6) + 1);
        this.minoRange = this.mino.minoRange();
        this.putMino();
    }

    public void putMino()
    {
        this.minoBuff = this.mino.getMino();
        this.feilds = this.feildCopy(this.feilds, this.baseFeilds);
        int xDis;
        int yDis;
        for(int i = 0; i < 4; i++)
        {
            xDis = this.minoBuff[i][0] + charX;
            yDis = this.minoBuff[i][1] + charY;
            this.feilds[yDis][xDis] = this.mino.getMinoType();
        }
    }

    public int[][] feildCopy(int[][] a, int[][] b)
    {
        for(int y = 0; y < 20; y++)
        {
            for(int x = 0; x < 10; x++)
            {
                a[y][x] = b[y][x];
            }
        }
        return a;
    }

    public void confirmMino()
    {
        int xDis;
        int yDis;
        for(int i = 0; i < 4; i++)
        {
            xDis = this.minoBuff[i][0] + charX;
            yDis = this.minoBuff[i][1] + charY;
            this.baseFeilds[yDis][xDis] = this.mino.getMinoType();
        }
        this.isFall = false;
    }

    public boolean check(int x, int y)
    {
        int[][] checkMino = this.mino.getMino();
        int checkX = this.charX + x;
        int checkY = this.charY + y;
        int checkSum = 0;
        for(int i = 0; i < 4; i++)
        {
            checkSum += baseFeilds[checkMino[i][1] + checkY][checkMino[i][0] + checkX];
        }
        if(checkSum == 0)return true;
        else return false;
    }

    public boolean onCheck()
    {
        int[][] checkMino = this.mino.getMino();
        int checkSum = 0;
        for(int i = 0; i < 4; i++)
        {
            checkSum += baseFeilds[checkMino[i][1] + this.charY][checkMino[i][0] + this.charX];
        }
        if(checkSum == 0)return true;
        else return false;
    }
    //-------------------------------------------
}