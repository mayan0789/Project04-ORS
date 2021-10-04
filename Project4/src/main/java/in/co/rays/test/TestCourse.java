package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.CourseModel;

public class TestCourse {

	public static CourseModel model = new CourseModel();

	public static void main(String[] args) throws ParseException, RecordNotFoundException, DuplicateRecordException, ApplicationException {

		// testAdd();
		// testDelete();
//		testUpdate();
 testFindByPK();
//testFindByCourse_Name();
//  testSearch();
//	 testList();

	}

	/**
	 * Tests add a Role
	 * 
	 * @throws ParseException
	 * @throws RecordNotFoundException
	 */
	public static void testAdd() throws ParseException, RecordNotFoundException {

		try {
			CourseBean bean = new CourseBean();
			// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			// bean.setId(1L);
			bean.setCourse_Name("vam");
			bean.setDescription("kumar");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
			bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
			bean.setDuration("3 yrs");
			long pk = model.add(bean);
			CourseBean addedbean = model.findByPk(pk);
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
			CourseBean bean = new CourseBean();
			long pk = 4L;
			bean.setId(pk);
			model.delete(bean);
			CourseBean deletedbean = model.findByPk(pk);
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
			CourseBean bean = model.findByPk(3L);
			bean.setCourse_Name("MSC");
			bean.setDescription("Scien");
			bean.setDuration("3 yrs");
			model.update(bean);

			CourseBean updatedbean = model.findByPk(3L);
			if (!"MSC".equals(updatedbean.getCourse_Name())) {
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
			CourseBean bean = new CourseBean();
			long pk = 3L;
			bean = model.findByPk(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getCourse_Name());
			System.out.println(bean.getDescription());
			System.out.println(bean.getDuration());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by Course_Name.
	 */
	public static void testFindByCourse_Name() throws ApplicationException {
		try {
			CourseBean bean = new CourseBean();
			bean = model.findByName("MSC");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getCourse_Name());
			System.out.println(bean.getDuration());
			System.out.println(bean.getDescription());
		} catch (Exception e) {
			e.getMessage();
		}

	}

	/**
	 * Tests get Search
	 */
	public static void testSearch() {

		try {
			CourseBean bean = new CourseBean();
			List list = new ArrayList();
			bean.setCourse_Name("MBA");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (CourseBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getCourse_Name());
				System.out.println(bean.getDescription());
				System.out.println(bean.getDuration());
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
			CourseBean bean = new CourseBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (CourseBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getCourse_Name());
				System.out.println(bean.getDescription());
				System.out.println(bean.getDuration());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
