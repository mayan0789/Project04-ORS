package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.UserBean;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.ServletUtility;

/**
 * @author Mayank
 *
 */
public abstract class BaseCtl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String OP_SAVE = "Save";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_GO = "Go";
	public static final String OP_BACK = "Back";
	public static final String OP_LOG_OUT = "Logout";
	public static final String OP_UPDATE = "Update";
	public static final String OP_RESET = "Reset";

	/**
	 * Success message key constant
	 */
	public static final String MSG_SUCCESS = "success";

	/**
	 * Error message key constant
	 */
	public static final String MSG_ERROR = "error";

	/**
	 * Validates input data entered by User
	 *
	 */
	protected boolean validate(HttpServletRequest request) {
	//	System.out.println("BaseCtl validate");
		return true;
	}

	/**
	 * Loads list and other data required to display at HTML form
	 *
	 */
	protected void preload(HttpServletRequest request) {
		//System.out.println("BaseCtl preload");

	}

	/**
	 * Populates bean object from request parameters
	 *
	 */
	protected BaseBean populateBean(HttpServletRequest request) {
	//	System.out.println("BaseCtl populatebean");

		return null;
	}

	/**
	 * Populates Generic attributes in DTO
	 *
	 */
	protected BaseBean populateDTO(BaseBean dto, HttpServletRequest request) {

		//System.out.println("BaseCtl populatedto Start");

		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;

		UserBean userbean = (UserBean) request.getSession().getAttribute("user");

		if (userbean == null) {
			// If record is created without login
			createdBy = "root";
			modifiedBy = "root";
		} else {

			modifiedBy = userbean.getLogin();

			// If record is created first time
			if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}

		}

		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		int cdt = DataUtility.getInt(request.getParameter("createdDatetime"));

		if (cdt > 0) {
			dto.setCreatedDateTime(DataUtility.getTimestamp(cdt));
		} else {
			dto.setCreatedDateTime(DataUtility.getCurrentTimestamp());
		}

		dto.setModifiedDateTime(DataUtility.getCurrentTimestamp());

		//System.out.println("BaseCtl populatedto end");
		return dto;

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	//	System.out.println("Basectl sevice start");
		// Load the preloaded data required to display at HTML form
		preload(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		// Check if operation is not DELETE, VIEW, CANCEL, and NULL then
		// perform input data validation

		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op) && !OP_VIEW.equalsIgnoreCase(op)
				&& !OP_DELETE.equalsIgnoreCase(op) && !OP_RESET.equalsIgnoreCase(op)) {
			// Check validation, If fail then send back to page with error
			// messages

			if (!validate(request)) {
				BaseBean bean = (BaseBean) populateBean(request);

				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);
				return;
			}
		}
		//System.out.println("BaseCtl service end");
		super.service(request, response);

	}

	/**
	 * Returns the VIEW page of this Controller
	 *
	 */
	protected abstract String getView();

}
