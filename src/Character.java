import java.awt.*;

public class Character {public int xpos;        //the x position
    public int ypos;        //the y position
    public int dx;          //the speed of the hero in the x direction
    public int dy;          //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive; //a boolean to denote if the hero is alive or not
    public Rectangle rec;
    //boolean movements
    public boolean leftPressed;
    public boolean rightPressed;

    public Character(int pXpos, int pYpos, int pDx, int pDy, int pWidth, int pHeight) {
        xpos = pXpos;
        ypos = pYpos;
        dx = pDx;
        dy = pDy;
        width = pWidth;
        height = pHeight;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);

    }

    public void printInfo() {
        System.out.println("(x,y): (" + xpos + ", " + ypos + ")");
        System.out.println("X Speed: " + dx);
        System.out.println("Y Speed: " + dy);
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        System.out.println("Is your Hero alive? " + isAlive);
    }

    public void move() {
        //horizontal
        if (leftPressed==true) {
            dx = -2;
        } else if (rightPressed==true) {
            dx = 2;
        } else {
            dx = 0;
        }
        if (ypos < 0) {
            dy = 0;
        }
        if (ypos > 700) {
            dy = 0;
        }
        if (xpos > 1000 - width) {
            dx = 0;
        }
        if (xpos < 0) {
            dx = 0;
        }
        if (ypos < 0) {
            dy = 0;
        }
        if (ypos > 700 - height) {
            dy = 0;
            xpos = xpos + dx;
            ypos = ypos + dy;
            rec = new Rectangle(xpos, ypos, width, height);
        }


    }

}//end of class




