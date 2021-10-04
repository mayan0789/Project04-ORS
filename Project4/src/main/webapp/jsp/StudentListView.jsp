<%@page import="in.co.rays.ctl.StudentListCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.bean.StudentBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<body>
    <%@include file="Header.jsp"%>
    <center>
        <h1>Student List</h1>

        <form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post">
            <table width="100%">
                <tr>
                    <td align="center"><label> FirstName :</label> <input
                        type="text" name="firstName"
                        value="<%=ServletUtility.getParameter("firstName", request)%>">
                        <label>LastName :</label> <input type="text" name="lastName"
                        value="<%=ServletUtility.getParameter("lastName", request)%>">
                        <label>Email_Id:</label> <input type="text" name="email"
                        value="<%=ServletUtility.getParameter("email", request)%>">
                        <input type="submit" name="operation" value="<%=StudentListCtl.OP_SEARCH %>"></td>
                </tr>
            </table>
            <br>
            <table border="1" width="100%">
                <tr>
                    <th>S.No.</th>
                    <th>ID.</th>
                    <th>College.</th>
                    <th>First Name.</th>
                    <th>Last Name.</th>
                    <th>Date Of Birth.</th>
                    <th>Mobil No.</th>
                    <th>Email ID.</th>
                    <th>Edit</th>
                </tr>
                <tr>
                    <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
                </tr>
                <%int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<StudentBean> it = list.iterator();

                    while (it.hasNext()) {

                        StudentBean bean = it.next();   %>
                <tr>
                    <td><%=index++%></td>
                    <td><%=bean.getId()%></td>
                    <td><%=bean.getCollege_Name()%></td>
                    <td><%=bean.getFirst_Name()%></td>
                    <td><%=bean.getLast_Name()%></td>
                    <td><%=bean.getDate_of_Birth()%></td>
                    <td><%=bean.getMobile_No()%></td>
                    <td><%=bean.getEmail()%></td>
                    <td><a href="StudentCtl?id=<%=bean.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                    <td><input type="submit" name="operation"
                        value="<%=StudentListCtl.OP_PREVIOUS%>"></td>
                    <td align="right"><input type="submit" name="operation"
                        value="<%=StudentListCtl.OP_NEXT%>"></td>
                </tr>
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"><input
                type="hidden" name="pageSize" value="<%=pageSize%>">


        </form>
    </center>
</body>
</html>
