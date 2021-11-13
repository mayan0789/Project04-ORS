package in.co.rays.bean;

import java.util.Date;

/**
 * @author Mayank
 *
 */
public class UserBean extends BaseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String First_Name;
	private String Last_Name;
	private String Login;
	private String Password;
	private Date DOB;
	private String Mobile_No;
	private long Roll_Id;
	private String Gender;

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String First_Nam) {
		First_Name = First_Nam;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getMobile_No() {
		return Mobile_No;
	}

	public void setMobile_No(String mobile_No) {
		Mobile_No = mobile_No;
	}

	public long getRoll_Id() {
		return Roll_Id;
	}

	public void setRoll_Id(long l) {
		Roll_Id = l;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public int getUnSuccessfulLogin() {
		// TODO Auto-generated method stub
		return 0;
	}



	public String getkey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	public String getvalue() {
		// TODO Auto-generated method stub
		return Login;
	}

	
	

}
