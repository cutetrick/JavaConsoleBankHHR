package banking;

import java.util.Scanner;

public class AccountManager {

	// makeAccount 인스턴스 생성
	public static int accCnt = 0;
	public static Account accArr[] = new Account[50];

	// 메인 메뉴 보여주기
	public static void showMenu() {
		System.out.println("-----Menu------");
		System.out.println("1. 계좌계설");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 전체계좌정보출력");
		System.out.println("5. 프로그램 종료");
		System.out.print("선택");
	}

	// 계좌생성하기
	public void makeAccount() {
		System.out.println("==makeAccount() 호출됨===");
		Scanner scanner = new Scanner(System.in);
		// 정보 입력 받기
		System.out.print("계좌번호:");
		String accNum = scanner.nextLine();
		System.out.print("고객이름:");
		String name = scanner.nextLine();
		System.out.print("잔고:");
		int balan = scanner.nextInt();
		// 입력받은 정보를 통해 인스턴스 생성
		Account account = new Account(accNum, name, balan);
		// 인스턴스 배열에 추가
		accArr[accCnt++] = account;
	}

	// 입금하기
	public void depositMoney() {
		int accNumY = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.print("계좌번호를 입력하세요:");
		String accNum2 = scanner.nextLine();

		System.out.print("입금하실 금액을 입력하세요:");
		int depNum = scanner.nextInt();

		for (int i = 0; i < accCnt; i++) {
			if (accNum2.equals(accArr[i].getAccount())) {
				accNumY = 1;

				accArr[i].setBalance(accArr[i].getBalance() + depNum);
				System.out.println("총 계좌 잔고: " + accArr[i].getBalance());

			}
		}
		if (accNumY == 0) {
			System.out.println("요청하신 계좌번호가 없습니다.");
		}
	}

	// 출금하기
	public void withdrawMoney() {
		System.out.print("계좌번호를 입력하세요:");
		Scanner scanner = new Scanner(System.in);
		String accNum2 = scanner.nextLine();
		System.out.print("출금하실 금액을 입력하세요:");
		int withNum = scanner.nextInt();

		int accNumY = 0;

		for (int i = 0; i < accCnt; i++) {
			if (accNum2.equals(accArr[i].getAccount())) {
				accNumY = 1;
				if (accArr[i].getBalance() >= withNum) {
					accArr[i].setBalance(accArr[i].getBalance() - withNum);
					System.out.println("출금이 완료되었습니다.");
					System.out.println("계좌 잔고: " + accArr[i].getBalance());
				} else {
					System.out.println("잔액이 부족합니다.");
				}
			}
		}
		if (accNumY == 0) {
			System.out.println("입력하신 계좌번호가 없습니다.");
		}
	}

	// 계좌 모든 정보 불러오기
	public static void showAccInfo() {
		System.out.println("--------------------");
		for (int i = 0; i < accCnt; i++) {
			accArr[i].showAccount();
			System.out.println("--------------------");
		}
	}
}
