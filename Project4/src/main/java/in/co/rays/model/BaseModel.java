
package in.co.rays.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import in.co.rays.bean.DropdownlistBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DataBaseException;
import in.co.rays.util.DataUtility;
import in.co.rays.util.JDBCDataSource;


/**
 * @author Mayank
 *
 */
public abstract class BaseModel implements DropdownlistBean, Serializable, Comparable<BaseModel> {
	
	 /** 
     * Non Business primary key
     */
    protected int id;

    /**
     * User name that creates this record.
     */
    protected String createdBy;

    /**
     * User name that modifies this record.
     */
    protected String modifiedBy;

    /**
     * Created timestamp of record
     */
    protected Timestamp createdDatetime;

    /**
     * Modified timestamp of record
     */
    protected Timestamp modifiedDatetime;

    /**
     * accessor methods
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Timestamp getModifiedDatetime() {
        return modifiedDatetime;
    }

    public void setModifiedDatetime(Timestamp modifiedDatetime) {
        this.modifiedDatetime = modifiedDatetime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Compares IDs ( Primary Key). If keys are equals then objects are equals.
     *
     */
    public int compareTo(BaseModel next) {
        return (int) (id - next.getId());
    }

    /**
     * created next PK of record
     *
     * @throws DatabaseException
     */

    public int nextPK() throws DataBaseException {
      //  log.debug("Model nextPK Started");
        Connection conn = null;
        int pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn
                    .prepareStatement("SELECT MAX(ID) FROM " + getTableName());
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
        //log.debug("Model nextPK End");
        return pk + 1;
    }

    /**
     * Gets table name of Model
     *
     * @return
     */
    public abstract String getTableName();

    /**
     * Updates created by info
     *
     * @throws ApplicationException
     *
     * @throws Exception
     */
    public void updateCreatedInfo() throws ApplicationException {

        //log.debug("Model update Started..." + createdBy);

        Connection conn = null;

        String sql = "UPDATE " + getTableName()
                + " SET CREATED_BY=?, CREATED_DATETIME=? WHERE ID=?";
      //  log.debug(sql);

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, createdBy);
            pstmt.setTimestamp(2, DataUtility.getCurrentTimestamp());
            pstmt.setLong(3, id);

            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (SQLException e) {
           // log.error("Database Exception..", e);
            JDBCDataSource.trnRollback(conn);
            throw new ApplicationException(e.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
       // log.debug("Model update End");
    }

    /**
     * Updates modified by info
     *
     * @param model
     * @throws Exception
     */
    public void updateModifiedInfo() throws Exception {

       // log.debug("Model update Started");
        Connection conn = null;

        String sql = "UPDATE " + getTableName()
                + " SET MODIFIED_BY=?, MODIFIED_DATETIME=? WHERE ID=?";

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, modifiedBy);
            pstmt.setTimestamp(2, DataUtility.getCurrentTimestamp());
            pstmt.setLong(3, id);
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (SQLException e) {
           // log.error("Database Exception..", e);
            JDBCDataSource.trnRollback(conn);
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
       // log.debug("Model update End");
    }

    /**
     * Populate Model from ResultSet
     *
     * @param model
     * @param rs
     * @return
     */
    protected <T extends BaseModel> T populateModel(T model, ResultSet rs)
            throws SQLException {
        model.setId(rs.getInt("ID"));
        model.setCreatedBy(rs.getString("CREATED_BY"));
        model.setModifiedBy(rs.getString("MODIFIED_BY"));
        model.setCreatedDatetime(rs.getTimestamp("CREATED_DATETIME"));
        model.setModifiedDatetime(rs.getTimestamp("MODIFIED_DATETIME"));
        return model;
    }

	public String getkey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getvalue() {
		// TODO Auto-generated method stub
		return null;
	}

}
