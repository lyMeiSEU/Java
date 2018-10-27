import java.util.Scanner;
import java.util.regex.Pattern;

public class ATM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account acc = new Account("ly", "71117408", "10000");
		System.out.println(acc.UserName);
		System.out.println(acc.getpassword());
		System.out.println(acc.getmoney());
		Scanner in = new Scanner(System.in);

		while (true) {
			System.out.println("Please Enter your username and password");
			String username = in.nextLine();
			String password = in.nextLine();
			if (!username.equalsIgnoreCase(acc.UserName) || !password.equalsIgnoreCase(acc.getpassword())) {
				System.out.println("ERROR password or username");
				continue;
			}
			System.out.print("Enter success");
			System.out.println(" Please choose which services do you want.");
			System.out.println("1.Balance inquiry");
			System.out.println("2.Deposit");
			System.out.println("3.Withdraw");
			System.out.println("4.Exit");
			while (true) {
				String choice = in.nextLine();
				if (choice.equalsIgnoreCase("1"))
					System.out.println(acc.getmoney());
				else if (choice.equalsIgnoreCase("2")) {
					System.out.println("How much do you want to deposit?");
					String money = in.nextLine();
					if (isInteger(money)) {
						long Money = acc.getmoney() + Long.parseLong(money);
						acc.setmoney(String.valueOf(Money));
						System.out.print("Deposit successfully! And now you have:" + acc.getmoney());
					} else
						System.out.println("Please enter an number.");
				} else if (choice.equalsIgnoreCase("3")) {
					System.out.println("How much do you want to get?");
					String money = in.nextLine();
					if (isInteger(money)) {
						long Money = acc.getmoney() - Long.parseLong(money);
						acc.setmoney(String.valueOf(Money));
						System.out.println("Withdraw successfully! And now you have:" + acc.getmoney());
					} 
					else if (choice.equalsIgnoreCase("4"))
						break;
					else
						System.out.println("Please enter an number.");
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
