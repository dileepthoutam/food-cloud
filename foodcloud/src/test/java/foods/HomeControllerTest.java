package foods;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import foods.web.WebConfig;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(excludeAutoConfiguration = SecurityAutoConfiguration.class)   // <1>
@ContextConfiguration(classes = {WebConfig.class})
@Disabled
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;   // <2>

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))    // <3>
                .andExpect(status().isOk())  // <4>
                .andExpect(view().name("home"))  // <5>
                .andExpect(content().string(           // <6>
                        containsString("Welcome to...")));
    }

}
