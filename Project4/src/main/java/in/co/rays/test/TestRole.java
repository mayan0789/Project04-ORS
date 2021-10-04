package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.RoleModel;

public class TestRole {

	public static RoleModel model = new RoleModel();
	
	public static void main(String[] args) throws ParseException, RecordNotFoundException, ApplicationException {
        // testAdd();
        // testDelete();
        // testUpdate();
       testFindByPK();
        // testFindByName();
       //  testSearch();
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
            RoleBean bean = new RoleBean();
            // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            // bean.setId(1L);
            bean.setName("shivam");
            bean.setDescription("kumar");
            bean.setCreatedBy("Admin");
            bean.setModifiedBy("Admin");
			bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
			bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
            long pk = model.add(bean);
            RoleBean addedbean = model.findByPK(pk);
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
            RoleBean bean = new RoleBean();
            long pk = 5L;
            bean.setId(pk);
            model.delete(bean);
            RoleBean deletedbean = model.findByPK(pk);
            if (deletedbean != null) {
                System.out.println("Test Delete fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests update a Role
     */
    public static void testUpdate() {

        try {
            RoleBean bean = model.findByPK(4L);
            bean.setName("Manaer");
            bean.setDescription("Dam");
            model.update(bean);

            RoleBean updatedbean = model.findByPK(4L);
            if (!"Manaer".equals(updatedbean.getName())) {
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
            RoleBean bean = new RoleBean();
            long pk = 1L;
            bean = model.findByPK(pk);
            if (bean == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(bean.getId());
            System.out.println(bean.getName());
            System.out.println(bean.getDescription());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tests find a User by Name.
     */
    public static void testFindByName() throws ApplicationException {
      try {
    	RoleBean bean = new RoleBean();
		bean = model.findByName("Relid");
		if (bean == null) {
		    System.out.println("Test Find By PK fail");
		}
		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
    }catch(Exception e) {
    	e.getMessage();
    }
      
    }

    /**
     * Tests get Search
     */
    public static void testSearch() {

        try {
            RoleBean bean = new RoleBean();
            List list = new ArrayList();
            bean.setName("CEO");
            list = model.search(bean, 0, 0);
            if (list.size() < 0) {
                System.out.println("Test Serach fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (RoleBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getName());
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
            RoleBean bean = new RoleBean();
            List list = new ArrayList();
            list = model.list(1, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (RoleBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getName());
                System.out.println(bean.getDescription());
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}


