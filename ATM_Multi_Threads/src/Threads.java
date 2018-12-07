
public class Threads implements Runnable{
	ATM atm;
	public Threads(ATM account){
		atm=account;
	}
	
	public void run() {
		atm.process();
	}
}
