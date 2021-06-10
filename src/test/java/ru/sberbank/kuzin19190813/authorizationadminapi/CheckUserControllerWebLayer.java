package ru.sberbank.kuzin19190813.authorizationadminapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.controller.UsersController;
import ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.body.OkBody;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@Sql(scripts = {"/schema.sql", "/data.sql"}, config = @SqlConfig(encoding = "UTF-8"))
public class CheckUserControllerWebLayer {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnOKMessage() throws Exception {
		mockMvc.perform(get("/ping")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(is(new OkBody("OK").toString())));
	}
}
