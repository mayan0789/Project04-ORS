
package in.co.rays.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DataBaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.util.EmailBuilder;
import in.co.rays.util.EmailMessage;
import in.co.rays.util.EmailUtility;
import in.co.rays.util.JDBCDataSource;

/**
 * @author Mayank
 *
 */
public class UserModel {

	/**
	 * Find next PK of user
	 *
	 */
	private long roleId;

	/**
	 */
	public long getRoleId() {
		return roleId;
	}

	/**
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public int nextPK() throws DataBaseException {
		// log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		// System.out.println("pk");
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_USER");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new DataBaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model nextPK End");
		return pk + 1;
	}

	/**
	 * Add a User
	 *
	 *
	 */
	public int add(UserBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		UserBean existbean = findByLogin(bean.getLogin());

		if (existbean != null) {
			throw new DuplicateRecordException("Login Id already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in User Model");

			conn.setAutoCommit(false); // Begin transaction
			
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getFirst_Name());
			pstmt.setString(3, bean.getLast_Name());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDOB().getTime()));
			pstmt.setString(7, bean.getMobile_No());
			pstmt.setLong(8, bean.getRoll_Id());
			pstmt.setString(9, bean.getGender());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDateTime());
			pstmt.setTimestamp(13, bean.getModifiedDateTime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// log.error("Database Exception..", e);
		/*
		 * try { conn.rollback(); } catch (Exception ex) { ex.printStackTrace(); throw
		 * new ApplicationException( "Exception : add rollback exception " +
		 * ex.getMessage()); } // throw new
		 * ApplicationException("Exception : Exception in add User"); }
		 */ finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a User
	 *
	 */
	public void delete(UserBean bean) throws ApplicationException {
		// log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_USER WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model delete Started");
	}

	/**
	 * Find User by Login
	 *
	 */

	public UserBean findByLogin(String login) throws ApplicationException {
		// log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE LOGIN=?");
		UserBean bean = null;
		Connection conn = null;
		System.out.println("USer Model Find by login " + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt(1));
				bean.setFirst_Name(rs.getString(2));
				bean.setLast_Name(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDOB(rs.getDate(6));
				bean.setMobile_No(rs.getString(7));
				bean.setRoll_Id(rs.getInt(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception..", e);
			// throw new ApplicationException( "Exception : Exception in getting User by
			// login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model findByLogin End");
		return bean;
	}

	/**
	 * Find User by PK
	 *
	 */

	public UserBean findByPK(long l) throws ApplicationException {
		// log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE ID=?");
		UserBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, l);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt(1));
				bean.setFirst_Name(rs.getString(2));
				bean.setLast_Name(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDOB(rs.getDate(6));
				bean.setMobile_No(rs.getString(7));
				bean.setRoll_Id(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model findByPK End");
		return bean;
	}

	/**
	 * Update a user
	 *
	 */

	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Model update Started");
		Connection conn = null;

		UserBean beanExist = findByLogin(bean.getLogin());
		// Check if updated LoginId already exist
		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException("LoginId is already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_USER SET FIRST_NAME=?,LAST_NAME=?,LOGIN=?,PASSWORD=?,DOB=?,MOBILE_NO=?,ROLE_ID=?,GENDER=?,Created_By=?,Modified_By=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getFirst_Name());
			pstmt.setString(2, bean.getLast_Name());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDOB().getTime()));
			pstmt.setString(6, bean.getMobile_No());
			pstmt.setLong(7, bean.getRoll_Id());

			pstmt.setString(8, bean.getGender());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDateTime());
			pstmt.setTimestamp(12, bean.getModifiedDateTime());
			pstmt.setLong(13, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model update End");
	}

	/**
	 * Search User
	 *
	 */

	public List search(UserBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search User with pagination
	 *
	 */

	public List search(UserBean bean, int pageNo, int pageSize) throws ApplicationException {
		// log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getFirst_Name() != null && bean.getFirst_Name().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + bean.getFirst_Name() + "%'");
			}
			if (bean.getLast_Name() != null && bean.getLast_Name().length() > 0) {
				sql.append(" AND LAST_NAME like '" + bean.getLast_Name() + "%'");
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" AND LOGIN like '" + bean.getLogin() + "%'");
			}
			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" AND PASSWORD like '" + bean.getPassword() + "%'");
			}
			if (bean.getDOB() != null && bean.getDOB().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getDOB());
			}
			if (bean.getMobile_No() != null && bean.getMobile_No().length() > 0) {
				sql.append(" AND MOBILE_NO = " + bean.getMobile_No());
			}
			if (bean.getRoll_Id() > 0) {
				sql.append(" AND ROLE_ID = " + bean.getRoll_Id());
			}

			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" AND GENDER like '" + bean.getGender() + "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
		}

		System.out.println(" User model search " + sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt(1));
				bean.setFirst_Name(rs.getString(2));
				bean.setLast_Name(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDOB((rs.getDate(6)));
				bean.setMobile_No(rs.getString(7));
				bean.setRoll_Id(rs.getInt(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Model search End");
		return list;
	}

	/**
	 * Get List of User
	 *
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of User with pagination
	 *
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER where 1=1");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + "," + pageSize);
		}

		Connection conn = null;
		System.out.println(sql);
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserBean bean = new UserBean();
				bean.setId(rs.getInt(1));
				bean.setFirst_Name(rs.getString(2));
				bean.setLast_Name(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDOB(rs.getDate(6));
				bean.setMobile_No(rs.getString(7));
				bean.setRoll_Id(rs.getInt(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));
				list.add(bean);

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model list End");
		return list;

	}

	/**
	 */

	public UserBean authenticate(String login, String password) throws Exception {
		// log.debug("Model authenticate Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE LOGIN = ? AND PASSWORD = ?");
		UserBean bean = null;
		Connection conn = null;

//        RoleBean rbean=findByPK(bean.getId());
		try {
			// conn=JDBCDataSource.getConnection();
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project04", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, login);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt(1));
				bean.setFirst_Name(rs.getString(2));
				bean.setLast_Name(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDOB(rs.getDate(6));
				bean.setMobile_No(rs.getString(7));
				bean.setRoll_Id(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));

			}
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception..", e);
			// throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Model authenticate End");
		return bean;
	}

	/**
	 * Lock User for certain time duration
	 *
	 */

	/*
	 * public boolean lock(String login) throws RecordNotFoundException,
	 * ApplicationException { // log.debug("Service lock Started"); boolean flag =
	 * false; UserBean beanExist = null; try { beanExist = findByLogin(login); if
	 * (beanExist != null) { beanExist.setLock(UserBean.Active); update(beanExist);
	 * flag = true; } else { throw new RecordNotFoundException("LoginId not exist");
	 * } } catch (DuplicateRecordException e) {
	 * //log.error("Application Exception..", e); throw new
	 * ApplicationException("Database Exception"); } //
	 * log.debug("Service lock End"); return flag; }
	 */

	/**
	 * Get User Roles
	 *
	 */

	public List getRoles(UserBean bean) throws ApplicationException {
		// log.debug("Model get roles Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE ROLE_Id=?");
		Connection conn = null;
		List list = new ArrayList();
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, bean.getRoll_Id());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt(1));
				bean.setFirst_Name(rs.getString(2));
				bean.setLast_Name(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDOB(rs.getDate(6));
				bean.setMobile_No(rs.getString(7));
				bean.setRoll_Id(rs.getInt(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model get roles End");
		return list;
	}

	/**
	 */

	public boolean changePassword(long l, String oldPassword, String newPassword)
			throws RecordNotFoundException, ApplicationException {

		// log.debug("model changePassword Started");
		boolean flag = false;
		UserBean beanExist = null;

		beanExist = findByPK(l);
		if (beanExist != null && beanExist.getPassword().equals(oldPassword)) {
			beanExist.setPassword(newPassword);
			try {
				update(beanExist);
			} catch (DuplicateRecordException e) {
				// log.error(e);
				throw new ApplicationException("LoginId is already exist");
			}
			flag = true;
		} else {
			throw new RecordNotFoundException("Login not exist");
		}

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("login", beanExist.getLogin());
		map.put("password", beanExist.getPassword());
		map.put("firstName", beanExist.getFirst_Name());
		map.put("lastName", beanExist.getLast_Name());

		String message = EmailBuilder.getChangePasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(beanExist.getLogin());
		msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		 EmailUtility.sendMail(msg);

		// log.debug("Model changePassword End");
		return flag;

	}

	public UserBean updateAccess(UserBean bean) throws ApplicationException {
		return null;
	}

	/**
	 * Register a user
	 *
	 */

	public int registerUser(UserBean bean) throws ApplicationException, DuplicateRecordException {

		// log.debug("Model add Started");

		int pk = add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(bean.getLogin());
		msg.setSubject("Registration is successful for ORS Project SunilOS");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		return pk;
	}

	/**
	 * Reset Password of User with auto generated Password
	 *
	 */

	public boolean resetPassword(UserBean bean) throws ApplicationException {

		String newPassword = String.valueOf(new Date().getTime()).substring(0, 4);
		UserBean userData = findByPK(bean.getId());
		userData.setPassword(newPassword);

		try {
			update(userData);
		} catch (DuplicateRecordException e) {
			return false;
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());
		map.put("firstName", bean.getFirst_Name());
		map.put("lastName", bean.getLast_Name());

		String message = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(bean.getLogin());
		msg.setSubject("Password has been reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		return true;
	}

	/**
	 * Send the password of User to his Email
	 *
	 */

	public boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException {
		UserBean userData = findByLogin(login);
		boolean flag = false;

		if (userData == null) {
			throw new RecordNotFoundException("Given Email ID Does Not Exist !");

		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", userData.getLogin());
		map.put("password", userData.getPassword());
		map.put("firstName", userData.getFirst_Name());
		map.put("lastName", userData.getLast_Name());

		String message = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(login);
		msg.setSubject("SUNARYS ORS Password reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		boolean check = EmailUtility.sendMail(msg);

		flag = check;

		return flag;
	}

}
