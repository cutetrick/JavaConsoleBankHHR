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
		String accNum, name=null;
		int balan=0;
		//이자율
		int rate;
		// 
		String credit;
		Scanner scanner = new Scanner(System.in);
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		while (true) {
			System.out.print("선택해주세요");
			int number2 = scanner.nextInt();
			scanner.nextLine();
			//1또는 2 아니면 유효하지 않는 숫자로 넘어감
			if (!((number2 == 1) || (number2 == 2))) {				
				System.out.println("유효하지 않는 숫자입니다.");
			} else {
				//맞을 시 정보 입력 받기로 넘어감
				System.out.print("계좌번호:");
				accNum = scanner.nextLine();
				System.out.print("고객이름:");
				name = scanner.nextLine();
				System.out.print("잔고:");
				balan = scanner.nextInt();
				
				//만약 입력한 숫자가 1과 같을 때
				if(number2 == 1) {
					System.out.print("기본이자%(정수로 입력): ");
					rate = scanner.nextInt();
					//인스턴스 생성(들어간 정보)
					Account account = new NormalAccount(accNum, name, balan, rate);
					//인스턴스 저장 및 증가
					accArr[accCnt++] = account;
					System.out.println("정상적으로 입력되었습니다");
				}
				//만약 입력한 숫자가 2와 같을때
				if(number2 ==2) {
					System.out.print("기본이자%(정수로 입력): ");
					rate = scanner.nextInt();
					scanner.nextLine(); 
					System.out.print("신용등급(A,B,C등급):");
					credit = scanner.nextLine();
					Account account = new HighCreditAccount(accNum, name, balan, rate, credit);
					accArr[accCnt++] = account;
					System.out.println("정상적으로 입력되었습니다");
				}
				//계정생성됨.메뉴로 들어감
				return;
			}
	
		}
		
	}

	// 입금하기
	public void depositMoney() {
//		NormalAccount normal = new NormalAccount(account, name, balance,rate);
//		HighCreditAccount high = new HighCreditAccount(account, name, balance,rate,credit);
		int accNumY=0;
		Scanner scanner = new Scanner(System.in);
		System.out.print("계좌번호를 입력하세요:");
		String accNum2 = scanner.nextLine();

		System.out.print("입금하실 금액을 입력하세요:");
		int depNum = scanner.nextInt();
		for(int i=0 ; i<accCnt ; i++) {
			if(accNum2.equals(accArr[i].getAccount())) {
				//accArr[i]를 노말로 바꿔불러옴
				if(accArr[i] instanceof NormalAccount) {
					NormalAccount normal = (NormalAccount)accArr[i];
				}
				// accArr[i]를 high로 바꿔불러옴
				if(accArr[i] instanceof HighCreditAccount) {
					HighCreditAccount high = (HighCreditAccount)accArr[i];
				}

				//잔고 합산
				accNumY=1;
				accArr[i].setBalance(accArr[i].getBalance() + depNum);
				System.out.println("잔고: " + accArr[i].getBalance());
			}
		}
		if(accNumY ==0) {
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

		for(int i=0 ; i<accCnt ; i++) {
			if(accNum2.equals(accArr[i].getAccount())) {
		
				accNumY=1;
				//잔고가 출금할 금액보다 큰지 작은지 확인
				if(accArr[i].getBalance() >= withNum) {
					//잔고에서 출금금액 빼기
				 accArr[i].setBalance(accArr[i].getBalance() - withNum);
				 //총 남은 계좌 잔고
				 System.out.println("계좌잔고: " + accArr[i].getBalance());
			}//잔고가 부족시 else로 처리
				else {
				System.out.println("잔고가 부족합니다.");	
				}
			}
		}
		if (accNumY==0) {
			System.out.println("입력하신 계좌번호가 없습니다.");
		}
		
		
	}

	// 계좌 모든 정보 불러오기
	public static void showAccInfo() {
		System.out.println("---------------------");
		for (int i = 0; i < accCnt; i++) {
			accArr[i].showAccount();
			System.out.println("---------------------");
		}
	}
}


	
	
	