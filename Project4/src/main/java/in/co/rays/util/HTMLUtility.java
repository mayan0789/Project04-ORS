package in.co.rays.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import in.co.rays.bean.DropdownlistBean;

/**
 * @author Mayank
 *
 */
public class HTMLUtility {
	/**
	 * Create HTML SELECT list from MAP paramters values v
	 * 
	 */

	public static String getList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer(
				"<select style='width: 200px;  height: 23px;' class='form-control' name='" + name + "'>");

		Set<String> keys = map.keySet();
		String val = null;
		boolean select = true;
		if (select) {

			sb.append(
					"<option style='width: 500px;  height: 30px;' selected value=''>--------------Select--------------------</option>");
		}

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + val + "'>" + val + "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}

		sb.append("</select>");

		return sb.toString();
	}

	/**
	 * Create HTML SELECT List from List parameter
	 * 
	 */
	public static String getList(String name, String selectedVal, List list) {

		Collections.sort(list);

		StringBuffer sb = new StringBuffer(
				"<select style='width: 200px;  height: 23px;' class='form-control' name='" + name + "'>");

		boolean select = true;
		if (select) {

			sb.append(
					"<option style='width: 203px;  height: 30px;' selected value=''>--------------Select--------------------`</option>");
		}


		List<DropdownlistBean> dd = (List<DropdownlistBean>) list;


		String key = null;
		String val = null;

		for (DropdownlistBean obj : dd) {
			key = obj.getkey();
			val = obj.getvalue();

			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");

		return sb.toString();
	}

	/**
	 * Returns Error Message with HTML tag and CSS
	 * 
	 */
	public static String getErrorMessage(HttpServletRequest request) {
		String msg = ServletUtility.getErrorMessage("Error", request);
		if (!DataValidator.isNull(msg)) {
			msg = "<p class='st-error-header'>" + msg + "</p>";
		}
		return msg;
	}

	/**
	 * Returns Success Message with HTML tag and CSS
	 * 
	 */

	public static String getSuccessMessage(HttpServletRequest request) {
		String msg = ServletUtility.getSuccessMessage(request);
		if (!DataValidator.isNull(msg)) {
			msg = "<p class='st-success-header'>" + msg + "</p>";
		}
		return msg;
	}

}