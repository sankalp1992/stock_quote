/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock.quote.service.service.impl;

import com.stock.quote.constants.ApplicationConstants;
import com.stock.quote.model.Company;
import com.stock.quote.repository.CompanyRepository;
import com.stock.quote.service.CompanyService;
import com.stock.quote.util.Util;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company findByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }

    @Override
    public Company addCompany(String companyName) {
        Company company = new Company();
        company.setCompanyName(companyName);
        company.setCompanyRegDate(Util.dateToString(new DateTime().getMillis(), ApplicationConstants.DATE_CONSTANTS.COMMON_DATE_FORMAT));
        company.setStatus(true);
        companyRepository.save(company);
        return company;
    }

}
