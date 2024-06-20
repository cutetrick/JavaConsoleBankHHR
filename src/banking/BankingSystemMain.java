package banking;

import java.util.Scanner;		
			
public class BankingSystemMain {

	Account account;
	
	public static void main(String[] args) {
	      Scanner scan = new Scanner(System.in);
	      AccountManager handler = new AccountManager();
	      
	      
	      while(true) {
	    	  AccountManager.showMenu();
	    	 
	          int Menu = scan.nextInt();
	          switch(Menu) {
	          
	          case ICustomDefine.MAKE:
	        	 handler.makeAccount();
	             break;
	          case ICustomDefine.DEPOSIT:
	             handler.depositMoney();
	             break;
	          case ICustomDefine.WITHDRAW:
	             handler.withdrawMoney();
	             break;
	          case ICustomDefine.INQUIRE:
	        	  handler.showAccInfo();
	             break;
	          case ICustomDefine.EXIT:
	        	  System.out.println("프로그램종료");
	        	  return;
	          } 
	       } 
	    } 
	 } 

