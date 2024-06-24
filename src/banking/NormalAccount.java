package banking;

import java.util.Objects;

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
		System.out.println("기본이자 : " + rate + "%");
		System.out.println();
	}

	public double getRate() {
		double interest = (double)rate/100;
		return interest;
	}
	public void setrate(int rate) {
		this.rate = rate;
	}
}
//	@Override
//	public int hashCode() {
//		System.out.println("확인" );
//		return Objects.hash();
//		
//	}
//	
//	@Override
//	public boolean equals(Object o) {
//		System.out.println("확인" );
//		NormalAccount acc = (NormalAccount) o;
//		if ((acc.getAccount()==super.getAccount())
//				&& acc.getClass().equals(this.getClass())) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//}

	
