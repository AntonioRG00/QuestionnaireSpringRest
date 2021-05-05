package es.antoniorg.myspringrest.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.antoniorg.myspringrest.model.Area;
import es.antoniorg.myspringrest.model.Categoria;
import es.antoniorg.myspringrest.model.Idioma;
import es.antoniorg.myspringrest.model.Pregunta;
import es.antoniorg.myspringrest.model.PreguntaRespuesta;
import es.antoniorg.myspringrest.model.Respuesta;
import es.antoniorg.myspringrest.model.RespuestaPorDefecto;
import es.antoniorg.myspringrest.repository.AreaRepository;
import es.antoniorg.myspringrest.repository.CategoriaRepository;
import es.antoniorg.myspringrest.repository.IdiomaRepository;
import es.antoniorg.myspringrest.repository.PreguntaRepository;
import es.antoniorg.myspringrest.repository.PreguntaRespuestaRepository;
import es.antoniorg.myspringrest.repository.RespuestaPorDefectoRepository;
import es.antoniorg.myspringrest.repository.RespuestaRepository;
import lombok.Getter;
import lombok.Setter;

@Component("mainController")
@ViewScoped
public class MainController implements Serializable {

	private static final long serialVersionUID = 8929086073643011545L;

	private Logger logger = LoggerFactory.getLogger(MainController.class);

	private @Autowired IdiomaRepository idiomaRepository;
	private @Autowired AreaRepository areaRepository;
	private @Autowired CategoriaRepository categoriaRepository;
	private @Autowired PreguntaRepository preguntaRepository;
	private @Autowired RespuestaRepository respuestaRepository;
	private @Autowired PreguntaRespuestaRepository preResRepository;
	private @Autowired RespuestaPorDefectoRepository respuestaDefaultRepository;

	/** Lista con los idiomas de la tabla persistente */
	private @Getter @Setter List<Idioma> idiomas;

	/** Idioma para el crud */
	private @Getter @Setter Idioma idiomaEdit;

	/** Lista con las áreas de la tabla persistente */
	private @Getter @Setter List<Area> areas;

	/** Area para el crud */
	private @Getter @Setter Area areaEdit;

	/** Categoría para el crud */
	private @Getter @Setter Categoria categoriaEdit;

	/** Lista con las categorías de la tabla persistente */
	private @Getter @Setter List<Categoria> categorias;

	/** Pregunta para el crud */
	private @Getter @Setter Pregunta preguntaEdit;

	/** Lista con las preguntas de la tabla persistente */
	private @Getter @Setter List<Pregunta> preguntas;

	/** Respuesta para el crud */
	private @Getter @Setter Respuesta respuestaEdit;

	/** Lista con las respuestas de la tabla persistente */
	private @Getter @Setter List<Respuesta> respuestas;

	/** Pregunta-Respuesta para el crud */
	private @Getter @Setter PreguntaRespuesta preResEdit;

	/** Lista con las Pregunta-Respuestas de la tabla persistente */
	private @Getter @Setter List<PreguntaRespuesta> preRes;
	
	/** Pregunta por defecto para el crud */
	private @Getter @Setter RespuestaPorDefecto respuestaDefectoEdit;
	
	/** Lista con las Respuestas por defecto de la tabla persistente */
	private @Getter @Setter List<RespuestaPorDefecto> respuestasDefecto;

	/** Árbol con todos los datos relacionados */
	private @Getter @Setter TreeNode arbolDatos;
	
	/** False: árbol contraido, True: árbol abierto */
	private @Getter @Setter boolean arbolShowed;
	
	/** Contiene las respuestas seleccionadas por defecto del último área creado */
	private @Getter @Setter List<RespuestaPorDefecto> respuestasPorDefectoSeleccionadas;

	@PostConstruct
	public void init() {
		logger.info("MainController init");

		limpiarVariables();

		logger.info("MainController end");
	}

	/** Inicializa todas las variables y recarga las tablas */
	public void limpiarVariables() {
		idiomaEdit = new Idioma();
		areaEdit = new Area();
		categoriaEdit = new Categoria();
		preguntaEdit = new Pregunta();
		respuestaEdit = new Respuesta();
		preResEdit = new PreguntaRespuesta();
		respuestaDefectoEdit = new RespuestaPorDefecto();

		idiomas = idiomaRepository.findAll();
		areas = areaRepository.findAll();
		categorias = categoriaRepository.findAll();
		preguntas = preguntaRepository.findAll();
		respuestas = respuestaRepository.findAll();
		preRes = preResRepository.findAll();
		respuestasDefecto = respuestaDefaultRepository.findAll();

		arbolDatos = getTreeNode();
	}

	// -----------------------------------Crud Tabla Idioma

	/** Actualiza el idioma seleccionado para después llamar a crearIdioma */
	public void actualizarIdioma(Idioma idioma) {
		logger.info("actualizarIdioma init: Se procede a actualizar el idioma seleccionado a: " + idioma.toString());
		idiomaEdit = idioma;
	}

