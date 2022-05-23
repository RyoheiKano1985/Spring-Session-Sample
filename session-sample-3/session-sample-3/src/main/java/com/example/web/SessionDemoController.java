package com.example.web;

import java.security.Principal;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

@RestController
public class SessionDemoController {

	private static final Logger LOG = LoggerFactory.getLogger(SessionDemoController.class);

//	@Autowired
//	FindByIndexNameSessionRepository<? extends Session> sessionRepository;

	@GetMapping("/create")
	public String create(HttpServletRequest request, HttpServletResponse response) {

		LOG.info("create");

		request.setAttribute("aaaaaaa", "aaaaaa");
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession(true);
		}
		session.setAttribute("aaaaaaa", "aaaaaa");
		session.setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "id");
		session.setAttribute("id", session.getId());

		LOG.info("session.getAttribute" + session.getAttribute("aaaaaaa"));

		return "Session create";
	}

	@GetMapping("/destory")
	public String destroy(HttpSession session, SessionStatus sessionStatus) {

		LOG.info("destory");
		session.invalidate();
		sessionStatus.setComplete();

		return "Session destory";
	}

	@GetMapping("/search")
	public String search(HttpSession session, Principal principal) {

		LOG.info("search");
//		Map<String, ? extends Session> usersSessions = this.sessionRepository.findByPrincipalName("id");
//		for (String key : usersSessions.keySet()) {
//
//			LOG.info(usersSessions.get(key).getAttribute("id"));
//		}

		getSearch();
		return "Session search";
	}

	private void test(String key) {
//		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//
//		// ... configure redisTemplate ...
//
//		RedisIndexedSessionRepository repository = new RedisIndexedSessionRepository(redisTemplate);
//		
//		
//		Map<String, Session> session = repository.findByPrincipalName(key);

	}

	private void getSearch() {

		ServletContext servletContext = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest().getServletContext();
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		FindByIndexNameSessionRepository<? extends Session> sessionRepository = applicationContext
				.getBean(FindByIndexNameSessionRepository.class);

		Map<String, ? extends Session> usersSessions = sessionRepository.findByPrincipalName("id");
		for (String key : usersSessions.keySet()) {

			LOG.info(usersSessions.get(key).getAttribute("id"));
		}

	}

}
