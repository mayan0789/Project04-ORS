package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.bean.TimetableBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

/**
 * @author Mayank
 *
 */
public class TimetableModel {

//	private String CourseName;
//	private String SubjcetName;

	/**
	 * Find next PK of TIMETABLE.
	 *
	 */

	public int nextPk() throws ApplicationException {
		// log.debug("Timetable model nextPk method Started ");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(id) FROM ST_TIMETABLE");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("database Exception ...", e);
			throw new ApplicationException("Exception in NextPk of TIMETABLE Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("TimeTable model nextpk method end");
		return pk + 1;
	}

	/**
	 * Add a TIMETABLE.
	 *
	 */

	public int add(TimetableBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("TimeTable model Add method End");
		Connection conn = null;
		int pk = 0;

		CourseModel coumodel = new CourseModel();
		CourseBean coubean = coumodel.findByPk(bean.getCourse_Id());
		String courseName = coubean.getCourse_Name();

		SubjectModel smodel = new SubjectModel();
		SubjectBean sbean = smodel.findByPk(bean.getSubject_Id());
		String subjectName = sbean.getSubject_Name();

  //  System.out.println("innnnnnnnnnnnnnnnnn adddddddddd method .................>>>>>>");
  //	System.out.println("______________________________>>>>>"+bean.getCourseId());
  //	System.out.println("______________________________>>>>>"+bean.getSemester());
  //	System.out.println("______________________________>>>>>"+bean.getExamDate());
		//TimetableModel model = new TimetableModel();

		TimetableBean bean11 = checkBycds(bean.getCourse_Id(), bean.getSemester(),
				new java.sql.Date(bean.getExam_Date().getTime()));
		TimetableBean bean12 = checkBycss(bean.getCourse_Id(), bean.getSubject_Id(), bean.getSemester());
		if (bean11 != null || bean12 != null) {
			throw new DuplicateRecordException("TimeTable Already Exsist");

		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_TIMETABLE VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, courseName);
			pstmt.setLong(3, bean.getCourse_Id());
			pstmt.setString(4, subjectName);
			pstmt.setLong(5, bean.getSubject_Id());
			pstmt.setDate(6, new java.sql.Date(bean.getExam_Date().getTime()));
			pstmt.setString(7, bean.getExam_Time());
			pstmt.setString(8, bean.getSemester());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDateTime());
			pstmt.setTimestamp(12, bean.getModifiedDateTime());
			pstmt.executeUpdate();

			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("database Exception ...", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception in the Rollback of TIMETABLE Model" + ex.getMessage());
			}
			throw new ApplicationException("Exception in Add method of TIMETABLE Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("TimeTable model Add method End");
		return pk;

	}

	/**
	 * Delete a TimeTable.
	 *
	 */

	public void delete(TimetableBean bean) throws ApplicationException {
		// log.debug("TIMETABLE Model Delete method Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_TIMETABLE WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("database Exception ...", e);

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception in Rollback of Delte Method of TIMETABLE Model" + ex.getMessage());
			}
			throw new ApplicationException("Exception in Delte Method of TIMETABLE Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("TIMETABLE Model Delete method End");
	}

	/**
	 * Update a TIMETABLE.
	 *
	 */

	public void update(TimetableBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("TIMETABLE Model update method Started");
		Connection conn = null;
		CourseModel coumodel = new CourseModel();
		CourseBean coubean = coumodel.findByPk(bean.getCourse_Id());
		String courseName = coubean.getCourse_Name();

		SubjectModel smodel = new SubjectModel();
		SubjectBean sbean = smodel.findByPk(bean.getSubject_Id());
		String subjectName = sbean.getSubject_Name();

		TimetableBean bean1 = checkBycds(bean.getCourse_Id(), bean.getSemester(),  new java.sql.Date(bean.getExam_Date().getTime()));
		TimetableBean bean2 = checkBycss(bean.getCourse_Id(), bean.getSubject_Id(), bean.getSemester());
		if (bean1 != null  || bean2 != null ){ 
			 throw new DuplicateRecordException("TimeTable Already Exsist"); 
		 }

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_TIMETABLE SET COURSE_NAME=?,COURSE_ID=?,SUBJECT_NAME=?,SUBJECT_ID=?,EXAM_DATE=?,EXAM_TIME=?,SEMESTER=?,CreatedBy=?,ModifiedBy=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, courseName);
			pstmt.setLong(2, bean.getCourse_Id());
			pstmt.setString(3, subjectName);
			pstmt.setLong(4, bean.getSubject_Id());
			pstmt.setDate(5, new java.sql.Date(bean.getExam_Date().getTime()));
			pstmt.setString(6, bean.getExam_Time());
			pstmt.setString(7, bean.getSemester());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDateTime());
			pstmt.setTimestamp(11, bean.getModifiedDateTime());
			pstmt.setLong(12, bean.getId());

