package in.co.rays.ctl;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

/**
 * @author Mayank
 *
 */
@WebServlet(name="CourseCtl",urlPatterns={"/ctl/CourseCtl"})
public class CourseCtl extends BaseCtl {


	
	private static final long serialVersionUID = 1L;
		private static Logger log = Logger.getLogger(CourseCtl.class);

		protected boolean validate(HttpServletRequest request) {
			log.debug("CourseCtl validate started");
			boolean pass = true;

			if (DataValidator.isNull(request.getParameter("name"))) {
				request.setAttribute("name", PropertyReader.getValue("error.require", "Course Name"));
				 pass = false ;
			}else if (!DataValidator.isName(request.getParameter("name"))) {
				request.setAttribute("name", PropertyReader.getValue("error.name", "Invalid Course"));
				 pass = false ;
			}
			if (DataValidator.isNull(request.getParameter("duration"))) {
				request.setAttribute("duration", PropertyReader.getValue("error.require", "Duration"));
				pass = false ;
			}
			if (DataValidator.isNull(request.getParameter("description"))) {
				request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
				pass = false ;
			}

			log.debug("CourseCtl validate End");
			return pass;
		}

		protected BaseBean populateBean(HttpServletRequest request){
			log.debug("CourseCtl PopulatedBean started");
			CourseBean bean = new CourseBean();
			
			bean.setId(DataUtility.getLong(request.getParameter("id")));
			bean.setCourse_Name(DataUtility.getString(request.getParameter("name")));
			System.out.println("popppp    "+request.getParameter("duration"));
			bean.setDuration(DataUtility.getString(request.getParameter("duration")));
			bean.setDescription(DataUtility.getString(request.getParameter("description")));
		
			populateDTO(bean, request);
			log.debug("CourseCtl PopulatedBean End");
			return bean;
		}
		
	    /**
	     * Contains Display logics
	     */

		
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			log.debug("Do get method od courseCtl started");
			String op = DataUtility.getString(request.getParameter("operation"));
			
			// get Model
			CourseModel model = new CourseModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			
			if(id>0){
				CourseBean bean;
				try{
				bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
				
				}catch(ApplicationException e){
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				}
			}
			ServletUtility.forward(getView(), request, response);
		}
	    /**
	     * Contains Submit logics
	     */

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			log.debug("Do Post method of CourseCtl started ");
			String op = DataUtility.getString(request.getParameter("operation"));
			
			// Get Model
			CourseModel model = new CourseModel();
			long id = DataUtility.getLong(request.getParameter("id"));
		
			if(OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)){
				CourseBean bean =(CourseBean) populateBean(request);
			try{
				if(id>0){		
						model.update(bean);
				}else{
					 long pk = model.add(bean);
				//		bean.setId(pk);
				}
					ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Course is Successfully saved", request);
			
			}catch(ApplicationException e ){
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Course Name Already Exist", request);
				
			}		
			}/*else if (OP_DELETE.equalsIgnoreCase(op)) {
				CourseBean bean =(CourseBean) populateBean(request);
				try {
					model.delete(bean);;
					ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
					return;
				} catch (ApplicationException e) {
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return ;
				}
			}*/
			else if (OP_CANCEL.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
				return;
			}
			else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
				return;
			}
			ServletUtility.forward(getView(), request, response);
			log.debug("Do Post method CourseCtl Ended");
		
		}

		@Override
		protected String getView() {
			return ORSView.COURSE_VIEW;
		}


}
