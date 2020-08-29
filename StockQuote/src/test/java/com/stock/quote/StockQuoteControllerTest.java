/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock.quote;

import com.stock.quote.model.Company;
import com.stock.quote.model.StockQuote;
import com.stock.quote.request.AddStockQuoteRequest;
import com.stock.quote.service.CompanyService;
import com.stock.quote.service.StockQuoteService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StockQuoteController.class)
public class StockQuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockQuoteService stockQuoteService;

    @MockBean
    private CompanyService companyService;

    String exampleCourseJson = "{\"stockQuote\":\"IBM\", \"companyName\":\"IBM Tech\", \"date\":\"12-04-20\",\"price\":\"400.20\", \"currency\":\"INR\", \"stockAvailability\":false}";

    @Test
    public void createStudentCourse() throws Exception {

        Company company = new Company();
        company.setCompanyId(2);

        StockQuote stockQuote = new StockQuote();

        Mockito.when(
                companyService.findByCompanyName(Mockito.anyString())).thenReturn(company);

        Mockito.when(
                stockQuoteService.findByStockNameAndCompanyId(Mockito.anyString(), Mockito.anyInt())).thenReturn(null);

        Mockito.when(
                stockQuoteService.addStockQuote(Mockito.any(AddStockQuoteRequest.class), Mockito.any(Company.class))).thenReturn(stockQuote);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/create")
                .accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{code:2000}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void queryAvailableStockQuote() throws Exception {

        StockQuote stockQuote = new StockQuote();
        Company company = new Company();
        company.setCompanyName("HCL Test");
        stockQuote.setCompany(company);
        stockQuote.setStockName("HCL");
        stockQuote.setStockCurrency("INR");
        stockQuote.setStockDateTime("24-04-20");
        stockQuote.setStockPrice(200.20);
        Mockito.when(
                stockQuoteService.findByStockNameAndAvailability(Mockito.anyString())).thenReturn(stockQuote);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/stockQuote?HCL").accept(
                        MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{stockQuote:HCL}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void queryNonAvailableStockQuote() throws Exception {

        Mockito.when(
                stockQuoteService.findByStockNameAndAvailability(Mockito.anyString())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/stockQuote?IBM").accept(
                        MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{code:3000}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

}
