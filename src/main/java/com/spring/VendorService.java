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
import java.util.*;

/**
 *
 */
@Service
public class VendorService {

    public static final int PAGE_SIZE = 10; //number of pages displayed at once
    public static final int CONTINUE = 1;   //user input
    public static final int QUIT = 2;   //user input

    private final String uri = "https://data.sfgov.org/resource/bbb8-hzi6.json";
    private final RestTemplate restTemplate;

    @Autowired
    private VendorRepository repository;

    public VendorService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void show() throws Exception {

        int today = getTodayDate();
        List<Vendor> openList = new ArrayList<>();

        for (Vendor v : getAllVendors()) {
            if (v.getDayOrder() == today && v.getOpen()) {
                openList.add(v);
            }
        }
        if (openList.isEmpty()) {
            return; //todo, display message            
        }
        repository.saveAll(openList);

        long count = openList.size();
        int pages = (int) Math.ceil(count / (float) PAGE_SIZE);

        System.out.println("SHOW OPEN FOOD TRUCKS: " + openList.size());
        //System.out.println("Food trucks opened today: " + all.size());
        System.out.println("Total pages: " + pages);

        int countVendor = display(today, 0, 0);

        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i < pages; ) {
            System.out.printf("%nPlease enter %s to view next page or %s to quit:", CONTINUE, QUIT);
            try {
                int input = scanner.nextInt(); //todo handle invalid case
                if (input == 1) {
                    //System.out.printf("\npage num: %s%n", i);
                    countVendor = display(today, i, countVendor);
                    i++;
                } else if (input == 2) {
                    break;
                } else {
                    System.out.print("Invalid input - ");
                    //continue;
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input - ");
            }

            scanner.nextLine();
        }
        scanner.close();
    }

    public List<Vendor> getAllVendors() throws Exception {
        ParameterizedTypeReference<PageImpl<Vendor>> responseType = new ParameterizedTypeReference<PageImpl<Vendor>>() {
        };
        ResponseEntity<Vendor[]> responseEntity = restTemplate.getForEntity(uri, Vendor[].class, responseType);
        return Arrays.asList(responseEntity.getBody());
    }

    private int display(int dayMatch, int pageNum, int recordIndex) {
        System.out.printf("\npage: %s%n", pageNum + 1);
        System.out.printf("\t%-30.30s  %-30.30s %-5.30s %-5.30s %-5.30s%n", "NAME", "ADDRESS", "HOURS", "", "STATUS");
        List<Vendor> next = repository.findVendorByDayorderOrderByApplicantAsc(dayMatch, PageRequest.of(pageNum, PAGE_SIZE));
        for (Vendor v : next) {
            System.out.println(++recordIndex + "\t" + v.toString());
        }
        return recordIndex;
    }

    private int getTodayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("u-E-Ka"); //
        String date = sdf.format(new Date());
        System.out.println("**Today's date : " + date);
        if (date.charAt(0) == '7') { //Sunday
            return Integer.valueOf("0");
        }
        return Integer.valueOf("" + date.charAt(0));
    }
}
