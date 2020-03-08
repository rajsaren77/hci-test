package co.id.hci.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.id.hci.entity.UserModule;
import co.id.hci.model.SimpleResponse;
import co.id.hci.repository.UserModuleRepository;
import co.id.hci.utils.BasicUtils;

@Service
public class UserModuleService {

	@Autowired
	EntityManager em;
	
	@Autowired
	UserModuleRepository userModuleRepository;

	@SuppressWarnings("unchecked")
    public SimpleResponse getUserModuleById(Long userId) throws Exception{
        SimpleResponse rm = new SimpleResponse();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT B.module_name, B.module_order ");
			sb.append(" FROM m_user A ");
			sb.append(" INNER JOIN m_user_module B ");
			sb.append(" ON A.user_id = B.user_id ");
			sb.append(" WHERE A.user_id = :userId ");
			sb.append(" ORDER BY module_order ASC ");
			
			Query q = em.createNativeQuery(sb.toString());
			q.setParameter("userId", userId);
			
			List<Object[]> list = q.getResultList();
			map.put("modules", BasicUtils.createListOfMapFromObjectArray(list, "moduleName", "moduleOrder"));
			
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
    public SimpleResponse addUserModule(Object req) throws Exception{
    		ObjectMapper om = new ObjectMapper();
    		Map<String, Object> data = om.convertValue(req, Map.class);
		SimpleResponse rm = new SimpleResponse();
		try {
			
			UserModule usrModule = new UserModule();
			usrModule.setUserId(Long.valueOf(data.get("userId").toString()));
			usrModule.setModuleName((String)data.get("moduleName"));
			usrModule.setModuleOrder(Long.valueOf(data.get("moduleOrder").toString()));

			userModuleRepository.save(usrModule);

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
    public SimpleResponse editUserModule(Object req) throws Exception{
	    	ObjectMapper om = new ObjectMapper();
		Map<String, Object> data = om.convertValue(req, Map.class);
		SimpleResponse rm = new SimpleResponse();
		try {

            boolean isExists = userModuleRepository.existsById(Long.valueOf(data.get("id").toString()));
            if(!isExists){
                rm.setStatusCode(300);
                rm.setStatusDesc("Data not exists");
                rm.setContents(new HashMap<String, Object>());	
                return rm;		    
            }
            
            UserModule usrModule = userModuleRepository.findUserModuleById(Long.valueOf(data.get("id").toString()));
            usrModule.setModuleName((String)data.get("moduleName"));
			usrModule.setModuleOrder(Long.valueOf(data.get("moduleOrder").toString()));

			userModuleRepository.save(usrModule);

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
    public SimpleResponse deleteUserModule(Object req) throws Exception{
	    	ObjectMapper om = new ObjectMapper();
		Map<String, Object> data = om.convertValue(req, Map.class);
		SimpleResponse rm = new SimpleResponse();
		try {
            boolean isExists = userModuleRepository.existsById(Long.valueOf(data.get("id").toString()));
            if(!isExists){
                rm.setStatusCode(300);
                rm.setStatusDesc("Data not exists");
                rm.setContents(new HashMap<String, Object>());	
                return rm;		    
            }

			userModuleRepository.deleteById(Long.valueOf(data.get("id").toString()));

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