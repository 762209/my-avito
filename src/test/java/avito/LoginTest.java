package avito;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-dev.properties")
@Sql(value = {"/create-user-before.sql",
			  "/create-ad-before.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql",
			  "/create-ad-after.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class LoginTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() throws Exception{
		this.mockMvc.perform(get("/home"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("Авто")))
					.andExpect(content().string(containsString("Квартиры")))
					.andExpect(content().string(containsString("Услуги")))
					.andExpect(content().string(containsString("Всего объявлений")));
	}
	@Test
	public void acessDeniedTest() throws Exception {
		this.mockMvc.perform(get("/profile"))
					.andDo(print())
					.andExpect(status().is3xxRedirection())
					.andExpect(redirectedUrl("http://localhost/login"));
	}
	@Test
	public void correctLoginTest() throws Exception {
		this.mockMvc.perform(formLogin().user("pavel").password("123"))
					.andExpect(status().is3xxRedirection())
					.andExpect(redirectedUrl("/home"));
	}
}
