import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class BackgroundPanel extends JPanel implements ActionListener {
    Vector<Ball> balls = new Vector<>();
    int speed = 1;
    int numOfBall = 1;

    public BackgroundPanel() {
        Dimension dimension = new Dimension(1920, 1080);
        setSize(dimension);
        setPreferredSize(dimension);
        setBackground(Color.white);

        //createBall();
    }

    private void createBall() {
      for (int i=0; i<numOfBall; i++) {
            balls.addElement(new Ball(i%3));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // paint the ball image according to the x and y position
        for (Ball ball : balls) {
            ball.paint(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // "this" mean JPanel

        for (Ball ball : balls) {
            ball.move(this);
        }

        // repaint the ball image,
        // the method actionPerformed(ActionEvent e) will be called.
        repaint();
    }

    // The encapsulation set method, make the speed attribute be writable
    public void setSpeed(int speed) {
        this.speed = speed;
        for (Ball ball : balls) {
            ball.setSpeed(speed);
        }
    }

    // The encapsulation get method, make the speed attribute be readable
    public int getSpeed() {
        return speed;
    }

    public void setNumOfBall(int numOfBall) {
        this.numOfBall = numOfBall;
        createBall();
    }
}
