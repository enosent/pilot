package com.ctb.pilot.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ctb.pilot.user.model.User;

@Controller
public class ListUsersController {

	@Autowired
	private UserService userService;

	@RequestMapping("/services/admin/user/list_users_view2.do")
	public String listUsers(HttpServletRequest request) {
		List<User> allUsers = userService.getAllUsers();
		request.setAttribute("users", allUsers);

		return "/services/admin/user/list_users_view";
	}

}
