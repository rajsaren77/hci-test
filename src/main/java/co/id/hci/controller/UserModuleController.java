package co.id.hci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.hci.model.SimpleResponse;
import co.id.hci.services.UserModuleService;

@RestController
@RequestMapping("/api/userModule")
public class UserModuleController {
	
	@Autowired
	UserModuleService userModuleService;

	@GetMapping("/{id}")
	public SimpleResponse getUserModuleById(@PathVariable("id") Long userId) throws Exception{	
		SimpleResponse rm = new SimpleResponse();
		
		try {
			return userModuleService.getUserModuleById(userId);
		} catch (Exception e) {
			rm = new SimpleResponse();
			rm.setStatusCode(500);
			rm.setStatusDesc("FAIL");
			rm.setContents(e.getMessage());
		}

		return rm;
	}


	@PostMapping("/add")
	public SimpleResponse addUserModule(@RequestBody Object data) throws Exception{
		SimpleResponse rm = new SimpleResponse();

		try {
			return userModuleService.addUserModule(data);
		} catch (Exception e) {
			rm = new SimpleResponse();
			rm.setStatusCode(500);
			rm.setStatusDesc("FAIL");
			rm.setContents(e.getMessage());			
		}

		return rm;
	}


	@PostMapping("/edit")
	public SimpleResponse editUserModule(@RequestBody Object data) throws Exception{
		SimpleResponse rm = new SimpleResponse();

		try {
			return userModuleService.editUserModule(data);
		} catch (Exception e) {
			rm = new SimpleResponse();
			rm.setStatusCode(500);
			rm.setStatusDesc("FAIL");
			rm.setContents(e.getMessage());			
		}

		return rm;
	}


	@PostMapping("/delete")
	public SimpleResponse deleteUserModule(@RequestBody Object data) throws Exception{
		SimpleResponse rm = new SimpleResponse();

		try {
			return userModuleService.deleteUserModule(data);
		} catch (Exception e) {
			rm = new SimpleResponse();
			rm.setStatusCode(500);
			rm.setStatusDesc("FAIL");
			rm.setContents(e.getMessage());			
		}

		return rm;
	}

	
}
