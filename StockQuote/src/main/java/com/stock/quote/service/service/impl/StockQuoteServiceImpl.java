/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock.quote.service.service.impl;

import com.stock.quote.constants.ApplicationConstants;
import com.stock.quote.model.Company;
import com.stock.quote.model.StockQuote;
import com.stock.quote.repository.StockQuoteRepository;
import com.stock.quote.request.AddStockQuoteRequest;
import com.stock.quote.service.StockQuoteService;
import com.stock.quote.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockQuoteServiceImpl implements StockQuoteService {

    @Autowired
    private StockQuoteRepository stockQuoteRepository;

    @Override
    public StockQuote findByStockNameAndCompanyId(String stockName, Integer companyId) {
        return stockQuoteRepository.findByStockNameAndCompanyId(stockName, companyId);
    }

    @Override
    public StockQuote addStockQuote(AddStockQuoteRequest addStockQuoteRequest, Company company) {
        StockQuote stockQuote = new StockQuote();
        stockQuote.setCompany(company);
        stockQuote.setStatus(true);
        stockQuote.setStockCurrency(addStockQuoteRequest.getCurrency());
        stockQuote.setStockDateTime(addStockQuoteRequest.getDate());
        stockQuote.setStockName(addStockQuoteRequest.getStockQuote());
        stockQuote.setStockPrice(addStockQuoteRequest.getPrice());
        stockQuote.setStockTimestamp(Util.stringDateToEpoch(addStockQuoteRequest.getDate(), ApplicationConstants.DATE_CONSTANTS.COMMON_DATE_FORMAT));
        stockQuote.setStockAvailability(addStockQuoteRequest.isStockAvailability());
        stockQuoteRepository.save(stockQuote);
        return stockQuote;
    }

    @Override
    public StockQuote findByStockNameAndAvailability(String stockQuote) {
        return stockQuoteRepository.findByStockNameAndAvailability(stockQuote);
    }
}
