package in.co.rays.ctl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.bean.TimetableBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.CourseModel;
import in.co.rays.model.SubjectModel;
import in.co.rays.model.TimetableModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * @author Mayank
 *
 */
@WebServlet(name = "TimeTableListCtl", urlPatterns = {"/ctl/TimeTableListCtl"})
public class TimeTableListCtl extends BaseCtl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(TimeTableListCtl.class);

	protected void preload(HttpServletRequest request) {

		CourseModel model = new CourseModel();
		SubjectModel smodel = new SubjectModel();
		List<CourseBean> list = null;
		List<SubjectBean> list2 = null;
		try {
			list = model.list();
			list2 = smodel.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("courseList", list);
		request.setAttribute("subjectList", list2);

	}

	protected BaseBean populateBean(HttpServletRequest request) {
		TimetableBean bean = new TimetableBean();

//		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCourse_Id(DataUtility.getInt(request.getParameter("clist")));
		bean.setSubject_Id(DataUtility.getInt(request.getParameter("slist")));
	//	bean.setSubjectName(DataUtility.getString(request.getParameter("slist")));
		bean.setExam_Date(DataUtility.getDate(request.getParameter("Exdate")));
	//	System.out.println("populate bean==========>>>> " + bean.getExamDate());
		populateDTO(bean, request);
		return bean;
	}
    /**
     * Contains display logics
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List list ;

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		TimetableModel model = new TimetableModel();
		TimetableBean bean =(TimetableBean) populateBean(request);

//		String op = DataUtility.getString(request.getParameter("operation"));
   //   String[] ids = request.getParameterValues("ids");
	    

		try {
			list = model.search(bean, pageNo, pageSize);
			ServletUtility.setBean(bean, request);
			
	//		ServletUtility.setList(list, request);
			if (list==null && list.size()==0) {
				ServletUtility.setErrorMessage("No record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);


		} catch (ApplicationException e) {
			e.printStackTrace();
			log.error(e);
			ServletUtility.handleException(e, request, response);
		}
	}
    /**
     * Contains Submit logics
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List list;
		String op = DataUtility.getString(request.getParameter("operation"));

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;


		TimetableBean bean = (TimetableBean) populateBean(request);	
		TimetableModel model = new TimetableModel();
		String[] ids = (String[]) request.getParameterValues("ids");
				
			        if (OP_SEARCH.equalsIgnoreCase(op)) {
				    pageNo = 1;
					} else if (OP_NEXT.equalsIgnoreCase(op)) {
						pageNo++;	
					} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
						if(pageNo<1){
							pageNo--;
						}else{
							pageNo= 1;
						}	
					}
					else if (OP_NEW.equalsIgnoreCase(op)) 
					{
						ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
						return ;
					}
					
					else if (OP_RESET.equalsIgnoreCase(op)) {
						ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
						return;
					}
					else if (OP_DELETE.equalsIgnoreCase(op)) {
						pageNo=1;
						if (ids != null && ids.length > 0) {
							TimetableBean bean3 = new TimetableBean();

							for (String id2 : ids) {
								int id1 = DataUtility.getInt(id2);
								bean3.setId(id1);
								try {
									model.delete(bean3);
								} catch (ApplicationException e) {
									e.printStackTrace();
									ServletUtility.handleException(e, request, response);
									return;
								}
								ServletUtility.setSuccessMessage("Data Deleted Succesfully", request);
							}
						
						}else{
							ServletUtility.setErrorMessage("Select at least one Record", request);
						}
					}
			    	try {
						list = model.search(bean, pageNo, pageSize);
						ServletUtility.setBean(bean, request);
					}
					catch(ApplicationException e){	
						ServletUtility.handleException(e, request, response);
						return;
					}
			   if(list==null || list.size()==0 && !OP_DELETE.equalsIgnoreCase(op)) 
			{
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setBean(bean, request);
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
	        ServletUtility.forward(getView(), request, response);			
		}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_LIST_VIEW;
	}

}
