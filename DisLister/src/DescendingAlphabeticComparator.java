import java.util.Comparator;

public class DescendingAlphabeticComparator implements Comparator{
	public int compare(Object obj1,Object obj2) {
		return - obj1.toString().compareTo(obj2.toString());
	}
	public boolean equals(Object obj1,Object obj2) {
		return ! obj1.toString().equals(obj2.toString());
	}
}