
<%@page import="in.co.rays.ctl.UserCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<html>
<head> 
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> Add User</title></head>

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
	<form action="<%=ORSView.USER_CTL%>" method="post">
		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="in.co.rays.bean.UserBean"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("roleList");
		%>

		<center>
		
		<%
				if (bean.getId() > 0) {
			%>
			<h1>Update User</h1>
			<%
				} else {
			%>
			<h1>Add User</h1>
			<%
				}
			%>
		
		

			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>



			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDateTime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDateTime())%>">


			<table>
				<tr>
					<th align="left">First Name <font color="red">*</font></th>					
					<td><input type="text" name="firstName" size="24" placeholder="Enter First Name"
						value="<%=DataUtility.getStringData(bean.getFirst_Name())%>">
						</td> <td style="position : fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Last Name <font color="red">*</font></th>
					<td><input type="text" name="lastName" size="24" placeholder="Enter Last Name"
						value="<%=DataUtility.getStringData(bean.getLast_Name())%>">
						</td> <td style="position : fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th align="left">LoginId <font color="red">*</font></th>
					<td><input type="text" name="login" size="24" placeholder="Enter Login Id"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>>
						</td> <td style="position : fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				<% if(bean.getId() > 0 && bean != null){ %>
				<tr>
                   
                    <td><input type="hidden" name="password" value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
                   <td><input type="hidden" name="confirmPassword"  value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
                   </tr>
				<% }else{ %>
				<tr>
					<th align="left">Password <font color="red">*</font></th>
					<td><input type="password" name="password" size="24" placeholder="Enter Password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>">
						</td> <td style="position : fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				                 <tr> 
				                     <th>Confirm Password <font color="red">*</font></th> 
				                     <td><input type="password" name="confirmPassword" size="24" placeholder="Confirm Your Password"
				                        value="<%=DataUtility.getStringData(bean.getPassword())%>">
				                        </td> <td style="position : fixed;"><font 
				                        color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", 
				                     request)%></font></td> 
				                 </tr> 
				<%} %>
				<tr>
					<th align="left">Gender <font color="red">*</font></th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("Male", "Male");
							map.put("Female", "Female");

							String htmlList = HTMLUtility.getList("gender", String.valueOf(bean.getGender()), map);
						%><%=htmlList%> 
						</td> <td style="position : fixed;"><font style="position: fixed;" color="red">
							<%=ServletUtility.getErrorMessage("gender", request)%></font>
					</td>
				</tr>
				<tr>
					<th align="left">Role <font color="red">*</font></th>
					<td><%=HTMLUtility.getList("roleId", String.valueOf(bean.getRoll_Id()), l)%>

						</td> <td style="position : fixed;"><font style="position: fixed;" color="red"> <%=ServletUtility.getErrorMessage("roleId", request)%></font>
					</td>
				</tr>
				<tr>
					<th align="left">Mobile No. <span style="color: red">*</span></th>

					<td><input type="text" name="mobileno" size="24" maxlength="10"
						placeholder="Enter Mobile No."
						value="<%=DataUtility.getStringData(bean.getMobile_No())%>">
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("mobileno", request)%></font></td>
				</tr>
				
				<tr>
					<th align="left">Date Of Birth <font color="red">*</font></th>
					<td><input type="text" name="dob" id="datepicker" size="24"
						placeholder="Enter Date Of Birth" readonly="readonly"
						
						value="<%=DataUtility.getDateString(bean.getDOB())%>">
						</td> <td style="position : fixed;"><font style="position: fixed;" color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>
				
				
					<th></th>
					<%
						if (bean.getId() > 0) {
					%>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=UserCtl.OP_UPDATE%>"> <input type="submit"
						name="operation" value="<%=UserCtl.OP_CANCEL%>"></td>
					<%
						} else {
					%>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=UserCtl.OP_SAVE%>">
						<input type="submit" name="operation"
						value="<%=UserCtl.OP_RESET%>"></td>
						<%
							}
						%> 
				</tr>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>
