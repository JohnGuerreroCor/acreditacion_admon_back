package com.usco.edu.restController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.activation.FileTypeMap;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RutasRestController {
	
    // REDIRECCIÓN A LA PÁGINA DE LOGIN
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@PreAuthorize("permitAll")
	public @ResponseBody ModelAndView mainController(HttpServletRequest request) {
		// CONFIGURACIÓN DE LA VISTA PARA REDIRIGIR A LA PÁGINA DE INICIO DE SESIÓN
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/login");
		return model;
	}
	
    // PÁGINA DE LOGIN
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@PreAuthorize("permitAll")
	public @ResponseBody ModelAndView loginControllerc(HttpServletRequest request,
			@RequestParam(value = "error", required = false) String error) {
		// CONFIGURACIÓN DE LA VISTA PARA LA PÁGINA DE INICIO DE SESIÓN
		ModelAndView model = new ModelAndView();
		model.setViewName("index.html");
		return model;
	}
	
    // PÁGINA DE TOKEN
	@RequestMapping(value = "/token", method = RequestMethod.GET)
	@PreAuthorize("permitAll")
	public @ResponseBody ModelAndView tokenController(HttpServletRequest request) {
		// CONFIGURACIÓN DE LA VISTA PARA LA PÁGINA DE TOKENS
		ModelAndView model = new ModelAndView();
		model.setViewName("index.html");
		return model;
	}
	
    // ENDPOINT PARA OBTENER EL LOGO
	@GetMapping("/logo")
	public ResponseEntity<byte[]> getImage(HttpServletRequest request) throws IOException{
		// RUTA DE LA IMAGEN DEL LOGO
		String ubicacionImg = request.getServletContext().getRealPath("/WEB-INF/classes/static/assets")
				+ File.separator + "USCO.png";
		File img = new File(ubicacionImg);
		
		// RESPUESTA CON EL CONTENIDO DE LA IMAGEN
	    return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
	}
}
