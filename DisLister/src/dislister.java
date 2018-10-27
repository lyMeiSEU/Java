import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

public class dislister {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dislister lister=new dislister();
		dirfilter filter=new dirfilter(".*log");
		Comparator comparator=new DescendingAlphabeticComparator();
		lister.listDirectory(new File("c:/Windows"), filter, comparator);
	}
	
	public void listDirectory(File file,FilenameFilter filter,Comparator comparator)
	{
		if(!file.exists()) {
			System.out.println("Path Error.");
		}
		else if(file.isFile())
		{
			System.out.print("Not a directory.");
		}
		else {
			File[] files=file.listFiles(filter);
			System.out.println(files.length);
			Arrays.sort(files,comparator);
			for(int i=0;i<files.length;++i)
			{
				if(files[i].isFile())
					System.out.println(files[i]);
			}
		}
	}
}
