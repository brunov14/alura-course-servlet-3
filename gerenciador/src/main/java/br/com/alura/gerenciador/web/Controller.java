package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/executar")
public class Controller extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String tarefa = (String) req.getParameter("tarefa");
		if(tarefa == null){
			throw new IllegalArgumentException("Tipode tarefa inválido");
		}
		
		String nomeClasse = "br.com.alura.gerenciador.web."+tarefa;
		
		try{
			Class type = Class.forName(nomeClasse);
			Tarefa tipo = (Tarefa) type.newInstance();
			String pagina = tipo.executa(req, resp);
			req.getRequestDispatcher(pagina).forward(req, resp);
		}catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
			throw new ServletException(e);
		}
		
	}
	
}
