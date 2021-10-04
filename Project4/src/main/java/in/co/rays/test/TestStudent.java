package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.StudentBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.StudentModel;

public class TestStudent {

	public static StudentModel model = new StudentModel();

    public static void main(String[] args) throws ParseException {
       // testAdd();
       // testDelete();
       //  testUpdate();
       // testFindByPK();
       //  testFindByEmailId();
       //  testSearch();
        testList();

    }

    /**
     * Tests add a Student
     * 
     * @throws ParseException
     */
    public static void testAdd() throws ParseException {

        try {
           
        	StudentBean bean = new StudentBean();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // bean.setId(1L);
            bean.setFirst_Name("Balram");
            bean.setLast_Name("kumawat");
            bean.setDate_of_Birth(sdf.parse("31/12/1990"));
            bean.setMobile_No("9165254357");
            bean.setEmail("vipie@systems.com");
            bean.setCollege_Id(2L);
            bean.setCreatedBy("Admin");
            bean.setModifiedBy("Admin");
            bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
            bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
            long pk = model.add(bean);
            StudentBean addedbean = model.findByPK(pk);
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
     * Tests delete a Student
     */
    public static void testDelete() {

        try {
            StudentBean bean = new StudentBean();
            long pk = 3L;
            bean.setId(pk);
            model.delete(bean);
            StudentBean deletedbean = model.findByPK(pk);
            if (deletedbean != null) {
                System.out.println("Test Delete fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests update a Student
     */
    public static void testUpdate() {

        try {
            StudentBean bean = model.findByPK(3L);
            bean.setCollege_Id(3L);
            bean.setFirst_Name("Panjkit");
            bean.setLast_Name("sharma");
            model.update(bean);

            StudentBean updatedbean = model.findByPK(3L);
            if (!"Panjkit".equals(updatedbean.getFirst_Name())) {
                System.out.println("Test Update fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests find a Student by PK.
     */
    public static void testFindByPK() {
        try {
            StudentBean bean = new StudentBean();
            long pk = 3L;
            bean = model.findByPK(pk);
            if (bean == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(bean.getId());
            System.out.println(bean.getFirst_Name());
            System.out.println(bean.getLast_Name());
            System.out.println(bean.getDate_of_Birth());
            System.out.println(bean.getMobile_No());
            System.out.println(bean.getEmail());
            System.out.println(bean.getCollege_Id());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tests find a Student by Emailid.
     */
    public static void testFindByEmailId() {
        try {
            StudentBean bean = new StudentBean();
            bean = model.findByEmailId("jloker@gmail.com");
            if (bean != null) {
                System.out.println("Test Find By EmailId fail");
            }
            System.out.println(bean.getId());
            System.out.println(bean.getFirst_Name());
            System.out.println(bean.getLast_Name());
            System.out.println(bean.getDate_of_Birth());
            System.out.println(bean.getMobile_No());
            System.out.println(bean.getEmail());
            System.out.println(bean.getCollege_Id());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests get Search
     */
    public static void testSearch() {

        try {
            StudentBean bean = new StudentBean();
            List list = new ArrayList();
            bean.setFirst_Name("Panjkit");
            list = model.search(bean,1, 1);
            if (list.size() < 0) {
                System.out.println("Test Serach fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (StudentBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getFirst_Name());
                System.out.println(bean.getLast_Name());
                System.out.println(bean.getDate_of_Birth());
                System.out.println(bean.getMobile_No());
                System.out.println(bean.getEmail());
                System.out.println(bean.getCollege_Id());
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tests get List.
     */
    public static void testList() {

        try {
            StudentBean bean = new StudentBean();
            List list = new ArrayList();
            list = model.list(1, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (StudentBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getFirst_Name());
                System.out.println(bean.getLast_Name());
                System.out.println(bean.getDate_of_Birth());
                System.out.println(bean.getMobile_No());
                System.out.println(bean.getEmail());
                System.out.println(bean.getCollege_Id());
                System.out.println(bean.getCreatedBy());
                System.out.println(bean.getCreatedDateTime());
                System.out.println(bean.getModifiedBy());
                System.out.println(bean.getModifiedDateTime());
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

}
