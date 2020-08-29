/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock.quote.service;

import com.stock.quote.model.Company;
import com.stock.quote.model.StockQuote;
import com.stock.quote.request.AddStockQuoteRequest;

public interface StockQuoteService {

    public StockQuote findByStockNameAndCompanyId(String stockName, Integer companyId);

    public StockQuote addStockQuote(AddStockQuoteRequest addStockQuoteRequest, Company company);

    public StockQuote findByStockNameAndAvailability(String stockQuote);
    
}
