package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

/**
 * @author Mayank
 *
 */
public class SubjectModel {

	/**
	 * Find next PK of Subject.
	 *
	 * 
	 */

	public int nextPk() throws ApplicationException {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(id) FROM ST_SUBJECT");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("database Exception ...", e);
			throw new ApplicationException("Exception in NextPk of subject Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	/**
	 * Add a Subject.
	 *
	 *
	 */

 public int add(SubjectBean bean) throws ApplicationException,
            DuplicateRecordException {
     //   log.debug("Model add Started");
        Connection conn = null;

        // get Course Name
        CourseModel cModel = new CourseModel();
        System.out.println(bean.getCourse_Id());
        CourseBean Coursebean = cModel.findByPk(bean.getCourse_Id());
       
        bean.setCourse_Name(Coursebean.getCourse_Name());
        System.out.println(bean.getCourse_Name());
    
        SubjectBean duplicateName = findByName(bean.getCourse_Name());
       
         if (duplicateName != null) {
             throw new DuplicateRecordException("Subject Name already exists");

         }

         int pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPk();
            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            
            conn.setAutoCommit(false); // Begin transaction
            
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO ST_SUBJECT VALUES(?,?,?,?,?,?,?,?,?)");
            
            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getSubject_Name());
        //    pstmt.setInt(3, bean.getSubject_Id());
            pstmt.setString(3, bean.getCourse_Name());
            pstmt.setLong(4, bean.getCourse_Id());
            pstmt.setString(5, bean.getDescription());   
            pstmt.setString(6, bean.getCreatedBy());
            pstmt.setString(7, bean.getModifiedBy());
            pstmt.setTimestamp(8, bean.getCreatedDateTime());
            pstmt.setTimestamp(9, bean.getModifiedDateTime());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
        	e.printStackTrace();
            //log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in add Subject");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
       // log.debug("Model add End");
        return pk;
    }
	
	/**
	 * Delete a Subject.

	 */

	public void delete(SubjectBean bean) throws ApplicationException {
		//log.debug("Subject Model Delete method Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_SUBJECT WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("database Exception ...", e);

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception in Rollback of Delte Method of Subject Model" + ex.getMessage());
			}
			throw new ApplicationException("Exception in Delte Method of Subject Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		//log.debug("Subject Model Delete method End");
	}

	/**
	 * Update a Subject.
	 *
	 *
	 */

	 public void update(SubjectBean bean) throws ApplicationException,
     DuplicateRecordException {
// log.debug("Model update Started");
 Connection conn = null;

// SubjectBean beanExist = findByName(bean.getCourse_Id());

 // Check if updated id already exist
// if (beanExist != null && beanExist.getId() != bean.getId()) {
//     throw new DuplicateException("Subject Name is already exist");
// }

 // get Course Name
 CourseModel cModel = new CourseModel();
 CourseBean CourseBean = cModel.findByPk(bean.getCourse_Id());
 bean.setCourse_Name(CourseBean.getCourse_Name());

 try {

     conn = JDBCDataSource.getConnection();

     conn.setAutoCommit(false); // Begin transaction
     PreparedStatement pstmt = conn
             .prepareStatement("UPDATE ST_SUBJECT SET Subject_Name=?,Course_NAME=?,Course_ID=?,Description=?,CreatedBy=?,ModifiedBy=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
     pstmt.setString(1, bean.getSubject_Name());
     pstmt.setString(2, bean.getCourse_Name());
     pstmt.setLong(3, bean.getCourse_Id());
     pstmt.setString(4, bean.getDescription()); 
     pstmt.setString(5, bean.getCreatedBy());
     pstmt.setString(6, bean.getModifiedBy());
     pstmt.setTimestamp(7, bean.getCreatedDateTime());
     pstmt.setTimestamp(8, bean.getModifiedDateTime());
     pstmt.setLong(9, bean.getId());
     pstmt.executeUpdate();
     conn.commit(); // End transaction
     pstmt.close();
 } catch (Exception e) {
	 e.printStackTrace();
    // log.error("Database Exception..", e);
     try {
         conn.rollback();
     } catch (Exception ex) {
         throw new ApplicationException(
                 "Exception : Delete rollback exception "
                         + ex.getMessage());
     }
    // throw new ApplicationException("Exception in updating Subject ");
 } finally {
     JDBCDataSource.closeConnection(conn);
 }
// log.debug("Model update End");
}

	/**
	 * Find User by Subject Name.
	 *
	 */

	public SubjectBean findByName(String name) throws ApplicationException {
		//log.debug("Subject Model findByName method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE SUBJECT_NAME=?");
		Connection conn = null;
		SubjectBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				bean = new SubjectBean();
				
				bean.setId(rs.getInt(1));
				bean.setSubject_Name(rs.getString(2));	
				bean.setCourse_Name(rs.getString(3));
				bean.setCourse_Id(rs.getInt(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDateTime(rs.getTimestamp(8));
				bean.setModifiedDateTime(rs.getTimestamp(9));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("database Exception....", e);
			e.printStackTrace();
			//throw new ApplicationException("Exception in findByName Method of Subject Model");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		//log.debug("Subject Model findByName method End");
		return bean;
	}

	/**
	 * Find User by Subject PK.
	 *
	 */
	public SubjectBean findByPk(long pk) throws ApplicationException {
		//log.debug("Subject Model findBypk method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE ID=?");
		Connection conn = null;
		SubjectBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				bean = new SubjectBean();
				
				bean.setId(rs.getInt(1));
				bean.setSubject_Name(rs.getString(2));
				bean.setCourse_Name(rs.getString(3));
				bean.setCourse_Id(rs.getInt(4));
				
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDateTime(rs.getTimestamp(8));
				bean.setModifiedDateTime(rs.getTimestamp(9));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("database Exception....", e);
			throw new ApplicationException("Exception in findByPk Method of Subject Model");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		//log.debug("Subject Model findByPk method End");
		return bean;
	}
	
	
	
	/**
	 * Search Subject.
	 *
	 */
	
	public List search(SubjectBean bean) throws ApplicationException{
		return search(bean,0,0);
	}
	
	/**
	 * Search Subject with pagination.
	 *
	 */


	public List search(SubjectBean bean, int pageNo, int pageSize) throws ApplicationException {
		//log.debug("Subject Model search method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE 1=1 ");
		System.out.println("model search" + bean.getId());
		
		if(bean!= null){
			if(bean.getId() > 0){
				sql.append(" AND id = " + bean.getId()); 
			}
			if(bean.getCourse_Id() > 0){
				sql.append(" AND Course_ID = " + bean.getCourse_Id()); 
			}
		
			if (bean.getSubject_Name() != null && bean.getSubject_Name().length() >0 ) {
				sql.append(" AND Subject_Name like '" +bean.getSubject_Name() + "%'");
			}
			if (bean.getCourse_Name() !=null && bean.getCourse_Name().length() >0 ) {
				sql.append(" AND Course_Name like '" + bean.getCourse_Name() + "%'");
			}
			if (bean.getDescription() !=null && bean.getDescription().length() >0 ) {
				sql.append(" AND description like '" + bean.getDescription() + " % ");
			}
			
			
		}
		
		// Page Size is greater then Zero then aplly pagination 
		if(pageSize>0){
			pageNo = (pageNo-1)*pageSize;
			sql.append(" limit "+pageNo+ " , " + pageSize);
		}
		System.out.println("sql is"+sql);
		Connection conn = null;
		ArrayList list = new ArrayList();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
		
			while(rs.next()){
				bean = new SubjectBean();
			
				bean.setId(rs.getInt(1));
				bean.setSubject_Name(rs.getString(2));
				bean.setCourse_Id(rs.getInt(4));
				bean.setCourse_Name(rs.getString(3));
				bean.setDescription(rs.getString(5));	
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDateTime(rs.getTimestamp(8));
				bean.setModifiedDateTime(rs.getTimestamp(9));
				list.add(bean);
			}
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		//	log.error("database Exception....", e);
			throw new ApplicationException("Exception in search Method of Subject Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		//log.debug("Subject Model search method End");		
		return list;
	}	
	
	/**
	 * Get List of Subject.
	 *
	 */
	public List list() throws ApplicationException{
		return list(0,0);
	}

	/**
	 * Get List of Subject with pagination.
	 *
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		//log.debug("Subject Model list method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT ");
		
		// Page Size is greater then Zero then aplly pagination 
		if (pageSize>0) {
			pageNo = (pageNo-1)*pageSize;
			sql.append(" limit "+ pageNo+ " , " + pageSize);
		}
		
		Connection conn = null;
		ArrayList list = new ArrayList();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				SubjectBean bean = new SubjectBean();
				
				bean.setId(rs.getInt(1));
				bean.setSubject_Name(rs.getString(2));
                bean.setCourse_Name(rs.getString(3));
				bean.setCourse_Id(rs.getInt(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDateTime(rs.getTimestamp(8));
				bean.setModifiedDateTime(rs.getTimestamp(9));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("database Exception....", e);
		//	throw new ApplicationException("Exception in list Method of Subject Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		//log.debug("Subject Model list method End");		
		return list;
	}

}
