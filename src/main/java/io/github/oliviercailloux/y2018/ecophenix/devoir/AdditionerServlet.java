package io.github.oliviercailloux.y2018.ecophenix.devoir;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
		resp.setContentType(MediaType.TEXT_PLAIN);
		resp.setLocale(Locale.ENGLISH);

		final ServletOutputStream out = resp.getOutputStream();
		
		try {
		int somme = 0;
		int parametre1 = Integer.parseInt(req.getParameter("param1"));
		int parametre2 = Integer.parseInt(req.getParameter("param2"));		
		LOGGER.info("paramètre 1 :"+ parametre1 + " et paramètre 2 :" + parametre2);
		somme = parametre1 + parametre2;
		resp.setStatus(200);
		out.println("la somme est  :" +somme);
		
		} catch (NumberFormatException e) {
				try {
					int parametre1 = Integer.parseInt(req.getParameter("param1"));	
					if (this.defaultparam!= null) {
						int parametre2 = this.defaultparam;
						int somme = parametre1 + parametre2;
						LOGGER.info("valeur par défaut est :" + defaultparam);
						resp.setStatus(200);
						out.println(somme);
					}
					else {
						
						LOGGER.warning("Exécution impossible, paramètre manquant");
						throw new NumberFormatException("NumberFormatException thrown");
						
					}
					
				 } catch (NumberFormatException e2) {
					// TODO: handle exception
					LOGGER.warning("les paramètres ne sont pas saisis correctement !!");
					resp.setStatus(400);
					out.println("Exécution impossible, paramètre manquant");
				}
			
		}
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
		resp.setContentType(MediaType.TEXT_PLAIN);
		
		try {
			int paramx= Integer.parseInt("param2");
			this.defaultparam = paramx ;
			LOGGER.fine("valeur par défaut est :" + defaultparam);
			resp.setStatus((200));
			resp.getWriter().println("OK");
		} catch (NumberFormatException e) {
			// TODO: handle exception
			LOGGER.warning("Param erroné");
			resp.setStatus((400));
			resp.getWriter().println("Exécution impossible, paramètre manquant");
		}
	}
}

