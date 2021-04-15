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
	}

};

/*** Eventos de RowSelect para cada tabla */
var RowSelect = {
	idioma: function(event, data) {
		document.getElementById('messages').show([{ severity: 'info', summary: 'Row Selected', detail: data.id + ' ' + data.nombre }]);
	},
	area: function(event, data) {

	},
	categoria: function(event, data) {

	},
	pregunta: function(event, data) {

	},
	respuesta: function(event, data) {

	}
}

function test() {
	window.alert("lol")
	return $('<button type="button"></button>').puibutton({
		icon: 'fa-search'
	});
}

$(document).ready(function() {


})
