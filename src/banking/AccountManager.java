package banking;

import java.util.HashSet;
import java.util.Scanner;

public class AccountManager {

//	// makeAccount 인스턴스 생성
//	public static int accCnt;
//	public static Account accArr[];
//	//hashSet<>컬렉션을 이용
//	class account<T> {
//		T accCnt;
//		public void store(T accCnt) {
//		this.accCnt = accCnt;	
//	}
//	public T pullOut() {
//		return accCnt;
//	}
//	public AccountManager() {
//		accArr = new Account[50];
//		
//	}
	static HashSet<Account> accounts = new HashSet<Account>();
	
	
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
		
		String accNum , name;
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
					System.out.println("정상적으로 입력되었습니다");

					boolean check = accounts.add(account);
					if(!check) {
						System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
						Scanner scn = new Scanner(System.in);
						String ac = scn.nextLine();

						if (ac.equalsIgnoreCase("y")) {

                            // 중복 계좌를 찾아서 제거하고 다시 추가
                            if (accounts.contains(account)) {
                                accounts.remove(account);
                                accounts.add(account);
                                System.out.println("중복계좌를 덮어썼습니다.");
                            }

						} else if (ac.equalsIgnoreCase("n")){
                            System.out.println("무시함");
                            return;
						}
					}
                } else if (number2 == 2) {
                    System.out.print("기본이자%(정수로 입력): ");
                    rate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("신용등급(A,B,C등급): ");
                    credit = scanner.nextLine();
                    Account account = new HighCreditAccount(accNum, name, balan, rate, credit);
                    System.out.println("정상적으로 입력되었습니다.");

                    boolean check = accounts.add(account);
                    if(!check) {
                        System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
                        Scanner scn = new Scanner(System.in);
                        String ac = scn.nextLine();

                        //만약 y를 누르면 중복 계좌 덮어쓰기
                        if (ac.equalsIgnoreCase("y")) {

                            // 중복 계좌를 찾아서 제거하고 다시 추가
                            if (accounts.contains(account)) {
                                accounts.remove(account);
                                accounts.add(account);
                                System.out.println("중복계좌를 덮어썼습니다.");
                            }
                        } else if
                            (ac.equalsIgnoreCase("n")){
                            System.out.println("무시함");
                            return;
                        }
                    }
				}
			}

			return;
		}
	}

	// 입금하기
	public void depositMoney() {
		boolean isAccountExists = false;
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
		
		double interest = 0;
		double credit = 0;

        // 계좌번호 확인
		for(Account account : accounts) {
			if (account.getAccount().equals(accNum2)) {
				isAccountExists = true;

                // 일반 계좌
				if (account instanceof NormalAccount) {
					interest = ((NormalAccount) account).getRate();
				}
                // 신용계좌
                else if (account instanceof HighCreditAccount) {
                    // 이자
					interest = ((HighCreditAccount) account).getRate();
					// 추가이자
					credit = ((HighCreditAccount) account).getshowGrade();
                }

                int money = account.getBalance();

                // 일반계좌일 경우 credit은 0이다.
                // 따라서 credit 계산은 무시되며 interest만 계산된다.

                // 신용계좌는 creditdl 0이 아니므로 credit도 계산된다.
				money += ((money * interest) + (money * credit) + depNum);

                // 계좌에 입금된 금액을 저장
				account.setBalance(money);

				System.out.println("잔고: " + account.getBalance());
				break;
			}
		}

		if (isAccountExists == false) {
			System.out.println("요청하신 계좌번호가 없습니다.");
		}
	}

    // 출금하기
	public void withdrawMoney() {
        boolean isAccountExists = false;

		Scanner scanner = new Scanner(System.in);

		System.out.print("계좌번호를 입력하세요: ");
		String accNum2 = scanner.nextLine();

		System.out.print("출금하실 금액을 입력하세요: ");
		int withNum = scanner.nextInt();
	
        // 1. 계좌번호 존재 유무 확인
        for(Account account : accounts) {
            if (account.getAccount().equals(accNum2)) {
                isAccountExists = true;

                // 2. 출금할 금액이 잔고보다 작은지 확인
                if (account.getBalance() >= withNum) {

                    if (withNum < 0) {
                        System.out.println("음수를 출금할 수 없습니다.");
                        return;
                    }
                    // 4. 1000원 단위인지 확인
                    if (withNum % 1000 != 0) {
                        System.out.println("출금은 1000원 단위로만 가능합니다.");
                        return;
                    }
                    int money = account.getBalance();
                    money -= withNum;
                    account.setBalance(money);
                    System.out.println("잔고: " + account.getBalance());
                    break;
                } else {
                    System.out.println("잔고가 부족합니다. 현재 잔고 : " + account.getBalance());
                    break;
                }
            }
        }

        if (isAccountExists == false) {
            System.out.println("요청하신 계좌번호가 없습니다.");
        }
	}

	// 계좌삭제
	public void accountDelete() {
        boolean isAccountExists = false;

        Scanner scanner = new Scanner(System.in);

        System.out.print("계좌번호를 입력하세요: ");
        String accNum2 = scanner.nextLine();

        for(Account account : accounts) {
            if (account.getAccount().equals(accNum2)) {
                isAccountExists = true;
                accounts.remove(account);
                System.out.println("계좌가 삭제되었습니다.");
                break;
            }
        }

        if (isAccountExists == false) {
            System.out.println("요청하신 계좌번호가 없습니다.");
        }
	}
	// 계좌 모든 정보 불러오기
	public static void showAccInfo() {
		for(Account account: accounts) {
			
			account.showAccount();
		}
	}
}