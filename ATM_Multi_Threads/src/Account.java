
public class Account {
	public String UserName;
	private String PassWord;
	private String Money;
	
	Account(String name,String word,String money)
	{
		UserName=name;
		PassWord=word;
		Money=money;
	}
	public String getUserName()
	{
		return UserName;
	}
	public String getpassword()
	{
		return PassWord;
	}
	public long getmoney()
	{
		return Long.parseLong(Money);
	}
	public void setUserName(String username)
	{
		UserName=username;
	}
	
	public void setpassword(String password)
	{
		PassWord=password;
	}
	public void setmoney(String money)
	{
		Money=money;
	}
}
