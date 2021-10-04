package in.co.rays.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Mayank
 *
 */
public abstract class BaseBean implements DropdownlistBean, Serializable, Comparable<BaseBean>{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected long id;
	 protected String CreatedBy;
	 protected String ModifiedBy;
	 protected Timestamp CreatedDateTime;
	 protected Timestamp ModifiedDateTime;
	public long getId() {
		return id;
	}
	public void setId(long pk) {
		this.id = pk;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String Created) {
		CreatedBy = Created;
	}
	public String getModifiedBy() {
		return ModifiedBy;
	}
	public void setModifiedBy(String Modified) {
		ModifiedBy = Modified;
	}
	public Timestamp getCreatedDateTime() {
		return CreatedDateTime;
	}
	public void setCreatedDateTime(Timestamp CreatedDate) {
		CreatedDateTime = CreatedDate;
	}
	public Timestamp getModifiedDateTime() {
		return ModifiedDateTime;
	}
	public void setModifiedDateTime(Timestamp ModifiedDate) {
		ModifiedDateTime = ModifiedDate;
	}

	public int compareTo(BaseBean next) {
		return getvalue().compareTo(next.getvalue());
	}
	public String getkey() {
		// TODO Auto-generated method stub
		return id+"";
	}
	public String getvalue() {
		// TODO Auto-generated method stub
		return null;
	}
	 

}
