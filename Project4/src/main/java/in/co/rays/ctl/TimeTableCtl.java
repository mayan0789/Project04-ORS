package in.co.rays.ctl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.CourseBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.bean.TimetableBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModel;
import in.co.rays.model.SubjectModel;
import in.co.rays.model.TimetableModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * 
 * @author Mayank
 *
 */
@WebServlet(name = "TimeTableCtl", urlPatterns ={"/ctl/TimeTableCtl"})
public class TimeTableCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(TimeTableCtl.class);

	protected void preload(HttpServletRequest request) {
		CourseModel cmodel = new CourseModel();
		SubjectModel smodel = new SubjectModel();
		List<CourseBean> clist = new ArrayList<CourseBean>();
		List<SubjectBean> slist = new ArrayList<SubjectBean>();
		try {
			clist = cmodel.list();
			slist = smodel.list();
			request.setAttribute("CourseList", clist);
			request.setAttribute("SubjectList", slist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean validate(HttpServletRequest request) {
		log.debug("validate method of TimeTable Ctl started");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("courseId"))) {
			request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subjectId"))) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("semester"))) {
			request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("ExDate"))) {
			request.setAttribute("ExDate", PropertyReader.getValue("error.require", "Exam Date"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("ExTime"))) {
			request.setAttribute("ExTime", PropertyReader.getValue("error.require", "Exam Time"));
			pass = false;
		}
		
		/*if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", "Please Select Description");
			pass = false;
		}*/

		log.debug("validate method of TimeTable Ctl End");
		return pass;
	}

	protected TimetableBean populateBean(HttpServletRequest request) {
		log.debug("populateBean method of TimeTable Ctl start");
		TimetableBean bean = new TimetableBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setSubject_Id(DataUtility.getInt(request.getParameter("subjectId")));
	//	bean.setSubjectName(DataUtility.getString(request.getParameter("subjectname")));
		bean.setCourse_Id(DataUtility.getInt(request.getParameter("courseId")));
//		bean.setCourseName(DataUtility.getString(request.getParameter("coursename")));
		bean.setSemester(DataUtility.getString(request.getParameter("semester")));
	//	bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setExam_Date(DataUtility.getDate(request.getParameter("ExDate")));
		bean.setExam_Time(DataUtility.getString(request.getParameter("ExTime")));
		System.out.println("<<<<<<__________>>>>>>>>");
		System.out.println(request.getParameter("ExDate"));
		System.out.println("<<<<<<__________>>>>>>>>");
		populateDTO(bean, request);
		log.debug("populateBean method of TimeTable Ctl End");
		return bean;
	}
    /**
     * Contains Display logics
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("do Get method of TimeTable Ctl Started");
	//System.out.println("Timetable ctl .do get started........>>>>>");

//		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
	
		TimetableModel model = new TimetableModel();
		TimetableBean bean = null;
		if (id > 0) {
			try {
				bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
			}
		}

		log.debug("do Get method of TimeTable Ctl End");
		System.out.println("Timetable ctl .do get End........>>>>>");
		ServletUtility.forward(getView(), request, response);
	}
    /**
     * Contains Submit logics
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("do post method of TimeTable Ctl start");

		List list;
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		TimetableModel model = new TimetableModel();
	
		
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) 
		{
			TimetableBean bean = (TimetableBean)populateBean(request);
			try {
				if(id>0){
					model.update(bean);
					ServletUtility.setSuccessMessage("Successfully Updated", request);

				}else{
				model.add(bean);
				ServletUtility.setSuccessMessage("Successfully Saved", request);

				} 
				ServletUtility.setBean(bean, request);
				
			}catch (ApplicationException e) {
				log.error(e);
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Time Table already Exists", request);
			}
		}
		else if (OP_CANCEL.equalsIgnoreCase(op))
		{
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;
		}
		else if ( OP_RESET.equalsIgnoreCase(op))
		{
			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;
		}
		
	
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_VIEW;
	}

}

