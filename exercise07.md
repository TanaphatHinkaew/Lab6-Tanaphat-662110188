# Lab07: Inheritance in action

## exercise 7.1

import these libs to your executable class, go figure package and structure by yourself

```
import java.awt.event.*;

//Hint3 you may need this lib
import java.util.Date;

import javax.swing.*;
```
Then use this applet to play with GUI
```
        JFrame f=new JFrame("J Swing intro");  

        //Text field
        final JTextField tf=new JTextField();  
        tf.setBounds(50,50, 150,20);  

        //Button
        JButton b=new JButton("click me");  
        b.setBounds(50,100,95,30);  

        //Action of button
        b.addActionListener(new ActionListener(){  

            public void actionPerformed(ActionEvent e){  
                //Hint1 check how ActionEvent works and get time information then setText to textfield
                //Hint2 maybe check this out: https://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionEvent.html

                tf.setText("When is the click happened?");  
            }  
        });  
        f.add(b);
        f.add(tf);  
        f.setSize(400,400);  
        f.setLayout(null);  
        f.setVisible(true); 
```
Goal of this exercise is let you familar with reading object/lib doc to get what you may want.
The text that represent clicking time should appear on your text field, something like "Thu Feb 08 17:31:11 ICT 2024"

## exercise 7.2

you will have 4 following files

### Lesson.java
```
package org.dii.oop.lesson07.exercise02;

public class Lesson {

    public static void run() {
        PinballApp frame = new PinballApp();
        frame.setVisible(true);
    }
}
```
### PinballApp.java
```
package org.dii.oop.lesson07.exercise02;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinballApp extends JFrame implements ActionListener, ChangeListener {
    protected final int FPS = 1000/120;
    private final BackgroundPanel panel;
    private final JTextField textField;
    private final JLabel labelSpeed;

    public PinballApp() {
        super("Pinball game");

        panel = new BackgroundPanel();
        textField = new JTextField("1", 5);
        JButton button = new JButton("OK");

        JSlider ballSpeed = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);


        // TODO: remove this comment and figure the error

        // button.addActionListener(this);
        // ballSpeed.addChangeListener(this);

        labelSpeed = new JLabel("Speed: " + panel.getSpeed());

        panel.add(new JLabel("Number of ball: "));
        panel.add(textField);
        panel.add(button);
        panel.add(ballSpeed);
        panel.add(labelSpeed);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        button.addActionListener(this);
        ballSpeed.addChangeListener(this);

        Timer timer = new Timer(FPS, panel);
        timer.start();
    }

    // Hint1 remember how exercise 1 manage addActionListener ?

    @Override
    public void actionPerformed(ActionEvent e) {
        String strNumOfBall = textField.getText();
        System.out.println("Number of ball: " + strNumOfBall);
        panel.setNumOfBall(Integer.parseInt(strNumOfBall));
    }

    // Hint2 quite same as actionEvent, but now is changeEvent
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int speed = source.getValue();
            panel.setSpeed(speed);
            labelSpeed.setText("Speed: " + speed);
        }
    }
}

```
### BackgroundPanel.java
```
package org.dii.oop.lesson07.exercise02;

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
        Dimension dimension = new Dimension(300, 400);
        setSize(dimension);
        setPreferredSize(dimension);
        setBackground(Color.white);

        createBall();
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

```

### Ball.java
```
package org.dii.oop.lesson07.exercise02;

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
    private int speed = 5;  // control the speed of the ball

    public Ball(int id) {
   
        // Hint1 check how java can get image resource, where is the directory of java source to place image files

        URL url = getClass().getResource(String.format("images/img%d.png", id)); // "images/img0.png", "images/img1.png", or "images/img2.png"
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

```
Make them run and enjoy your GUI from Java Swing
