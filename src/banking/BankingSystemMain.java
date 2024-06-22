package banking;

import java.util.Scanner;		
			
public class BankingSystemMain {

	Account account;
	
	public static void main(String[] args) {
	      Scanner scan = new Scanner(System.in);
	      AccountManager handler = new AccountManager();
	      while(true) {
	    	  //메인메뉴 보여주기
	    	  AccountManager.showMenu();
	    	
	          int Menu = 0;
	          try {
	        	  String strNum = scan.nextLine();
	        	  Menu = Integer.parseInt(strNum);
	        	  //1-5까지 선택
	        	  if(Menu <1 || Menu>5) {
	        		  throw  new NumberFormatException();
	        	  };
	          }
	          // 1-5사이의 숫자가 아닐 시 예외발생, 문자로 입력시 오류
	          catch(NumberFormatException e ) {
	        	  System.out.println("유효하지 않는 숫자입니다.");
	        	  System.out.println("1~5사이의 숫자를 입력해주세요."); 
	          }
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
	          case ICustomDefine.DELETE:
	        	  handler.accountDelete();
	        	  return;
	          case ICustomDefine.EXIT:
	        	  System.out.println("프로그램종료");
	        	  return;
	        	  
	          } 
	       } 
	    } 
	 } 

