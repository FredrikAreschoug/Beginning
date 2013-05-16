package post.shared;


public class PlayerState {
    private int x,y, direction;
    private String username;

    public PlayerState(int x, int y, String username) {
        this.x = x;
        this.y = y;
        this.username = username;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void moveX(int mX) {
        x = x + mX;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveY(int mY) {
        y = y + mY;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
