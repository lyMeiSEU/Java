import java.io.Serializable;

class Student extends MyClass implements Serializable {

	private int id;
	private String name;
	private int age;

	public Student() {
	}

	public Student(int sid, String sname, int age) {
		this.id = sid;
		this.name = sname;
		this.age = age;
	}

	public void setSid(int sid) {
		this.id = sid;
	}

	public int getSid() {
		return id;
	}

	public void setSname(String sname) {
		this.name = sname;
	}

	public String getSname() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	/**
	 * Output name and age.
	 */
	public String toString() {
		return this.getSid() + "\t" + this.getSname() + "\t" + this.getAge();
	}
}
