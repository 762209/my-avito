package avito;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("pavel")
@TestPropertySource("/application-dev.properties")
@Sql(value = {"/create-user-before.sql",
			  "/create-ad-before.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql",
			  "/create-ad-after.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void mainPageTest() throws Exception {
		this.mockMvc.perform(get("/home"))
					.andDo(print())
					.andExpect(authenticated())
					.andExpect(xpath("/html/body/nav/div/span").string("Павел Сергеевич"));
	}
	@Test
	public void adTableTest() throws Exception {
		this.mockMvc.perform(get("/home"))
					.andDo(print())
					.andExpect(authenticated())
					.andExpect(xpath("/html/body/main/div[2]/div[@class='addItem']").nodeCount(3));
	}
	@Test
	public void filterAdTest() throws Exception {
		this.mockMvc.perform(get("/home").param("name", "собака"))
					.andDo(print())
					.andExpect(authenticated())
					.andExpect(xpath("/html/body/main/div[1]/div[1]").exists());
	}
}
