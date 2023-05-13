import java.sql.SQLException;
import java.util.Scanner;

public class ATMBank {
    Scanner sc = new Scanner(System.in);
    ConnectToDataBase ctD;
    //Constructor
    public ATMBank() throws SQLException {
        ctD = new ConnectToDataBase();/*creating obj of ConnectToDatabase CLass
                                        Whenever obj of ATMBAnk is Made automatically it will connect to db*/
    }
    public void login_Customer() throws SQLException{
    	System.out.println("Enter Customer Name: ");
    	String name = sc.next();
    	System.out.println("Enter Pin: ");
    	String password = sc.next();
    	boolean flag = ctD.login(name,password); //returnType of loginis boolean
    	if(flag)
    	{
    		System.out.println("Login is Successfull");
    		display_Menu();
    	}else {
    		System.out.println("Login Failed, Try again");
    		login_Customer();
    	}
    }
    
    public void display_Menu() throws SQLException{
    	System.out.println("-----------ATM INTERFACE------------------");
    	System.out.println("1.Check Balance");
    	System.out.println("2.Withdraw Money");
    	System.out.println("3.Deposit Money");
    	System.out.println("4.Logout");
    	System.out.println("----------------------------------------------");
    	System.out.println("Select Your Choice:");
    	int choice = sc.nextInt();
    	switch(choice) {
    	
    	case 1: check_Balance();
    				break;
    	case 2: withdraw_Money();
    				break;
    	case 3: deposit_Money();
    				break;
    	case 4:log_out();
    				break;
    	default:System.out.println("Please Enter choice 1-4!");
    			display_Menu();
    				break;
    					
    	}
    	
    }
    public void check_Balance() throws SQLException {
    	int balance = ctD.getBalance(ctD.CustomerID);
    	System.out.println("Your Total Balance in your Account : "+balance);
    	display_Menu();
    	
    }
    public void withdraw_Money() throws SQLException {
    	System.out.println("Enter the amount to be withdrawn: ");
    	int amount = sc.nextInt();
    	boolean flag = ctD.withDrawMoney(ctD.CustomerID, amount);
    	if(flag) {
    		System.out.println("Recieve Your Money:Rs "+amount);
    		display_Menu();
    	}else {
    		System.out.println("Not Enough Money in the Account!");
    		display_Menu();
    	}
    }
    public void deposit_Money() throws SQLException{
    	System.out.println("Enter the amount to be deposited: ");
    	int amount = sc.nextInt();
    	boolean flag = ctD.depositMoney(ctD.CustomerID, amount);
    	if(flag) {
    		System.out.println("Money Depositted Successfully:Rs "+amount);
    		display_Menu();
    	}
    }
    public void log_out() {
    	System.out.println("You have logged out Successfully! Have a Good Day!");
    	System.exit(0);
    	
    }
    public static void main(String[] args) throws SQLException{
        ATMBank at1 = new ATMBank();
        at1.login_Customer();

    }
}
