package in.co.rays.ctl;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * My Profile functionality Controller. Performs operation for update your
 * Profile
 * 
 * @author Mayank
 * 
 */
@ WebServlet(name="MyProfileCtl",urlPatterns={"/ctl/MyProfileCtl"})
public class MyProfileCtl extends BaseCtl {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String OP_CHANGE_MY_PASSWORD = "Change Password";
    private static Logger log = Logger.getLogger(MyProfileCtl.class);

    @Override
    protected boolean validate(HttpServletRequest request) {

        log.debug("MyProfileCtl Method validate Started");
        boolean pass = true;
        String op = DataUtility.getString(request.getParameter("operation"));

        if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {
            return pass;
        }

        if (DataValidator.isNull(request.getParameter("firstName"))) {
   //         System.out.println("firstName" + request.getParameter("firstName"));
            request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("lastName"))) {
            request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("gender"))) {
            request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("mobileNo"))) {
            request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "MobileNo"));
            pass = false;
        }
        else if (!DataValidator.isMobileNo(request.getParameter("mobileNo"))) {
        	request.setAttribute("mobileNo", PropertyReader.getValue("error.mobile", "Invalid"));
        	pass = false;
			
		}
        if (DataValidator.isNull(request.getParameter("dob"))) {
            request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
            pass = false;
        }

        log.debug("MyProfileCtl Method validate Ended");
        return pass;

    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        log.debug("MyProfileCtl Method populatebean Started");

        UserBean bean = new UserBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setLogin(DataUtility.getString(request.getParameter("login")));
        bean.setFirst_Name(DataUtility.getString(request .getParameter("firstName")));
        bean.setLast_Name(DataUtility.getString(request.getParameter("lastName")));
        bean.setMobile_No(DataUtility.getString(request.getParameter("mobileNo")));
        bean.setGender(DataUtility.getString(request.getParameter("gender")));
        bean.setDOB(DataUtility.getDate(request.getParameter("dob")));

        populateDTO(bean, request);
        return bean;
    }
    /**
     * Contains Display logics
     */

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        log.debug("MyprofileCtl Method doGet Started");

        UserBean UserBean = (UserBean) session.getAttribute("user");
        long id = UserBean.getId();
        String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        UserModel model = new UserModel();
        
        if (id > 0 || op != null) {
       //     System.out.println("in id > 0  condition");
            UserBean bean;
            try {
                bean = model.findByPK(id);
            //    System.out.println("======my profile=====))))"+bean.getGender());
                ServletUtility.setBean(bean, request);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
        }
        ServletUtility.forward(getView(), request, response);
        log.debug("MyProfileCtl Method doGet Ended");
    }

    /**
     * contain Submit Logic
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        log.debug("MyprofileCtl Method doPost Started");

        UserBean UserBean = (UserBean) session.getAttribute("user");
        long id = UserBean.getId();
        String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        UserModel model = new UserModel();

        if (OP_SAVE.equalsIgnoreCase(op)) {
            UserBean bean = (UserBean) populateBean(request);
            try {
                if (id > 0) {
                    UserBean.setFirst_Name(bean.getFirst_Name());
                    UserBean.setLast_Name(bean.getLast_Name());
                    UserBean.setGender(bean.getGender());
                    UserBean.setMobile_No(bean.getMobile_No());
                    UserBean.setDOB(bean.getDOB());
                    model.update(UserBean);

                }	
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage( "Profile is updated Successfully. ", request);
            } catch (ApplicationException e) {
            	e.printStackTrace();
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Login id already exists", request);
			} /*
				 * catch (RecordNotFoundException e) { 
				 * e.printStackTrace(); }
				 */
        } else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {

            ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request,
                    response);
            return;

        }

        ServletUtility.forward(getView(), request, response);

        log.debug("MyProfileCtl Method doPost Ended");
    }

    @Override
    protected String getView() {
        return ORSView.MY_PROFILE_VIEW;
    }

}