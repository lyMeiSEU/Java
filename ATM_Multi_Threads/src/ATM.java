import java.util.Scanner;
import java.util.regex.Pattern;

public class ATM {

	public static int MAXACCONT = 100;

	public synchronized void process() {
		Account[] acc = new Account[MAXACCONT];
		acc[0] = new Account(String.valueOf("ly".hashCode()), String.valueOf("71117408".hashCode()), "10000");
		Scanner in = new Scanner(System.in);
		int i = 0;
		while (true) {
			System.out.print("Login Or Create Account?(1/2)");
			String choice1 = in.nextLine();
			if (choice1.equalsIgnoreCase("2")) {
				System.out.println("Please Enter your username and password");
				String username = in.nextLine();
				
				String password = in.nextLine();
				acc[++i] = new Account(String.valueOf(username.hashCode()), String.valueOf(password.hashCode()), "0");
			}
			if (choice1.equalsIgnoreCase("1")) {
				System.out.println("Please Enter your username and password");
				String username = in.nextLine();
				String password = in.nextLine();
				int q = 0;
				while (q <= acc.length) {
					q++;
					if (String.valueOf(username.hashCode()).equalsIgnoreCase(acc[i].UserName) && String.valueOf(password.hashCode()).equalsIgnoreCase(acc[i].getpassword())) {
						System.out.print("Enter success");
						break;
					} else if (q == acc.length) {
						System.err.println("ERROR password or username");
						q = 0;
						System.out.println("Please Enter your username and password");
						username = in.nextLine();
						password = in.nextLine();
					}
				}

				System.out.println(" Please choose which services do you want.");
				System.out.println("1.Balance Query");
				System.out.println("2.Deposit");
				System.out.println("3.Withdraw");
				System.out.println("4.Exit");
				a:while (true) {
					String choice2 = in.nextLine();
					if (choice2.equalsIgnoreCase("1"))
						System.out.println(acc[i].getmoney());
					else if (choice2.equalsIgnoreCase("2")) {
						System.out.println("How much do you want to deposit?");
						String money = in.nextLine();
						if (isInteger(money)) {
							long Money = acc[i].getmoney() + Long.parseLong(money);
							acc[i].setmoney(String.valueOf(Money));
							System.out.print("Deposit successfully! And now you have:" + acc[i].getmoney());
						} else
							System.out.println("Please enter an number.");
					} else if (choice2.equalsIgnoreCase("3")) {
						System.out.println("How much do you want to get?");
						String money = in.nextLine();
						if (isInteger(money)) {
							long Money = acc[i].getmoney() - Long.parseLong(money);
							acc[i].setmoney(String.valueOf(Money));
							System.out.println("Withdraw successfully! And now you have:" + acc[i].getmoney());
						}
					}
					else if (choice2.equalsIgnoreCase("4"))
						break a;
					else
						System.err.println("Please enter an number.");
				}
			}
		}

	}

	public static boolean Enter(Account acc, String name, String password) {
		if (name.equalsIgnoreCase(acc.UserName) && password.equalsIgnoreCase(acc.getpassword()))
			return true;
		else
			return false;
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
}
