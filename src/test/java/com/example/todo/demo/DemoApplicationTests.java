package com.example.todo.demo;

import com.example.todo.demo.users.User;
import com.example.todo.demo.users.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@WebMvcTest
//@ExtendWith(SpringExtension.class)
@SpringBootTest
class DemoApplicationTests {


    //@Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserService service;
    //@Autowired
    //UserService service;
    UUID createdUUID;

    @BeforeEach
    public void load(){
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();

        Mockito.when(service.getUsers())
                .thenReturn(Arrays.asList(new User("toto", "titi"),
                                        new User("toto", "titi"),
                                        new User("toto", "titi")));

        createdUUID = UUID.randomUUID();
        Mockito.when(service.addUser(Mockito.any())).thenReturn(createdUUID);
    }


    @Test
    void testCreateUser() throws Exception {

        this.mockMvc.perform(
                        post("/users" )
                                .content("{\"name\": \"toto\"}")
                                .header("Content-Type", "application/json"))
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(header().exists("Location"))
                        .andExpect(mvcResult -> Assertions.assertEquals("/users/" + createdUUID, mvcResult.getResponse().getHeader("Location") ));
    }

    @Test
    void testGetAllUser() throws Exception {

        this.mockMvc.perform(
                get("/users" ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    ObjectMapper mapper = new ObjectMapper();
                    java.util.List<User> userList = Arrays.asList(mapper.readValue(mvcResult.getResponse().getContentAsString(), User[].class));
                    Assert.assertEquals(3, userList.size());
                });
    }


}
