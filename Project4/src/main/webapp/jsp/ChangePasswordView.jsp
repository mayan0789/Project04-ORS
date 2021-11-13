<%@page import="in.co.rays.ctl.ChangePasswordCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title>Change Password</title></head>

<body>
    <form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">
        
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.rays.bean.UserBean"
            scope="request"></jsp:useBean>

        <center>
            <h1>Change Password</h1>


            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>
 <H3>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H3>
            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDateTime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDateTime())%>">

            <table>



                <tr>
                    <th align="left">Old Password <span style="color: red">*</span> </th>
                    <td><input type="password" name="oldPassword" size=30 placeholder="Enter Old Password Here"
                        value=<%=DataUtility
                    .getString(request.getParameter("oldPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("oldPassword")))%>>
                                      </td> <td style="position : fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font></td>
                </tr>

                <tr>
                    <th align="left">New Password <span style="color: red">*</span> </th>
                    <td><input type="password" name="newPassword" size=30 placeholder="Enter New Password Here"
                        value=<%=DataUtility
                    .getString(request.getParameter("newPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("newPassword")))%>> 
                                     </td> <td style="position : fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font></td>
                </tr>

                <tr>
                    <th align="left">Confirm Password <span style="color: red">*</span> </th>
                    <td><input type="password" name="confirmPassword" size = 30 placeholder="Confirm New Password Here"
                        value=<%=DataUtility.getString(request
                    .getParameter("confirmPassword") == null ? "" : DataUtility
                    .getString(request.getParameter("confirmPassword")))%>>
                      </td> <td style="position : fixed;"><font
                        color="red"> <%=ServletUtility
                    .getErrorMessage("confirmPassword", request)%></font></td>
                </tr>

                <tr>
                    <th></th>
                    <td colspan="2"><input type="submit" name="operation" 
                        value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE %>"> <input type="submit"
                        name="operation" value="<%= ChangePasswordCtl.OP_SAVE%>"> &nbsp;
                        <input type="submit"
                        name="operation" value="<%= ChangePasswordCtl.OP_RESET%>"></td>
                </tr>

            </table>
           
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>