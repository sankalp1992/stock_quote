/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock.quote.repository;

import com.stock.quote.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query(value = "select * from company where company_name = ?1 and status = 1", nativeQuery = true)
    public Company findByCompanyName(String companyName);
}
