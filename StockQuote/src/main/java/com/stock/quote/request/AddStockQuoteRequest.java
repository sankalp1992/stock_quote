/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock.quote.request;

import lombok.Data;

@Data
public class AddStockQuoteRequest {
    
    String stockQuote;
    
    String companyName;
    
    String date;
    
    double price;
    
    String currency;
    
    boolean stockAvailability;
}
