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
 *
 */
@Repository
public interface VendorRepository extends PagingAndSortingRepository<Vendor, Integer> {
    public List<Vendor> findVendorByDayorderOrderByApplicantAsc(int dayorder, Pageable pageable);
}
