<!-- tilesHBF.jsp
  Define una template de tipo header-body y footer
  aca se incluye la hoja de estilo que se usa en todas
  las páginas. 
-->

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<html>
<head>
<title><tiles:getAsString name="title" /></title>

<link rel="stylesheet" href="<html:rewrite page='/css/dsisic.css'/>"
	type="text/css">

<style type="text/css">
#leftcolumn {
	float: left;
	width: 200px; /*Width of left column*/
	margin-left: -840px; /*Set left margin to -(MainContainerWidth)*/
	background: #C8FC98;
}
</style>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<TABLE cellSpacing=0 cellPadding=0 border=0 height="100%" width="100%"
	align="center">
	<TR>
		<TD><tiles:insert attribute="header" /></TD>
	</TR>
	<TR>
		<TD><tiles:insert attribute="menu" /></TD>
	</TR>
	<TR>
		<TD height="70%"><tiles:insert attribute="body" /></TD>
	</TR>
	<TR>
		<TD height="10%"><tiles:insert attribute="footer" /></TD>
	</TR>
</table>
</body>
</html>