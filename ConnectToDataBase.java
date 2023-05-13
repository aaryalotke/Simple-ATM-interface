import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class ConnectToDataBase {
    Statement stmt = null;
    ResultSet rs = null;
    Connection con = null;
    int CustomerID = 0;
    int Balance_Money = 0;

    //Constructor ->A special function whose name is same as className and has no returnType
    public ConnectToDataBase() {
        try {
            //Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create the connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank?characterEncoding=latin1", "root", "9823999100");
            System.out.println("SUCCESS");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("FAIL");
        }

    }

    public boolean login(String name, String password) throws SQLException {

        stmt = con.createStatement();
        rs = stmt.executeQuery("select * from Customer where CustomerName='" + name + "'and CustomerPassword='" + password + "'");
        if (rs.next()) {
            //retrieve customerID

            CustomerID = rs.getInt("CustomerID");
            return true;
        } else {
            return false;
        }

    }

    public int getBalance(int id) throws SQLException {
        rs = stmt.executeQuery("select BalanceAmount from Account where CustomerID='" + id + "'");
        if (rs.next()) {
            Balance_Money = rs.getInt("BalanceAmount");
            return Balance_Money;
        }else{
            return 0;
        }
    }
    public boolean withDrawMoney(int id, int amount) throws SQLException{
        if(amount > Balance_Money){
            return false;
        }else{
            stmt.executeUpdate("update Account set BalanceAmount='"+(Balance_Money-amount)+"'where CustomerID='"+id+"'");//update the table
            return true;
        }
    }

    public boolean depositMoney(int id, int amount) throws SQLException{

            int a = stmt.executeUpdate("update Account set BalanceAmount='"+(Balance_Money+amount)+"'where CustomerID='"+id+"'");//update the table
            if(a==1){
                return true;
        }else{
                return false;
            }
    }

}

