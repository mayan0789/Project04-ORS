package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.TimetableBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.TimetableModel;

public class TestTimeTable {

	public static TimetableModel model = new TimetableModel();
	
	public static void main(String[] args) throws ParseException, RecordNotFoundException, DuplicateRecordException, ApplicationException {
		
		 testAdd();
	//	 testDelete();
				//testUpdate();
				// testFindByPK();
				 //testfindBySubName();
				//testSearch();
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
					TimetableBean bean = new TimetableBean();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

					// bean.setId(1L);
					
					
			//		bean.setCourse_Name("Mcom");
					bean.setSubject_Id(3);
					bean.setCourse_Id(2);
		//			bean.setSubject_Name("VCR");
					bean.setExam_Date(sdf.parse("03-06-2021"));
					bean.setExam_Time("4:00");
					bean.setSemester("2");
					bean.setCreatedBy("Admin");
					bean.setModifiedBy("Admin");
					bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
					bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
					long pk = model.add(bean);
					TimetableBean addedbean = model.findByPk(pk);
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
					TimetableBean bean = new TimetableBean();
					long pk = 4L;
					bean.setId(pk);
					model.delete(bean);
					TimetableBean deletedbean = model.findByPk(pk);
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
					TimetableBean bean = model.findByPk(2L);
					bean.setCourse_Name("MBa");
					bean.setSubject_Name("Advertise");
					model.update(bean);

					TimetableBean updatedbean = model.findByPk(2L);
					if (!"MBa".equals(updatedbean.getCourse_Name())) {
						System.out.println("Test Update Fa");
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
					TimetableBean bean = new TimetableBean();
					long pk = 2L;
					bean = model.findByPk(pk);
					if (bean == null) {
						System.out.println("Test Find By PK fail");
					}
					System.out.println(bean.getId());
					System.out.println(bean.getSubject_Name());
					System.out.println(bean.getCourse_Name());
					System.out.println(bean.getCourse_Name());
					System.out.println(bean.getSubject_Name());
				} catch (ApplicationException e) {
					e.printStackTrace();
				}

			}

			/**
			 * Tests find a User by Course_Name.
			 */
			public static void testfindBySubName() throws ApplicationException {
				try {
					TimetableBean bean = new TimetableBean();
					bean = model.findBySubName("VCR");
					if (bean == null) {
						System.out.println("Test Find By PK fail");
					}
					System.out.println(bean.getId());
					System.out.println(bean.getSubject_Name());
					System.out.println(bean.getCourse_Name());
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
					TimetableBean bean = new TimetableBean();
					List list = new ArrayList();
					bean.setCourse_Name("Mphil");
					list = model.search(bean, 0, 0);
					if (list.size() < 0) {
						System.out.println("Test Serach fail");
					}
					Iterator it = list.iterator();
					while (it.hasNext()) {
						bean = (TimetableBean) it.next();
						System.out.println(bean.getId());
						System.out.println(bean.getSemester());
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
					TimetableBean bean = new TimetableBean();
					List list = new ArrayList();
					list = model.list(1, 10);
					if (list.size() < 0) {
						System.out.println("Test list fail");
					}
					Iterator it = list.iterator();
					while (it.hasNext()) {
						bean = (TimetableBean) it.next();
						System.out.println(bean.getId());
						System.out.println(bean.getSubject_Name());
						System.out.println(bean.getCourse_Name());
					
						System.out.println(bean.getCourse_Name());
						System.out.println(bean.getSubject_Name());
					}

				} catch (ApplicationException e) {
					e.printStackTrace();
				}
			}
	
}
