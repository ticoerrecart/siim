<%@page contentType="text/html; charset=ISO-8859-1"
	pageEncoding='ISO-8859-1'%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<title><tiles:getAsString name="title" /></title>
<html:base />
<link rel="stylesheet" href="<html:rewrite page='/css/main.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<html:rewrite page='/css/jqueryslidemenu.css'/>" type="text/css">
</head>

<body>



<TABLE cellSpacing=0 cellPadding=0 border=0 height="100%" width="100%"
	align="center">
	<TR>
		<TD><tiles:insert attribute="header" /></TD>
	</TR>
	<TR>
		<TD><tiles:insert attribute="menu" /></TD>
	</TR>
	<TR>
		<TD height="80%"><tiles:insert attribute="body" /></TD>
	</TR>
	<TR>
		<TD><tiles:insert attribute="footer" /></TD>
	</TR>
</table>


</body>
</html>


