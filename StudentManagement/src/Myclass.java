import java.io.Serializable;
import java.util.List;

class MyClass implements Serializable {

	private String cname;
	private List<Student> ss;

	public MyClass() {
	}

	public MyClass(String cname, List<Student> ss) {
		this.cname = cname;
		this.ss = ss;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCname() {
		return cname;
	}

	public void setSs(List<Student> ss) {
		this.ss = ss;
	}

	public List<Student> getSs() {
		return ss;
	}
	/*
	 * public String toString(){
	 * 
	 * return this.cname+"\t"+this.ss; }
	 */
}