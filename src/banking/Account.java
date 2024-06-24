package banking;

import java.util.Objects;

abstract class Account {
	//계좌
	private String account;
	//이름
	private String name;
	//잔고
	private int balance;
	

	public Account(String account, String name, int balance) {
		this.account = account;
		this.name = name;
		this.balance = balance;
	}
	//계좌번호 가져오기
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	//잔고 가져오기
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance; 
	}
	
	public String getname() {
		return name;
	}
	
	
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if(!(o instanceof Account)) return false;
//		Account account = (Account) o;
//		return Objects.equals(this.name, account.name);
//	}
	@Override
	public int hashCode() {
		return Objects.hash(account);
		
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Account acc = (Account) o;
		return Objects.equals(account, acc.account);
	}


	public void showAccount() {
		System.out.println("계좌번호 : " + account);
		System.out.println("고객이름 : " + name);
		System.out.println("잔고 : " + balance);
	}
}
 