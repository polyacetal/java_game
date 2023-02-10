public class GameManager
{
    private int[][] baseFeilds; //確定したfeild
    private int[][] feilds; //進行中のfeild
    private int[] feildSize;    //feildの大きさ
    private int charX;  //minoのx座標
    private int charY;  //minoのy座標
    private int charR;  //minoの回転
    private int hold;   //holdのmino
    private TetMino mino;   //TetMinoのクラス
    private int[] minoRange;    //minoの範囲
    private int[][] minoBuff;   //TetMinoクラスから受け取ったminoの情報
    private boolean isFall; //0:静止, 1:落下中
    private boolean isHold; //true:Hold, false:nonHold
    private MyModel mm;

    public GameManager(MyModel mm)
    {
        this.feildSize = new int[] {10, 22};
        this.baseFeilds = new int[this.feildSize[1]][this.feildSize[0]];
        this.feilds = new int[this.feildSize[1]][this.feildSize[0]];
        this.hold = 0;
        this.mm = mm;
    }

    //------------------入力系-------------------
    public void fallMino()
    {
        if(this.isMoveable(this.mino.getMinoType(), 0, 1, 0))
        {
            this.putMino(0);
            this.charY += 1;
            this.putMino(this.mino.getMinoType());
        }
        else
        {
            this.confirmMino();
        }
    }

    public void hardDrop()
    {
        while(this.isMoveable(this.mino.getMinoType(), 0, 1,0))
        {
            this.putMino(0);
            this.charY += 1;
            this.putMino(this.mino.getMinoType());
        }
        this.confirmMino();
        this.mm.addScore(50);
    }

    public void moveRight()
    {
        if(this.isMoveable(this.mino.getMinoType(), 1, 0, 0))
        {
            this.putMino(0);
            this.charX += 1;
            this.putMino(this.mino.getMinoType());
        }
    }

    public void moveLeft()
    {
        if(this.isMoveable(this.mino.getMinoType(), -1, 0, 0))
        {
            this.putMino(0);
            this.charX -= 1;
            this.putMino(this.mino.getMinoType());
        }
    }

    public void  rRotateMino()
    {
        if(this.isMoveable(this.mino.getMinoType(), 0, 0, 3))
        {
            this.putMino(0);
            this.mino.rRotateMino();
            this.charR += 3;
            this.putMino(this.mino.getMinoType());
            
        }
    }
    
    public void  lRotateMino()
    {
        if(this.isMoveable(this.mino.getMinoType(), 0, 0, 1))
        {
            this.putMino(0);
            this.mino.lRotateMino();
            this.charR += 1;
            this.putMino(this.mino.getMinoType());
            
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

    public boolean getIsFall()
    {
        return this.isFall;
    }
    
    public boolean getIsHold()
    {
        return this.isHold;
    }

    public int getHold()
    {
        return this.hold;
    }
    //-------------------------------------------

    //------------------処理---------------------
    public void game(int n)
    {
        //初期化
        this.isFall = true;
        this.charX = 5;
        this.charY = 3;
        this.charR = 0;
        //処理
        this.mino = new TetMino(n);
        this.minoRange = this.mino.minoRange();
        this.putMino(this.mino.getMinoType());
    }

    public void putMino(int tetType)
    {
        this.minoBuff = this.mino.getMino();
        int xDis;
        int yDis;
        for(int i = 0; i < 4; i++)
        {
            xDis = this.minoBuff[i][0] + charX;
            yDis = this.minoBuff[i][1] + charY;
            this.feilds[yDis][xDis] = tetType;
        }
    }

    public void confirmMino()
    {
        int xDis;
        int yDis;
        for(int i = 0; i < 4; i++)
        {
            xDis = this.minoBuff[i][0] + charX;
            yDis = this.minoBuff[i][1] + charY;
            this.feilds[yDis][xDis] = this.mino.getMinoType();
            this.baseFeilds[yDis][xDis] = this.mino.getMinoType();
        }
        this.isFall = false;
        this.isHold = false;
        this.clearLine();
    }

    public void reflectFeild()
    {
        for(int y = 0; y < this.feildSize[1]; y++)
        {
            for(int x = 0; x < this.feildSize[0]; x++)
            {
                this.feilds[y][x] = this.baseFeilds[y][x];
            }
        }
    }

    public void clearLine()
    {
        int score = 0;
        for(int i = 0; i < this.feildSize[1]; i++)
        {
            if(this.checkLine(i))
            {
                for(int t = i; t >0; t--)
                {
                    this.baseFeilds[t] = this.baseFeilds[t - 1].clone();
                }
                score += 1;
            }
        }
        if(score == 4) score += 1;
        this.mm.addScore(score * 100);
        this.reflectFeild();
    }

    public boolean checkLine(int y)
    {
        int checkSum = 1;
        for(int i = 0; i < this.feildSize[0]; i++)
        {
            checkSum = checkSum * this.baseFeilds[y][i];
        }
        if(checkSum == 0) return false;
        else return true;
    }

    public boolean isMoveable(int tetType, int x, int y, int r)
    {
        int moveX;
        int moveY;
        int moveR = this.charR + r % 4;
        int checkSum = 0;
        TetMino moveMino = new TetMino(tetType);
        for(int i = 0; i < moveR; i++)
        {
            moveMino.lRotateMino();
        }
        int[][] testMino = moveMino.getMino();
        for(int i = 0; i < 4; i++)
        {
            moveX = this.charX + x + testMino[i][0];
            moveY = this.charY + y + testMino[i][1];
            if(moveX < 0 || moveX > this.feildSize[0] - 1 || moveY >  this.feildSize[1] - 1) return false;
            checkSum += this.baseFeilds[moveY][moveX];
        }
        if(checkSum == 0)return true;
        else return false;

    }

    public int sendHold()
    {
        this.isHold = true;
        int buffHold = this.hold;
        this.hold = this.mino.getMinoType();
        this.reflectFeild();
        return buffHold;
    }

    public boolean isGameOver()
    {
        int checkSum = 0;
        for(int i = 0; i < 4; i++)
        {
            checkSum = baseFeilds[3][i + 3];
        }
        if(checkSum == 0) return false;
        else return true;
    }
    //-------------------------------------------
}
