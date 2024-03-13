import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Ball {
    private final ImageIcon imageIcon;
    private int x;          // the horizontal position of the ball
    private int y;          // the vertical position of the ball
    private int dx = 1;     // control the direction of the ball on a horizontal axis
    private int dy = 1;     // control the direction of the ball on a vertical axis
    private int speed = 1;  // control the speed of the ball

    public Ball(int id) {

        // Hint1 check how java can get image resource, where is the directory of java source to place image files

        String url = "images/decoy-duck.png";
        assert url != null;
        imageIcon = new ImageIcon(url);

        Random random = new Random();
        x = random.nextInt(0, 500);
        y = random.nextInt(0, 500);
    }

    public void paint(Graphics g) {
        g.drawImage(imageIcon.getImage(), x, y, null);
    }

    public void move(JPanel panel) {
        x+=(speed*dx);
        if (x<=0 || x>=panel.getWidth()-imageIcon.getIconWidth()) {
            dx = -dx;
        }
        y+=(speed*dy);
        if (y<=0 || y>=panel.getHeight()-imageIcon.getIconHeight()) {
            dy = -dy;
        }

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