	/** Asigna un nuevo idioma a la variable idioma editable */
	public void crearIdioma() {
		logger.info("crearIdioma init: Aplastando la variable idiomaEdit");
		idiomaEdit = new Idioma();
	}

	/** Persiste un nuevo idioma con la variable idiomaEdit */
	public void persistIdioma() {
		logger.info("crearIdioma init: Se procede a persistir el idioma " + idiomaEdit.toString());
		idiomaRepository.saveAndFlush(idiomaEdit);
		limpiarVariables();
	}

	/** Elimina el idioma pasado por parámetro */
	public void eliminarIdioma(Idioma idioma) {
		logger.info("eliminarIdioma init: Se va a borrar el idioma: " + idioma.toString());
		idiomaRepository.delete(idioma);
		limpiarVariables();
	}

	// -----------------------------------Crud Tabla Area

	/** Actualiza el area seleccionado para después llamar a crearArea */
	public void actualizarArea(Area area) {
		logger.info("actualizarArea init: Se procede a actualizar el area seleccionado a: " + area.toString());
		areaEdit = area;
	}

	/** Asigna un nuevo area a la variable area editable */
	public void crearArea() {
		logger.info("crearArea init: Aplastando la variable areaEdit");
		areaEdit = new Area();
	}

	/** Persiste un nuevo area con la variable areaEdit */
	public void persistArea() {
		logger.info("crearArea init: Se procede a persistir el area " + areaEdit.toString());
		areaRepository.saveAndFlush(areaEdit);
		limpiarVariables();
	}

	/** Elimina el area pasado por parámetro */
	public void eliminarArea(Area area) {
		logger.info("eliminarArea init: Se va a borrar el area: " + area.toString());
		areaRepository.delete(area);
		limpiarVariables();
	}

	// -----------------------------------Crud Tabla Categoría

	/** Actualiza la categoría seleccionada para después llamar a crearCategoria */
	public void actualizarCategoria(Categoria categoria) {
		logger.info("actualizarCategoria init: Se procede a actualizar la categoría seleccionada a: "
				+ categoria.toString());
		categoriaEdit = categoria;
	}

	/** Asigna una nueva categoría a la variable categoria editable */
	public void crearCategoria() {
		logger.info("crearCategoria init: Aplastando la variable categoriaEdit");
		categoriaEdit = new Categoria();
	}

	/** Persiste una nueva categoria con la variable categoriaEdit */
	public void persistCategoria() {
		logger.info("crearCategoria init: Se procede a persistir la categoría " + categoriaEdit.toString());
		categoriaRepository.saveAndFlush(categoriaEdit);
		limpiarVariables();
	}

	/** Elimina la categoria pasada por parámetro */
	public void eliminarCategoria(Categoria categoria) {
		logger.info("eliminarCategoria init: Se va a borrar la categoría: " + categoria.toString());
		categoriaRepository.delete(categoria);
		limpiarVariables();
	}

	// -----------------------------------Crud Tabla Pregunta

	/** Actualiza la pregunta seleccionada para después llamar a crearPregunta */
	public void actualizarPregunta(Pregunta pregunta) {
		logger.info(
				"actualizarPregunta init: Se procede a actualizar la pregunta seleccionada a: " + pregunta.toString());
		preguntaEdit = pregunta;
	}

	/** Asigna una nueva pregunta a la variable pregunta editable */
	public void crearPregunta() {
		logger.info("crearPregunta init: Aplastando la variable preguntaEdit");
		preguntaEdit = new Pregunta();
	}

	/** Persiste una nueva pregunta con la variable preguntaEdit */
	public void persistPregunta() {
		logger.info("crearPregunta init: Se procede a persistir la pregunta " + preguntaEdit.toString());
		preguntaRepository.saveAndFlush(preguntaEdit);
		limpiarVariables();
	}

	/** Elimina la pregunta pasada por parámetro */
	public void eliminarPregunta(Pregunta pregunta) {
		logger.info("eliminarPregunta init: Se va a borrar la pregunta: " + pregunta.toString());
		preguntaRepository.delete(pregunta);
		limpiarVariables();
	}

	// -----------------------------------Crud Tabla Respuesta

	/** Actualiza la respuesta seleccionada para después llamar a crearRespuesta */
	public void actualizarRespuesta(Respuesta respuesta) {
		logger.info("actualizarRespuesta init: Se procede a actualizar la respuesta seleccionada a: " + respuesta.toString());
		respuestaEdit = respuesta;
	}

	/** Asigna una nueva respuesta a la variable respuesta editable */
	public void crearRespuesta() {
		logger.info("crearRespuesta init: Aplastando la variable respuestaEdit");
		respuestaEdit = new Respuesta();
	}

	/** Persiste una nueva respuesta con la variable respuestaEdit */
	public void persistRespuesta() {
		logger.info("persistRespuesta init: Se procede a persistir la respuesta " + respuestaEdit.toString());
		respuestaRepository.saveAndFlush(respuestaEdit);
		limpiarVariables();
	}