			pstmt.executeUpdate();

			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			// log.error("database Exception....", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception in Rollback of Update Method of TimeTable Model" + ex.getMessage());
			}
			throw new ApplicationException("Exception in update Method of TimeTable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("TimeTable Model Update method End");
	}

	/**
	 * Find User by TimeTable Name.
	 *
	 */

	public TimetableBean findBySubName(String name) throws ApplicationException {
		// log.debug("TimeTable Model findByName method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE Subject_Name = ?");
		Connection conn = null;
		TimetableBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimetableBean();

				bean.setId(rs.getInt(1));
				bean.setCourse_Name(rs.getString(2));
				bean.setCourse_Id(rs.getInt(3));
				bean.setSubject_Name(rs.getString(4));
				bean.setSubject_Id(rs.getInt(5));
				bean.setExam_Date(rs.getDate(6));
				bean.setExam_Time(rs.getString(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDateTime(rs.getTimestamp(11));
				bean.setModifiedDateTime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("database Exception....", e);
			throw new ApplicationException("Exception in findByName Method of TimeTable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("TimeTable Model findByName method End");
		return bean;
	}

	/**
	 * Find User by TimeTable PK.
	 *
	 */
	public TimetableBean findByPk(long pk) throws ApplicationException {
		// log.debug("TimeTable Model findBypk method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE ID=?");
		Connection conn = null;
		TimetableBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimetableBean();

				bean.setId(rs.getInt(1));
				bean.setCourse_Name(rs.getString(2));
				bean.setCourse_Id(rs.getInt(3));
				bean.setSubject_Name(rs.getString(4));
				bean.setSubject_Id(rs.getInt(5));
				bean.setExam_Date(rs.getDate(6));
				bean.setExam_Time(rs.getString(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDateTime(rs.getTimestamp(11));
				bean.setModifiedDateTime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("database Exception....", e);
			throw new ApplicationException("Exception in findByPk Method of TimeTable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		// log.debug("TimeTable Model findByPk method End");
		return bean;
	}

	/**
	 * Search TimeTable.
	 *
	 */

	public List search(TimetableBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search TimeTable with pagination.
	 *
	 */

	public List search(TimetableBean bean, int pageNo, int pageSize) throws ApplicationException {
		// log.debug("TimeTable Model search method Started");

		Connection conn = null;
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}

			if (bean.getCourse_Id() > 0) {
				sql.append(" AND Course_ID = " + bean.getCourse_Id());
			}
			if (bean.getSubject_Id() > 0) {
				sql.append(" AND Subject_ID = " + bean.getSubject_Id());
			}
			if (bean.getExam_Date() != null && bean.getExam_Date().getTime() > 0) {

			//	Date d = new Date(bean.getExam_Date().getTime());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				sdf.format(bean.getExam_Date());
				sql.append(" AND Exam_Date = '" + sdf.format(bean.getExam_Date()) + "'");
			}

			if (bean.getCourse_Name() != null && bean.getCourse_Name().length() > 0) {
				sql.append(" AND Course_Name like '" + bean.getCourse_Name() + "%'");
			}

			
			if (bean.getSubject_Name() != null && bean.getSubject_Name().length() > 0) {
				sql.append(" AND Subject_Name like '" + bean.getSubject_Name() + "%'");
			}

		}

		// Page Size is greater then Zero then apply pagination
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}

		System.out.println("Time table model search  " + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimetableBean();

				bean.setId(rs.getInt(1));
				bean.setCourse_Name(rs.getString(2));
				bean.setCourse_Id(rs.getInt(3));
				bean.setSubject_Name(rs.getString(4));
				bean.setSubject_Id(rs.getInt(5));
				bean.setExam_Date(rs.getDate(6));
				bean.setExam_Time(rs.getString(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDateTime(rs.getTimestamp(11));
				bean.setModifiedDateTime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("database Exception....", e);
			throw new ApplicationException("Exception in search Method of TimeTable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("TimeTable Model search method End");
		return list;
	}

	/**
	 * Get List of TimeTable.
	 *
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of TimeTable with pagination.
	 *
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		// log.debug("TimeTable Model list method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE ");

		// Page Size is greater then Zero then aplly pagination
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + " , " + pageSize);
		}

		System.out.println("Time Table Model List Sql  -" + sql);
		Connection conn = null;
		ArrayList list = new ArrayList();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				TimetableBean bean = new TimetableBean();

				bean.setId(rs.getInt(1));
				bean.setCourse_Name(rs.getString(2));
				bean.setCourse_Id(rs.getInt(3));
				bean.setSubject_Name(rs.getString(4));
				bean.setSubject_Id(rs.getInt(5));
				bean.setExam_Date(rs.getDate(6));
				bean.setExam_Time(rs.getString(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDateTime(rs.getTimestamp(11));
				bean.setModifiedDateTime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("database Exception....", e);
			throw new ApplicationException("Exception in list Method of timetable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Timetable Model list method End");
		return list;
	}

	/**
	 * Check bycss.
	 *
	 */
	public TimetableBean checkBycss(long l, long m, String sem) throws ApplicationException {
		System.out.println("Time Table Model in from css...................<<<<<<<<<<<>>>> ");
		Connection conn = null;
		TimetableBean bean = null;
		// java.util.Date ExamDAte,String ExamTime
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_TIMETABLE WHERE Course_ID=? AND Subject_ID=? AND Semester=?");

		try {
			Connection con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setLong(1, l);
			ps.setLong(2, m);
			ps.setString(3, sem);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getInt(1));
				bean.setCourse_Name(rs.getString(2));
				bean.setCourse_Id(rs.getInt(3));
				bean.setSubject_Name(rs.getString(4));
				bean.setSubject_Id(rs.getInt(5));
				bean.setExam_Date(rs.getDate(6));
				bean.setExam_Time(rs.getString(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDateTime(rs.getTimestamp(11));
				bean.setModifiedDateTime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("database Exception....", e);
			throw new ApplicationException("Exception in list Method of timetable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Timetable Model list method End");
		return bean;
	}

	/**
	 * Check bycds.
	 *
	 */
	public TimetableBean checkBycds(long l, String semester, Date ExamDate) throws ApplicationException {
		System.out.println(" Time Table Model in from cds.........................<<<<<<<<<<<>>>> ");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_TIMETABLE WHERE Course_ID=? AND Semester=? AND Exam_Date=?");

		Connection conn = null;
		TimetableBean bean = null;
//    	Date ExDate = new Date(ExamDAte.getTime());

		try {
			Connection con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setLong(1, l);
			ps.setString(2, semester);
			ps.setDate(3, (java.sql.Date) ExamDate);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getInt(1));
				bean.setCourse_Name(rs.getString(2));
				bean.setCourse_Id(rs.getInt(3));
				bean.setSubject_Name(rs.getString(4));
				bean.setSubject_Id(rs.getInt(5));
				bean.setExam_Date(rs.getDate(6));
				bean.setExam_Time(rs.getString(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDateTime(rs.getTimestamp(11));
				bean.setModifiedDateTime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("database Exception....", e);
			throw new ApplicationException("Exception in list Method of timetable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Timetable Model list method End");
		System.out.println("out from cds.........................<<<<<<<<<<<>>>> ");
		return bean;
	}

	/**
	 * Check bysemester.
	 *
	 */
	public static TimetableBean checkBysemester(long CourseId, long SubjectId, String semester,
			java.util.Date ExamDAte) {

		TimetableBean bean = null;

		Date ExDate = new Date(ExamDAte.getTime());

		StringBuffer sql = new StringBuffer(
				"SELECT * FROM TIMETABLE WHERE COURSE_ID=? AND SUBJECT_ID=? AND" + " SEMESTER=? AND EXAM_DATE=?");

		try {
			Connection con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setLong(1, CourseId);
			ps.setLong(2, SubjectId);
			ps.setString(3, semester);
			// ps.setDate(4, ExDate);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getInt(1));
				bean.setCourse_Name(rs.getString(2));
				bean.setCourse_Id(rs.getInt(3));
				bean.setSubject_Name(rs.getString(4));
				bean.setSubject_Id(rs.getInt(5));
				bean.setExam_Date(rs.getDate(6));
				bean.setExam_Time(rs.getString(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDateTime(rs.getTimestamp(11));
				bean.setModifiedDateTime(rs.getTimestamp(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * Check by course name.
	 *
	 */
	public static TimetableBean checkByCourseName(long CourseId, java.util.Date ExamDate) {
		TimetableBean bean = null;

		Date Exdate = new Date(ExamDate.getTime());

		StringBuffer sql = new StringBuffer("SELECT * FROM TIMETABLE WHERE COURSE_ID=? " + "AND EXAM_DATE=?");

		try {
			Connection con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
//			ps.setInt(1, CourseId);
//			ps.setDate(2, Exdate);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getInt(1));
				bean.setCourse_Id(rs.getInt(2));
				bean.setCourse_Name(rs.getString(3));
				bean.setSubject_Id(rs.getInt(4));
				bean.setSubject_Name(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExam_Date(rs.getDate(7));
				bean.setExam_Time(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDateTime(rs.getTimestamp(11));
				bean.setModifiedDateTime(rs.getTimestamp(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

}
