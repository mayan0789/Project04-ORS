package in.co.rays.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.RoleModel;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * Servlet implementation class LoginCtl
 */

/**
 * @author Mayank
 *
 */
public class LoginCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";

	// private static Logger log = Logger.getLogger(LoginCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		// log.debug("LoginCtl Method validate Started");
		System.out.println("LoginCtl validate Start");

		boolean pass = true;

		String op = request.getParameter("operation");
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}

		String login = request.getParameter("login");

		if (DataValidator.isNull(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Login "));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		} /*
			 * else if (!DataValidator.isPassword(request.getParameter("password"))) {
			 * request.setAttribute("password",
			 * "Password must have aplhanumeric characters"); pass = false; }
			 */

		// log.debug("LoginCtl Method validate Ended");
		System.out.println("Login Ctl Validate end");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		// log.debug("LoginCtl Method populatebean Started");

		System.out.println("LoginCtl populatebean Start");
		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		// log.debug("LoginCtl Method populatebean Ended");
		System.out.println("LoginCtl populatebean End");

		return bean;
	}

	/**
	 * Display Login form
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		// log.debug(" Method doGet Started");
		System.out.println("LoginCtl Doget Start");

		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_LOG_OUT.equals(op)) {

			session = request.getSession();

			session.invalidate();

			ServletUtility.setSuccessMessage("You Have Logged Out Succesfully", request);

			ServletUtility.forward(getView(), request, response);

			return;

		}

		ServletUtility.forward(getView(), request, response);

		// log.debug("UserCtl Method doPost Ended");

	}

	/**
	 * Submitting or login action performing
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// log.debug(" Method doPost Started");

		HttpSession session = request.getSession(true);

		String op = DataUtility.getString(request.getParameter("operation"));

		System.out.println("LoginCtl dopost start");

		// get model
		UserModel model = new UserModel();
		RoleModel role = new RoleModel();
		String uri = request.getParameter("URI");

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			UserBean bean = (UserBean) populateBean(request);

			try {

				bean = model.authenticate(bean.getLogin(), bean.getPassword());

				if (bean != null) {
					session.setAttribute("user", bean);

					long rollId = bean.getRoll_Id();

					RoleBean rolebean = role.findByPK(rollId);

					if (rolebean != null) {
						session.setAttribute("role", rolebean.getName());
					}

					// front-controller

					System.out.println("URI---- " + uri);
					if (uri == null || "null".equalsIgnoreCase(uri)) {
						ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);
						return;

					} else {

						ServletUtility.redirect(uri, request, response);
						return;

					}

					/*
					 * ServletUtility.forward(ORSView.WELCOME_VIEW, request, response); return;
					 */

				} else {
					bean = (UserBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Invalid LoginId And Password", request);
				}

			} catch (ApplicationException e) {
				// log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;

		}
		System.out.println("LoginCtl dopost end");

		ServletUtility.forward(getView(), request, response);

		// log.debug("UserCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}

}