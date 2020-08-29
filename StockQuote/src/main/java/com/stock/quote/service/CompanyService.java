/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock.quote.service;

import com.stock.quote.model.Company;

public interface CompanyService {

    public Company findByCompanyName(String companyName);

    public Company addCompany(String companyName);
    
}
