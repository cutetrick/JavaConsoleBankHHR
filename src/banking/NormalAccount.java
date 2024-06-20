package banking;

public class NormalAccount extends Account {
	//이자율
	int rate;

	public NormalAccount(String account, String name, int balance, int rate) {
		super(account, name, balance);
		this.rate = rate;
	}
	
	public void showAccount() {
		System.out.println("보통계좌");
		super.showAccount();
		System.out.println("기본이자" + rate + "%");
		
	}
	

	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}

}

