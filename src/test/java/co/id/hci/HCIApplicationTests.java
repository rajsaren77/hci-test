package co.id.hci;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.id.hci.HCIConfig;
import co.id.hci.model.SimpleResponse;
import co.id.hci.services.UserModuleService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HCIConfig.class})
public class HCIApplicationTests {

	Logger log = LoggerFactory.getLogger(HCIApplicationTests.class);
	
	@Autowired
	UserModuleService userModuleService;
	
	@Test
	@SuppressWarnings("unchecked")
	public void eachCase() throws Exception {
		SimpleResponse simpleResponse = userModuleService.getUserModuleById(1L);
		Map<String, Object> map = (Map<String, Object>) simpleResponse.getContents();
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("modules");

		/** size */
		assertSame(5L, Long.valueOf(list.size()));
		
		/** general assert */
		for (Map<String, Object> data : list) {
			assertTrue(data.containsKey("moduleName"));
			assertTrue(data.containsKey("moduleOrder"));
		}
	}

}
