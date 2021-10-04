package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.SubjectModel;

public class TestSubject {

	public static SubjectModel model = new SubjectModel();

	public static void main(String[] args) throws ParseException, RecordNotFoundException, DuplicateRecordException, ApplicationException {

		 testAdd();
		// testDelete();
		// testUpdate();
		//testFindByPK();
		// testFindByCourse_Name();
		// testSearch();
		// testList();

	}

	/**
	 * Tests add a Role
	 * 
	 * @throws ParseException
	 * @throws RecordNotFoundException
	 */
	public static void testAdd() throws ParseException, RecordNotFoundException {

		try {
			SubjectBean bean = new SubjectBean();
			// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			// bean.setId(1L);
			bean.setSubject_Name("chems");
			// bean.setCourse_Name("vibam");
			bean.setCourse_Id(3);
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
			bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			SubjectBean addedbean = model.findByPk(pk);
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
			SubjectBean bean = new SubjectBean();
			long pk = 4L;
			bean.setId(pk);
			model.delete(bean);
			SubjectBean deletedbean = model.findByPk(pk);
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
			SubjectBean bean = model.findByPk(3L);
			bean.setCourse_Name("Manaer");
			bean.setCourse_Id(3);
			bean.setSubject_Name("math");
			model.update(bean);

			SubjectBean updatedbean = model.findByPk(3L);
			if (!"Manaer".equals(updatedbean.getCourse_Name())) {
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
			SubjectBean bean = new SubjectBean();
			long pk = 2L;
			bean = model.findByPk(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getCourse_Name());
			System.out.println(bean.getCourse_Id());
			System.out.println(bean.getSubject_Name());
			
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by Course_Name.
	 */
	public static void testFindByCourse_Name() throws ApplicationException {
		try {
			SubjectBean bean = new SubjectBean();
			bean = model.findByName("Chems");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getCourse_Name());
			System.out.println(bean.getSubject_Name());
			System.out.println(bean.getCourse_Id());
		} catch (Exception e) {
			e.getMessage();
		}

	}

	/**
	 * Tests get Search
	 */
	public static void testSearch() {

		try {
			SubjectBean bean = new SubjectBean();
			List list = new ArrayList();
			bean.setCourse_Name("vibam");
			list = model.search(bean, 1, 1);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (SubjectBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getCourse_Name());
				System.out.println(bean.getSubject_Name());
				System.out.println(bean.getDescription());
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
			SubjectBean bean = new SubjectBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (SubjectBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getSubject_Name());
				System.out.println(bean.getCourse_Name());
				System.out.println(bean.getDescription());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
