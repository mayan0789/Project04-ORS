<%@page import="in.co.rays.ctl.UserRegistrationCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<html>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'dd/mm/yy',
			changeMonth : true,
			changeYear : true,
			yearRange : "1980:2002",
		//maxDate:'0',
		// minDate:0
		//yearRange : "-40:-18"
		});
	});
</script>

<body>
    <form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">

        <%@ include file="Header.jsp"%>
        <script type="text/javascript" src="./js/calendar.js"></script>
        <jsp:useBean id="bean" class="in.co.rays.bean.UserBean"
            scope="request"></jsp:useBean>

        <center>
            <h1>User Registration</h1>

            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage("property", request)%>
                </font>
            </H2>

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDateTime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDateTime())%>">
            

            <table>

                <tr>
                    <th>First Name*</th>
                    <td><input type="text" name="firstName"
                        value="<%=DataUtility.getStringData(bean.getFirst_Name())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("First_Name", request)%></font></td>
                </tr>
                <tr>
                    <th>Last Name*</th>
                    <td><input type="text" name="lastName"
                        value="<%=DataUtility.getStringData(bean.getLast_Name())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("Last_Name", request)%></font></td>
                </tr>
                <tr>
                    <th>LoginId*</th>
                    <td><input type="text" name="login"
                        placeholder="Must be Email ID"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
                <tr>
                    <th>Password*</th>
                    <td><input type="password" name="password"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr>
<!--                 <tr> -->
<!--                     <th>Confirm Password*</th> -->
<!--                     <td><input type="password" name="confirmPassword" -->
<%--                         value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"><font --%>
<%--                         color="red"> <%=ServletUtility --%>
<%--                     .getErrorMessage("confirmPassword", request)%></font></td> --%>
<!--                 </tr> -->
                <tr>
                    <th>Gender</th>
                    <td>
                        <%
                            HashMap map = new HashMap();
                            map.put("M", "Male");
                            map.put("F", "Female");

                            String htmlList = HTMLUtility.getList("gender", bean.getGender(),
                                    map);
                        %> <%=htmlList%>

                    </td>
                </tr>

                <tr>
                    <th>Date Of Birth</th>
                    <td><input type="text" name="dob" readonly="readonly" id="datepicker"
                        value="<%=DataUtility.getDateString(bean.getDOB())%>"> 
                        
                    </a><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <td colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                        &nbsp; <input type="submit" name="operation" value="<%=UserRegistrationCtl.OP_SIGN_UP %>">
                    </td>
                </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>
