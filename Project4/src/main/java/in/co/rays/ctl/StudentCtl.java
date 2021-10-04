package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.CollegeModel;
import in.co.rays.model.StudentModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * Servlet implementation class StudentCtl
 */
/**
 * @author Mayank
 *
 */
@WebServlet("/ctl/StudentCtl")
public class StudentCtl extends BaseCtl {
	//private static Logger log = Logger.getLogger(StudentCtl.class);

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void preload(HttpServletRequest request) {
        CollegeModel model = new CollegeModel();
        try {
            List l = model.list();
            request.setAttribute("collegeList", l);
        } catch (ApplicationException e) {
          //  log.error(e);
        }

    }

    @Override
    protected boolean validate(HttpServletRequest request) {

      //  log.debug("StudentCtl Method validate Started");

        boolean pass = true;

        String op = DataUtility.getString(request.getParameter("operation"));
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");

        if (DataValidator.isNull(request.getParameter("firstname"))) {
            request.setAttribute("firstname",
                    PropertyReader.getValue("error.require", "FristName"));
            
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("lastname"))) {
            request.setAttribute("lastname",
                    PropertyReader.getValue("error.require", "Last Name"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("mobile"))) {
            request.setAttribute("mobile",
                    PropertyReader.getValue("error.require", "Mobile No"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("email"))) {
            request.setAttribute("email",
                    PropertyReader.getValue("error.require", "Email "));
            pass = false;
        } else if (!DataValidator.isEmail(request.getParameter("email"))) {
            request.setAttribute("email",
                    PropertyReader.getValue("error.email", "Email "));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("collegename"))) {
            request.setAttribute("collegename",
                    PropertyReader.getValue("error.require", "College Name"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("dob"))) {
            request.setAttribute("dob",
                    PropertyReader.getValue("error.require", "Date Of Birth"));
            pass = false;
        } else if (!DataValidator.isDate(request.getParameter("dob"))) {
            request.setAttribute("dob",
                    PropertyReader.getValue("error.date", "Date Of Birth"));
            pass = false;
        }

       // log.debug("StudentCtl Method validate Ended");

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {

       // log.debug("StudentCtl Method populatebean Started");

        StudentBean bean = new StudentBean();

        bean.setId(DataUtility.getInt(request.getParameter("id")));

        bean.setFirst_Name(DataUtility.getString(request
                .getParameter("firstname")));
        System.out.println("fname>>>>"+request.getParameter("firstname"));
        bean.setLast_Name(DataUtility.getString(request.getParameter("lastname")));
        System.out.println("lastname>>>>"+request.getParameter("lastname"));

        bean.setDate_of_Birth(DataUtility.getDate(request.getParameter("dob")));
        System.out.println("dob>>>>"+request.getParameter("dob"));

        bean.setMobile_No(DataUtility.getString(request.getParameter("mobile")));
        System.out.println("modile>>>>"+request.getParameter("mobile"));

        bean.setEmail(DataUtility.getString(request.getParameter("email")));
        System.out.println("email>>>>"+request.getParameter("email"));
        bean.setCollege_Id(DataUtility.getInt(request.getParameter("collegename")));
        System.out.println("cid>>>>"+request.getParameter("collegename"));
        populateDTO(bean, request);

       // log.debug("StudentCtl Method populatebean Ended");

        return bean;
    }

    /**
     * Contains Display logics
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

      //  log.debug("StudentCtl Method doGet Started");

        String op = DataUtility.getString(request.getParameter("operation"));
        int id = DataUtility.getInt(request.getParameter("id"));

        // get model

        StudentModel model = new StudentModel();
        if (id > 0 || op != null) {
            StudentBean bean;
            try {
                bean = model.findByPK(id);
                ServletUtility.setBean(bean, request);
            } catch (ApplicationException e) {
                //log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
        }
        ServletUtility.forward(getView(), request, response);
       // log.debug("StudentCtl Method doGett Ended");
    }

    /**
     * Contains Submit logics
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        //log.debug("StudentCtl Method doPost Started");
    		System.out.println("inside do post");
        String op = DataUtility.getString(request.getParameter("operation"));

        // get model

        StudentModel model = new StudentModel();

        int id = DataUtility.getInt(request.getParameter("id"));
//		System.out.println("inside save  op");
        if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
    		System.out.println("inside save  op");

            StudentBean bean = (StudentBean) populateBean(request);
    		System.out.println("inside populate bean");

            try {
                if (id > 0) {
                    model.update(bean);
                } else {
            		System.out.println("upper add model");

                    int pk = model.add(bean);
                    bean.setId(pk);
                }

                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Data is successfully saved",
                        request);

            } catch (Exception e) {
            	e.printStackTrace();
            	//   log.error(e);
             //   ServletUtility.handleException(e, request, response);
                return;
            }
                //            } catch (DuplicateException e) {
//                ServletUtility.setBean(bean, request);
//                ServletUtility.setErrorMessage(
//                        "Student Email Id already exists", request);
//            }

        }

        else if (OP_DELETE.equalsIgnoreCase(op)) {

            StudentBean bean = (StudentBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request,
                        response);
                return;

            } catch (ApplicationException e) {
              //  log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

            ServletUtility
                    .redirect(ORSView.STUDENT_LIST_CTL, request, response);
            return;

        }
        ServletUtility.forward(getView(), request, response);

       // log.debug("StudentCtl Method doPost Ended");
    }

	@Override
	protected String getView() {
		
		return ORSView.STUDENT_VIEW;
	}

}
