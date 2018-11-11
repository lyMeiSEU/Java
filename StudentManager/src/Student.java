

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

 

class Student {

	public String Name;

	public String Snum;

	public String School;

 

	public Student() {

 

	}

 

	public Student(String name, String snum, String school) {

		Name = name;

		Snum = snum;

		School = school;

	}

}

 

class Node { // ½Úµã

	public Node next;

	Student stu ;

	public Node(Student stu) {

		this.stu = stu;

	}

}
