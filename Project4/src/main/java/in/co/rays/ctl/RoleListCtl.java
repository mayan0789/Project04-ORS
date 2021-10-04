package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.RoleModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * Servlet implementation class RoleListCtl
 */

/**
 * @author Mayank
 *
 */
@WebServlet("/ctl/RoleListCtl")
public class RoleListCtl extends  BaseCtl {
	//private static Logger log = Logger.getLogger(RoleListCtl.class);

	
	
	@Override
	protected void preload(HttpServletRequest request)
	{
		RoleModel rmodel=new RoleModel();
		try {
			List rlist=rmodel.list();
			request.setAttribute("roleList", rlist);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
	}
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected BaseBean populateBean(HttpServletRequest request) {
        RoleBean bean = new RoleBean();
        bean.setName(DataUtility.getString(request.getParameter("name")));
        System.out.println("name>>>>>>"+request.getParameter("name"));

        return bean;
    }

    /**
     * Contains Display logics
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException{
       // log.debug("RoleListCtl doGet Start");
        System.out.println(">>>>>>>inside doGet>>>>");
    	List list = null;
        int pageNo = 1;
        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
        RoleBean bean = (RoleBean) populateBean(request);
        String op = DataUtility.getString(request.getParameter("operation"));
        RoleModel model = new RoleModel();
    	try {
        list = model.search(bean, pageNo, pageSize);
		ServletUtility.setList(list, request);
		if (list == null || list.size() == 0) {
		    ServletUtility.setErrorMessage("No record found ", request);
		}
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
	
			ServletUtility.forward(getView(), request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("do get end>>>>>>>>>");
    }

    /**
     * Contains Submit logics
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
      //  log.debug("RoleListCtl doPost Start");
        List list = null;
        System.out.println(">>>>>>>inside dopost>>>>>>>.");
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;
        RoleBean bean = (RoleBean) populateBean(request);
        String op = DataUtility.getString(request.getParameter("operation"));

        RoleModel model = new RoleModel();
        		
        if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
		        || "Previous".equalsIgnoreCase(op)) {

		    if (OP_SEARCH.equalsIgnoreCase(op)) {
		    	System.out.println("if search>>>>>>>>>");
		        pageNo = 1;
		    } else if (OP_NEXT.equalsIgnoreCase(op)) {
		        pageNo++;
		    } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
		        pageNo--;
		    }

		}
		list = model.search(bean, pageNo, pageSize);
		ServletUtility.setList(list, request);
		if (list == null || list.size() == 0) {
		    ServletUtility.setErrorMessage("No record found ", request);
		}
		ServletUtility.setList(list, request);

		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
    }

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.ROLE_LIST_VIEW;
	}

}
