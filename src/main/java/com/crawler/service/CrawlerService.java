package com.crawler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.util.CrawlerUtility;

@Service
public class CrawlerService {

	@Autowired
	CrawlerUtility webCrawler;
	
	public List<List<String>> getAllSEOVal(String URL,String key){
		return webCrawler.getArticles(URL, key);
	}
}
