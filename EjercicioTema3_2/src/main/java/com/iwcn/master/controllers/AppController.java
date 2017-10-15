package com.iwcn.master.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iwcn.master.model.Producto;

@Controller
public class AppController {

	@Autowired
	public Producto p;
	
	ArrayList<Producto> lista = new ArrayList<>();
	
	@RequestMapping("/")
	public ModelAndView index(Model model) {
		return new ModelAndView("index_template");
	}
	
    @RequestMapping("/nuevoProducto")
    public ModelAndView nuevoProducto(Model model) {
    	model.addAttribute(p);
        return new ModelAndView("nuevoProducto_template");
    }
    
    @RequestMapping("/guardado")
    public ModelAndView respuesta(Model model, Producto prod) {
    	lista.add(prod);
    	return new ModelAndView("guardado_template").addObject("nombre", prod.getNombre());
    }
    
    @RequestMapping("/listaProductos")
    public ModelAndView listaProductos() {
    	return new ModelAndView("listaProductos_template").addObject("lista", lista);
    }
    
    @RequestMapping("/info/{option}")
    public ModelAndView info(@PathVariable String option) {
    	int indice = Integer.parseInt(option);
    	Producto pr = lista.get(indice);
    	return new ModelAndView("info_template").addObject("codigo", pr.getCodigo()).addObject("nombre", pr.getNombre()).addObject("descripcion", pr.getDescripcion()).addObject("precio", pr.getPrecio());
    }
    
    @RequestMapping("/eliminado/{option}")
    public ModelAndView eliminado(@PathVariable String option) {
    	int indice = Integer.parseInt(option);
    	Producto pr = lista.get(indice);
    	lista.remove(pr);
    	return new ModelAndView("eliminado_template").addObject("nombre", pr.getNombre());
    }

}
