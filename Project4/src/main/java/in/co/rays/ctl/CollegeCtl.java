package in.co.rays.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CollegeBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CollegeModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * Servlet implementation class CollegeCtl
 */
/**
 * @author Mayank
 *
 */
@WebServlet("/ctl/CollegeCtl")
public class CollegeCtl extends BaseCtl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	   // private static Logger log = Logger.getLogger(CollegeCtl.class);

	    @Override
	    protected boolean validate(HttpServletRequest request) {

	       // log.debug("CollegeCtl Method validate Started");

	        boolean pass = true;

	        if (DataValidator.isNull(request.getParameter("name"))) {
	            request.setAttribute("name",
	                    PropertyReader.getValue("error.require", "Name"));
	            pass = false;
	        }else  if (!DataValidator.isName(request.getParameter("name"))) {
	            request.setAttribute("name",
	                    PropertyReader.getValue("error.name", "Name"));
	            pass = false;
	        }

	        if (DataValidator.isNull(request.getParameter("address"))) {
	            request.setAttribute("address",
	                    PropertyReader.getValue("error.require", "Address"));
	            pass = false;
	        }

	        if (DataValidator.isNull(request.getParameter("state"))) {
	            request.setAttribute("state",
	                    PropertyReader.getValue("error.require", "State"));
	            pass = false;
	        }else if (!DataValidator.isName(request.getParameter("state"))) {
	            request.setAttribute("state",
	                    PropertyReader.getValue("error.name", "State name"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("city"))) {
	            request.setAttribute("city",
	                    PropertyReader.getValue("error.require", "City"));
	            pass = false;
	        }else if (!DataValidator.isName(request.getParameter("city"))) {
	            request.setAttribute("city",
	                    PropertyReader.getValue("error.name", "City name"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("phoneNo"))) {
	            request.setAttribute("phoneNo",
	                    PropertyReader.getValue("error.require", "Phone No"));
	            pass = false;
	        }else if (!DataValidator.isMobileNo(request.getParameter("phoneNo"))) {
	            request.setAttribute("phoneNo", "Mobile No. must start with 6-9 and contain ten digits only");
	            pass = false;
	        }

	       // log.debug("CollegeCtl Method validate Ended");

	        return pass;
	    }

	    @Override
	    protected BaseBean populateBean(HttpServletRequest request) {

	        //log.debug("CollegeCtl Method populatebean Started");

	        CollegeBean bean = new CollegeBean();

	        bean.setId(DataUtility.getInt(request.getParameter("id")));

	        bean.setName(DataUtility.getString(request.getParameter("name")));

	        bean.setAddress(DataUtility.getString(request.getParameter("address")));

	        bean.setState(DataUtility.getString(request.getParameter("state")));

	        bean.setCity(DataUtility.getString(request.getParameter("city")));

	        bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));

	        populateDTO(bean, request);

	       // log.debug("CollegeCtl Method populatebean Ended");

	        return bean;
	    }

	    /**
	     * Contains display logic
	     */
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {


	        // get model
	        CollegeModel model = new CollegeModel();

	        int id = DataUtility.getInt(request.getParameter("id"));

	        if (id > 0) {
	            CollegeBean bean;
	            try {
	                bean = model.findByPK(id);
	                ServletUtility.setBean(bean, request);
	            } catch (ApplicationException e) {
	               // log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }

	        ServletUtility.forward(getView(), request, response);
	    }

	    /**
	     * Contains Submit logics
	     */
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {

	       // log.debug("CollegeCtl Method doPost Started");

	        String op = DataUtility.getString(request.getParameter("operation"));

	        // get model
	        CollegeModel model = new CollegeModel();

	        long id = DataUtility.getLong(request.getParameter("id"));

	        if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

	            CollegeBean bean = (CollegeBean) populateBean(request);

	            try {
	                if (id > 0) {
	                    model.update(bean);
	                    ServletUtility.setSuccessMessage("Successfully Updated",
		                        request);
	                } else {
	                   // int pk ;
	                    		model.add(bean);
	                    		 ServletUtility.setSuccessMessage("Successfully Saved",
	         	                        request);
	                  //  bean.setId(pk);
	                }
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setSuccessMessage("successfully saved",
	                        request);

	            } catch (ApplicationException e) {
	                e.printStackTrace();
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage("College Name already exists",
	                        request);
	            }

	        }  else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility
	                    .redirect(ORSView.COLLEGE_LIST_CTL, request, response);
	            return;

	        }

	        ServletUtility.forward(getView(), request, response);

	        //log.debug("CollegeCtl Method doGet Ended");
	    }

	    @Override
	    protected String getView() {
	        return ORSView.COLLEGE_VIEW;
	    }



	}
