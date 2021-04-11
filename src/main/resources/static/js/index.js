function onClickCrearDato() {
	$("#tipo-operacion").text("Operacion de insercion")
	$("#fragment-consulta-insert").css("display", "inline-block")
	$("#fragment-consulta-update").css("display", "none")
	$("#fragment-consulta-delete").css("display", "none")
	$("#fragment-consulta-select").css("display", "none")
}

function onClickActualizarDato() {
	$("#tipo-operacion").text("Operacion de actualizacion")
	$("#fragment-consulta-update").css("display", "inline-block")
	$("#fragment-consulta-insert").css("display", "none")
	$("#fragment-consulta-delete").css("display", "none")
	$("#fragment-consulta-select").css("display", "none")
}

function onClickBorrarDato() {
	$("#tipo-operacion").text("Operacion de borrado")
	$("#fragment-consulta-delete").css("display", "inline-block")
	$("#fragment-consulta-update").css("display", "none")
	$("#fragment-consulta-insert").css("display", "none")
	$("#fragment-consulta-select").css("display", "none")
}

function onClickConsultarDato() {
	$("#tipo-operacion").text("Operacion de selecion")
	$("#fragment-consulta-select").css("display", "inline-block")
	$("#fragment-consulta-update").css("display", "none")
	$("#fragment-consulta-delete").css("display", "none")
	$("#fragment-consulta-insert").css("display", "none")
}