public class MyModel
{
    public final int WIDTH = 960; //1280,640
    public final int HEIGHT = 540; //960, 480
    private int sceneNum;   //0:title, 1:singleGame, 2:multiGame, 3:pause, 4:result
    private int charPosition;
    private GameManager gm;
    private TetMino mino;

    public MyModel()
    {
        //title
        this.charPosition = 0;
        this.sceneNum = 0;

        //gameManager
        this.gm = new GameManager();
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

    public int getCharPosition()
    {
        return this.charPosition;
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

    public void pushedSpace()
    {
        switch(this.sceneNum)
        {
            case 0:
                if(this.charPosition == 0)
                {
                    this.mino = this.gm.getTetMino();
                    this.gm.game();
                    this.sceneNum = 1;
                }
                if(this.charPosition == 1)
                {
                    this.sceneNum = 2;
                }
                break;
            case 1:
                break;
        }
    }
    //--------------------------------------------
}