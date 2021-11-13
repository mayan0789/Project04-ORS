package in.co.rays.bean;

/**
 * @author Mayank
 *
 */
public class MarksheetBean extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String RollNo;
	private long studentId;
	private String Name;
	private int Physics;
	private int Chemistry;
	private int Maths;
	public  MarksheetBean(){
		//Default Const;
	}
	public void setRollNo(String Roll) {
		RollNo = Roll;
	}
	public void setStudentId(long l) {
		studentId = l;
	}
	public void setName(String name) {
		Name = name;
	}
	public void setPhysics(int physics) {
		Physics = physics;
	}
	public void setChemistry(int chemistry) {
		Chemistry = chemistry;
	}
	public void setMaths(int maths) {
		Maths = maths;
	}
	public String getRollNo() {
		return RollNo;
	}
	public long getStudentId() {
		return studentId;
	}
	public String getName() {
		return Name;
	}
	public int getPhysics() {
		return Physics;
	}
	public int getChemistry() {
		return Chemistry;
	}
	public int getMaths() {
		return Maths;
	}
	public String getkey() {
		return id+"";
		// TODO Auto-generated method stub
		
	}
	public String getvalue() {
		return RollNo;
		// TODO Auto-generated method stub
		
	}


}
