		$.mask.rules =  {
		'z': /[a-z]/,
		'Z': /[A-Z]/,
		'a': /[a-zA-Z]/,
		'*': /[0-9a-zA-Z]/,
		'@': /[0-9a-zA-ZçÇáàãéèíìóòõúùü]/,
		'#': /[*0-9]/,		
		'&': /[*0-9a-zA-Z]/
		};		
		
		$.mask.masks.partido= {mask: '999', autoTab: false};
		$.mask.masks.partida= {mask: '999999', autoTab: false};								
		$.mask.masks.numplano= {mask: '9999999', autoTab: false};				
		$.mask.masks.anio= {mask: '9999', autoTab: false};				
		$().ready(function (){
			//$('input:text').setMask();
			$('input:text:visible').setMask();			
		});