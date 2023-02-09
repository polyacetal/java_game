import java.util.Random;
public class MyModel
{
    public final int WIDTH = 960; //1280,640
    public final int HEIGHT = 540; //960, 480
    private int sceneNum;
    private int beforSceneNum;
    /*
    sceneNum
    0:title, 1:singleGame, 2:pause, 3:result
    20:multiSeting, 21:multiGame
    */
    private int score;
    private int charPosition;
    private Random rand;
    private int[] randArray;
    private int speed;
    private GameManager gm;

    public MyModel()
    {
        //title
        this.charPosition = 0;
        this.sceneNum = 0;
        this.beforSceneNum = 0;

        //game
        this.score = 0;
        this.speed = 100;
        this.rand = new Random();
        this.randArray = new int[4];
        for(int i = 0; i < 4; i++)
        {
            this.randArray[i] = this.rand.nextInt(7) + 1;
        }
    }

    public GameManager getGameManager()
    {
        return this.gm;
    }

    public int getSceneNum()
    {
        return this.sceneNum;
    }

    public void setSceneNum(int sceneNum)
    {
        this.sceneNum = sceneNum;
    }

    public void addScore(int score)
    {
        this.score += score;
    }

    public int getScore()
    {
        return this.score;
    }

    public int getCharPosition()
    {
        return this.charPosition;
    }

    public int[] getRandArray()
    {
        return this.randArray;
    }

    public int getSpeed()
    {
        return this.speed;
    }

    public void game(int n)
    {
        if(n == 0)
        {
            n = this.randArray[0];
            for(int i = 0; i < 3; i++)
            {
                this.randArray[i] = this.randArray[i + 1];
            }
            this.randArray[3] = this.rand.nextInt(7) +1;
        }
        this.gm.game(n);
    }

    //-------------------入力系-------------------
    public void pushedW()
    {
        switch(this.sceneNum)
        {
            case 0:
                this.charPosition = 0;
                break;
            case 1:
                this.gm.hardDrop();
                break;
            case 2:
                this.charPosition = 0;
                break;
        }
    }

    public void pushedA()
    {
        switch(this.sceneNum)
        {
            case 1:
                this.gm.moveLeft();
                break;
        }
    }

    public void pushedS()
    {
        switch(this.sceneNum)
        {
            case 0:
                this.charPosition = 1;
                break;
            case 1:
                this.gm.fallMino();
                break;
            case 2:
                this.charPosition = 1;
                break;
        }
    }

    public void pushedD()
    {
        switch(this.sceneNum)
        {
            case 1:
                this.gm.moveRight();
                break;
        }
    }

    public void pushedQ()
    {
        switch(this.sceneNum)
        {
            case 1:
                this.gm.lRotateMino();
                break;
        }
    }

    public void pushedE()
    {
        switch(this.sceneNum)
        {
            case 1:
                this.gm.rRotateMino();
                break;
        }
    }

    public void pushedP()
    {
        switch(this.sceneNum)
        {
            case 1:
            this.beforSceneNum = this.sceneNum;
            this.sceneNum = 2;
                break;
            case 20:
            this.beforSceneNum = this.sceneNum;
            this.sceneNum = 2;
                break;
        }
    }

    public void pushedF()
    {
        switch(this.sceneNum)
        {
            case 1:
                if(!this.gm.getIsHold())
                {
                    int n = this.gm.sendHold();
                    this.game(n);
                }
                break;
        }
    }

    public void pushedSpace()
    {
        switch(this.sceneNum)
        {
            case 0:
                if(this.charPosition == 0)
                {
                    this.gm = new GameManager(this);
                    this.score = 0;
                    this.game(0);
                    this.sceneNum = 1;
                    this.charPosition = 0;
                }
                if(this.charPosition == 1)
                {
                    this.sceneNum = 20;
                }
                break;
            case 1:
                break;
            case 2:
                if(this.charPosition == 0)
                {
                    this.sceneNum = this.beforSceneNum;
                    this.charPosition = 0;
                }
                if(this.charPosition == 1)
                {
                    this.sceneNum = 0;
                    this.charPosition = 0;
                }
                break;

        }
    }
    //--------------------------------------------
}