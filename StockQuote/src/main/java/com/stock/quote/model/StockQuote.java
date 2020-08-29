/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock.quote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stock_quote")
@Data
public class StockQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Integer stockId;

    @ManyToOne
    @JoinColumn(name = "stock_company_id", nullable = false, referencedColumnName = "company_id")
    private Company company;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "stock_timestamp")
    private long stockTimestamp;

    @Column(name = "stock_date_time")
    private String stockDateTime;

    @Column(name = "stock_price")
    private double stockPrice;

    @Column(name = "stock_currency")
    private String stockCurrency;

    @Column(name = "stock_availability")
    private boolean stockAvailability;

    private boolean status;

}
