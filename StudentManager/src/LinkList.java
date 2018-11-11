import java.util.*;

public class LinkList { // 链表

	public Node first;

	//private int pos;

 

	public LinkList() {

		this.first = new Node(null);

	}

 

/**

 * 

 * @param stu

 */

	public void addNode(Student stu)  {

		Node node = new Node(stu);

		Node current = first;

		while(current.next!= null) {

			current = current.next;

		}

		node.next=current.next;

		current.next = node;

		

	}

 

	// 删除节点

	public void deleteNode(String num) {

		Node current = first;

		if (first.next != null) {

			while (!current.next.stu.Snum.equals(num) && (current.next != null)) {

				current = current.next;

			}

			if (current.next.stu.Snum.equals(num)) {

				current.next = current.next.next;

			} else {

				System.out.println("系统内没有该同学信息");

			}

		} else {

			System.out.println("目前系统内没有存储内容");

		}

	}

 

	// 搜素节点

	public Node selectNode(String num) {

		Node current = first;

		if (first.next != null) {

			while (!current.next.stu.Snum.equals(num) && (current.next != null)) {

				current = current.next;

			}

			if (current.next.stu.Snum.equals(num)) {

				//current.next = current.next.next;

				return current.next;

			} else {

				System.out.println("系统内没有该同学信息");

			}

		} else {

			System.out.println("目前系统内没有存储内容");

		}

		return null;

	}

	

	public boolean select(String num) {

		Node current = first;

		if (first.next != null) {

			while ((current.next.next!= null)&&!current.next.stu.Snum.equals(num)  ) {

				current = current.next;

			}

			if (current.next.stu.Snum.equals(num)) {

				//current.next = current.next.next;

				return true;

			} else {

				System.out.println("系统内没有该同学信息");

			}

		} else {

			System.out.println("目前系统内没有存储内容");

		}

		return false;

	}

 

}
