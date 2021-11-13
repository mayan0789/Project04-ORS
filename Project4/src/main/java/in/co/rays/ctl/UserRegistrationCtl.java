package in.co.rays.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * Servlet implementation class UserRegistrationCtl
 */

/**
 * @author Mayank
 *
 */
public class UserRegistrationCtl extends BaseCtl {

    
	
	private static final long serialVersionUID = 1L;
	
	public static final String OP_SIGN_UP = "SignUp";

//    private static Logger log = Logger.getLogger(UserRegistrationCtl.class);

    @Override
    protected boolean validate(HttpServletRequest request) {

//        log.debug("UserRegistrationCtl Method validate Started");

        boolean pass = true;

        String login = request.getParameter("login");
        String dob = request.getParameter("dob");

        if (DataValidator.isNull(request.getParameter("firstName"))) {
            request.setAttribute("firstName",
                    PropertyReader.getValue("error.require", "First Name"));
            pass = false;
        }
        else if(!DataValidator.isName(request.getParameter("firstName"))) {
        	request.setAttribute("firstName","First Name must have Alphabets Only ");
        	pass=false;
        	        }
        if (DataValidator.isNull(request.getParameter("lastName"))) {
            request.setAttribute("lastName",
                    PropertyReader.getValue("error.require", "Last Name"));
            pass = false;
        }
        else if(!DataValidator.isName(request.getParameter("lastName"))) {
        	request.setAttribute("lastName","Last Name must have Alphabets Only ");
        	pass=false;
        	        }
        if (DataValidator.isNull(login)) {
            request.setAttribute("login",
                    PropertyReader.getValue("error.require", "Login Id"));
            pass = false;
        } else if (!DataValidator.isEmail(login)) {
            request.setAttribute("login",
                    PropertyReader.getValue("error.email", "Login "));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("password"))) {
            request.setAttribute("password",
                    PropertyReader.getValue("error.require", "Password"));
            pass = false;
        }else if (!DataValidator.isPassword(request.getParameter("password"))) {
            request.setAttribute("password",
                     "Password must contain alphanumeric and one special character");
            pass = false;
        }
       if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
           request.setAttribute("confirmPassword", PropertyReader.getValue(
                   "error.require", "Confirm Password"));
            pass = false;
        }
       else if (!DataValidator.isPassword(request.getParameter("confirmPassword"))) {
           request.setAttribute("confirmPassword",
                    "Password must contain alphanumeric and one special character");
           pass = false;
       }
        if (DataValidator.isNull(request.getParameter("gender"))) {
            request.setAttribute("gender",
                    PropertyReader.getValue("error.require", "Gender"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("mobileno"))) {
            request.setAttribute("mobileno",
                    PropertyReader.getValue("error.require", "Mobile No."));
            pass = false;
        }
        else if (!DataValidator.isMobileNo(request.getParameter("mobileno"))) {
            request.setAttribute("mobileno",
                    "Mobile No. must start from 6-9 and have 10 digits");
            pass = false;
        }
        if (DataValidator.isNull(dob)) {
            request.setAttribute("dob",
                    PropertyReader.getValue("error.require", "Date Of Birth"));
            pass = false;
        } else if (!DataValidator.isDate(dob)) {
            request.setAttribute("dob",
                    PropertyReader.getValue("error.date", "Date Of Birth"));
            pass = false;
        }
        else if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))   && !"".equals(request.getParameter("confirmPassword"))) {
            request.setAttribute("confirmPassword",
                    "Confirm  Password and Password Does Not Match");

            pass = false;
        }
//       log.debug("UserRegistrationCtl Method validate Ended");

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {

  //      log.debug("UserRegistrationCtl Method populatebean Started");

        UserBean bean = new UserBean();

        bean.setId(DataUtility.getInt(request.getParameter("id")));

        bean.setRoll_Id(RoleBean.STUDENT);

        bean.setFirst_Name(DataUtility.getString(request
                .getParameter("firstName")));

        bean.setLast_Name(DataUtility.getString(request.getParameter("lastName")));

        bean.setLogin(DataUtility.getString(request.getParameter("login")));

        bean.setPassword(DataUtility.getString(request.getParameter("password")));

//        bean.setConfirmPassword(DataUtility.getString(request
//                .getParameter("confirmPassword")));

        bean.setMobile_No(DataUtility.getString(request.getParameter("mobileno")));
        
        bean.setGender(DataUtility.getString(request.getParameter("gender")));

        bean.setDOB(DataUtility.getDate(request.getParameter("dob")));

        populateDTO(bean, request);

     //   log.debug("UserRegistrationCtl Method populatebean Ended");

        return bean;
    }

    /**
     * Display concept of user registration
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
       // log.debug("UserRegistrationCtl Method doGet Started");
        ServletUtility.forward(getView(), request, response);

    }

    /**
     * Submit concept of user registration
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("user regis ctl post method");
       // log.debug("UserRegistrationCtl Method doPost Started");
        
        String op = DataUtility.getString(request.getParameter("operation"));
        
        
        // get model
        UserModel model = new UserModel();

        if (OP_SIGN_UP.equalsIgnoreCase(op)) {
            UserBean bean = (UserBean) populateBean(request);
            try {
                int pk = model.add(bean);
                     //  model.registerUser(bean);
                bean.setId(pk);
                System.out.println("onee time");
                request.getSession().setAttribute("UserBean", bean);
                ServletUtility.setSuccessMessage("Registered Successfully ", request);
                
            } catch (ApplicationException e) {
           //     log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
         //       log.error(e);
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Login Id Already Exists",
                        request);
                ServletUtility.forward(getView(), request, response);
            }
        }
        else if (OP_RESET.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;	
        }
        ServletUtility.forward(getView(), request, response);

        // log.debug("UserRegistrationCtl Method doPost Ended");
    }

    @Override
    protected String getView() {
        return ORSView.USER_REGISTRATION_VIEW;
    }
}
