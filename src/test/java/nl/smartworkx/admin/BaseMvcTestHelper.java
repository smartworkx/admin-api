package nl.smartworkx.admin;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 */
public class BaseMvcTestHelper {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    protected ResultActions performGet(String url) throws Exception {
        return mvc().perform(MockMvcRequestBuilders.get(url)).andDo(print());
    }

    protected MockMvc mvc() {

        if (mvc == null) {
            mvc = MockMvcBuilders
                    .webAppContextSetup(context)
                    .build();
        }
        return mvc;
    }
}
