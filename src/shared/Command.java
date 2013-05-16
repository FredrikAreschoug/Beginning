package post.shared;


import java.io.Serializable;


public class Command implements Serializable {

    private static final long serialVersionUID = 2L;


    private int directrion;
    private float x,y;
    public String message;

    public Command() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDirectrion() {

        return directrion;
    }

    public void setDirectrion(int directrion) {
        this.directrion = directrion;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
