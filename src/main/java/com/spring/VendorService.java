/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author sunny
 */

@Service
public class VendorService {

    public static final int PAGE_SIZE = 10;
    public static final int CONTINUE = 1;
    public static final int QUIT = 2;

    private final String uri = "https://data.sfgov.org/resource/bbb8-hzi6.json";
    private final RestTemplate restTemplate;

    @Autowired
    private VendorRepository repository;

    public VendorService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void run() throws Exception {
        List<Vendor> vendors = getAllVendors();

        //System.out.println("getAllVendors size: " + vendors.size());

        repository.saveAll(vendors);

        int today = getDate();
        //int today = 2;

        List<Vendor> all = repository.findVendorByDayorderOrderByApplicantAsc(today);
        long count = all.size();
        int pages = (int) Math.ceil(count / (float) PAGE_SIZE);

        System.out.println("Vendors Opened today: " + all.size());
        System.out.println("Total pages: " + pages);

        int countVendor = display(today, 0, 0);

        Scanner scanner = new Scanner(System.in);

        for (int i = 1; i < pages; ) {
            System.out.print("Please enter your selection( 1 continue, 2 quit):\t");
            int input = scanner.nextInt(); //todo handle invalid case
            if (input == 1) {
                scanner.nextLine();
                System.out.printf("\npage num: %s%n", i);
                countVendor = display(today, i, countVendor);
                i++;
            } else if (input == 2) break;
            else {
                System.out.println("Invalid option");
                continue;
            }
        }
        scanner.close();
    }

    private int display(int match, int pageNum, int idx) {

        List<Vendor> next = repository.findVendorByDayorderOrderByApplicantAsc(match, PageRequest.of(pageNum, PAGE_SIZE));
        for (Vendor v : next) {
            System.out.println(++idx + "\t" + v.toString());
        }
        return idx;
    }

    public List<Vendor> getAllVendors() throws Exception {
        ParameterizedTypeReference<PageImpl<Vendor>> responseType = new ParameterizedTypeReference<PageImpl<Vendor>>() {
        };
        ResponseEntity<Vendor[]> responseEntity = restTemplate.getForEntity(uri, Vendor[].class, responseType);
        return Arrays.asList(responseEntity.getBody());
    }

    private int getDate() {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("u-E-k-a"); //
        String date = DATE_FORMAT.format(new Date());
        System.out.println("**Today : " + date);
        if (date.charAt(0) == '7') return Integer.valueOf("0");
        return Integer.valueOf("" + date.charAt(0));
    }
}
