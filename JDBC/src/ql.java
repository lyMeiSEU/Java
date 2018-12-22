import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ql {

	String sqlQuery = null;
	static Statement statement = null;
	static ResultSet rs = null;
	static Connection conn = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ql sql = new ql();
		sql.ConnectMySQL();
		sql.insertData("Los Angeles", "1500", "1999-01-09");
		sql.insertData("San Diego", "250", "1999-01-07");
		sql.insertData("Los Angeles", "300", "1999-01-08");
		sql.insertData("Boston", "700", "1999-01-08");
		sql.selectData();
		System.out.println("Now delete all the data");
		sql.deleteDate("Los Angeles");
		sql.deleteDate("San Diego");
		sql.deleteDate("Boston");
		sql.selectData();
	}

	public void ConnectMySQL() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/bookstore?serverTimezone=GMT%2B8&"
					+ "user=root&password=Mly200211";
			conn = DriverManager.getConnection(dbURL);
			System.out.println("MySQL entered successfully");
		} catch (ClassNotFoundException e) {
			System.out.println("无法找到驱动");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertData(String store_name, String Sales, String Date) {
		try {
			statement=conn.createStatement();
			String sql = "insert into bookstore(store_name,Sales,Date)" + "values('" + store_name + "'," + "'" + Sales
					+ "'," + Date + ")";
			int count=statement.executeUpdate(sql);
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void selectData() {
		try {
			statement=conn.createStatement();
			String sql="select * from bookstore";
			rs=statement.executeQuery(sql);
			String title="store_name"+"\t"+"Sales"+"\t"+"Date"+"\t";
			System.out.println(title);
			int length=title.length();
			length+=6;
			while(length!=0) {
				System.out.print("-");
				length--;
			}
			System.out.println();
			while(rs.next()) {
				System.out.print(rs.getString("store_name")+"\t");
				System.out.print(rs.getString("Sales")+"\t");
				System.out.println(rs.getString("Date"));
			}
			rs.close();
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteDate(String store_name) {
		try {
			statement=conn.createStatement();
			String sql="delete from bookstore where store_name='"+store_name+"'";
			int count=statement.executeUpdate(sql);
			statement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
