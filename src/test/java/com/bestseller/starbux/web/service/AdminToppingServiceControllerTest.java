package com.bestseller.starbux.web.service;

import com.bestseller.starbux.business.domain.ItemRequest;
import com.bestseller.starbux.business.service.ToppingService;
import com.bestseller.starbux.data.entity.Topping;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminToppingServiceController.class)
public class AdminToppingServiceControllerTest {

    @MockBean
    private ToppingService toppingService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<ItemRequest> itemRequestJacksonTester;

    @Before
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void add_shouldReturnHttpStatusOk () throws Exception {
        ItemRequest itemRequest = new ItemRequest("Milk", 2.00);
        given(toppingService.add(itemRequest)).willReturn(new Topping(itemRequest.getName(), itemRequest.getPrice(), 1));

        MockHttpServletResponse response = mvc.perform(post("/admin/toppings")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemRequestJacksonTester.write(itemRequest).getJson()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void update_shouldReturnHttpStatusOk () throws Exception {
        Integer id = 1;
        ItemRequest itemRequest = new ItemRequest("Milk", 2.00);
        given(toppingService.add(itemRequest)).willReturn(new Topping(itemRequest.getName(), itemRequest.getPrice(), 1));

        MockHttpServletResponse response = mvc.perform(put("/admin/toppings/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemRequestJacksonTester.write(itemRequest).getJson()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void delete_shouldHttpStatusOk() throws Exception {
        Integer id = 1;

        MockHttpServletResponse response = mvc.perform(delete("/admin/toppings/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}