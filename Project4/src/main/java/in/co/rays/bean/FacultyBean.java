package in.co.rays.bean;

import java.util.Date;

/**
 * @author Mayank
 *
 */
public class FacultyBean extends BaseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String First_Name;
	private String Last_Name;
	private String Gender;
	private Date DOJ;
	private String Qualification;
	private String Email_id;
	private String Mobile_No;
	private int College_id;
	private String College_Name;
	private int Course_id;
	private String Course_Name;
	private int Subject_id;
	private String Subject_Name;

	public FacultyBean() {
		// Default Const;
	}

	public void setFirst_Name(String First_Na) {
		First_Name = First_Na;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public void setDOJ(Date dOJ) {
		DOJ = dOJ;
	}

	public void setQualification(String qualification) {
		Qualification = qualification;
	}

	public void setEmail_id(String email_id) {
		Email_id = email_id;
	}

	public void setMobile_No(String mobile_No) {
		Mobile_No = mobile_No;
	}

	public void setCollege_id(int college_id) {
		College_id = college_id;
	}

	public void setCollege_Name(String college_Name) {
		College_Name = college_Name;
	}

	public void setCourse_id(int course_id) {
		Course_id = course_id;
	}

	public void setCourse_Name(String Course_Na) {
		Course_Name = Course_Na;
	}

	public void setSubject_id(int subject_id) {
		Subject_id = subject_id;
	}

	public void setSubject_Name(String subject_Name) {
		Subject_Name = subject_Name;
	}

	public String getFirst_Name() {
		return First_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public String getGender() {
		return Gender;
	}

	public Date getDOJ() {
		return DOJ;
	}

	public String getQualification() {
		return Qualification;
	}

	public String getEmail_id() {
		return Email_id;
	}

	public String getMobile_No() {
		return Mobile_No;
	}

	public int getCollege_id() {
		return College_id;
	}

	public String getCollege_Name() {
		return College_Name;
	}

	public int getCourse_id() {
		return Course_id;
	}

	public String getCourse_Name() {
		return Course_Name;
	}

	public int getSubject_id() {
		return Subject_id;
	}

	public String getSubject_Name() {
		return Subject_Name;
	}

	public String getkey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	public String getvalue() {
		// TODO Auto-generated method stub
		return First_Name;
	}

}
