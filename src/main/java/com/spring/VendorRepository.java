/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sunny
 */
@Repository
public interface VendorRepository extends PagingAndSortingRepository<Vendor, Integer> {

    // Enabling ORDER BY for a query
    public List<Vendor> findVendorByDayorderOrderByApplicantAsc(int dayorder);

    public List<Vendor> findVendorByDayorderOrderByApplicantAsc(int dayorder, Pageable pageable);

}
