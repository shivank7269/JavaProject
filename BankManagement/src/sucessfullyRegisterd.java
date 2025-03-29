import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sucessfullyRegisterd {
    JFrame frame = new JFrame();
    JLabel label = new JLabel("You Have Successfully Registered");

    public void sr() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 200);
        frame.setLayout(null);
        label.setBounds(0, 0, 500, 200);
        frame.add(label);


        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main bankApp = new Main();
                bankApp.mainMenu();
                frame.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();

        frame.setVisible(true);
    }
}


