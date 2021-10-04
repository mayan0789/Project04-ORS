package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.FacultyBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.FacultyModel;

public class TestFaculty {

	public static FacultyModel model = new FacultyModel();

	public static void main(String[] args) throws ParseException, RecordNotFoundException, DuplicateRecordException, ApplicationException {

		// testAdd();
		// testDelete();
		//testUpdate();
		// testFindByPK();
		 //testFindByEmail_Id();
		//testSearch();
		 testList();

	}

	/**
	 * Tests add a Role
	 * 
	 * @throws ParseException
	 * @throws RecordNotFoundException
	 */
	public static void testAdd() throws ParseException, RecordNotFoundException {

		try {
			FacultyBean bean = new FacultyBean();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			// bean.setId(1L);
			bean.setFirst_Name("Sham");
			bean.setLast_Name("Japan");
			bean.setGender("male");
			bean.setDOJ(sdf.parse("22-02-1993"));
			bean.setQualification("grad");
			bean.setEmail_id("Demo@yahoo.com");
			bean.setMobile_No("5665445");
			bean.setCourse_id(01);
			bean.setCourse_Name("Mcom");
			bean.setCollege_id(02);
			bean.setCollege_Name("Naples");
			bean.setSubject_id(03);
			bean.setSubject_Name("VCR");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
			bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			FacultyBean addedbean = model.findByPk(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests delete a Role
	 */
	public static void testDelete() {

		try {
			FacultyBean bean = new FacultyBean();
			long pk = 2L;
			bean.setId(pk);
			model.delete(bean);
			FacultyBean deletedbean = model.findByPk(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Role
	 * 
	 * @throws DuplicateRecordException
	 */
	public static void testUpdate() throws DuplicateRecordException {

		try {
			FacultyBean bean = model.findByPk(2L);
			bean.setCourse_Name("Mphil");
			bean.setSubject_Name("Humanity");
			model.update(bean);

			FacultyBean updatedbean = model.findByPk(2L);
			if (!"Mphil".equals(updatedbean.getCourse_Name())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests find a User by PK.
	 */
	public static void testFindByPK() {
		try {
			FacultyBean bean = new FacultyBean();
			long pk = 2L;
			bean = model.findByPk(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirst_Name());
			System.out.println(bean.getLast_Name());
			System.out.println(bean.getCourse_Name());
			System.out.println(bean.getSubject_Name());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by Course_Name.
	 */
	public static void testFindByEmail_Id() throws ApplicationException {
		try {
			FacultyBean bean = new FacultyBean();
			bean = model.findByEmail("De@yahoo.com");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirst_Name());
			System.out.println(bean.getLast_Name());
			System.out.println(bean.getCourse_Name());
			System.out.println(bean.getSubject_Name());
		} catch (Exception e) {
			e.getMessage();
		}

	}

	/**
	 * Tests get Search
	 */
	public static void testSearch() {

		try {
			FacultyBean bean = new FacultyBean();
			List list = new ArrayList();
			bean.setCourse_Name("Mphil");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (FacultyBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirst_Name());
				System.out.println(bean.getLast_Name());
							System.out.println(bean.getCourse_Name());
				System.out.println(bean.getSubject_Name());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests get List.
	 */
	public static void testList() {

		try {
			FacultyBean bean = new FacultyBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (FacultyBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirst_Name());
				System.out.println(bean.getLast_Name());
			
				System.out.println(bean.getCourse_Name());
				System.out.println(bean.getSubject_Name());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
