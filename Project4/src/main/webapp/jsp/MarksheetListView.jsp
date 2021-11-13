<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.model.MarksheetModel"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.ctl.MarksheetListCtl"%>
<%@page import="in.co.rays.ctl.BaseCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.bean.MarksheetBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title>Marksheet List</title>
</head>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>

<body>
	<%@include file="Header.jsp"%>
	<center>

		<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="POST">

			<jsp:useBean id="bean" class="in.co.rays.bean.MarksheetBean"
				scope="request"></jsp:useBean>

			<div>
				<h1>Marksheet List</h1>

				<h2>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h2>
				<h2>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h2>
			</div>

			<%
				List roll = (List) request.getAttribute("roll");
				System.out.println(roll.isEmpty());
			%>
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<MarksheetBean> it = list.iterator();

				if (list.size() != 0) {
			%>

			<table width="100%">
				<tr>
					<td align="center"><label> Name :</label> <input type="text"
						placeholder="Enter a Name" name="name"
						value="<%=ServletUtility.getParameter("name", request)%>">
						&emsp; <label>RollNo :</label> <%=HTMLUtility.getList("rollNo", String.valueOf(bean.getId()), roll)%>&emsp; <input type="submit"
						name="operation" value="<%=MarksheetListCtl.OP_SEARCH%>">
						&nbsp; <input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>
			<table border="1" width="100%" cellspacing="0" cellpadding="10">
				<tr>
					<th><input type="checkbox" id="select_all" name="select">
						Select All</th>
					<th>S.No.</th>
					<th>RollNo</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
					<th>Total</th>
					<th>Result</th>
					<th>Edit</th>
				</tr>
				<%
					while (it.hasNext()) {
							bean = it.next();

							int phy = DataUtility.getInt(DataUtility.getStringData(bean.getPhysics()));
							int chem = DataUtility.getInt(DataUtility.getStringData(bean.getChemistry()));
							int math = DataUtility.getInt(DataUtility.getStringData(bean.getMaths()));
							int total = (phy + chem + math);
				%>

				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>">
					<td><%=index++%></td>
					<td><%=bean.getRollNo()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getPhysics()%></td>
					<td><%=bean.getChemistry()%></td>
					<td><%=bean.getMaths()%></td>
					<td><%=total%></td>
					<td>
						<%
							if (phy >= 33 && chem >= 33 && math >= 33) {
						%> <span style="color: green"> Pass</span> <%
 	} else {
 %> <span style="color: red"> Fail</span> <%
 	}
 %>
					</td>
					<td><a href="MarksheetCtl?id=<%=bean.getId()%>"> Edit</a> <%
 	}
 %>
			</table>
			<table width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_NEW%>"></td>


					<%
						MarksheetModel model = new MarksheetModel();
							if (list.size() < pageSize || model.nextPK() - 1 == bean.getId()) {
					%><td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=MarksheetListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_NEXT%>"></td>
					<%
						}
					%>
				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=MarksheetListCtl.OP_BACK%>"></td>
			<%
				}
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
	</center>
	<%@include file="Footer.jsp"%>
</body>
</html>
