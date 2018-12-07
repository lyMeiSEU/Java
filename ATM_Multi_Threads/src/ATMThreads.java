public class ATMThreads {
	ATM Atm;

	public ATMThreads() {
		Atm=new ATM();
		while(true) {
			System.out.println("Welcome to my ATM");
			Thread th=new Thread(new Threads(Atm),"th");
			th.run();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ATMThreads threads = new ATMThreads();
	}
}
