import java.awt.event.*;

//Hint3 you may need this lib
import java.util.Date;

import javax.swing.*;

public class Main {
    public static void main(String[] arge) {
        JFrame f = new JFrame("J Swing intro");

        //Text field
        final JTextField tf = new JTextField();
        tf.setBounds(50, 50, 300, 20);

        //Button
        JButton b = new JButton("click me");
        b.setBounds(50, 100, 95, 30);

        //Action of button
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //Hint1 check how ActionEvent works and get time information then setText to textfield
                //Hint2 maybe check this out: https://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionEvent.html
                Date deta = new Date(e.getWhen());
                tf.setText(deta.toString());
            }
        });
        f.add(b);
        f.add(tf);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }
}