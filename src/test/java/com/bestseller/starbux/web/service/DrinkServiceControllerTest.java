package com.bestseller.starbux.web.service;

import com.bestseller.starbux.business.service.DrinkService;
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
@WebMvcTest(DrinkServiceController.class)
public class DrinkServiceControllerTest {

    @MockBean
    private DrinkService drinkService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllDrinks_shouldReturnHttpStatusOk() throws Exception{
        MockHttpServletResponse response = mvc.perform(get("/drinks")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        verify(drinkService).getAllDrinks();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}