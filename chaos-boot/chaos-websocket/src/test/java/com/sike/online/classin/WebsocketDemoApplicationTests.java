package com.sike.online.classin;

import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebsocketDemoApplicationTests {
    @Autowired
    private org.springframework.web.context.WebApplicationContext context;
    MockMvc mockMvc;

    @Before
    public void before() {
        //可以对所有的controller来进行测试
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testIndex() throws Exception {
        for (int i = 1; i <= 5; i++) {
            String classid = i + "";
            for (int j = 1; j <= 10; j++) {
                String userid = j + "";
                String uri = "/okya/classin/index/" + classid + "/1" + userid;
                JSONObject json = new JSONObject();
                json.put("lessonUnique", 1);
                String paraJson = JSONObject.toJSONString(json);
                String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(paraJson)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
            }
        }
        Thread.sleep(100000);

    }

}
