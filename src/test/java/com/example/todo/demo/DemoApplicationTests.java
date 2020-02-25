package com.example.todo.demo;

import com.example.todo.demo.users.UserDTO;
import com.example.todo.demo.users.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.matches;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@WebMvcTest
//@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) //Permet de réinitialiser la BDD entre chaque test
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private DataSource dataSource;

    //@Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService service;
    //@Autowired
    //UserService service;
    UUID createdUUID;

    @BeforeEach
    public void load(){
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();

        /*
        service.addUser(new UserDTO("toto", "titi"));
        service.addUser(new UserDTO("toto2", "titi2"));
        service.addUser(new UserDTO("toto3", "titi3"));
*/

                /*
        Mockito.when(service.getUsers())
                .thenReturn(Arrays.asList(new UserDTO("toto", "titi"),
                                        new UserDTO("toto", "titi"),
                                        new UserDTO("toto", "titi")));

        createdUUID = UUID.randomUUID();
        Mockito.when(service.addUser(Mockito.any())).thenReturn(createdUUID);
        */

    }


    @Test
    @Sql({"data_inte.sql"})
    void testCreateUser() throws Exception {

        this.mockMvc.perform(
                        post("/users" )
                                .content("{\"name\": \"toto\"}")
                                .header("Content-Type", "application/json; charset=UTF-8"))
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(header().exists("Location"))
                        .andExpect(mvcResult -> Assertions.assertTrue(mvcResult.getResponse()
                                                                                .getHeader("Location")
                                                                                .replace("/users/", "")
                                                                                .matches("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89ab][a-f0-9]{3}-[0-9a-f]{12}")));

        this.mockMvc.perform(
                get("/users" ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    ObjectMapper mapper = new ObjectMapper();
                    java.util.List<UserDTO> userList = Arrays.asList(mapper.readValue(mvcResult.getResponse().getContentAsString(), UserDTO[].class));
                    Assert.assertEquals(7, userList.size());
                });

    }

    @Test
    void testGetAllUser() throws Exception {

        this.mockMvc.perform(
                get("/users" ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    ObjectMapper mapper = new ObjectMapper();
                    java.util.List<UserDTO> userList = Arrays.asList(mapper.readValue(mvcResult.getResponse().getContentAsString(), UserDTO[].class));
                    Assert.assertEquals(3, userList.size());
                });
    }


}
