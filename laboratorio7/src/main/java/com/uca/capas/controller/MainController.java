package com.uca.capas.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.dto.EstudianteDTO;
import com.uca.capas.service.EstudianteService;

@Controller
public class MainController {
	Logger log = Logger.getLogger(MainController.class.getName());
	
	@Autowired //inyectamos objeto
	private EstudianteService estudianteService;
	
	@RequestMapping("/inicio")	
	public ModelAndView initMain() {	
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try {
			estudiantes = estudianteService.todos();
		}
		catch(Exception e) {
			log.info("Error encontrado");
			e.printStackTrace();
		}
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		return mav;
	}
	
	@RequestMapping("/formGuardar")
	public ModelAndView formGuardar() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("estudiante", new Estudiante());
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/formEditar/{codigoEstudiante}")
	public ModelAndView formEditar(@PathVariable int codigoEstudiante) {
		ModelAndView mav = new ModelAndView();
		Estudiante e = estudianteService.findOne(codigoEstudiante);
		mav.addObject("estudiante", e);
		mav.setViewName("update");
		return mav;
	}
	

	
	//********* Guardar o actualizar estudiante en la base de datos *********
	@RequestMapping("/guardar")
	public ModelAndView guardar(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) { 
			mav.setViewName("index");
			log.info("Error encontrado");
		}else {	
			List<Estudiante> estudiantes =null;
			try {
				log.info("Estudiante agregado");
				estudianteService.save(estudiante);
				estudiantes = estudianteService.todos();
			}catch(Exception ex) {
				log.info("No se pudo agregar");
				ex.printStackTrace();
			}
			mav.setViewName("redirect:/listado");
			mav.addObject("estudiantes", estudiantes);	
		}
		return mav;
	}

	//********* Ver lista de estudiantes guardados *********
	@RequestMapping("/listado")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try {
			estudiantes = estudianteService.todos();
		}
		catch(Exception e) {
			log.info("Error encontrado");
			e.printStackTrace();
		}
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		return mav;
	}
	
	//********* Elimina studiante por código*********
	@RequestMapping(value = "/eliminar/{codigoEstudiante}")
	public ModelAndView eliminar(@PathVariable("codigoEstudiante") int codigoEstudiante) {
		ModelAndView mav = new ModelAndView();
		Estudiante e = null;
		try {
			e = estudianteService.findOne(codigoEstudiante);
			estudianteService.delete(e);
			log.info("Estudiante eliminado");
		}catch(Exception ex){
			log.info("Error:" + ex.toString());
		}	
		mav.setViewName("redirect:/listado");
		return mav;
	}

	
	@PostMapping(value="/filtrar")
	public ModelAndView filtro(@RequestParam(value="nombre") String cadena) {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try {
			estudiantes = estudianteService.filtrarPor(cadena);
			//estudiantes = estudianteService .empiezaCon(cadena);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		
		return mav;
	}
	
	@PostMapping(value="/mostrarDTO")
	public ModelAndView mostrarDTO() {
		ModelAndView mav = new ModelAndView();
		List<EstudianteDTO> estudiantes = null;
		try {
			estudiantes = estudianteService.dtoPrueba();
			//estudiantes = estudianteService .empiezaCon(cadena);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("muestraDTO");
		
		return mav;
	}
	
}