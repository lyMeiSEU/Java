import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 *��֪��Ϊʲô��Ԥ�Ƚ���D://student.txt���޷�ִ��
 *
 */

public class Save {

	/**

	 * д�뷽��

	 * 

	 * @param str

	 * @throws IOException

	 */

	/*public static void Write(String str, boolean applent) throws IOException {

		String fileName = "C:" + File.separator + "student.txt";

		File file = new File(fileName);

		OutputStream out = new FileOutputStream(file, applent);

		str += "/r";

		byte[] b = str.getBytes();

		try {

			file.createNewFile();

		} catch (Exception e) {

			e.printStackTrace();

		}

		out.close();

	}*/

	public static void Write(String str,boolean p) throws IOException {

		String fileName = "D:" + File.separator + "student.txt";

		File file = new File(fileName);

		//OutputStream out = new FileOutputStream(file, true);	

		//String str= "dsf";

		String destStr = new String();

		destStr =str;  

        FileWriter fw = new FileWriter("D://student.txt", p);  

        PrintWriter pw = new PrintWriter(fw);  

        pw.println(destStr);  

        

		//byte[] b = str.getBytes();

		try {

			file.createNewFile();

		} catch (Exception e) {

			e.printStackTrace();

		}

		//out.close();

		pw.close();  

        fw.close(); 

	}

 

	/**

	 * ���뷽��

	 * 

	 * @return

	 * @throws IOException

	 */

	public static void Read(LinkList list)

			throws IOException {

		InputStream is = new FileInputStream("D://student.txt");

		

		//String str=new ()

		String line; // ��������ÿ�ж�ȡ������

		Student stu= new Student();

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		line = reader.readLine(); // ��ȡ��һ��

		if(line == null) System.out.println("�ļ�Ϊ��");

		while (line!=null&&line.length()>=2) { // ��� line Ϊ��˵��������

			//line+="\n"; // ��ӻ��з�

			stu = transformToStudent(line);

			list.addNode(stu);

			System.out.println(line);

			line = reader.readLine(); // ��ȡ��һ��

		}

		reader.close();

		is.close();

	}

 

	/**

	 * ���ַ���ת��Ϊstudent���

	 * 

	 * @param str

	 * @param list

	 */

	public static Student transformToStudent(String str) {

		Student stu;

		String[] Str1 = str.split(",");

		if (Str1.length != 3) {

			System.out.println("��Ϣ����");

			return null;

		} else {

			stu = new Student(Str1[1], Str1[0], Str1[2]);

			return stu;

		}

	}

 

	public static void printStudent(LinkList link) throws IOException {

		Node n = link.first.next;

		String S = new String();

		while (n!= null) {

			//System.out.println(n.stu.Snum + "," + n.stu.Name + ","+ n.stu.School);

			S += n.stu.Snum + "," + n.stu.Name + "," + n.stu.School +"\r\n";

			n = n.next;

		}

		Write(S, false);

		System.out.println(S);

	}

 

	/**

	 * 

	 * @param args

	 * @throws IOException

	 */

	public static void main(String[] args) throws IOException {

		LinkList list = new LinkList();

		Student stu = new Student();

		File file = new File("D:" + File.separator + "student.txt");

		Read(list);

		String str = new String();

		/*if (Str.length()== 0)

			System.out.println("�ļ�Ϊ��");

		else {

			String[] line = Str.split("\n");

			

			// ���ַ���ת��Ϊstudent�ಢ��������

			for (int i = 0; i < line.length; i++) {

				stu = transformToStudent(line[i]);

				list.addNode(stu);

			}

		}*/

		//

		Scanner in = new Scanner(System.in);

		String help = "������keyֵ  ���ֶ�Ӧ��Ӧ���ܣ� 1.����ϵͳ��ѧ����Ϣ  2.ɾ��ϵͳ��ѧ����Ϣ "

				+ "3.�޸�ѧ����Ϣ  4.����ѧ����Ϣ  0.�˳�";

		System.out.println(help);

		int key = in.nextInt();

		while (key != 0) {

			//System.out.println(help);

			switch (key) {

			// ��

			case 1: {

				//do {

					System.out.println("������ѧ����Ϣ,˳��Ϊ  ѧ��,����,ѧУ");

					str = in.next();

					Student stu1 = transformToStudent(str);

					if(list.select(stu1.Snum)){

						System.out.println("��ѧ���Ѿ���ϵͳ�У������ظ�����");

					}

					else{

						list.addNode(stu1);

						String S = new String();

						S = stu1.Snum + "," + stu1.Name + "," + stu1.School ;

					}

					//Write(S, true);

					printStudent(list);

					System.out.println(help);

					key = in.nextInt();

				//} while (key == 5);

 

				break;

			}

			// ɾ

			case 2: {

				//do {

					System.out.println("������ѧ��");

					str = in.next();

					list.deleteNode(str);

					printStudent(list);

					System.out.println(help);

					key = in.nextInt();

				//} while (key == 5);

 

				break;

			}

			// ��

			case 3: {

				//do {

					System.out.println("������ѧ��");

					str = in.next();

					Student s2 = list.selectNode(str).stu;

					System.out.println("�������޸ĵ���Ϣ˳��Ϊ������,ѧУ");

					str = in.next();

					String[] Arr = str.split(",");

					s2.Name = Arr[0];

					s2.School = Arr[1];

					printStudent(list);

					System.out.println(help);

					key = in.nextInt();

				//} while (key == 5);

 

				break;

			}

			// ��

			case 4: {

				//do {

					System.out.println("������ѧ��");

					String str4 = in.next();

					Student s2 = list.selectNode(str4).stu;

					// String ste = s2.Snum+","+s2.Name+","+s2.School;

					System.out.println("ѧ�ţ�" + s2.Snum);

					System.out.println("������" + s2.Name);

					System.out.println("ѧУ��" + s2.School);

					System.out.println(help);

					key = in.nextInt();

				//} while (key == 5);

				break;

			}

 

			/*default:

				System.out.println(help);

				break;*/

			}

			

 

		}

 

	}

 

}