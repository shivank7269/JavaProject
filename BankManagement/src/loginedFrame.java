import javax.swing.*;
public class loginedFrame {
    BankManagement bm=new BankManagement();
    JFrame loginFrame = new JFrame("Welcome You Have Successfully Logined");
    ImageIcon icon = new ImageIcon("a.png");
    JLabel login_image=new JLabel(icon);
    JButton viewBalance = new JButton("View Balance");
    JButton depositeBalance = new JButton("Deposite Balance");
    JButton withdrawBalance = new JButton("Withdraw Balance");
    JButton transferBalance = new JButton("Transfer Balance");
    JButton delete = new JButton("Delete");
    JButton logout = new JButton("Logout");
    JButton showInfo = new JButton("Show Info");
    loginedFrame(){
        //make  a frame
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(500,500);
        loginFrame.setLayout(null);

        //set bounds
        login_image.setBounds(0,0,500,500);
        viewBalance.setBounds(50,20,150,100);
        depositeBalance.setBounds(50,140,150,100);
        withdrawBalance.setBounds(50,260,150,100);
        transferBalance.setBounds(270,20,150,100);
        delete.setBounds(270,140,150,100);
        showInfo.setBounds(270,260,150,100);
        logout.setBounds(170,360,150,100);

        //add button to frame
        loginFrame.add(login_image);
        loginFrame.add(viewBalance);
        loginFrame.add(depositeBalance);
        loginFrame.add(withdrawBalance);
        loginFrame.add(transferBalance);
        loginFrame.add(delete);
        loginFrame.add(showInfo);
        loginFrame.add(logout);

        //add action listener
        viewBalance.addActionListener(e->{
            bm.viewBalance();
            loginFrame.dispose();
        });
        depositeBalance.addActionListener(e->{
            bm.depositeBalance();
            loginFrame.dispose();
        });
        withdrawBalance.addActionListener(e->{
            bm.withDrawBalance();
            loginFrame.dispose();
        });
        transferBalance.addActionListener(e->{
            bm.transferBalance();
            loginFrame.dispose();
        });
        delete.addActionListener(e->{
            bm.delete();
            loginFrame.dispose();
        });
        showInfo.addActionListener(e->{
            bm.showInfo();
            loginFrame.dispose();
        });
        logout.addActionListener(e->{
            Main m=new Main();
            m.mainMenu();
            loginFrame.dispose();
        });

        //make frame visible
        loginFrame.setVisible(true);
    }
}
