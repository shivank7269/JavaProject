import javax.swing.*;
import java.sql.*;
public class Login {
    String query;

    Login(String username,String password) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Shadow@123");
            query = "select * from account where username=? and password=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet set = pstmt.executeQuery();
            if (set.next()) {
                loginedFrame logf=new loginedFrame();
            } else {
                JFrame loginMainMenu=new JFrame("Oops!");
                loginMainMenu.setSize(300,100);
                loginMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                loginMainMenu.setLocationRelativeTo(null);
                JLabel label=new JLabel("You Have Enter The Wrong Username pr Password");
                loginMainMenu.add(label);
                loginMainMenu.setVisible(true);
                Timer time=new Timer(2000,e->{
                    loginMainMenu.dispose();
                    Main m= new Main();
                    m.mainMenu();
                });
                time.setRepeats(false);
                time.start();
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
