/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock.quote.response;

import java.util.List;
import lombok.Data;

@Data
public class StockQuoteResponse {

    private List<Stock> stocks;
}
