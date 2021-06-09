package ru.sberbank.kuzin19190813.authorizationadminapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@SpringBootTest(classes = {AuthorizationAdminApiApplicationTests.class})
public class AuthorizationAdminApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnOKMessage() throws Exception {
		mockMvc.perform(get("/admin/api/v1/user/ping/")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(is("OK")));
	}
}
