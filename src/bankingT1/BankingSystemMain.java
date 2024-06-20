package bankingT1;

import java.util.Scanner;

public class BankingSystemMain {


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AccountManager am = new AccountManager();
		
		//한번만 보여주는게 아니기때문에 루프시키기 위해 while문 사용
		while (true) {
			am.showMenu();
			int choice = scanner.nextInt();

			switch (choice) {
			case ICustomDefine.MAKE:
				System.out.println("계좌계설 선택");
				am.makeAccount();
				//선택을 누르고나서 선택된것만 보여주기 위해 break문 사용
				break;
			case ICustomDefine.DEPOSIT:
				System.out.println("입금 선택");
				am.depositMoney();
				break;
			case ICustomDefine.WITHDRAW:
				System.out.println("출금 선택");
				am.withdrawMoney();
				break;
			case ICustomDefine.INQUIRE:
				System.out.println("전체계좌정보 출력 선택");
				am.showAccInfo();
				break;
			case ICustomDefine.EXIT:
				System.out.println("종료 선택");
				//프로그램 종료
				return;
			}
		}
	}
}
