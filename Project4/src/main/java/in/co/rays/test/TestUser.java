package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.UserModel;

public class TestUser {

	public static UserModel model = new UserModel();

	public static void main(String[] args) throws Exception {
		//testAdd();
		// testDelete();
		// testUpdate();
		// testFindByPK();
	// testFindByLogin();
	//	 testSearch();
		// testGetRoles();
		// testList();
		// testAuthenticate();
		// testRegisterUser();
		 //testchangePassword();
		 testforgetPassword();
		// testresetPassword();

	}

	/**
	 * Tests add a User
	 * 
	 */
	public static void testAdd() throws ParseException, DuplicateRecordException {

		try {
			UserBean bean = new UserBean();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");

			// bean.setId(5234L);
			bean.setFirst_Name("cjhsghbdswv");
			bean.setLast_Name("kumevbebawat");
			bean.setLogin("abhi12@g.com");
			bean.setPassword("pass1234");
			bean.setDOB(sdf.parse("31-12-1990"));
			bean.setMobile_No("44545456");
			bean.setRoll_Id(1L);
			bean.setGender("Male");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
			bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			UserBean addedbean = model.findByPK(pk);
			System.out.println("Test add succ");
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests delete a User
	 */
	public static void testDelete() {

		try {
			UserBean bean = new UserBean();
			long pk = 3L;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("Test Delete succ" + bean.getId());
			UserBean deletedbean = model.findByPK(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a User
	 */
	public static void testUpdate() throws ParseException {

		try {
			SimpleDateFormat dsk = new SimpleDateFormat("dd/MM/yyyy");
			
			UserBean bean = model.findByPK(2L);
			bean.setFirst_Name("cmc limited");
			bean.setLast_Name("pvt ltd");
			bean.setLogin("ranj@gmail.com");
			bean.setDOB(dsk.parse("02/02/1994"));
			bean.setPassword("658986");

			model.update(bean);

			UserBean updatedbean = model.findByPK(1L);
			/*
			 * if (!"ranjit".equals(updatedbean.getLogin())) {
			 * System.out.println("Test Update fail"); }
			 */
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests find a User by PK.
	 */
	public static void testFindByPK() {
		try {
			UserBean bean = new UserBean();
			long pk = 1L;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirst_Name());
			System.out.println(bean.getLast_Name());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDOB());
			System.out.println(bean.getRoll_Id());
			System.out.println(bean.getUnSuccessfulLogin());
			System.out.println(bean.getGender());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by Login.
	 */
	public static void testFindByLogin() {
		try {
			UserBean bean = new UserBean();
			bean = model.findByLogin("ranj@gmail.com");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirst_Name());
			System.out.println(bean.getLast_Name());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDOB());
			System.out.println(bean.getRoll_Id());
			System.out.println(bean.getUnSuccessfulLogin());
			System.out.println(bean.getGender());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get Roles.
	 */
	public static void testGetRoles() {

		try {
			UserBean bean = new UserBean();
			List list = new ArrayList();
			bean.setRoll_Id(1L);
			list = model.getRoles(bean);
			if (list.size() < 0) {
				System.out.println("Test Get Roles fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirst_Name());
				System.out.println(bean.getLast_Name());
				System.out.println(bean.getLogin());
				System.out.println(bean.getPassword());
				System.out.println(bean.getDOB());
				System.out.println(bean.getRoll_Id());
				System.out.println(bean.getGender());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get Search
	 */
	public static void testSearch() {

		try {
			UserBean bean = new UserBean();
			List list = new ArrayList();
			bean.setFirst_Name("Suman");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirst_Name());
				System.out.println(bean.getLast_Name());
				System.out.println(bean.getLogin());
				System.out.println(bean.getPassword());
				System.out.println(bean.getDOB());
				System.out.println(bean.getRoll_Id());
				System.out.println(bean.getGender());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests get List.
	 */
	public static void testList() {
System.out.println("Stage1 ");
		try {
			UserBean bean = new UserBean();
			List list = new ArrayList();
			list = model.list(1,10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				System.out.println("Stage2 ");
				bean = (UserBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirst_Name());
				System.out.println(bean.getLast_Name());
				System.out.println(bean.getLogin());
				System.out.println(bean.getPassword());
				System.out.println(bean.getDOB());
				System.out.println(bean.getRoll_Id());
				System.out.println(bean.getUnSuccessfulLogin());
				System.out.println(bean.getGender());
				System.out.println(bean.getLogin());
				System.out.println(bean.getMobile_No());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDateTime());
				System.out.println(bean.getModifiedDateTime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests authenticate User.
	 * 
	 */
	public static void testAuthenticate() throws Exception {

		try {
			UserBean bean = new UserBean();
			bean.setLogin("poly@yahoo.com");
			bean.setPassword("gon");
			bean = model.authenticate(bean.getLogin(), bean.getPassword());
			if (bean != null) {
				System.out.println("Successfully login");

			} else {
				System.out.println("Invalid login Id or password or both");
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests add a User register
	 * 
	 */

	public static void testRegisterUser() throws ParseException {
		try {
			UserBean bean = new UserBean();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			// bean.setId(8L);
			bean.setFirst_Name("vipin");
			// bean.setLast_Name("kumawat");
			bean.setLogin("ranjitdher@gmail.com");
			bean.setPassword("rr");
			bean.setPassword("4444");
			bean.setDOB(sdf.parse("11/20/2015"));
			bean.setGender("Male");
			bean.setRoll_Id(2);
			long pk = model.registerUser(bean);
			System.out.println("Successfully register");
			System.out.println(bean.getFirst_Name());
			System.out.println(bean.getLogin());
			System.out.println(bean.getLast_Name());
			System.out.println(bean.getDOB());
			UserBean registerbean = model.findByPK(pk);
			if (registerbean != null) {
				System.out.println("Test registation fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests changepassword
	 * 
	 */
	public static void testchangePassword() {

		try {
			UserBean bean = model.findByLogin("ranj@gmail.com");
			String oldPassword = bean.getPassword();
			bean.setId(2L);
			bean.setPassword("888");
			String newPassword = bean.getPassword();
			try {
				model.changePassword(2L, oldPassword, newPassword);
				System.out.println("password has been change successfully");
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests fogetPassword
	 * 
	 */
	public static void testforgetPassword() {
		try {
			boolean b = model.forgetPassword("ranj@gmail.com");

			System.out.println("Suucess : Test Forget Password Success");

		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests resetPassword
	 * 
	 */
	public static void testresetPassword() {
		UserBean bean = new UserBean();
		try {
			bean = model.findByLogin("ranjitchoudhary20@gmail.com");
			if (bean != null) {
				boolean pass = model.resetPassword(bean);
				if (pass = false) {
					System.out.println("Test Update fail");
				}
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}
}
