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
 *不知道为什么不预先建立D://student.txt就无法执行
 *
 */

public class Save {

	/**

	 * 写入方法

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

	 * 读入方法

	 * 

	 * @return

	 * @throws IOException

	 */

	public static void Read(LinkList list)

			throws IOException {

		InputStream is = new FileInputStream("D://student.txt");

		

		//String str=new ()

		String line; // 用来保存每行读取的内容

		Student stu= new Student();

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		line = reader.readLine(); // 读取第一行

		if(line == null) System.out.println("文件为空");

		while (line!=null&&line.length()>=2) { // 如果 line 为空说明读完了

			//line+="\n"; // 添加换行符

			stu = transformToStudent(line);

			list.addNode(stu);

			System.out.println(line);

			line = reader.readLine(); // 读取下一行

		}

		reader.close();

		is.close();

	}

 

	/**

	 * 将字符串转化为student类加

	 * 

	 * @param str

	 * @param list

	 */

	public static Student transformToStudent(String str) {

		Student stu;

		String[] Str1 = str.split(",");

		if (Str1.length != 3) {

			System.out.println("信息有误");

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

			System.out.println("文件为空");

		else {

			String[] line = Str.split("\n");

			

			// 将字符串转化为student类并加入链表

			for (int i = 0; i < line.length; i++) {

				stu = transformToStudent(line[i]);

				list.addNode(stu);

			}

		}*/

		//

		Scanner in = new Scanner(System.in);

		String help = "请输入key值  数字对应相应功能： 1.增加系统内学生信息  2.删除系统内学生信息 "

				+ "3.修改学生信息  4.查找学生信息  0.退出";

		System.out.println(help);

		int key = in.nextInt();

		while (key != 0) {

			//System.out.println(help);

			switch (key) {

			// 增

			case 1: {

				//do {

					System.out.println("请输入学生信息,顺序为  学号,姓名,学校");

					str = in.next();

					Student stu1 = transformToStudent(str);

					if(list.select(stu1.Snum)){

						System.out.println("该学号已经在系统中，请勿重复输入");

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

			// 删

			case 2: {

				//do {

					System.out.println("请输入学号");

					str = in.next();

					list.deleteNode(str);

					printStudent(list);

					System.out.println(help);

					key = in.nextInt();

				//} while (key == 5);

 

				break;

			}

			// 改

			case 3: {

				//do {

					System.out.println("请输入学号");

					str = in.next();

					Student s2 = list.selectNode(str).stu;

					System.out.println("请输入修改的信息顺序为：姓名,学校");

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

			// 查

			case 4: {

				//do {

					System.out.println("请输入学号");

					String str4 = in.next();

					Student s2 = list.selectNode(str4).stu;

					// String ste = s2.Snum+","+s2.Name+","+s2.School;

					System.out.println("学号：" + s2.Snum);

					System.out.println("姓名：" + s2.Name);

					System.out.println("学校：" + s2.School);

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