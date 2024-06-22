package banking;

import java.util.Scanner;

public class AccountManager {

	// makeAccount 인스턴스 생성
	public static int accCnt;
	public static Account accArr[];

	public AccountManager() {
		accArr = new Account[50];
		accCnt = 0;
	}

	
	// 메인 메뉴 보여주기
	public static void showMenu() {
		System.out.println("-----Menu------");
		System.out.println("1. 계좌계설");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 전체계좌정보출력");
		System.out.println("5. 계좌정보 삭제");
		System.out.println("6. 프로그램 종료");
		System.out.print("선택: ");
	}

	// 계좌생성하기
	public void makeAccount() {
		String accNum, name = null;
		int balan = 0;
		// 이자율
		int rate;
		//
		String credit;
		Scanner scanner = new Scanner(System.in);
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		while (true) {
			System.out.print("선택해주세요: ");
			int number2 = scanner.nextInt();
			scanner.nextLine();

			// 1또는 2 아니면 유효하지 않는 숫자로 넘어감

			if (!((number2 == 1) || (number2 == 2))) {
			} else {
				// 맞을 시 정보 입력 받기로 넘어감
				System.out.print("계좌번호: ");
				accNum = scanner.nextLine();
				System.out.print("고객이름: ");
				name = scanner.nextLine();
				System.out.print("잔고: ");
				balan = scanner.nextInt();
				scanner.nextLine();
				// 만약 입력한 숫자가 1과 같을 때
				if (number2 == 1) {
					System.out.print("기본이자%(정수로 입력): ");
					rate = scanner.nextInt();
					scanner.nextLine();
					// 인스턴스 생성(들어간 정보)
					Account account = new NormalAccount(accNum, name, balan, rate);
					// 인스턴스 저장 및 증가
					accArr[accCnt++] = account;
					System.out.println("정상적으로 입력되었습니다.");
				}
				// 만약 입력한 숫자가 2와 같을때
				if (number2 == 2) {
					System.out.print("기본이자%(정수로 입력): ");
					rate = scanner.nextInt();
					scanner.nextLine();
					System.out.print("신용등급(A,B,C등급): ");
					credit = scanner.nextLine();
					Account account = new HighCreditAccount(accNum, name, balan, rate, credit);
					accArr[accCnt++] = account;
					System.out.println("정상적으로 입력되었습니다.");
				}
				// 계정생성됨.메뉴로 들어감
				return;
			}
		}
	}

	// 입금하기
	public void depositMoney() {
		int accNumY = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.print("계좌번호를 입력하세요: ");
		String accNum2 = scanner.nextLine();

		System.out.print("입금하실 금액을 입력하세요: ");
		int depNum = scanner.nextInt();
		scanner.nextLine();

		// 입력한 금액이 0보다 작을 수 없다. 음수인지 확인
		if (depNum < 0) {
			System.out.println("음수를 입금할 수 없습니다.");
			return;
		}
		// 500원 단위인지 확인
		if (depNum % 500 != 0) {
			System.out.println("입금은 500원 단위로만 가능합니다.");
			return;
		}

		for (int i = 0; i < accCnt; i++) {
			if (accNum2.equals(accArr[i].getAccount())) {
				// 이자
				double interest = 0;
				// 추가이자
				double credit = 0;
				if (accArr[i] instanceof NormalAccount) {
					interest = ((NormalAccount) accArr[i]).getRate();
				}
				if (accArr[i] instanceof HighCreditAccount) {
					// 이자
					interest = ((HighCreditAccount) accArr[i]).getRate();
					// 추가이자
					credit = ((HighCreditAccount) accArr[i]).getshowGrade();
				}
				int money = accArr[i].getBalance();
				// 신용계좌 : 잔고 + (잔고 * 기본이자) + (잔고 * 추가이자) + 입금액
				money += ((money * interest) + (money * credit) + depNum);
				accArr[i].setBalance(money);

				System.out.println("잔고: " + accArr[i].getBalance());
				accNumY = 1;
				break;
			}
		}

		if (accNumY == 0) {
			System.out.println("요청하신 계좌번호가 없습니다.");
		}
	}

	// 출금하기
	public void withdrawMoney() {
		System.out.print("계좌번호를 입력하세요: ");
		Scanner scanner = new Scanner(System.in);
		String accNum2 = scanner.nextLine();
		System.out.print("출금하실 금액을 입력하세요: ");
		int withNum = scanner.nextInt();

		int accNumY = 0;

		for (int i = 0; i < accCnt; i++) {
			if (accNum2.equals(accArr[i].getAccount())) {
				accNumY = 1;
				// 잔고가 출금할 금액보다 큰지 작은지 확인
				if (accArr[i].getBalance() >= withNum) {
					// 음수를 출금할 수 없습니다.
					if (withNum < 0) {
						System.out.println("음수를 출금할 수 없습니다.");
						return;
					}
					// 1000원 단위인지 확인
					if (withNum % 1000 != 0) {
						System.out.println("출금은 1000원 단위로만 가능합니다.");
						return;
					}
					// 잔고에서 출금금액 빼기
					accArr[i].setBalance(accArr[i].getBalance() - withNum);
					// 총 남은 계좌 잔고
					System.out.println("계좌잔고: " + accArr[i].getBalance());
				} // 잔고가 부족시 else로 처리
				else {
					System.out.print("잔고가 부족합니다. 금액전체를 출금할까요?(y/n): ");
					scanner.nextLine();
					String select = scanner.nextLine();
					// equalsIgnoreCase가 대소문자 구분없이 해줌
					if (select.equalsIgnoreCase("y")) {
						withNum = accArr[i].getBalance();
						accArr[i].setBalance(0);
						System.out.println("잔고 전체 출금 완료.");
						System.out.println("출금한 금액: " + withNum + "원");
					} else {
						System.out.println("출금 요청이 취소되었습니다.");
						return;
					}
				}
				break;
			}
		}

		if (accNumY == 0) {
			System.out.println("입력하신 계좌번호가 없습니다.");
		}
	}

	// 계좌삭제
	public void accountDelete() {
		
	}
	// 계좌 모든 정보 불러오기
	public static void showAccInfo() {
		for (int i = 0; i < accCnt; i++) {
			accArr[i].showAccount();
		}
	}
}