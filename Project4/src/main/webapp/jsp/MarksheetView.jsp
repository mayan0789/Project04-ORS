<%@page import="in.co.rays.ctl.MarksheetCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title>Marksheet View</title>
</head>

<body>

	<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.bean.MarksheetBean"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("Studentlist");
		%>

		<center>
			<%
				if (bean != null && bean.getId() > 0) {
			%>
			<h1>Update Marksheet</h1>
			<%
				} else {
			%>
			<h1>Add Marksheet</h1>
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
					<th align="left">Roll No. <span style="color: red">*</span></th>
					<td><input type="text" name="rollNo" size="24"
						placeholder="Enter Roll No."
						value="<%=DataUtility.getStringData(bean.getRollNo())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("rollNo", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Name <span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("StudentId", String.valueOf(bean.getStudentId()), l)%></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("StudentId", request)%></font></td>

				</tr>
				<tr>
					<th align="left">Physics <span style="color: red">*</span></th>
					<td><input type="text" name="physics" size=24
						placeholder="Enter Physics No."
						value="<%=DataUtility.getStringData(bean.getPhysics()).equals("0") ? ""
					: DataUtility.getStringData(bean.getPhysics())%>"></td>

					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("physics", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Chemistry <span style="color: red">*</span></th>
					<td><input type="text" name="chemistry" size=24
						placeholder="Enter Chemistry No."
						value="<%=DataUtility.getStringData(bean.getChemistry()).equals("0") ? ""
					: DataUtility.getStringData(bean.getChemistry())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("chemistry", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Maths <span style="color: red">*</span></th>
					<td><input type="text" name="maths" size=24
						placeholder="Enter Maths No."
						value="<%=DataUtility.getStringData(bean.getMaths()).equals("0") ? ""
					: DataUtility.getStringData(bean.getMaths())%>"></td>

					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("maths", request)%></font></td>

				</tr>
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=MarksheetCtl.OP_UPDATE%>"> <input type="submit"
						name="operation" value="<%=MarksheetCtl.OP_CANCEL%>"></td>
					<%
						} else {
					%>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=MarksheetCtl.OP_SAVE%>"> <input type="submit"
						name="operation" value="<%=MarksheetCtl.OP_RESET%>"></td>
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
