import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class ListTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		Vector<Integer> vector = new Vector<Integer>();

		// ArrayList add
		long startTime = System.nanoTime();

		for (int i = 0; i < 100000; i++) {
			arrayList.add(i);
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println("ArrayList add:  " + duration);

		// LinkedList add
		startTime = System.nanoTime();

		for (int i = 0; i < 100000; i++) {
			linkedList.add(i);
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("LinkedList add: " + duration);

		// Vector add
		startTime = System.nanoTime();

		for (int i = 0; i < 100000; ++i) {
			vector.add(i);
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("Vector add: " + duration);
		
		System.out.println();

		// ArrayList get
		startTime = System.nanoTime();

		for (int i = 0; i < 10000; i++) {
			arrayList.get(i);
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("ArrayList get:  " + duration);

		// LinkedList get
		startTime = System.nanoTime();

		for (int i = 0; i < 10000; i++) {
			linkedList.get(i);
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("LinkedList get: " + duration);
		
		// Vector get
		startTime=System.nanoTime();
		
		for(int i=0;i<10000;++i) {
			vector.get(i);
		}
		endTime=System.nanoTime();
		duration=endTime-startTime;
		System.out.println("Vector get: "+duration);
		
		System.out.println();

		// ArrayList remove
		startTime = System.nanoTime();

		for (int i = 9999; i >= 0; i--) {
			arrayList.remove(i);
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("ArrayList remove:  " + duration);

		// LinkedList remove
		startTime = System.nanoTime();

		for (int i = 9999; i >= 0; i--) {
			linkedList.remove(i);
		}
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.println("LinkedList remove: " + duration);

		// Vector remove
		startTime=System.nanoTime();
		
		for(int i=9999;i>=0;--i) {
			vector.remove(i);
		}
		endTime=System.nanoTime();
		duration=endTime-startTime;
		System.out.println("Vector remove: "+duration);
		
		System.out.println();
		
		// ArrayList iterator
		startTime=System.nanoTime();
		
		display(arrayList.iterator());
		endTime=System.nanoTime();
		duration=endTime-startTime;
		System.out.println("ArrayList iterator: "+duration);
		
		// LinkList iterator
		startTime=System.nanoTime();
		
		display(linkedList.iterator());
		endTime=System.nanoTime();
		duration=endTime-startTime;
		System.out.println("Linklist iterator: "+duration);
		
		// Vector iterator
		startTime=System.nanoTime();
		
		display(vector.iterator());
		endTime=System.nanoTime();
		duration=endTime-startTime;
		System.out.println("Vector iterator: "+duration);
	}
	
	public static void display(Iterator<Integer> it)
    {
        while(it.hasNext())
        {
            //System.out.print(it.next());
        	it.next();
        }        
    }

}
