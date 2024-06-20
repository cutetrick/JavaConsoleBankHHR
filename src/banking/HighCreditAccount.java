package banking;

public class HighCreditAccount extends Account {

	// 이자율
	int rate;
	// 신용
	String credit;

	public HighCreditAccount(String account, String name, int balance, int rate, String credit) {
		super(account, name, balance);
		this.rate = rate;
		this.credit = credit;
	}

	public void showAccount() {
		System.out.println("신용신뢰계좌");
		super.showAccount();
		System.out.println("기본이자" + rate + "%");
		System.out.println("신용등급" + credit);
	}

	public int getrate() {
		return rate;
	}
	public void setrate(int rate) {
		this.rate = rate;
	}
	public String getcredit() { 
		return credit;
	}
	public void setcredit(String credit) { 
		this.credit = credit;
	}
	//이자 신용등급 나누기
	private int getshowGrade(String credit) {
		System.out.println("실행확인");
		switch(credit) {
		case "A":
			 return 7;
		case "B":
			return 4;
		case "C":
			return 2;
			default:
				return 0;
		}
		
	}

}
