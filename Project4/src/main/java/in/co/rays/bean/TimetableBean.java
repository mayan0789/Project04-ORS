package in.co.rays.bean;

import java.util.Date;

/**
 * @author Mayank
 *
 */
public class TimetableBean extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String Course_Name;
	private long Course_Id;
	private String Subject_Name;
	private long Subject_Id;
	private Date Exam_Date;
	private String Exam_Time;
	private String Semester;

	public TimetableBean(){
		
	}

	public String getCourse_Name() {
		return Course_Name;
	}

	public void setCourse_Name(String course_Name) {
		Course_Name = course_Name;
	}

	public long getCourse_Id() {
		return Course_Id;
	}

	public void setCourse_Id(long course_Id) {
		Course_Id = course_Id;
	}

	public String getSubject_Name() {
		return Subject_Name;
	}

	public void setSubject_Name(String subject_Name) {
		Subject_Name = subject_Name;
	}

	public long getSubject_Id() {
		return Subject_Id;
	}

	public void setSubject_Id(long subject_Id) {
		Subject_Id = subject_Id;
	}

	public Date getExam_Date() {
		return Exam_Date;
	}

	public void setExam_Date(Date exam_Date) {
		Exam_Date = exam_Date;
	}

	public String getExam_Time() {
		return Exam_Time;
	}

	public void setExam_Time(String exam_Time) {
		Exam_Time = exam_Time;
	}

	public String getSemester() {
		return Semester;
	}

	public void setSemester(String semester) {
		Semester = semester;
	}

	public String getkey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getvalue() {
		// TODO Auto-generated method stub
		return Course_Name;
	}



}
