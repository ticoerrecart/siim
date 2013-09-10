<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<html>
<head>
<link rel="stylesheet" href="<html:rewrite page='/css/main.css'/>" type="text/css">
<link rel="stylesheet" href="<html:rewrite page='/css/jqueryslidemenu.css'/>" type="text/css">
</head>

<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/jquery-1.7.2.min.js'/>"></script>
<script>
	function darFoco(){
		if($('#idUsuario').val()==''){
			$('#idUsuario').focus();
		}else{
			if($('#password').val()==''){
				$('#password').focus();
			}else{
				$('#idAceptar').focus();
			}
		}
	}
</script>
</head>

<body onload="darFoco();">

<html:form action="login">
	<html:hidden property="metodo" value="login" />
	
	<table align="center" border="0" width="100%" height="100%">
		<tr>
			<td height="85%">
				<div id="errores" class="rojoAdvertencia">${error}</div>
				<br>
				<table class="cuadrado" border="0" width="40%" align="center">
					<tr>
						<td colspan="4" height="26em" align="center" class="azulAjustado">
							<CENTER>
								<b><bean:message key='SIIM.label.Bienvenido'/></b>
							</CENTER>
						</td> 
					</tr>  
					<tr>
						<td height="15" colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td height="32" width="25%">
							<div align="right">
								<img src="<html:rewrite page='/imagenes/personitas.jpg'/>" width="33" height="32" alt="Roles">
							</div>
						</td>
						<td width="25%"><b><bean:message key='SIIM.label.Usuario'/></b></td>
						<td align="left">
							<html:text styleId="idUsuario" property="usuario" styleClass="botonerab" size="15" value=""/>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="32">
							<div align="right">
								<img src="<html:rewrite page='/imagenes/llaves.gif'/>" width="33" height="32" alt="Roles">
							</div>
						</td>
						<td><b><bean:message key='SIIM.label.Contrasenia'/></b></td>
						<td align="left">
							<html:password property="password"	styleId="password" styleClass="botonerab" size="15" value=""></html:password>
						</td>
						<td>&nbsp;</td>
					</tr>
					
					<tr>
						<td height="15" colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="4" height="29" align="center">
							<html:submit styleId="idAceptar" styleClass="botonerab" value="Ingresar"></html:submit>
						</td>
					</tr>
					<tr>
						<td height="14" colspan="4">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="10%"></td>	
		</tr>
		<tr>
			<td>
				<table border="0" width="100%" align="center">
					<tr>
						<td>
							<DIV class="footer"><img src="./imagenes/footerSIIM.jpg"></DIV>
						</td>
					</tr>	 
				</table>
			</td>	
		</tr>	
	</table>		 
</html:form>

</body>
</html>
