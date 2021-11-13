package in.co.rays.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.ctl.BaseCtl;
import in.co.rays.ctl.ORSView;

public class ServletUtility {

	  /**
     * Forward to given JSP/Servlet
     *
     
     */
    public static void forward(String page, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }

    /**
     * Forward to Layout View
     *
     *  page
     *  request
     */
    public static void forwardView(String page, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        request.setAttribute("bodyPage", page);
        RequestDispatcher rd = request
                .getRequestDispatcher(ORSView.LAYOUT_VIEW);
        rd.forward(request, response);
    }

    /**
     * Redirect to given JSP/Servlet
     *
     *  page
     *  request
     *  response
     */
    public static void redirect(String page, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect(page);
    }

    /**
     * Redirect to Application Error Handler Page
     *
     *  e
     *  request
     *  response
     */
    public static void handleException(Exception e, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("exception", e);
//        response.sendRedirect(ORSView.ERROR_CTL);

    }
    
    public static String getErrorMessage(String property,HttpServletRequest request) {

        String val = (String) request.getAttribute(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }


    /**
     * Gets error message from request
     *
     */
    public static String getErrorMessage(HttpServletRequest request) {

        String val = (String) request.getAttribute(BaseCtl.MSG_ERROR);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * returns all input error messages
     *
     * 
     */
    public static String getErrorMessageHtml(HttpServletRequest request) {

        Enumeration<String> e = request.getAttributeNames();

        StringBuffer sb = new StringBuffer("<UL>");
        String name = null;

        while (e.hasMoreElements()) {
            name = e.nextElement();
            if (name.startsWith("error.")) {
                sb.append("<LI class='error'>" + request.getAttribute(name)
                        + "</LI>");
            }
        }
        sb.append("</UL>");
        return sb.toString();
    }

    /**
     * Gets a message from request
     *
     
     */
    public static String getMessage(String property, HttpServletRequest request) {
        String val = (String) request.getAttribute(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * Sets error message to request
     *
     *  msg
     *  request
     */
   public static void setErrorMessage(String msg, HttpServletRequest request) {
        request.setAttribute(BaseCtl.MSG_ERROR, msg);
    }

    /**
     * Gets error message from request
     *
     *  request
     * 
     */
    public static String getErrorMessage1(HttpServletRequest request) {
        String val = (String) request.getAttribute(BaseCtl.MSG_ERROR);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * Sets success message to request
     *
     *  msg
     *  request
     */
    public static void setSuccessMessage(String msg, HttpServletRequest request) {
        request.setAttribute(BaseCtl.MSG_SUCCESS, msg);
    }

    /**
     * Gets success message from request
     *
     *  request
     * 
     */
    public static String getSuccessMessage(HttpServletRequest request) {
        String val = (String) request.getAttribute(BaseCtl.MSG_SUCCESS);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

	/*
	 * public static void setModel(BaseModel model, HttpServletRequest request) {
	 * request.setAttribute("model", model); }
	 */
    /**
     * Sets default Bean to request
     *
     *  bean
     *  request
     */
    public static void setBean(BaseBean bean, HttpServletRequest request) {
        request.setAttribute("bean", bean);
    }

/*    public static void setUserModel(UserModel model, HttpServletRequest request) {
        request.getSession().setAttribute("user", model);
    }
*/
    /**
     * Gets default bean from request
     *
     *  request
     * 
     */

    public static BaseBean getBean(HttpServletRequest request) {
        return (BaseBean) request.getAttribute("bean");
    }

/*    public static boolean isLoggedIn(HttpServletRequest request) {
        UserModel model = (UserModel) request.getSession().getAttribute("user");
        return model != null;
    }
*/
    /**
     * Returns logged in user role
     *
     *  request
     * 
     */

//  public static int getRole(HttpServletRequest request) {
//        UserModel model = (UserModel) request.getSession().getAttribute("user");
//        if (model != null) {
//            return model.getRoleId();
//        } else {
//            return AppRole.GUEST;
//        }
//   }

  /**
     * gets Model from request scope
     *
     *  request
     * 
     */
   /* public static BaseModel getModel(HttpServletRequest request) {
        return (BaseModel) request.getAttribute("model");
    }*/

    /**
     * Get request parameter to display. If value is null then return empty
     * string
     *
     *  property
     *  request
     * 
     */

    public static String getParameter(String property,
            HttpServletRequest request) {
        String val = (String) request.getParameter(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * Sets default List to request
     *
     *  list
     *  request
     */
    public static void setList(List list, HttpServletRequest request) {
        request.setAttribute("list", list);
    }

    /**
     * Gets default list from request
     *
     *  request
     * 
     */
    public static List getList(HttpServletRequest request) {
        return (List) request.getAttribute("list");
    }

    /**
     * Sets Page Number for List pages
     *
     *  pageNo
     *  request
     */
    public static void setPageNo(int pageNo, HttpServletRequest request) {
        request.setAttribute("pageNo", pageNo);
    }

    /**
     * Gets Page Number for List pages
     *
     *  request
     * 
     */
    public static int getPageNo(HttpServletRequest request) {
        return (Integer) request.getAttribute("pageNo");
    }

    /**
     * Sets Page Size for list pages
     *
     *  pageSize
     *  request
     */
    public static void setPageSize(int pageSize, HttpServletRequest request) {
        request.setAttribute("pageSize", pageSize);
    }

    /**
     * Gets Page Size for List pages
     *
     *  request
     * 
     */
  public static int getPageSize(HttpServletRequest request) {
        return (Integer) request.getAttribute("pageSize");
    }

}
