package in.co.rays.bean;

public class CourseBean extends BaseBean{
	private String Course_Name;
	private String Description;
	private String Duration;
	
	public String getDuration() {
		return Duration;
	}

	public void setDuration(String duration) {
		Duration = duration;
	}

	public  CourseBean(){
		//Default constructor;
	}

	public String getCourse_Name() {
		return Course_Name;
	}

	public String getDescription() {
		return Description;
	}

	public void setCourse_Name(String course_Name) {
		Course_Name = course_Name;
	}

	public void setDescription(String Descrip) {
		Description = Descrip;
	}

	public String getkey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	public String getvalue() {
		// TODO Auto-generated method stub
		return Course_Name;
	}


}
