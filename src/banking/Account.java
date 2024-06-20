package banking;

public class Account {
	private String account;
	private String name;
	private int balance;
	

	public Account(String account, String name, int balance) {
		this.account = account;
		this.name = name;
		this.balance = balance;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}


	public void showAccount() {
		System.out.println("계좌번호" + account);
		System.out.println("고객이름" + name);
		System.out.println("잔고" + balance);
		

	
	}
}
