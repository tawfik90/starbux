package com.bestseller.starbux.web.service;

import com.bestseller.starbux.business.service.ToppingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(ToppingServiceController.class)
public class ToppingServiceControllerTest {

    @MockBean
    private ToppingService toppingService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllToppings_shouldReturnHttpStatusOk() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/toppings")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        verify(toppingService).getAllToppings();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}