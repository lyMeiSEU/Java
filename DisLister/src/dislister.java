import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class dislister {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dislister lister = new dislister();
		String PATH, TYPE, WAY;
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the path you want to search.");
		PATH = in.nextLine();
		System.out.print("Please enter the type you want to search for.");
		TYPE = in.nextLine();
		System.out.print("Please enter the way you want to format the list.'down'/'up'/'ByTime'");
		WAY = in.nextLine();
		dirfilter filter = new dirfilter(TYPE);
		/**
		 * Control the input of user.
		 */
		if(!WAY.equalsIgnoreCase("down")&&!WAY.equalsIgnoreCase("up")&&!WAY.equalsIgnoreCase("ByTime")) {
			System.err.print("Please Enter a Correct way!");
			WAY=in.nextLine();
		}
		if (WAY.equalsIgnoreCase("down")) {
			Comparator comparator = new DescendingAlphabeticComparatordown();
			lister.listDirectory(new File(PATH), filter, comparator);
		}
		if (WAY.equalsIgnoreCase("up")) {
			Comparator comparator = new DescendingAlphabeticComparatorup();
			lister.listDirectory(new File(PATH), filter, comparator);
		}
		if(WAY.equalsIgnoreCase("ByTime")){
			Comparator comparator=new DescendingAlphabeticComparatorTime();
			lister.listDirectory(new File(PATH), filter, comparator);
		}
	}

	public void listDirectory(File file, FilenameFilter filter, Comparator comparator) {
		if (!file.exists()) {
			System.out.println("Path Error.");
		} else if (file.isFile()) {
			System.out.print("Not a directory.");
		} else {
			File[] files = file.listFiles(filter);
			System.out.println(files.length);
			Arrays.sort(files, comparator);
			for (int i = 0; i < files.length; ++i) {
				if (files[i].isFile())
					System.out.println(files[i]);
			}
		}
	}

}
