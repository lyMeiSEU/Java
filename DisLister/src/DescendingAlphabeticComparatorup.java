import java.util.Comparator;

public class DescendingAlphabeticComparatorup implements Comparator {
	public int compare(Object obj1, Object obj2) {
		/**
		 * Format list by Up order.
		 */
		return obj1.toString().compareToIgnoreCase(obj2.toString());
	}

	public boolean equals(Object obj1, Object obj2) {
		return !obj1.toString().equals(obj2.toString());
	}
}