/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock.quote.response;

import lombok.Data;

@Data
public class Stock {

    String stockQuote;

    String companyName;

    String date;

    double price;

    String currency;
}
