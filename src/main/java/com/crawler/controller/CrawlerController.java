package com.crawler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.service.CrawlerService;
import com.crawler.util.CrawlerUtility;

@RestController
@RequestMapping("/crawl")
public class CrawlerController {

    @Autowired
    private CrawlerService crawlerService;

    @ResponseBody
    @RequestMapping(value = "/getSEOResponse", method = RequestMethod.GET)
    public ResponseEntity<List<List<String>>> getAll(@RequestParam String url,@RequestParam String key ) {
//        webCrawler.getPageLinks(url,  key);
        List<List<String>> info=crawlerService.getAllSEOVal(url, key);
    	return ResponseEntity.ok(info);
    }

}