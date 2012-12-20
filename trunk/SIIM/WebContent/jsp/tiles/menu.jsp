<%@page contentType="text/html; charset=ISO-8859-1"
	pageEncoding='ISO-8859-1'%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<head>
<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/jquery-1.7.2.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/JSCookMenu/JSCookMenu.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/JSCookMenu/effect.js'/>"></script>
<!-- <link rel="stylesheet"	href="<html:rewrite page='/js/JSCookMenu/ThemeGray/theme.css'/>"	type="text/css">
<script type="text/javascript"	src="<html:rewrite page='/js/JSCookMenu/ThemeGray/theme.js'/>"></script> 

<link rel="stylesheet"	href="<html:rewrite page='/js/JSCookMenu/ThemeOffice2003/theme.css'/>"	type="text/css">
<script type="text/javascript"	src="<html:rewrite page='/js/JSCookMenu/ThemeOffice2003/theme.js'/>"></script>-->

<link rel="stylesheet"
	href="<html:rewrite page='/js/JSCookMenu/ThemeSIC/theme.css'/>"
	type="text/css">
<script type="text/javascript"
	src="<html:rewrite page='/js/JSCookMenu/ThemeSIC/theme.js'/>"></script>

</head>
<body>


<script type="text/javascript" charset="ISO-8859-1">

	var url = '../../menu.do?metodo=getMenu&param=1';
	$().ready (function(){
			$.post(url,null,armarMenu);
		}
	)


	function armarMenu(datos){
		var strings = datos.split('@');
		$('#usuario').html(strings[1]);
		$('#roles').html(strings[2]);
		$('#version').html(strings[3]);

		var prop = cmClone (cmThemeSIC);
		prop.effect = new CMSlidingEffect(6);
		//prop.effect = new CMFadingEffect (50, 30);
		
		//cmDraw('toolbar', eval(strings[0]) , 'hbr', cmThemeOffice2003);		
		//cmDraw('toolbar', eval(strings[0]) , 'hbr', cmThemeGray);		
		//cmDraw('toolbar', eval(strings[0]) , 'hbr', cmThemeSIC);
		cmDraw('toolbar', eval(strings[0]) , 'hbr', prop);
	
	}

	function irA(url) {
		var appPath = 'http://' + window.location.host + getContextRoot(); 
		valor = url.substring(0,4); 
		if (valor=='java'){
		  eval(url);
		  return;
		}
		if (valor != 'http' ) {
			pref=appPath;
		} else {
			pref='' ;
		} 
		location.href=pref+url;
	}

	function getContextRoot(){
		var path = window.location.pathname; 
		var firstSlashPosition = 0; 
		var secondSlashPosition = path.indexOf('/',1); 
		var contextRoot = path.slice(firstSlashPosition,secondSlashPosition); 
		return contextRoot;
	}
	
	
</script>

<table height="17" width="100%" bgcolor="#C2DAF9">
	<tr>
		<td>
		<div id="toolbar"></div>
		</td>
	</tr>
</table>
</body>
</html>
