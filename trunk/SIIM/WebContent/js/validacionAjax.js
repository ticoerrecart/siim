
/***  Validaciones via AJAX   ***/

	function validarForm(idFormJsp,action,metodo,actionForm){
		var form = $('#'+idFormJsp).serialize(); 
		var url = '../' + action + '.do?metodo=validar&validador=' + metodo + '&form=' + actionForm + '&formJsp=' + idFormJsp;
		preValidar();
		$.post(url,form,validarFormCallBack);
	}
	
	function validarFormCallBack(xmlDoc){
	   	var nodos = xmlDoc.getElementsByTagName('error');
	    if (nodos.length==0){
    	   	var nodos = xmlDoc.getElementsByTagName('formId');
    	   	var idForm = nodos[0].firstChild.nodeValue;
	    	$('#'+idForm).submit();
	    } else {
	    	$('#errores').text("");
		    for(var i=0; i < nodos.length; i++) { 
			    $('#errores').append( '<div>* ' + nodos[i].firstChild.nodeValue + '</div>');
		    }
		 	
		 	posValidar();
	    }
	}

	function preValidar(){
		//muestro la animacion
		if ($("#cargando").length > 0){
			$("#cargando").show();
		}

		//deshabilito el boton
		if ($("#enviar").length > 0){
			$("#enviar").attr("disabled", "true"); 
		}
	}
	
	function posValidar(){
		//oculto la animacion
		if ($("#cargando").length > 0){
			$("#cargando").hide();
		}

		//habilito el boton
		if ($("#enviar").length > 0){
			$("#enviar").removeAttr('disabled'); 
		}

		//muevo el cursor hasta donde están definidos los errores
		moverAErrores();
	}

	function jumpScroll(x, y) {
   		window.scroll(x,y); // horizontal and vertical scroll targets
	}
	
	function moverAErrores(){
		 jumpScroll($('#errores').offset().left,$('#errores').offset().top);
	}
	
	function clearTagErrores(errores){
		return errores.replace(/<\/error>\n<error>/gi,",").replace(/\n/gi,"").replace(/<error>/gi,"").replace(/<\/error>/gi,"").split(",");
	}
	
	function mostrarErroresOSubmitir(errores,metodoSubmit){
		$('#errores').text("");
		err =  clearTagErrores(errores)
		if (err[0] != "" ){
			 for(var i=0; i < err.length; i++) { 
				    $('#errores').append( '<div>* ' + err[i] + '</div>');
			    }
			 moverAErrores();
		} else {
			eval(metodoSubmit);
		}	 
	}
	
	function clearMensajesErrores(){
		$('#errores').text("");
	}
	
	