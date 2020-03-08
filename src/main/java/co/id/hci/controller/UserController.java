package co.id.hci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.hci.model.SimpleResponse;
import co.id.hci.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("")
	public SimpleResponse getUser() throws Exception{	
		SimpleResponse rm = new SimpleResponse();
		
		try {
			return userService.getUser();
		} catch (Exception e) {
			rm = new SimpleResponse();
			rm.setStatusCode(500);
			rm.setStatusDesc("FAIL");
			rm.setContents(e.getMessage());
		}

		return rm;
	}


	@PostMapping("/add")
	public SimpleResponse addUser(@RequestBody Object data) throws Exception{
		SimpleResponse rm = new SimpleResponse();

		try {
			return userService.addUser(data);
		} catch (Exception e) {
			rm = new SimpleResponse();
			rm.setStatusCode(500);
			rm.setStatusDesc("FAIL");
			rm.setContents(e.getMessage());			
		}

		return rm;
	}


	@PostMapping("/edit")
	public SimpleResponse editUser(@RequestBody Object data) throws Exception{
		SimpleResponse rm = new SimpleResponse();

		try {
			return userService.editUser(data);
		} catch (Exception e) {
			rm = new SimpleResponse();
			rm.setStatusCode(500);
			rm.setStatusDesc("FAIL");
			rm.setContents(e.getMessage());			
		}

		return rm;
	}


	@PostMapping("/delete")
	public SimpleResponse deleteUser(@RequestBody Object data) throws Exception{
		SimpleResponse rm = new SimpleResponse();

		try {
			return userService.deleteUser(data);
		} catch (Exception e) {
			rm = new SimpleResponse();
			rm.setStatusCode(500);
			rm.setStatusDesc("FAIL");
			rm.setContents(e.getMessage());			
		}

		return rm;
	}

	
}
