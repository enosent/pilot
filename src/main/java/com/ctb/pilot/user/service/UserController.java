package com.ctb.pilot.user.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ctb.pilot.common.model.Multipart;
import com.ctb.pilot.user.model.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/services/user/login.do")
	public void login(@RequestParam("user_id") String userId,
			@RequestParam("password") String password,
			@RequestParam("current_url") String currentUrl,
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		User user = userService.login(userId, password);
		System.out.println("user: " + user);
		if (user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);

			int sequence = user.getSequence();
			Cookie cookie = new Cookie("seq", String.valueOf(sequence));
			cookie.setMaxAge(60 * 60 * 24 * 365);
			cookie.setPath("/");
			resp.addCookie(cookie);
		}

		// FIXME:
		// Remove this code if possible.
		if (currentUrl.isEmpty()) {
			currentUrl = "/";
		}
		resp.sendRedirect(currentUrl);
	}

	@RequestMapping("/services/user/logout.do")
	public void logout(@RequestParam("current_url") String currentUrl,
			HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("user");

		Cookie cookie = new Cookie("seq", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		resp.addCookie(cookie);

		resp.sendRedirect(currentUrl);
	}

	@RequestMapping("/services/user/sign-up.do")
	public void signUp(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			Multipart multipart = new Multipart(req);
			String userId = multipart.getParameter("user_id");
			String password = multipart.getParameter("password");
			String nickname = multipart.getParameter("nickname");

			if (userId == null || password == null || nickname == null
					|| userId.isEmpty() || password.isEmpty()
					|| nickname.isEmpty()) {
				throw new ServletException("Some field is null or empty.");
			}

			// ServletContext application = req.getServletContext();
			// String realPath = application.getRealPath("/images/" + nickname
			// + ".jpg");
			// multipart.saveFile("imageFile", realPath);

			InputStream image = multipart.getInputStream("imageFile");

			userService.signUp(userId, password, nickname, image);
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw new ServletException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

		resp.sendRedirect("/common/web_template.jsp?body_path=/services/user/sign_up/sign_up_result.jsp");
	}

	@RequestMapping("/services/admin/user/list_users_view.do")
	public String listUsers(HttpServletRequest request) {
		List<User> allUsers = userService.getAllUsers();
		request.setAttribute("users", allUsers);

		return "/services/admin/user/list_users_view";
	}

	@RequestMapping("/about_us.do")
	public String listStaff(Model model) {
		List<User> allStaff = userService.getAllStaff();
		model.addAttribute("staff", allStaff);
		return "/view/jsp/about_us_view";
	}

}