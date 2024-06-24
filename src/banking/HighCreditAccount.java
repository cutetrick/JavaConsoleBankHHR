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
		System.out.println("기본이자 : " + rate + "%");
		System.out.println("신용등급 : " + credit);
		System.out.println();
	}

	public double getRate() {
		//형변환시 int로 되기 때문에 rate앞에 (double)을 넣어 실수로 바꿔준다
		double interest = (double)rate / 100;
		return interest;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getCredit() { 
		return credit;
	}
	public void setCredit(String credit) { 
		this.credit = credit;
	}
	
	
	//이자 신용등급 나누기
	
	public double getshowGrade() {
		switch(credit) {
		case "A":
			 return ICustomDefine.A;
		case "B":
			return ICustomDefine.B;
		case "C":
			return ICustomDefine.C;
			default:
				return 0;
		}
		
	}

}
