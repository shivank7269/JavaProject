import javax.swing.*;

public class MyLoginFrame {
    JFrame loginFrame = new JFrame("LOGIN");
    ImageIcon icon = new ImageIcon("a.png");
    JLabel login_image=new JLabel(icon);
    JLabel userLabel=new JLabel("Username:");
    JLabel passwordLabel=new JLabel("Password:");
    JTextField userText=new JTextField(30);
    JPasswordField passwordText=new JPasswordField(16);
    JButton login_button = new JButton("Login");
    JButton back_button = new JButton("Back to Menu");
    static String username;
    static String password;

    public void myLoginFrame(){
        //make a frame
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(500,500);
        loginFrame.setLayout(null);

        //set bounds
        login_image.setBounds(0,0,500,500);
        userLabel.setBounds(0,100,100,50);
        passwordLabel.setBounds(0,250,100,50);
        login_image.setBounds(0,0,500,500);
        userText.setBounds(120,100,300,50);
        passwordText.setBounds(120,250,300,50);
        login_button.setBounds(260,350,200,50);
        back_button.setBounds(10,350,200,50);
        //add labels and button to frame
        loginFrame.add(login_image);
        loginFrame.add(userLabel);
        loginFrame.add(passwordLabel);
        loginFrame.add(userText);
        loginFrame.add(passwordText);
        loginFrame.add(login_button);
        loginFrame.add(back_button);
        //add action listeners on button
        login_button.addActionListener(e->{
            username= userText.getText();
            password=new String(passwordText.getPassword());
            Login log=new Login(username,password);
            loginFrame.dispose();
        });
        back_button.addActionListener(e->{
            Main m=new Main();
            m.mainMenu();
            loginFrame.dispose();
        });

        //make frame visible
        loginFrame.setVisible(true);
    }

}
