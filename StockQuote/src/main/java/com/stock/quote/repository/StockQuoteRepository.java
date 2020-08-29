/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock.quote.repository;

import com.stock.quote.model.StockQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockQuoteRepository extends JpaRepository<StockQuote, Integer> {
    
    @Query(value = "select * from stock_quote where stock_name = ?1 and stock_company_id = ?2 and status = 1", nativeQuery = true)
    public StockQuote findByStockNameAndCompanyId(String stockName, int companyId);

    @Query(value = "select * from stock_quote where stock_name = ?1 and stock_availability = 1 and status = 1", nativeQuery = true)
    public StockQuote findByStockNameAndAvailability(String stockQuote);

}
