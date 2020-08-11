package com.bestseller.starbux.web.service;

import com.bestseller.starbux.business.domain.CustomerRequest;
import com.bestseller.starbux.business.domain.OrderDetailsRequest;
import com.bestseller.starbux.business.service.OrderService;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderServiceController.class)
public class OrderServiceControllerTest {

    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<OrderDetailsRequest> detailsRequestJacksonTester;
    private JacksonTester<CustomerRequest> customerRequestJacksonTester;

    @Before
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void createOrder_shouldReturnHttpStatusCreated() throws Exception{

        MockHttpServletResponse response = mvc.perform(post("/orders")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        verify(orderService).create();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void addItem_shouldReturnHttpStatusOk() throws Exception{
        Long id = 1L;
        OrderDetailsRequest orderDetailsRequest = new OrderDetailsRequest(1, new ArrayList<>());
        orderDetailsRequest.getToppingIds().add(1);
        orderDetailsRequest.getToppingIds().add(2);
        String json = detailsRequestJacksonTester.write(orderDetailsRequest).getJson();

        MockHttpServletResponse response = mvc.perform(post("/orders/{id}/drinks", id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void getOrder_shouldReturnHttpStatusOk() throws Exception{
        Long id = 0L;

        MockHttpServletResponse response = mvc.perform(get("/orders/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void Finalize_shouldReturnHttpStatusOk() throws Exception{
        Long id = 1L;
        String json = customerRequestJacksonTester.write(new CustomerRequest("tawfik90")).getJson();

        MockHttpServletResponse response = mvc.perform(post("/orders/{id}/finalize", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}