	/** Elimina la respuesta pasada por parámetro */
	public void eliminarRespuesta(Respuesta respuesta) {
		logger.info("eliminarRespuesta init: Se va a borrar la respuesta: " + respuesta.toString());
		respuestaRepository.delete(respuesta);
		limpiarVariables();
	}

	// -----------------------------------Crud Tabla Pregunta-Respuesta

	/** Actualiza la PreRes seleccionada para después llamar a crearRespuesta */
	public void actualizarPreRes(PreguntaRespuesta preRes) {
		logger.info("actualizarPreRes init: Se procede a actualizar la preRes seleccionada a: " + preRes.toString());
		preResEdit = preRes;
	}

	/** Asigna una nueva Pregunta-Respuesta a la variable PreRes editable */
	public void crearPreRes() {
		logger.info("crearPreRes init: Aplastando la variable preResEdit");
		preResEdit = new PreguntaRespuesta();
	}

	/** Persiste una nueva Pregunta-Respuesta con la variable preResEdit */
	public void persistPreRes() {
		logger.info("persistPreRes init: Se procede a persistir la preRes " + preRes.toString());
		preResRepository.saveAndFlush(preResEdit);
		limpiarVariables();
	}

	/** Elimina la Pregunta-Respuesta pasada por parámetro */
	public void eliminarPreRes(PreguntaRespuesta preRes) {
		logger.info("eliminarPreRes init: Se va a borrar la preRes: " + preRes.toString());
		preResRepository.delete(preRes);
		limpiarVariables();
	}
	
	// -----------------------------------Crud Tabla Area-Respuesta
	
	/** Actualiza la respuesta por defecto seleccionada para después llamar a crearRespuestaDefecto */
	public void actualizarRespuestaDefecto(RespuestaPorDefecto respuestaDefecto) {
		logger.info("actualizarRespuestaDefecto init: Se procede a actualizar la respuestaDefecto seleccionada a: " + respuestaDefecto.toString());
		respuestaDefectoEdit = respuestaDefecto;
	}

	/** Asigna una nueva respuesta por defecto a la variable respuestaDefectoEdit editable */
	public void crearRespuestaDefecto() {
		logger.info("crearRespuestaDefecto init: Aplastando la variable respuestaDefectoEdit");
		respuestaDefectoEdit = new RespuestaPorDefecto();
	}

	/** Persiste una nueva respuesta por defecto con la variable respuestaDefectoEdit */
	public void persistRespuestaDefecto() {
		logger.info("persistRespuestaDefecto init: Se procede a persistir la resupuesta por defecto " + respuestaDefectoEdit.toString());
		respuestaDefaultRepository.saveAndFlush(respuestaDefectoEdit);
		limpiarVariables();
	}

	/** Elimina la respuesta por defecto pasada por parámetro */
	public void eliminarRespuestaDefecto(RespuestaPorDefecto respuestaDefecto) {
		logger.info("eliminarRespuestaDefecto init: Se va a borrar la respuesta por defecto: " + respuestaDefecto.toString());
		respuestaDefaultRepository.delete(respuestaDefecto);
		limpiarVariables();
	}

	// -----------------------------------Funciones

	/** Genera la tabla en forma de árbol para visualizar la estructura de datos */
	public TreeNode getTreeNode() {
		TreeNode root = new DefaultTreeNode("MiÁrbol", null);
		TreeNode header = new DefaultTreeNode("Raíz", root);
		
		for (Idioma i : idiomas) {
			TreeNode trIdioma = new DefaultTreeNode(i.toStringArbol(), header);
			for (Area a : i.getAreas()) {
				TreeNode trArea = new DefaultTreeNode(a.toStringArbol(), trIdioma);
				for (Categoria c : a.getCategorias()) {
					TreeNode trCategorias = new DefaultTreeNode(c.toStringArbol(), trArea);
					for (Pregunta p : c.getPreguntas()) {
						TreeNode trPregunta = new DefaultTreeNode(p.toStringArbol(), trCategorias);
						if(p.getRespuestas().isEmpty()) {
							for (RespuestaPorDefecto r : a.getRespuestasPorDefecto()) {
								new DefaultTreeNode(r.getRespuesta().toStringArbol() + ", Valor: " + r.getPuntuacion(), trPregunta);
							}
						} else {							
							for (PreguntaRespuesta r : p.getRespuestas()) {
								new DefaultTreeNode(r.getRespuesta().toStringArbol() + ", Valor: " + r.getPuntuacion(), trPregunta);
							}
						}
					}
				}
			}
		}
		return root;
	}
	
	/** Cambia el estado de la variable arbolShowed */
	public boolean arbolShowedReverse() {
		if(arbolShowed) {
			arbolShowed = false;
			return arbolShowed;
		}
		
		arbolShowed = true;
		return arbolShowed;
	}

	/** Expande o contrae los nodos del árbol pasado por parámetro recursivamente */
	public void mostrarNodos(TreeNode n, boolean option) {
		if (n.getChildren().size() == 0) {
			n.setSelected(false);
		} else {
			for (TreeNode s : n.getChildren()) {
				mostrarNodos(s, option);
			}
			n.setExpanded(option);
			n.setSelected(false);
		}
	}
}