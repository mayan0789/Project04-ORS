package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.MarksheetBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.MarksheetModel;
import in.co.rays.model.StudentModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * Servlet implementation class MarksheetCtl
 */
/**
 * @author Mayank
 *
 */
@WebServlet("/ctl/MarksheetCtl")
public class MarksheetCtl extends BaseCtl {
	// private static Logger log = Logger.getLogger(MarksheetCtl.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void preload(HttpServletRequest request) {
		//System.out.println("MarksheetCtl Preload Satrt");

		StudentModel model = new StudentModel();
		try {
			List l = model.list();
			request.setAttribute("Studentlist", l);
		} catch (ApplicationException e) {
			// log.error(e);
		}
		//System.out.println("MarksheetCtl  Preload End");
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		// log.debug("MarksheetCtl Method validate Started");
		boolean pass = true;

	//	System.out.println("MarksheetCtl validate Start");

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll Number"));
			pass = false;
		}else if (!DataValidator.isRollNo(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", "Roll Number should be Alphanumeric('10AA0111')");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("physics"))) {
			request.setAttribute("physics", PropertyReader.getValue("error.require", "Physics marks"));
			pass = false;
		} else if (DataUtility.getInt(request.getParameter("physics")) < 0) {
			request.setAttribute("physics", "Marks can not less than 0");
			pass = false;
		} else if (DataUtility.getInt(request.getParameter("physics")) > 100) {
			request.setAttribute("physics", "Marks can not be more than 100");
			pass = false;
		} else if (DataValidator.isNotNull(request.getParameter("physics"))
				&& !DataValidator.isInteger(request.getParameter("physics"))) {
			request.setAttribute("physics", PropertyReader.getValue("error.integer", "Physics marks"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", PropertyReader.getValue("error.require", "Chemistry Mark"));
			pass = false;
		} else if (DataUtility.getInt(request.getParameter("chemistry")) > 100) {
			request.setAttribute("chemistry", "Marks can not more then 100");
			pass = false;
		} else if (DataUtility.getInt(request.getParameter("chemistry")) < 0) {
			request.setAttribute("chemistry", "Marks can not be less then 0 ");
			pass = false;
		} else if (DataValidator.isNotNull(request.getParameter("chemistry"))
				&& !DataValidator.isInteger(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", PropertyReader.getValue("error.integer", "Chemistry Marks"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("maths"))) {
			request.setAttribute("maths", PropertyReader.getValue("error.require", "Maths Marks"));
			pass = false;
		} else if (DataUtility.getInt(request.getParameter("maths")) > 100) {
			request.setAttribute("maths", "Marks can not be more then 100");
			pass = false;
		} else if (DataUtility.getInt(request.getParameter("maths")) < 0) {
			request.setAttribute("maths", "Marks can not be less then 0 ");
			pass = false;
		} else if (DataValidator.isNotNull(request.getParameter("maths"))
				&& !DataValidator.isInteger(request.getParameter("maths"))) {
			request.setAttribute("maths", PropertyReader.getValue("error.integer", "Chemistry Marks"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("StudentId"))) {
			request.setAttribute("StudentId", "Select A StudentName");
		}
//log.debug("MarksheetCtl Method validate Ended");
//		System.out.println("MarksheetCtl validate end");

		return pass;

	}

	protected BaseBean populateBean(HttpServletRequest request) {

		// log.debug("MarksheetCtl Method populatebean Started");
	//	System.out.println("MarksheetCtl Populate Start");

		MarksheetBean bean = new MarksheetBean();

		bean.setId(DataUtility.getInt(request.getParameter("id")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("StudentId")));
		populateDTO(bean, request);

	//	System.out.println("MarksheetCtl Populate end");

		return bean;
	}

	/**
	 * Contain display logics
	 * 
	 * 
	 **/

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
//    log.debug("MarksheetCtl Method doGet Started");
		//System.out.println("MarksheetCtl doget");

		MarksheetModel model = new MarksheetModel();

		int id = DataUtility.getInt(request.getParameter("id"));

		if (id > 0) {
			MarksheetBean bean;
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
//log.debug("MarksheetCtl Method doGet Ended");

	}

	/**
	 * Contain Submit Logic
	 * 
	 *
	 **/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// log.debug("MarksheetCtl Method doPost Started");
		//System.out.println("MarksheetCtl dopost ");

		String op = DataUtility.getString(request.getParameter("operation"));
		int id = DataUtility.getInt(request.getParameter("id"));

		MarksheetBean bean = (MarksheetBean) populateBean(request);
		
		MarksheetModel model = new MarksheetModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			try {
				if (id > 0) {
					model.update(bean);
				} else {
					int pk = model.add(bean);
					// bean.setId(pk);
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Succesfully Saved", request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				// log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Rollno is already exist", request);

			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
			return;
		}
		ServletUtility.setBean(bean, request);
		ServletUtility.forward(getView(), request, response);
	//	System.out.println("marksheetctl dopost end");
//	log.debug("MarksheetCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.MARKSHEET_VIEW;
	}

}
