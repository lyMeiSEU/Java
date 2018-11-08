import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClassInfoManagement {

	static void AddInform() {
		/**
		 * Build class list.
		 */
		Scanner in = new Scanner(System.in);
		System.out.println("Buliding a Empty Class");
		List<Student> lis = new ArrayList();
		/**
		 * Build student object and put it into class list.
		 */
		long stdnum;
		System.out.println("Enter Student number in class");
		stdnum = in.nextLong();
		System.out.println("Enter id,name,age of each student");
		while (stdnum-- != 0) {
			int id, age;
			String name;
			id = in.nextInt();
			name = in.next();
			age = in.nextInt();
			Student st = new Student(id, name, age);
			lis.add(st);
		}
		System.out.print("Enter the name of Class");
		String classname = in.nextLine();
		MyClass mc = new MyClass(classname, lis);
		/**
		 * Create Class List.
		 */
		List<MyClass> myclass = new ArrayList();
		myclass.add(mc);
		/**
		 * Write information into files.
		 */

		/**
		 * Read information from files.
		 */
		try {
			try {
				List<MyClass> mylis2 = OutputTheFile();
				for (MyClass mclassout : mylis2) {
					/**
					 * Output Class Info.
					 */
					System.out.println(mclassout.getCname());
					/**
					 * Output Student Info.
					 */
					for (Student ST : mclassout.getSs()) {
						System.out.println(ST.toString());
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.close();
	}

	static void InputTheFile(List<MyClass> mylis) throws IOException {
		/**
		 * Build the relationship between Data and Files.
		 */
		FileOutputStream fos = new FileOutputStream("c:\\classinform.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(mylis);
		System.out.println("Information successfully wirted into file.");
		oos.close();
		fos.close();

	}

	static List<MyClass> OutputTheFile() throws IOException, ClassNotFoundException {
		String filenameTemp,path,fileName;
		System.out.print("Enter file's name");
		Scanner in=new Scanner(System.in);
		path="D:\\";
		fileName=in.nextLine();
		filenameTemp = path+fileName+".txt";//文件路径+名称+文件类型
        File file = new File(filenameTemp);
        try {
            //如果文件不存在，则创建新的文件
            if(!file.exists()){
                file.createNewFile();
                System.out.println("success create file,the file is "+filenameTemp);
                //创建文件成功后，写入内容到文件里
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

		FileInputStream fos = new FileInputStream(file);
		ObjectInputStream oos = new ObjectInputStream(fos);
		List<MyClass> mylis = new ArrayList();
		try {
			while (true) {

				MyClass MC = (MyClass) oos.readObject();
				mylis.add(MC);
			}
		} catch (Exception e) {

		} finally {
			oos.close();
			fos.close();
		}
		in.close();
		System.out.println("File successfully readed.");
		return mylis;
	}

	public static void main(String[] args) {
		AddInform();
	}
}