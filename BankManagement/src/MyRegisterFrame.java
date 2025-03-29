import javax.swing.*;

public class MyRegisterFrame {
    JFrame registerFrame = new JFrame("REGISTER");
    ImageIcon icon = new ImageIcon("a.png");
    JLabel register_image=new JLabel(icon);
    JLabel Name = new JLabel("Name:");
    JLabel phoneNumber=new JLabel("PhoneNumber:");
    JLabel username=new JLabel("Username:");
    JLabel password=new JLabel("Password:");
    JLabel confirmPassword=new JLabel("ConfirmPassword:");
    JTextField NameText = new JTextField(30);
    JTextField usernameText = new JTextField(30);
    JTextField phoneNumberText=new JTextField(12);
    JPasswordField passwordText=new JPasswordField(16);
    JPasswordField confirmPasswordText=new JPasswordField(16);
    JButton register_button = new JButton("Register");
    JButton back_button = new JButton("Back to Menu");
    String sName;
    String sUsername;
    String sPassword;
    String sPhoneNumber;
    public void myRegisterFrame() {
        //make a frame
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setSize(500,500);
        registerFrame.setLayout(null);

        //set bounds
        register_image.setBounds(0,0,500,500);
        Name.setBounds(0,50,100,50);
        NameText.setBounds(100,50,250,50);
        username.setBounds(0,110,100,50);
        usernameText.setBounds(100,110,250,50);
        phoneNumber.setBounds(0,170,100,50);
        phoneNumberText.setBounds(100,170,250,50);
        password.setBounds(0,230,100,50);
        passwordText.setBounds(100,230,250,50);
        register_button.setBounds(260,350,200,50);
        back_button.setBounds(10,350,200,50);

        //add labels and button to frame
        registerFrame.add(register_image);
        registerFrame.add(Name);
        registerFrame.add(NameText);
        registerFrame.add(phoneNumber);
        registerFrame.add(username);
        registerFrame.add(password);
        registerFrame.add(confirmPassword);
        registerFrame.add(usernameText);
        registerFrame.add(phoneNumberText);
        registerFrame.add(passwordText);
        registerFrame.add(confirmPasswordText);
        registerFrame.add(register_button);
        registerFrame.add(back_button);

        //add action listeners on button
        register_button.addActionListener(e->{
            sName=NameText.getText();
            sUsername=usernameText.getText();
            sPhoneNumber=phoneNumberText.getText();
            sPassword=new String(passwordText.getPassword());
            Register register = new Register(sName,sPhoneNumber,sUsername,sPassword);
            registerFrame.dispose();

        });
        back_button.addActionListener(e->{
            Main m=new Main();
            m.mainMenu();
            registerFrame.dispose();
        });

        //make frame visible
        registerFrame.setVisible(true);
    }
}
