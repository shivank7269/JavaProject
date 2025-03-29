import javax.swing.*;
import java.sql.*;

public class BankManagement {
    String query;
    MyLoginFrame mlf = new MyLoginFrame();
    String username= MyLoginFrame.username;
    String password= MyLoginFrame.password;
    void viewBalance(){
        int balance;
        try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Shadow@123");
        query = "select * from account where username=? and password=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet set = pstmt.executeQuery();
        if (set.next()) {
            balance = set.getInt("amount");
            String balanceText="your Account Balance " + balance + " is only";
            System.out.println(balanceText);
            con.close();
            JFrame frame= new JFrame("Balance");
            frame.setSize(300,100);
            frame.setLocationRelativeTo(null);
            JLabel label=new JLabel(balanceText);
            frame.add(label);
            frame.setVisible(true);
            Timer timer = new Timer(3000,e->{
                frame.dispose();
                loginedFrame lf =new loginedFrame();
            });
            timer.setRepeats(false); // Only execute once
            timer.start();

        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    void withDrawBalance() {
        JFrame frame = new JFrame("Balance");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel label = new JLabel("Enter Amount To Withdraw:");
        JTextField withdrawAmount = new JTextField(20);
        JButton withdrawButton = new JButton("Withdraw");

        label.setBounds(10, 50, 300, 50);
        withdrawAmount.setBounds(200, 50, 100, 50);
        withdrawButton.setBounds(120, 200, 150, 100);

        frame.add(label);
        frame.add(withdrawButton);
        frame.add(withdrawAmount);

        withdrawButton.addActionListener(e -> {
            try {
                int withdraw = Integer.parseInt(withdrawAmount.getText());

                performWithdrawal(username, password, withdraw);
                frame.dispose();
            } catch (NumberFormatException ex) {
                ex.printStackTrace();

            }
        });

        frame.setVisible(true);

    }

    private void performWithdrawal(String username, String password, int withdraw) {
        String query;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Shadow@123")) {
            query = "SELECT amount FROM account WHERE username=? AND password=?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                try (ResultSet set = pstmt.executeQuery()) {
                    if (set.next()) {
                       int balance = set.getInt("amount");
                        if (withdraw > balance) {
                            JFrame frame = new JFrame("Notification");
                            frame.setSize(400, 300);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            JLabel label = new JLabel("You do not have sufficient balance.", SwingConstants.CENTER);
                            frame.add(label);
                            frame.setVisible(true);
                            Timer time=new Timer(2000,e->{
                                frame.dispose();
                                loginedFrame lf= new loginedFrame();
                            });
                            time.setRepeats(false);
                            time.start();
                        }
                        else {
                            query = "UPDATE account SET amount=? WHERE username=? AND password=?";
                            try (PreparedStatement pstmt1 = con.prepareStatement(query)) {
                                pstmt1.setInt(1, (balance - withdraw));
                                pstmt1.setString(2, username);
                                pstmt1.setString(3, password);
                                pstmt1.executeUpdate();

                                viewBalance();
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void depositeBalance() {
        JFrame frame = new JFrame("Balance");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel label = new JLabel("Enter Amount To Deposite :");
        JTextField depositeAmount = new JTextField(20);
        JButton depositeButton = new JButton("Deposite");

        label.setBounds(10, 50, 300, 50);
        depositeAmount.setBounds(200, 50, 100, 50);
        depositeButton.setBounds(120, 200, 150, 100);

        frame.add(label);
        frame.add(depositeButton);
        frame.add(depositeAmount);


        depositeButton.addActionListener(e -> {
            try {
                int deposite = Integer.parseInt(depositeAmount.getText());

                performDeposite(deposite);
                frame.dispose();
            } catch (NumberFormatException ex) {
                ex.printStackTrace();

            }
        });

        frame.setVisible(true);

    }
    private void performDeposite(int deposite){
        int balance=0;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Shadow@123");
            query = "select amount from account where username=? and password=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet set = pstmt.executeQuery();
            if (set.next()) {
                 balance = set.getInt("amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Shadow@123");
            query = "update account set amount=? where username=? and password=?";
            PreparedStatement pstmt1 = con.prepareStatement(query);
            pstmt1.setInt(1, (balance + deposite));
            pstmt1.setString(2, username);
            pstmt1.setString(3, password);
            pstmt1.executeUpdate();
            viewBalance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void transferBalance(){
        JFrame frame= new JFrame("Balance");
        frame.setSize(400,300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel label1=new JLabel("Enter name of Receiver :");
        JLabel label2=new JLabel("Enter phone Number oF Receiver :");
        JTextField r1= new JTextField(30);
        JTextField r2= new JTextField(30);
        JButton transferButton = new JButton("Add Amount");

        label1.setBounds(0,50,200,50);
        label2.setBounds(0,150,200,50);
        r1.setBounds(200,50,200,50);
        r2.setBounds(200,150,200,50);
        transferButton.setBounds(120,200,150,100);
        frame.add(label1);
        frame.add(label2);
        frame.add(r1);
        frame.add(r2);
        frame.add(transferButton);
        transferButton.addActionListener(e->{
            String r=r1.getText();
            String pn=r2.getText();
            transferFrameMethod(r,pn);
            frame.dispose();
        });
        frame.setVisible(true);

    }
    void transferFrameMethod(String r,String pn){

        JFrame frame = new JFrame("Transfer");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel label = new JLabel("Enter Amount To Send:");
        JTextField sendAmount = new JTextField(20);
        JButton sendButton = new JButton("Transfer");

        label.setBounds(10, 50, 300, 50);
        sendAmount.setBounds(200, 50, 100, 50);
        sendButton.setBounds(120, 200, 150, 100);

        frame.add(label);
        frame.add(sendButton);
        frame.add(sendAmount);

        sendButton.addActionListener(e -> {
            try {
                int send = Integer.parseInt(sendAmount.getText());

                performTransfer(r,pn,send);
                frame.dispose();

            } catch (NumberFormatException ex) {
                ex.printStackTrace();

            }
        });

        frame.setVisible(true);
    }
    void performTransfer(String receiver,String phone_number,int send){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Shadow@123");
            System.out.println("username:"+username+"  password:"+password+"  reciever:"+receiver);
            query = "select * from account where username=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet set = pstmt.executeQuery();
            if (set.next()) {
                int rb = set.getInt("amount");

                if (rb >= send) {
                    query = "update account set amount=amount+? where username=?";
                    PreparedStatement pstmt1 = con.prepareStatement(query);
                    pstmt1.setInt(1, (send));
                    pstmt1.setString(2, receiver);
                    pstmt1.executeUpdate();
                    System.out.println("Successfully Transfered");
                    int balance;
                    query = "update account set amount=? where username=? and password=?";
                    PreparedStatement pstmt2 = con.prepareStatement(query);
                    balance=rb-send;
                    pstmt2.setInt(1, (balance));
                    pstmt2.setString(2, username);
                    pstmt2.setString(3, password);
                    pstmt2.executeUpdate();
                    System.out.println("Your remaining balance is " + balance + " only");
                    con.close();
                    loginedFrame lf= new loginedFrame();
                }
                else {
                    JFrame frame = new JFrame("Notification");
                    frame.setSize(400, 300);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JLabel label = new JLabel("You do not have sufficient balance.", SwingConstants.CENTER);
                    frame.add(label);
                    frame.setVisible(true);
                    Timer time=new Timer(2000,e->{
                        frame.dispose();
                        loginedFrame lf= new loginedFrame();
                    });
                    time.setRepeats(false);
                    time.start();
                }
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void delete(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Shadow@123");
            query = "delete from customer where username=? and password=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            query = "delete from account where username=? and password=?";
            PreparedStatement pstmt1 = con.prepareStatement(query);
            pstmt1.setString(1, username);
            pstmt1.setString(2, password);
            pstmt1.executeUpdate();
            con.close();

            JFrame frame= new JFrame("Success");
            frame.setSize(300,100);
            frame.setLocationRelativeTo(null);
            JLabel label=new JLabel("Successfully Deleted");
            frame.add(label);
            frame.setVisible(true);
            Timer timer = new Timer(2000,e->{
                frame.dispose();
                Main m = new Main();
                m.mainMenu();
            });
            timer.setRepeats(false); // Only execute once
            timer.start();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    void showInfo(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 300); // Adjusted the size of the JFrame

        String url = "jdbc:mysql://localhost:3306/mydb";
        String u = "root";
        String p = "Shadow@123";

        try {
            Connection conn = DriverManager.getConnection(url, u, p);
            String query = "select * from customer where username=? and password=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            int y = 0; // This will be used to position the labels

            while (rs.next()) {
                String col1 = rs.getString("Name");
                String col2 = rs.getString("phone_number");
                String col3 = rs.getString("username");
                String col4 = rs.getString("password");

                JLabel label1 = new JLabel("Name: " + col1);
                label1.setBounds(0, y, 200, 30);
                frame.add(label1);

                JLabel label2 = new JLabel("Phone: " + col2);
                label2.setBounds(200, y, 200, 30);
                frame.add(label2);

                y += 30; // Increase y by 30 for each row

                JLabel label3 = new JLabel("Username: " + col3);
                label3.setBounds(0, y, 200, 30);
                frame.add(label3);

                JLabel label4 = new JLabel("Password: " + col4);
                label4.setBounds(200, y, 200, 30);
                frame.add(label4);

                y += 30; // Increase y by 30 for each row
            }
            y=200;
            JButton back_button = new JButton("Back To Menu");
            back_button.setBounds(0, y, 400, 100); // Adjusted the y-coordinate here
            back_button.addActionListener(e -> {
                frame.dispose();
                loginedFrame lf = new loginedFrame();
            });
            frame.add(back_button);

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
    }

}


