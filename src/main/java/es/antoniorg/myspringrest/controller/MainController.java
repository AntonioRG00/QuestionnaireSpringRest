package es.antoniorg.myspringrest.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.antoniorg.myspringrest.model.Area;
import es.antoniorg.myspringrest.model.Categoria;
import es.antoniorg.myspringrest.model.Idioma;
import es.antoniorg.myspringrest.model.Perfil;
import es.antoniorg.myspringrest.model.Pregunta;
import es.antoniorg.myspringrest.model.PreguntaRespuesta;
import es.antoniorg.myspringrest.model.Respuesta;
import es.antoniorg.myspringrest.model.RespuestaPorDefecto;
import es.antoniorg.myspringrest.repository.AreaRepository;
import es.antoniorg.myspringrest.repository.CategoriaRepository;
import es.antoniorg.myspringrest.repository.IdiomaRepository;
import es.antoniorg.myspringrest.repository.PerfilRepository;
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
	private @Autowired HttpServletRequest request;

	private @Autowired IdiomaRepository idiomaRepository;
	private @Autowired AreaRepository areaRepository;
	private @Autowired CategoriaRepository categoriaRepository;
	private @Autowired PreguntaRepository preguntaRepository;
	private @Autowired RespuestaRepository respuestaRepository;
	private @Autowired PreguntaRespuestaRepository preResRepository;
	private @Autowired RespuestaPorDefectoRepository respuestaDefaultRepository;
	private @Autowired PerfilRepository perfilRepository;

	/** Lista con los idiomas de la tabla persistente */
	private @Getter @Setter List<Idioma> idiomas;

	/** Idioma para el crud */
	private @Getter @Setter Idioma idiomaEdit;

	/** Lista con las áreas de la tabla persistente */
	private @Getter @Setter List<Area> areas;

	/** Area para el crud */
	private @Getter @Setter Area areaEdit;

	/** Lista con las categorías de la tabla persistente */
	private @Getter @Setter List<Categoria> categorias;

	/** Categoría para el crud */
	private @Getter @Setter Categoria categoriaEdit;

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
	
	/** Perfil para el crud */
	private @Getter @Setter Perfil perfilEdit;
	
	/** Lista con los perfiles de la tabla persistente */
	private @Getter @Setter List<Perfil> perfiles;

	/** Árbol con todos los datos relacionados */
	private @Getter @Setter TreeNode arbolDatos;

	/** False: árbol contraido, True: árbol abierto */
	private @Getter @Setter boolean arbolShowed;
	
	/** Contiene los posibles perfiles a seleccionar por pregunta */
	private @Getter @Setter List<Perfil> posiblesPerfiles;

	@PostConstruct
	public void init() {
		limpiarVariables();
	}

	/** Limpia todas las variables y recarga las tablas */
	public void limpiarVariables() {
		idiomaEdit = new Idioma();
		areaEdit = new Area();
		categoriaEdit = new Categoria();
		preguntaEdit = new Pregunta();
		respuestaEdit = new Respuesta();
		preResEdit = new PreguntaRespuesta();
		respuestaDefectoEdit = new RespuestaPorDefecto();
		perfilEdit = new Perfil();
		posiblesPerfiles = new ArrayList<>();
		arbolShowed = false;

		idiomas = idiomaRepository.findAll();
		areas = areaRepository.findAll();
		categorias = categoriaRepository.findAll();
		preguntas = preguntaRepository.findAll();
		respuestas = respuestaRepository.findAll();
		preRes = preResRepository.findAll();
		respuestasDefecto = respuestaDefaultRepository.findAll();
		perfiles = perfilRepository.findAll();

		arbolDatos = getTreeNode();
	}

	// -----------------------------------Crud Tabla Idioma

	/** Actualiza el idioma seleccionado para después llamar a persistIdioma */
	public void actualizarIdioma(Idioma idioma) {
		logger.info("IP: " + getClientIP() + " -> actualizarIdioma init: Se procede a actualizar el idioma seleccionado a: " + idioma.toString());
		idiomaEdit = idioma;
	}

	/** Asigna un nuevo idioma a la variable idioma editable */
	public void crearIdioma() {
		logger.info("IP: " + getClientIP() + " -> crearIdioma: Aplastando la variable idiomaEdit");
		idiomaEdit = new Idioma();
	}

	/** Persiste un nuevo idioma con la variable idiomaEdit */
	public void persistIdioma() {
		logger.info("IP: " + getClientIP() + " -> crearIdioma: Se procede a persistir el idioma " + idiomaEdit.toString());
		idiomaEdit = idiomaRepository.saveAndFlush(idiomaEdit);
		limpiarVariables();
		Collections.rotate(idiomas, 1);
	}

	/** Elimina el idioma pasado por parámetro */
	public void eliminarIdioma(Idioma idioma) {
		logger.info("IP: " + getClientIP() + " -> eliminarIdioma: Se va a borrar el idioma: " + idioma.toString());
		idiomaRepository.delete(idioma);
		limpiarVariables();
	}

	// -----------------------------------Crud Tabla Area

	/** Actualiza el area seleccionado para después llamar a persistArea */
	public void actualizarArea(Area area) {
		logger.info("IP: " + getClientIP() + " -> actualizarArea: Se procede a actualizar el area seleccionado a: " + area.toString());
		areaEdit = area;
	}

	/** Asigna un nuevo area a la variable area editable */
	public void crearArea() {
		logger.info("IP: " + getClientIP() + " -> crearArea: Aplastando la variable areaEdit");
		areaEdit = new Area();
	}

	/** Persiste un nuevo area con la variable areaEdit */
	public void persistArea() {
		logger.info("IP: " + getClientIP() + " -> crearArea: Se procede a persistir el area " + areaEdit.toString());
		areaEdit = areaRepository.saveAndFlush(areaEdit);
		limpiarVariables();
		Collections.rotate(areas, 1);
	}

	/** Elimina el area pasado por parámetro */
	public void eliminarArea(Area area) {
		logger.info("IP: " + getClientIP() + " -> eliminarArea: Se va a borrar el area: " + area.toString());
		areaRepository.delete(area);
		limpiarVariables();
	}

	// -----------------------------------Crud Tabla Categoría

	/** Actualiza la categoría seleccionada para despuás llamar a persistCategoria */
	public void actualizarCategoria(Categoria categoria) {
		logger.info("IP: " + getClientIP() + " -> actualizarCategoria: Se procede a actualizar la categoría seleccionada a: "+ categoria.toString());
		categoriaEdit = categoria;
	}

	/** Asigna una nueva categoría la variable categoria editable */
	public void crearCategoria() {
		logger.info("IP: " + getClientIP() + " -> crearCategoria: Aplastando la variable categoriaEdit");
		categoriaEdit = new Categoria();
	}

	/** Persiste una nueva categoria con la variable categoriaEdit */
	public void persistCategoria() {
		logger.info("IP: " + getClientIP() + " -> crearCategoria: Se procede a persistir la categoría " + categoriaEdit.toString());
		categoriaEdit = categoriaRepository.saveAndFlush(categoriaEdit);
		limpiarVariables();
		Collections.rotate(categorias, 1);
	}

	/** Elimina la categoria pasada por parámetro */
	public void eliminarCategoria(Categoria categoria) {
		logger.info("IP: " + getClientIP() + " -> eliminarCategoria: Se va a borrar la categoría: " + categoria.toString());
		categoriaRepository.delete(categoria);
		limpiarVariables();
	}

	// -----------------------------------Crud Tabla Pregunta

	/** Actualiza la pregunta seleccionada para después llamar a persistPregunta */
	public void actualizarPregunta(Pregunta pregunta) {
		logger.info("IP: " + getClientIP() + " -> actualizarPregunta: Se procede a actualizar la pregunta seleccionada a: " + pregunta.toString());
		preguntaEdit = pregunta;
		posiblesPerfiles = pregunta.getCategoria().getArea().getIdioma().getPerfiles();
	}

	/** Asigna una nueva pregunta a la variable pregunta editable */
	public void crearPregunta() {
		logger.info("IP: " + getClientIP() + " -> crearPregunta: Aplastando la variable preguntaEdit");
		preguntaEdit = new Pregunta();
		posiblesPerfiles = new ArrayList<>();
	}

	/** Persiste una nueva pregunta con la variable preguntaEdit */
	public void persistPregunta() {
		logger.info("IP: " + getClientIP() + " -> crearPregunta: Se procede a persistir la pregunta " + preguntaEdit.toString());
		preguntaEdit = preguntaRepository.saveAndFlush(preguntaEdit);
		limpiarVariables();
		Collections.rotate(preguntas, 1);
	}

	/** Elimina la pregunta pasada por parámetro */
	public void eliminarPregunta(Pregunta pregunta) {
		logger.info("IP: " + getClientIP() + " -> eliminarPregunta: Se va a borrar la pregunta: " + pregunta.toString());
		preguntaRepository.delete(pregunta);
		limpiarVariables();
	}

	// -----------------------------------Crud Tabla Respuesta

	/** Actualiza la respuesta seleccionada para después llamar a persistRespuesta */
	public void actualizarRespuesta(Respuesta respuesta) {
		logger.info("IP: " + getClientIP() + " -> actualizarRespuesta: Se procede a actualizar la respuesta seleccionada a: " + respuesta.toString());
		respuestaEdit = respuesta;
	}

	/** Asigna una nueva respuesta a la variable respuesta editable */
	public void crearRespuesta() {
		logger.info("IP: " + getClientIP() + " -> crearRespuesta: Aplastando la variable respuestaEdit");
		respuestaEdit = new Respuesta();
	}

	/** Persiste una nueva respuesta con la variable respuestaEdit */
	public void persistRespuesta() {
		logger.info("IP: " + getClientIP() + " -> persistRespuesta: Se procede a persistir la respuesta " + respuestaEdit.toString());
		respuestaEdit = respuestaRepository.saveAndFlush(respuestaEdit);
		limpiarVariables();
		Collections.rotate(respuestas, 1);
	}

	/** Elimina la respuesta pasada por parámetro */
	public void eliminarRespuesta(Respuesta respuesta) {
		logger.info("IP: " + getClientIP() + " -> eliminarRespuesta: Se va a borrar la respuesta: " + respuesta.toString());
		respuestaRepository.delete(respuesta);
		limpiarVariables();
	}

	// -----------------------------------Crud Tabla Pregunta-Respuesta

	/** Actualiza la PreRes seleccionada para después llamar a persistPreRes */
	public void actualizarPreRes(PreguntaRespuesta preRes) {
		logger.info("IP: " + getClientIP() + " -> actualizarPreRes: Se procede a actualizar la preRes seleccionada a: " + preRes.toString());
		preResEdit = preRes;
	}

	/** Asigna una nueva Pregunta-Respuesta a la variable PreRes editable */
	public void crearPreRes() {
		logger.info("IP: " + getClientIP() + " -> crearPreRes: Aplastando la variable preResEdit");
		preResEdit = new PreguntaRespuesta();
	}

	/** Persiste una nueva Pregunta-Respuesta con la variable preResEdit */
	public void persistPreRes() {
		logger.info("IP: " + getClientIP() + " -> persistPreRes: Se procede a persistir la preRes " + preResEdit.toString());
		preResEdit = preResRepository.saveAndFlush(preResEdit);
		limpiarVariables();
		Collections.rotate(preRes, 1);
	}

	/** Elimina la Pregunta-Respuesta pasada por parámetro */
	public void eliminarPreRes(PreguntaRespuesta preRes) {
		logger.info("IP: " + getClientIP() + " -> eliminarPreRes: Se va a borrar la preRes: " + preRes.toString());
		preResRepository.delete(preRes);
		limpiarVariables();
	}

	// -----------------------------------Crud Tabla Area-Respuesta

	/** Actualiza la respuesta por defecto seleccionada para después llamar a persistRespuestaDefecto */
	public void actualizarRespuestaDefecto(RespuestaPorDefecto respuestaDefecto) {
		logger.info("IP: " + getClientIP() + " -> actualizarRespuestaDefecto: Se procede a actualizar la respuestaDefecto seleccionada a: " + respuestaDefecto.toString());
		respuestaDefectoEdit = respuestaDefecto;
	}

	/** Asigna una nueva respuesta por defecto a la variable respuestaDefectoEdit editable */
	public void crearRespuestaDefecto() {
		logger.info("IP: " + getClientIP() + " -> crearRespuestaDefecto: Aplastando la variable respuestaDefectoEdit");
		respuestaDefectoEdit = new RespuestaPorDefecto();
	}

	/** Persiste una nueva respuesta por defecto con la variable respuestaDefectoEdit */
	public void persistRespuestaDefecto() {
		logger.info("IP: " + getClientIP() + " -> persistRespuestaDefecto: Se procede a persistir la resupuesta por defecto " + respuestaDefectoEdit.toString());
		respuestaDefectoEdit = respuestaDefaultRepository.saveAndFlush(respuestaDefectoEdit);
		limpiarVariables();
		Collections.rotate(respuestasDefecto, 1);
	}

	/** Elimina la respuesta por defecto pasada por parámetro */
	public void eliminarRespuestaDefecto(RespuestaPorDefecto respuestaDefecto) {
		logger.info("IP: " + getClientIP() + " -> eliminarRespuestaDefecto: Se va a borrar la respuesta por defecto: " + respuestaDefecto.toString());
		respuestaDefaultRepository.delete(respuestaDefecto);
		limpiarVariables();
	}
	
	// -----------------------------------Crud Tabla Perfil
	
	/** Actualiza el perfil seleccionado para después llamar a persistPerfil */
	public void actualizarPerfil(Perfil perfil) {
		logger.info("IP: " + getClientIP() + " -> actualizarPerfil: Se procede a actualizar el perfil seleccionado a: " + perfil.toString());
		perfilEdit = perfil;
	}

	/** Asigna un nuevo perfil a la variable perfilEdit */
	public void crearPerfil() {
		logger.info("IP: " + getClientIP() + " -> crearPerfil: Aplastando la variable perfilEdit");
		perfilEdit = new Perfil();
	}

	/** Persiste un nuevo perfil con la variable perfilEdit */
	public void persistPerfil() {
		logger.info("IP: " + getClientIP() + " -> persistPerfil: Se procede a persistir la el perfil " + perfilEdit.toString());
		perfilEdit = perfilRepository.saveAndFlush(perfilEdit);
		limpiarVariables();
		Collections.rotate(respuestasDefecto, 1);
	}

	/** Elimina el perfil por defecto pasado por parámetro */
	public void eliminarPerfil(Perfil perfil) {
		logger.info("IP: " + getClientIP() + " -> eliminarPerfil: Se va a borrar el perfil por defecto: " + perfil.toString());
		perfilRepository.delete(perfil);
		limpiarVariables();
	}

	// -----------------------------------Funciones

	/** Genera la tabla en forma de árbol para visualizar la estructura de datos */
	public TreeNode getTreeNode() {
		TreeNode root = new DefaultTreeNode("MiÁrbol", null);

		TreeNode container = new DefaultTreeNode("Contenedor", root);

		for (Idioma i : idiomas) {
			TreeNode trIdioma = new DefaultTreeNode(i.toStringArbol(), container);
			for (Area a : i.getAreas()) {
				TreeNode trArea = new DefaultTreeNode(a.toStringArbol(), trIdioma);
				for (Categoria c : a.getCategorias()) {
					TreeNode trCategorias = new DefaultTreeNode(c.toStringArbol(), trArea);
					for (Pregunta p : c.getPreguntas()) {
						TreeNode trPregunta = new DefaultTreeNode(p.toStringArbol(), trCategorias);
						if (p.getRespuestas().isEmpty()) {
							for (RespuestaPorDefecto r : a.getRespuestasPorDefecto()) {
								new DefaultTreeNode(r.getRespuesta().toStringArbol() + ", Valor: " + r.getPuntuacion(),trPregunta);
							}
						} else {
							for (PreguntaRespuesta r : p.getRespuestas()) {
								new DefaultTreeNode(r.getRespuesta().toStringArbol() + ", Valor: " + r.getPuntuacion(),trPregunta);
							}
						}
					}
				}
			}
		}
		return root;
	}

	/** Cambia el estado de la variable arbolShowed y la devuelve */
	public boolean arbolShowedReverse() {
		return arbolShowed = !arbolShowed;
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
	
	/** Actualiza los posibles perfiles según la categoría seleccionada en pregunta */
	public void onChangeCategoriaInPregunta() {
		if(preguntaEdit.getCategoria() == null) {
			posiblesPerfiles = new ArrayList<>();
		} else {
			posiblesPerfiles = preguntaEdit.getCategoria().getArea().getIdioma().getPerfiles();
		}
	}
	
	
	/** Devuelve la IP del usuario */
	private String getClientIP() {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}
}