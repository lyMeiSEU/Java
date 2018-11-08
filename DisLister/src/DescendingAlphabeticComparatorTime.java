import java.io.File;
import java.util.Comparator;

public class DescendingAlphabeticComparatorTime implements Comparator {
	public int compare(Object obj1, Object obj2) {
		/**
		 * Change object into File, use lastModified to get Last Change time.
		 */
		File file1 = (File) obj1;
		File file2 = (File) obj2;
		long diff = file1.lastModified() - file2.lastModified();
		if (diff > 0)
			return 1;
		else if (diff == 0)
			return 0;
		else
			return -1;
	}

	public boolean equals(Object obj1, Object obj2) {
		return !obj1.toString().equals(obj2.toString());
	}
}