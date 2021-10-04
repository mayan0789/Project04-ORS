package in.co.rays.bean;

/**
 * @author Mayank
 *
 */
public class CollegeBean extends BaseBean {
	
	private String Name ;
	private String Address;
	private String State;
	private String City;
	private String PhoneNo;
	
	public  CollegeBean(){
		//Default constructor;

	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public void setState(String state) {
		State = state;
	}
	public void setCity(String city) {
		City = city;
	}
	public void setPhoneNo(String phone_No) {
		PhoneNo = phone_No;
	}
	public String getAddress() {
		return Address;
	}
	public String getState() {
		return State;
	}
	public String getCity() {
		return City;
	}
	public String getPhoneNo() {
		return PhoneNo;
	}
	public String getkey() {
		// TODO Auto-generated method stub
		return id + "";
	}
	public String getvalue() {
		// TODO Auto-generated method stub
		return Name;
	}


	}

