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
 * Servlet implementation class MarksheetListCtl
 */
/**
 * @author Mayank
 *
 */
@WebServlet("/ctl/MarksheetListCtl")
public class MarksheetListCtl extends BaseCtl {
	// private static Logger log = Logger.getLogger(MarksheetListCtl.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void preload(HttpServletRequest request) {
	//	System.out.println("marksheet list ctl preload start");
		MarksheetModel mdoel = new MarksheetModel();
		try {
			List cList = mdoel.list();
			System.out.println(cList.isEmpty());
			request.setAttribute("roll", cList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		//System.out.println("marksheet list ctl preload end");
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		MarksheetBean bean = new MarksheetBean();

	////	System.out.println("marksheet list ctl populate start");

		bean.setId(DataUtility.getLong(request.getParameter("rollNo")));
		bean.setName(DataUtility.getString(request.getParameter("name")));

		//System.out.println("marksheet list ctl populate end");

		return bean;
	}

	/**
	 * ContainsDisplaylogics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	//	System.out.println("marksheet list ctl do get start");

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

//		System.out.println("marklistctl do get pageno and size " + pageNo + pageSize);
		MarksheetBean bean = (MarksheetBean) populateBean(request);

		List list = null;

		MarksheetModel model = new MarksheetModel();
		
		try {
			list = model.search(bean, pageNo, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			// log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}

	//	System.out.println("marksheet list ctl doget end");

		// log.debug("MarksheetListCtl doGet End");

	}

	/**
	 * Contains Submit logics
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// log.debug("MarksheetListCtl doPost Start");

	//	System.out.println("marksheet list ctl dopost start");

		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		MarksheetBean bean = (MarksheetBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");

		MarksheetModel model = new MarksheetModel();

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)
					|| OP_RESET.equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_RESET.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
					return;
				}

				else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					MarksheetBean deletebean = new MarksheetBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
						ServletUtility.setSuccessMessage("Record Deleted Successfully", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			}
			list = model.search(bean, pageNo, pageSize);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setBean(bean, request);
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			// log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		//System.out.println("marksheet list ctl dopost end");

		// log.debug("MarksheetListCtl doPost End");
	}

	@Override
	protected String getView() {
		return ORSView.MARKSHEET_LIST_VIEW;
	}

}
