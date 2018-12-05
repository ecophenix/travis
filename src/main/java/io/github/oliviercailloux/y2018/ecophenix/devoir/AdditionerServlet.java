package io.github.oliviercailloux.y2018.ecophenix.devoir;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("serial")
@WebServlet("additioner/add")
public class AdditionerServlet extends HttpServlet {
	
	private Integer defaultparam = null ;
	private  final static Logger LOGGER  = Logger.getLogger(AdditionerServlet.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
		resp.setContentType("text/plain");
		resp.setContentType(MediaType.TEXT_PLAIN);

		try {
			
		int parametre1 = Integer.parseInt(req.getParameter("param1"));
		int parametre2 = Integer.parseInt(req.getParameter("param2"));		
		LOGGER.info("paramètre 1 :"+ parametre1 + " et paramètre 2 :" + parametre2);
		
		int somme = parametre1 + parametre2;
		resp.setStatus(200);
		resp.getWriter().println("la somme est  :" +somme );
		
		} catch (NumberFormatException e) {
				try {
					int parametre1 = Integer.parseInt(req.getParameter("param1"));	
					if (this.defaultparam!= null) {
						int parametre2 = this.defaultparam;
						int somme = parametre1 + parametre2;
						LOGGER.info("La valeur par défaut est :" + defaultparam);
						resp.setStatus(200);
						resp.getWriter().println(somme);
					}
					else {
						
						LOGGER.warning("Exécution impossible, paramètre 2 manquant.");
						throw new NumberFormatException("NumberFormatException thrown");					
					}
					
				 } catch (NumberFormatException e2) {
					// TODO: handle exception
					LOGGER.warning("Exécution impossible, paramètres manquants.");
					resp.setStatus(400);
					resp.getWriter().println("Exécution impossible, paramètre manquant.");
				}
			
		}
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
		resp.setContentType("text/plain");
		resp.setContentType(MediaType.TEXT_PLAIN);
			
		try {
			defaultparam = Integer.parseInt(req.getParameter("param2"));
			//this.defaultparam = paramx ;
			LOGGER.info("valeur par défaut est :" + defaultparam);
			resp.setStatus((200));
			resp.getWriter().println("ok");
		} catch (NumberFormatException e) {
			// TODO: handle exception
			resp.setStatus((400));
			resp.getWriter().println("Exécution impossible, paramètre manquant.");
		}
	}
}

