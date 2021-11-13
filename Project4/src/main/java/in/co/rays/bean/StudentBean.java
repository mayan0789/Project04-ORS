package in.co.rays.bean;

import java.util.Date;

/**
 * @author Mayank
 *
 */
public class StudentBean extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long College_Id;
	private String College_Name;
	private String First_Name;
	private String Last_Name;
	private Date Date_of_Birth;
	private String Mobile_No;
	private String Email;
	
	public  StudentBean(){
		//Default Const;
	}
	public long getCollege_Id() {
		return College_Id;
	}
	public String getCollege_Name() {
		return College_Name;
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public Date getDate_of_Birth() {
		return Date_of_Birth;
	}
	public String getMobile_No() {
		return Mobile_No;
	}
	public String getEmail() {
		return Email;
	}
	public void setCollege_Id(long l) {
		College_Id = l;
	}
	public void setCollege_Name(String college_Name) {
		College_Name = college_Name;
	}
	public void setFirst_Name(String FirstName) {
		First_Name = FirstName;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public void setDate_of_Birth(Date date_of_Birth) {
		Date_of_Birth = date_of_Birth;
	}
	public void setMobile_No(String mobile_No) {
		Mobile_No = mobile_No;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getkey() {
		// TODO Auto-generated method stub
		return id+"";
	}
	public String getvalue() {
		// TODO Auto-generated method stub
		String fn = First_Name;
		String ln = Last_Name;
		
		String namme = fn+" "+ln;
		return namme;
	}

}
