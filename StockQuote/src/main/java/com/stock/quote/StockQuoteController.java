/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock.quote;

import com.stock.quote.constants.ApplicationConstants;
import com.stock.quote.model.Company;
import com.stock.quote.model.StockQuote;
import com.stock.quote.request.AddStockQuoteRequest;
import com.stock.quote.response.CommonResponse;
import com.stock.quote.response.Stock;
import com.stock.quote.service.CompanyService;
import com.stock.quote.service.StockQuoteService;
import com.stock.quote.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockQuoteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockQuoteController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private StockQuoteService stockQuoteService;

    @PostMapping(path = "/create") // Map ONLY POST Requests
    @ResponseBody
    public Object createStockQuote(@RequestBody AddStockQuoteRequest addStockQuoteRequest) {

        LOGGER.info("Request URL : /create");
        CommonResponse commonResponse = new CommonResponse(ApplicationConstants.RESPONSE_CODES.ERROR, "Failed to add stock quote");
        //check if company is avalable
        Company company = companyService.findByCompanyName(addStockQuoteRequest.getCompanyName());
        if (company == null) {
            //if not the add new company
            company = companyService.addCompany(addStockQuoteRequest.getCompanyName());
        }

        StockQuote stockQuote = stockQuoteService.findByStockNameAndCompanyId(addStockQuoteRequest.getStockQuote(), company.getCompanyId());
        if (stockQuote != null) {
            commonResponse = new CommonResponse(ApplicationConstants.RESPONSE_CODES.ERROR, "Stock already exists");
        } else {
            stockQuote = stockQuoteService.addStockQuote(addStockQuoteRequest, company);
            if (stockQuote != null) {
                LOGGER.info("Stock quote added");
                commonResponse = new CommonResponse(ApplicationConstants.RESPONSE_CODES.SUCCESS, "Stock quote added successfully");
            }
        }
        return commonResponse;
    }

    @GetMapping(path = "/stockQuote") // Map ONLY POST Requests
    @ResponseBody
    public Object createStockQuote(HttpServletRequest httpServletRequest) {

        LOGGER.info("Request URL : /create  " + httpServletRequest.getQueryString());
        CommonResponse commonResponse = new CommonResponse(ApplicationConstants.RESPONSE_CODES.ERROR, "Query failed");
        String queryString = httpServletRequest.getQueryString();
        if (queryString != null && !queryString.isEmpty()) {
            String stockQuotes[] = queryString.split(",");
            if (stockQuotes.length > 10) {
                commonResponse = new CommonResponse(ApplicationConstants.RESPONSE_CODES.ERROR, "Query can not have more than 10 stock");
            } else {
                List<Stock> stocks = new ArrayList<>();
                for (int i = 0; i < stockQuotes.length; i++) {
                    StockQuote stockQuote = stockQuoteService.findByStockNameAndAvailability(stockQuotes[i]);
                    if (stockQuote != null) {
                        Stock stock = new Stock();
                        stock.setCompanyName(stockQuote.getCompany().getCompanyName());
                        stock.setCurrency(stockQuote.getStockCurrency());
                        stock.setDate(Util.dateToString(stockQuote.getStockTimestamp(), ApplicationConstants.DATE_CONSTANTS.COMMON_DATE_FORMAT));
                        stock.setPrice(stockQuote.getStockPrice());
                        stock.setStockQuote(stockQuote.getStockName());
                        stocks.add(stock);
                    }
                }
                if (stocks.isEmpty()) {
                    commonResponse = new CommonResponse(ApplicationConstants.RESPONSE_CODES.ERROR, "Stock quotes not available");
                } else {
                    return stocks;
                }
            }
        }
        return commonResponse;
    }
}
