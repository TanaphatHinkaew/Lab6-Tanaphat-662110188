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

        //button.addActionListener(this);
        //ballSpeed.addChangeListener(this);

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
        Ball b = new Ball(0);
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
            System.out.println("Speed: " + labelSpeed.getText());
        }
    }
}