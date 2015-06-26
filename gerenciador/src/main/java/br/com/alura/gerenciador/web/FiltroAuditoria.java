package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.Usuario;


@WebFilter(urlPatterns="/*")
public class FiltroAuditoria implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String URI = request.getRequestURI();
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		if(usuario == null){
			System.out.println("Usuario <deslogado> acessando a pagina: "+URI);
			
		}else{
			System.out.println("Usuario "+usuario.getEmail()+" acessando a pagina: "+URI);
		}
		
		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	private Cookie getUsuario(HttpServletRequest req){
		Cookie[] cookies = req.getCookies();
		if(cookies == null) return null;
		
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("usuario.logado")){
				return cookie;
			}
		}

		return null;
		
	}

}
