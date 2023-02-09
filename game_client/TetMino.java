public class TetMino
{
    private int minoType;
    private int[][] mino;

    public TetMino(int n)
    {
        this.minoType = n;
        switch(n)
        {
            case 1:
                setIMino();
                break;
            case 2:
                setJMino();
                break;
            case 3:
                setLMino();
                break;
            case 4:
                setOMino();
                break;
            case 5:
                setSMino();
                break;
            case 6:
                setTMino();
                break;
            case 7:
                setZMino();
                break;
        }
    }

    //-----------------minoの定義--------------------
    public void setIMino()
    {
        this.mino = new int[][] {
            {-2,0},{-1,0},{0,0},{1,0}
        };
    }

    public void setJMino()
    {
        this.mino = new int[][] {
            {-1,-1},{-1,0},{0,0},{1,0}
        };
    }

    public void setLMino()
    {
        this.mino = new int[][] {
            {-1,0},{0,0},{1,0},{1,-1}
        };
    }

    public void setOMino()
    {
        this.mino = new int[][] {
            {-1,-1},{-1,0},{0,0},{0,-1}
        };
    }

    public void setSMino()
    {
        this.mino = new int[][] {
            {-1,0},{0,0},{0,-1},{1,-1}
        };
    }

    public void setTMino()
    {
        this.mino = new int[][] {
            {-1,0},{0,0},{0,-1},{1,0}
        };
    }

    public void setZMino()
    {
        this.mino = new int[][] {
            {-1,-1},{0,-1},{0,0},{1,0}
        };
    }
    //-----------------------------------------------

    //-----------------------geter-------------------
    public int[][] getMino()    //2次元配列minoを返す
    {
        return this.mino;
    }

    public int getMinoType()    //色を表す数字を返す
    {
        return this.minoType;
    }
    //-----------------------------------------------

    //----------------minoの計算---------------------
    public void rRotateMino()   //右回り回転
    {
        int[][] rotatedMino = new int[4][2];
        int x;
        int y;
        for(int i = 0; i < 4; i++)
        {
            x = this.mino[i][0];
            y = this.mino[i][1];
            rotatedMino[i][0] = -y;
            rotatedMino[i][1] = x;
        }
        this.mino = rotatedMino;
    }

    public void lRotateMino()   //左回り回転
    {
        int[][] rotatedMino = new int[4][2];
        int x;
        int y;
        for(int i = 0; i < 4; i++)
        {
            x = this.mino[i][0];
            y = this.mino[i][1];
            rotatedMino[i][0] = y;
            rotatedMino[i][1] = -x;
        }
        this.mino = rotatedMino;
    }

    public int[] minoRange()    //minoの範囲
    {
        int xMax = 0;
        int xMin = 0;
        int yMax = 0;
        int yMin = 0;
        for(int i = 0; i < 4; i++)
        {
            if(xMax < this.mino[i][0])
            {
                xMax = this.mino[i][0];
            }
            if(xMin > this.mino[i][0])
            {
                xMin = this.mino[i][0];
            }
            if(yMax < this.mino[i][1])
            {
                yMax = this.mino[i][1];
            }
            if(yMin > this.mino[i][1])
            {
                yMin = this.mino[i][1];
            }
        }
        int[] range = new int[] {
            xMax, xMin, yMax, yMin
        };
        return range;
    }

    //-----------------------------------------------
}