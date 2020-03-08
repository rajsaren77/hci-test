package co.id.hci.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.id.hci.entity.User;
import co.id.hci.model.SimpleResponse;
import co.id.hci.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

    public SimpleResponse getUser() throws Exception{
        SimpleResponse rm = new SimpleResponse();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			rm = new SimpleResponse();
			map = new HashMap<String, Object>();
			map.put("userList", userRepository.findAll());
			
			rm.setStatusCode(200);
			rm.setStatusDesc("SUCCESS");
			rm.setContents(map);
			
		} catch (Exception e) {
			rm = new SimpleResponse();
			rm.setStatusCode(500);
			rm.setStatusDesc("FAIL");
			rm.setContents(e.getMessage());
		}

		return rm;
    }

    @SuppressWarnings("unchecked")
    public SimpleResponse addUser(Object req) throws Exception{
    		ObjectMapper om = new ObjectMapper();
    		Map<String, Object> data = om.convertValue(req, Map.class);
		SimpleResponse rm = new SimpleResponse();
		try {
			User usr = new User();
			usr.setUsername((String)data.get("username"));
			usr.setPassword((String)data.get("password"));

			userRepository.save(usr);

			rm = new SimpleResponse();
			rm.setStatusCode(200);
			rm.setStatusDesc("SUCCESS");
			rm.setContents(new HashMap<String, Object>());

		} catch (Exception e) {
			rm = new SimpleResponse();
			rm.setStatusCode(500);
			rm.setStatusDesc("FAIL");
			rm.setContents(e.getMessage());			
		}

		return rm;

    }

    @SuppressWarnings("unchecked")
    public SimpleResponse editUser(Object req) throws Exception{
	    	ObjectMapper om = new ObjectMapper();
		Map<String, Object> data = om.convertValue(req, Map.class);
		SimpleResponse rm = new SimpleResponse();
		try {

            boolean isExists = userRepository.existsById(Long.valueOf(data.get("id").toString()));
            if(!isExists){
                rm.setStatusCode(300);
                rm.setStatusDesc("Data not exists");
                rm.setContents(new HashMap<String, Object>());	
                return rm;		    
            }
            
            User usr = userRepository.findUserById(Long.valueOf(data.get("id").toString()));
            usr.setUsername((String)data.get("username"));
            usr.setPassword((String)data.get("password"));

			userRepository.save(usr);

			rm = new SimpleResponse();
			rm.setStatusCode(200);
			rm.setStatusDesc("SUCCESS");
			rm.setContents(new HashMap<String, Object>());

		} catch (Exception e) {
			rm = new SimpleResponse();
			rm.setStatusCode(500);
			rm.setStatusDesc("FAIL");
			rm.setContents(e.getMessage());			
		}

		return rm;
    }

    @SuppressWarnings("unchecked")
    public SimpleResponse deleteUser(Object req) throws Exception{
	    	ObjectMapper om = new ObjectMapper();
		Map<String, Object> data = om.convertValue(req, Map.class);
		SimpleResponse rm = new SimpleResponse();
		try {
            boolean isExists = userRepository.existsById(Long.valueOf(data.get("id").toString()));
            if(!isExists){
                rm.setStatusCode(300);
                rm.setStatusDesc("Data not exists");
                rm.setContents(new HashMap<String, Object>());	
                return rm;		    
            }

			userRepository.deleteById(Long.valueOf(data.get("id").toString()));

			rm = new SimpleResponse();
			rm.setStatusCode(200);
			rm.setStatusDesc("SUCCESS");
			rm.setContents(new HashMap<String, Object>());

		} catch (Exception e) {
			rm = new SimpleResponse();
			rm.setStatusCode(500);
			rm.setStatusDesc("FAIL");
			rm.setContents(e.getMessage());			
		}

		return rm;

    }

}