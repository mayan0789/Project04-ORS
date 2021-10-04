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
import in.co.rays.model.MarksheetModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * Servlet implementation class MarksheetMeritListCtl
 */
/**
 * @author Mayank
 *
 */
@WebServlet("/ctl/MarksheetMeritListCtl")
public class MarksheetMeritListCtl extends BaseCtl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static Logger log = Logger.getLogger(MarksheetMeritListCtl.class);

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        MarksheetBean bean = new MarksheetBean();

        return bean;
    }

    /**
     * Contains Display logics
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

//        log.debug("MarksheetMeritListCtl doGet Start");

        int pageNo = 1;

        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

        MarksheetBean bean = (MarksheetBean) populateBean(request);

        String op = DataUtility.getString(request.getParameter("operation"));

        MarksheetModel model = new MarksheetModel();
        List list = null;
        try {
            list = model.getMeritList(pageNo, pageSize);
            ServletUtility.setList(list, request);
            if (list == null || list.size() == 0) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            ServletUtility.setList(list, request);
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(ORSView.MARKSHEET_MERIT_LIST_VIEW, request,
                    response);
        } catch (ApplicationException e) {
          //  log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
       // log.debug("MarksheetMeritListCtl doGet End");
    }

    /**
     * Contains Submit logics
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
      //  log.debug("MarksheetMeritListCtl doGet Start");
        List list = null;
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;
        MarksheetBean bean = (MarksheetBean) populateBean(request);
        String op = DataUtility.getString(request.getParameter("operation"));
        MarksheetModel model = new MarksheetModel();
        try {
            if (OP_BACK.equalsIgnoreCase(op)) {
                ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
                return;
            }
            list = model.getMeritList(pageNo, pageSize);
            ServletUtility.setList(list, request);
            if (list == null || list.size() == 0) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            ServletUtility.setList(list, request);
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(ORSView.MARKSHEET_MERIT_LIST_VIEW, request,
                    response);
        } catch (ApplicationException e) {
          //  log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
       // log.debug("MarksheetMeritListCtl doPost End");
    }
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.MARKSHEET_MERIT_LIST_VIEW;
	}

}
