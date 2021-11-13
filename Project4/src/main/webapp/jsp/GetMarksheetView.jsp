<%@page import="in.co.rays.ctl.*"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<html>
<head> 
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> Get Marksheet </title></head>

<body>
    <%@ include file="Header.jsp"%>

    <jsp:useBean id="bean" class="in.co.rays.bean.MarksheetBean"
        scope="request"></jsp:useBean>

    <center>
        <h1>Get Marksheet</h1>

      <h2>  <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
        </font>
</h2>
        <H2>
            <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
            </font>
        </H2>

        <form action="<%=ORSView.GET_MARKSHEET_CTL%>" method="post">

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <table>
                <tr><th align ="right">RollNo <font color="red">*</font>:</th></label>&emsp;
                <td><input type="text" name="rollNo" placeholder="Enter Roll No. here"
                    value="<%=ServletUtility.getParameter("rollNo", request)%>">&emsp;
                    
                    </td><td style="position: fixed"> <font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%></font></td>
                     <br>
              <tr><th></th><td>  <input type="submit" name="operation" value="<%=GetMarksheetCtl.OP_GO%>">
                                <input type="submit" name="operation" value="<%=GetMarksheetCtl.OP_RESET%>">
                </td></tr>
                </table>
                <br>
              <br> 
<br>
                <%
                    if (bean.getRollNo() != null && bean.getRollNo().trim().length() > 0 && bean.getName() != null) {
                %>

            	
				<table border="1" width="35%" cellpadding="5" cellspacing="0">
				  <tr align="center">
				  	<td><h2>Rays Technologies</h2></td>
				  </tr></table>
				  
				 <table border="1" width="35%" cellpadding="5" cellspacing="0" >
				 	<tr>
				 		<th align="center"> Name</th>
				 		<td align="center"> <%=DataUtility.getStringData(bean.getName()) %></td>
				 		<th align="center"> Roll No</th>
				 		<td align="center"> <%=DataUtility.getStringData(bean.getRollNo()) %> </td>
				 						 
					</tr>
				 	<tr>
				 		<th align="center"> Status</th>
				 		<td align="center">Regular</td>
				 		<th align="center"> Course</th>
				 		<td align="center">BE</td>				 
					</tr>			
				 </table> 
	<%
	int phy =DataUtility.getInt(DataUtility.getStringData(bean.getPhysics()));
	int chem =DataUtility.getInt(DataUtility.getStringData(bean.getChemistry()));
	int math =DataUtility.getInt(DataUtility.getStringData(bean.getMaths()));
	int total = (phy+chem+math);
	float perc = total/3;
	%>
					  
			<table border="1" width="35%" cellpadding="5" cellspacing="0">
				<tr>
					<th align="center" style="width: 25%">Subject</th>
					<th align="center" style="width: 25%">Maximum Marks</th>
					<th align="center" style="width: 25%">Minimum Marks</th>
					<th align="center" style="width: 25%">Marks Obtained</th>
					<th align="center" style="width: 25%">Grade</th>
				</tr>
				<tr>
				<td align="center">Physics</td>
				<td align="center">100</td>
				<td align="center">33</td>
				<td align="center"><%=phy %>
				
				<%if(phy<33){%>
					<span style="color: red">*</span>
				<% } %>	</td>
				
				<td align="center">
				
				<%
					if(phy <=100 && phy >85){ %> A+
				<%} else if(phy<=85 && phy > 75 ) {%>B+
				<%} else if(phy<=75 && phy > 65 ) {%>B
				<%} else if(phy<=65 && phy > 55 ){ %>C+
				<%} else if(phy<=55 && phy >=33  ){ %>C
				
				<%} else if(phy< 33 && phy >= 0 ) {%><span style="color: red"> Fail</span>
				<% } %>
				</td>
				</tr>
			
			<tr>
				<td align="center">Chemistry</td>
				<td align="center">100</td>
				<td align="center">33</td>
				<td align="center"><%=chem %>
				
				<%if(chem<33){%>
					<span style="color: red">*</span>
				<% } %>	</td>
				
				<td align="center">
				
				<%
					if(chem <=100 && chem >85){ %> A+
				<%} else if(chem<=85 && chem > 75 ) {%>B+
				<%} else if(chem <=75 && chem > 65 ) {%>B
				<%} else if(chem <=65 && chem > 55 ){ %>C+
				<%} else if(chem <=55 && chem >=33  ){ %>C
				
				<%} else if(chem < 33 && chem >= 0 ) {%><span style="color: red"> Fail</span>
				<% } %>
				</td>
				</tr>
					
			<tr>
				<td align="center"> Maths</td>
				<td align="center">100</td>
				<td align="center">33</td>
				<td align="center"><%=math %>
				
				<%if(math<33){%>
					<span style="color: red">*</span>
				<% } %>	</td>
				
				<td align="center">
				
				<%
					if(math <=100 && math >85){ %> A+
				<%} else if(math <=85 && math > 75 ) {%>B+
				<%} else if( math <=75 && math > 65 ) {%>B
				<%} else if(math <=65 && math > 55 ){ %>C+
				<%} else if(math <=55 && math >=33  ){ %>C
				
				<%} else if(math < 33 && math >= 0 ) {%><span style="color: red"> Fail</span>
				<% } %>
				</td>
				</tr>
			</table>	  
			
			<table border="1" width="35%">
			<tr>
				<th>Total</th>
				<th>Percentage</th>
				<th>Division</th>
				<th>Result</th>
			</tr>
			<tr>
				<th><%=total %>
				<% if(total<99 || phy<33|| chem<33|| 	math<33){ %>
				<span style="color: red">*</span>
				<% } %>
				</th>
			
				<th><%=perc %> %</th>
				<th>
				<% if(perc <100 && perc >= 60){ %>1<sup>st</sup>
				<%} else if(perc <60 && perc >=45){ %>2<sup>nd</sup>
				<%} else if(perc <45 && perc >=30){ %>3<sup>rd</sup>
				<%} else if(perc <30 && perc >=0){ %> Fail
				<%} %>
				</th>
				
				<th>
				<%
				if(phy >=33 && chem>=33 && math >= 33) { %>
				<span style="color: green"> Pass</span>
				<%}else{ %>
				<span style="color: red"> Fail</span>
				<% } %>
				
				</th>
				</tr>            </table>
            <%
                }
            %>
            <br><br><br><br><br><br><br><br>
        </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>
