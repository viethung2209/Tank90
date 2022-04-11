package Model;

public class Obj2DMoveable extends Obj2D{
    public static final int LEFT = 0;
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;

    protected int ori;
    protected int delay;
    private long lastTimeMove;

    public boolean canMove(){
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeMove >= delay) {
            move();
            lastTimeMove = currentTime;
            return true;
        }
        return false;
    }
    public void move() {
        switch (ori){
            case LEFT:
                x -= 2;
                break;
            case UP:
                y -= 2;
                break;
            case RIGHT:
                x += 2;
                break;
            case DOWN:
                y += 2;
                break;
        }
    }

    public int getOri() {
        return ori;
    }

    public void setOri(int ori) {
        this.ori = ori;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }


}
