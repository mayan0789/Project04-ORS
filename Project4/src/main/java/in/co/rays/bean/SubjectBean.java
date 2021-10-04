package in.co.rays.bean;

/**
 * @author Mayank
 *
 */
public class SubjectBean extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Subject_Name;
	private String Course_Name;
	private long Course_Id;
	private String Description;
	public  SubjectBean(){
		//Default Const;
	}
	public String getSubject_Name() {
		return Subject_Name;
	}
	public String getCourse_Name() {
		return Course_Name;
	}
	public void setSubject_Name(String subject_Name) {
		Subject_Name = subject_Name;
	}
	public void setCourse_Name(String course_Name) {
		Course_Name = course_Name;
	}
	public void setDescription(String Descrip) {
		Description = Descrip;
	}
	public String getDescription() {
		return Description;
	}
	public long getCourse_Id() {
		return Course_Id;
	}
	
	public void setCourse_Id(long cl) {
		this.Course_Id = cl;
	}
	public String getkey() {
		return id+"";
		// TODO Auto-generated method stub
		
	}
	public String getvalue() {
		return Subject_Name;
		// TODO Auto-generated method stub
		
	}
	


}
