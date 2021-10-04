package in.co.rays.util;

import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import in.co.rays.exception.ApplicationException;

import java.sql.Connection;

public class JDBCDataSource {

	private JDBCDataSource() {
    }

    /**
     * JDBC Database connection pool ( DCP )
     */
    private static JDBCDataSource datasource;


    private ComboPooledDataSource cpds = null;

    /**
     * Create instance of Connection Pool
     * 
     * @return
     */
    public static JDBCDataSource getInstance() {
        if (datasource == null) {

            ResourceBundle rb = ResourceBundle
                    .getBundle("in.co.rays.bundle.system");

            datasource = new JDBCDataSource();
            datasource.cpds = new ComboPooledDataSource();
            try {
                datasource.cpds.setDriverClass(rb.getString("driver"));
                datasource.cpds.setJdbcUrl(rb.getString("url"));
                datasource.cpds.setUser(rb.getString("username"));
                datasource.cpds.setPassword(rb.getString("password"));
                datasource.cpds.setInitialPoolSize(DataUtility.getInt (rb.getString("initialPoolSize")));
                datasource.cpds.setAcquireIncrement(DataUtility.getInt(rb.getString("acquireIncrement")));
                datasource.cpds.setMaxPoolSize(DataUtility.getInt(rb.getString("maxPoolSize")));
                datasource.cpds.setMinPoolSize(DataUtility.getInt(rb.getString("minPoolSize")));
                datasource.cpds.setMaxIdleTime(DataUtility.getInt(rb.getString("timeout")));
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        /*    datasource.cpds.setJdbcUrl(PropertyReader.getValue("url"));
            datasource.cpds.setUser(PropertyReader.getValue("username"));
            datasource.cpds.setPassword(PropertyReader.getValue("password"));
            datasource.cpds.setInitialPoolSize(new Integer((String) PropertyReader.getValue("initialPoolSize")));
            datasource.cpds.setAcquireIncrement(new Integer((String) PropertyReader.getValue("acquireIncrement")));
            datasource.cpds.setMaxPoolSize(new Integer((String) PropertyReader.getValue("maxPoolSize")));
            datasource.cpds.setMaxIdleTime(DataUtility.getInt(PropertyReader.getValue("timeout")));
            datasource.cpds.setMinPoolSize(new Integer((String) PropertyReader.getValue("minPoolSize")));
        */    
          
        }
        return datasource;
    }

    /**
     * Gets the connection from ComboPooledDataSource
     * 
     * @return connection
     */
    public static Connection getConnection() throws Exception {
        return getInstance().cpds.getConnection();
    }

    /**
     * Closes a connection
     * 
     * @param connection
     * 
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }

    public static void trnRollback(Connection connection)
            throws ApplicationException {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new ApplicationException(ex.toString());
            }
        }
    }
}
