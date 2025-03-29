import java.sql.*;
public class Register{
    String query;
    Register(String Name,String phone_number,String username,String password) {
        System.out.println(Name+" "+phone_number+" "+username+" "+password);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Shadow@123");
            query = "insert into customer values(?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, Name);
            pstmt.setString(2, phone_number);
            pstmt.setString(3, username);
            pstmt.setString(4, password);
            pstmt.executeUpdate();
            query = "insert into account(username,password,amount) values(?,?,0)";
            PreparedStatement pstmt1 = con.prepareStatement(query);
            pstmt1.setString(1, username);
            pstmt1.setString(2, password);
            pstmt1.executeUpdate();
            con.close();
            sucessfullyRegisterd success = new sucessfullyRegisterd();
            success.sr();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
