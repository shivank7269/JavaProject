import java.util.Scanner;
import java.sql.*;
class Login{
    static String username;
    static String password;
    static boolean login_status=false;
    static String query;
    static Scanner scan=new Scanner(System.in);
    public static void getLogin(){
        if(login_status==true) {
            BankApp.logined();
        }
        else{
            System.out.print("Enter The username :");
            username = scan.nextLine();
            System.out.print("Enter the password :");
            password = scan.nextLine();
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Shadow@123");
                query = "select * from account where username=? and password=?";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet set = pstmt.executeQuery();
                if (set.next()) {
                    System.out.println("Successfully Logined!");
                    BankApp.balance=set.getInt("amount");
                    login_status = true;
                    BankApp.logined();
                } else {
                    System.out.println("You have entered wrong username or password");
                    login_status = false;
                }
                con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
class Register extends Login{
    static String Name;
    static String phone_number;
    public static void getRegister(){
        System.out.print("Enter Your Name :");
        Name=scan.nextLine();
        System.out.print("Enter Your Phone Number:");
        phone_number=scan.nextLine();
        System.out.print("Enter You Username :");
        username=scan.nextLine();
        System.out.print("Enter Password :");
        password=scan.nextLine();
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Shadow@123");
            query="insert into customer(Name,phone_number,username,password) values(?,?,?,?)";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1,Name);
            pstmt.setString(2,phone_number);
            pstmt.setString(3,username);
            pstmt.setString(4,password);
            pstmt.executeUpdate();
            query="insert into account(username,password,amount) values(?,?,1000)";
            PreparedStatement pstmt1=con.prepareStatement(query);
            pstmt1.setString(1,username);
            pstmt1.setString(2,password);
            pstmt1.executeUpdate();
            System.out.println("You have been sucessfully registerd");
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
class BankApp extends Register {
    static int balance;

    public static void viewBalance() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Shadow@123");
            query = "select * from account where username=? and password=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet set = pstmt.executeQuery();
            if (set.next()) {
                balance = set.getInt("amount");
                System.out.println("your Account Balance " + balance + " is only:");
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void withdrawBalance() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Shadow@123");
            System.out.print("Enter the amount you want to withdraw :");
            int withdraw = scan.nextInt();
            query = "select amount from account where username=? and password=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            ResultSet set = pstmt.executeQuery();
            if (set.next()) {
                int balance = set.getInt("amount");
                if (withdraw > balance) {
                    System.out.println("Your Withdrawl is exeeding your available balance..!");
                } else {
                    query = "update account set amount=? where username=? and password=?";
                    PreparedStatement pstmt1 = con.prepareStatement(query);
                    pstmt1.setInt(1, (balance-withdraw));
                    pstmt1.setString(2,username);
                    pstmt1.setString(3,password);
                    pstmt1.executeUpdate();
                    System.out.println("Your remaining balance is " + (balance-withdraw)+ " only");
                    balance=balance-withdraw;
                }
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void depositeBalance() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Shadow@123");
            query = "select amount from account where username=? and password=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet set = pstmt.executeQuery();
            if (set.next()) {
                int balance = set.getInt("amount");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Shadow@123");
            query = "update account set amount=? where username=? and password=?";
            PreparedStatement pstmt1 = con.prepareStatement(query);
            System.out.print("Enter The money you want to deposite:");
            int deposite = scan.nextInt();
            pstmt1.setInt(1, (balance+deposite));
            pstmt1.setString(2, username);
            pstmt1.setString(3, password);
            pstmt1.executeUpdate();
            System.out.println("Your remaining balance is " + (balance+deposite) + " only");
            balance=balance+deposite;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void logined(){
        System.out.print("Choices:\n1 to View Balance\n2 to Deposit\n3 to Withdraw\n4 to Send Money\n5 to Delete\n6 to Log out\n7 to Exit\nEnter Your Choice:");
        int c=scan.nextInt();
        scan.nextLine();
        while(true) {
            if (c == 1) {
                viewBalance();
                logined();
                break;
            }
            else if(c == 2){
                depositeBalance();
                logined();
                break;
            }
            else if(c == 3){
                withdrawBalance();
                logined();
                break;
            }
            else if(c == 4){
                transferMoney();
                logined();
                break;
            }
            else if(c == 5){
                deleteData();
                break;
            }
            else if(c == 6){
                login_status=false;
                break;
            }
            else if(c == 7){
                break;
            }
            else{
                logined();
            }
        }
    }
    public static void transferMoney(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Shadow@123");
            System.out.print("Enter the username of The user you want to send:");
            String receiver = scan.nextLine();
            System.out.print("Enter the phone number:");
            String phone_number=scan.nextLine();
            query = "select * from customer,account where account.username=customer.username and customer.username=? and phone_number=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,receiver);
            pstmt.setString(2,phone_number);
            ResultSet set = pstmt.executeQuery();
            if (set.next()) {
                int rb=set.getInt("amount");
                System.out.print("Enter the amount you want to send:");
                int send = scan.nextInt();
                if (balance > send) {
                    int id = set.getInt("acc_no");
                    query = "update account set amount=? where username=? and acc_no=?";
                    PreparedStatement pstmt1 = con.prepareStatement(query);
                    pstmt1.setInt(1, (rb + send));
                    pstmt1.setString(2, receiver);
                    pstmt1.setInt(3, id);
                    pstmt1.executeUpdate();
                    System.out.println("Successfully Transfered");
                    query = "update account set amount=? where username=? and password=?";
                    PreparedStatement pstmt2 = con.prepareStatement(query);
                    pstmt2.setInt(1, (balance - send));
                    pstmt2.setString(2, username);
                    pstmt2.setString(3, password);
                    pstmt2.executeUpdate();
                    System.out.println("Your remaining balance is " + (balance - send) + " only");
                    balance=balance-send;
                    con.close();
                }
                else{
                    System.out.println("you do not have sufficient available balance");
                }
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteData(){
        try {
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Shadow@123");
            query = "delete from customer where username=? and password=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            pstmt.executeUpdate();
            query = "delete from account where username=? and password=?";
            PreparedStatement pstmt1 = con.prepareStatement(query);
            pstmt1.setString(1,username);
            pstmt1.setString(2,password);
            pstmt1.executeUpdate();
            login_status=false;
            con.close();
            System.out.println("You data is deleted");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Welcome to MyBank\nChoices:\n1 to Login\n2 to Register\n3 to Go Back\nEnter Your choice:");
        int choice = sc.nextInt();
        while(true) {
            if (choice == 1) {
                getLogin();
                main(args);
                break;
            } else if (choice == 2) {
                getRegister();
                System.out.println("Registered successfully Now You Can Login");
            } else if(choice == 3){
                break;
            }
            else {
                System.out.println("You have entered wrong input !..");
            }
            break;
        }
    }

}
