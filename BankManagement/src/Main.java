import javax.swing.*;
import java.awt.event.*;
public class Main{
    JFrame menuFrame=new JFrame("BankManagement");
    ImageIcon image=new ImageIcon("../images/banking-system.png");
    JLabel menuLabel=new JLabel(image);
    JButton loginButton=new JButton("Login");
    JButton registerButton=new JButton("Register");
    JButton exitButton=new JButton("Exit");
    void mainMenu(){
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(500,500);
        menuFrame.setLayout(null);
        menuLabel.setBounds(0,0,500,500);
        loginButton.setBounds(150,50,200,100);  //first button
        registerButton.setBounds(150,160,200,100);  //second button
        exitButton.setBounds(150,270,200,100);    //third
        menuFrame.add(menuLabel);
        menuFrame.add(loginButton);
        menuFrame.add(registerButton);
        menuFrame.add(exitButton);
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                MyLoginFrame login_page = new MyLoginFrame();
                login_page.myLoginFrame();
                menuFrame.dispose();
            }
        });
        registerButton.addActionListener(e ->{
            MyRegisterFrame register_page = new MyRegisterFrame();
            register_page.myRegisterFrame();
            menuFrame.dispose();
        });
        exitButton.addActionListener(e->{
            System.exit(0);
        });
        menuFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Main m=new Main();
        m.mainMenu();
    }

}
