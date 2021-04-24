/*** Contiene las llamadas ajax de cada datatable */
var DataTables = {

	idioma: function(callback) {
		$.ajax({
			type: "GET",
			url: '/rest_idioma/idioma',
			dataType: "json",
			context: this,
			success: function(response) {
				callback.call(this, response);
			}
		});
	},

	area: function(callback) {
		$.ajax({
			type: "GET",
			url: '/rest_area/area',
			dataType: "json",
			context: this,
			success: function(response) {
				if (response != null) {
					for (let i = 0; i < response.length; i++) {
						response[i].idioma = response[i].idioma.id
					}
				}
				callback.call(this, response);
			}
		});
	},

	categoria: function(callback) {
		$.ajax({
			type: "GET",
			url: '/rest_categoria/categoria',
			dataType: "json",
			context: this,
			success: function(response) {
				if (response != null) {
					for (let i = 0; i < response.length; i++) {
						response[i].area = response[i].area.id
					}
				}
				callback.call(this, response);
			}
		});
	},

	pregunta: function(callback) {
		$.ajax({
			type: "GET",
			url: '/rest_pregunta/pregunta',
			dataType: "json",
			context: this,
			success: function(response) {
				if (response != null) {
					for (let i = 0; i < response.length; i++) {
						response[i].categoria = response[i].categoria.id
					}
				}
				callback.call(this, response);
			}
		});
	},

	respuesta: function(callback) {
		$.ajax({
			type: "GET",
			url: '/rest_respuesta/respuesta',
			dataType: "json",
			context: this,
			success: function(response) {
				callback.call(this, response);
			}
		});
	},

	preguntarespuesta: function(callback) {
		$.ajax({
			type: "GET",
			url: '/rest_preguntarespuesta/preguntarespuesta',
			dataType: "json",
			context: this,
			success: function(response) {
				if (response != null) {
					for (let i = 0; i < response.length; i++) {
						response[i].pregunta = response[i].pregunta.id
						response[i].respuesta = response[i].respuesta.id
					}
				}
				callback.call(this, response);
			}
		});
	}

};

/*** Eventos de RowSelect para cada tabla */
var RowSelect = {
	idioma: function(event, data) {
		document.getElementById('messages').show([{ severity: 'info', summary: 'Fila seleccionada', detail: "ID: " + data.id }]);
		$('#DataTable-Idioma-Dialog').puidialog('show');
	},
	area: function(event, data) {

	},
	categoria: function(event, data) {

	},
	pregunta: function(event, data) {

	},
	respuesta: function(event, data) {

	},
	preguntarespuesta: function(event, data) {

	}
}

function test() {
	window.alert("xdd")
	$('#DataTable-Idioma').puidatatable('reload');
}

$(document).ready(function() {
	$('#DataTable-Idioma-Dialog').puidialog({
		showEffect: 'fade',
		hideEffect: 'fade',
		resizable: false,
		width: 500,
		minimizable: false,
		maximizable: false,
		closeOnEscape: true,
		responsive: true,
		modal: true,
		title: " "
	});
})

