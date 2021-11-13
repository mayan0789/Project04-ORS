<%@page import="in.co.rays.ctl.CollegeCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<html>
<head>
<head> 
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> Add College</title></head>

<body>
	<form action="<%=ORSView.COLLEGE_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.bean.CollegeBean"
			scope="request"></jsp:useBean>

		<center>
			<%
				if (bean.getId() > 0) {
			%>
			<h1>Update College</h1>
			<%
				} else {
			%>
			<h1>Add College</h1>
			<%
				}
			%>


			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
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
					<th align="left">Name<font color="red">*</font></th>
					<td><input type="text" name="name" placeholder="Enter Name Here"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td> <td style="position : fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Address<font color="red">*</font></th>
					<td><input type="text" name="address" placeholder="Enter Address Here"
						value="<%=DataUtility.getStringData(bean.getAddress())%>"></td> <td style="position : fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font></td>
				</tr>
				<tr>
					<th align="left">State<font color="red">*</font></th>
					<td><input type="text" name="state" placeholder="Enter State Here"
						value="<%=DataUtility.getStringData(bean.getState())%>"></td> <td style="position : fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
				</tr>
				<tr>
					<th align="left">City<font color="red">*</font></th>
					<td><input type="text" name="city" placeholder="Enter City Here"
						value="<%=DataUtility.getStringData(bean.getCity())%>"></td> <td style="position : fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Mobile No.<font color="red">*</font></th>
					<td><input type="text" name="phoneNo" placeholder="Enter Phone No. Here" maxlength="10"
						value="<%=DataUtility.getStringData(bean.getPhoneNo())%>"></td> <td style="position : fixed;"><font
						color="red"> <%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
				</tr>


				<tr>
					<th></th>
					<%
	if(bean.getId() > 0){
	%>
	<td>
	<input type="submit" name ="operation" value="<%=CollegeCtl.OP_UPDATE %>">
	<input type="submit" name ="operation" value="<%=CollegeCtl.OP_CANCEL %>">
	</td>
	<%}else{ %>
	<td>
	<input type="submit" name ="operation" value="<%=CollegeCtl.OP_SAVE %>">
		<input type="submit" name ="operation" value="<%=CollegeCtl.OP_RESET %>">
	</td>
	<%} %>
				</tr>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>
