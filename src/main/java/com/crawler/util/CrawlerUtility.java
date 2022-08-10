package com.crawler.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CrawlerUtility {
    private  HashSet<String> links ;
    private  List<List<String>> articles;
    private  int count=2;

    public  CrawlerUtility() {
		links = new HashSet<String>();
		articles= new ArrayList<List<String>>();
		articles.clear();
		links.clear();
    }
    public HashSet<String> getPageLinks(String URL, String key) {
        if (!links.contains(URL) && count>=0) {
            try {
                Document document = Jsoup.connect(URL).get();
                Elements otherLinks = document.select("a[href^=\""+URL +"\"]");
                for (Element page : otherLinks) {
                    if (links.add(URL)) {
                    	count--;
                        //System.out.println("links "+URL);
                    }
                    
                    getPageLinks(page.attr("abs:href"), key);
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return links;
    }

    public List<List<String>> getArticles(String URL, String key) {
    	getPageLinks( URL,  key);
    	//System.out.println("articles >> "+links);
        links.forEach(x -> {
            Document document;
            try {
                document = Jsoup.connect(x).get();
                Elements articleLinks = document.select("a[href^=\""+URL +"\"]");
                for (Element article : articleLinks) {
                	System.out.println("articleLinks "+article.toString());
                    if ( article.toString().contains(key) ) {
                        ArrayList<String> temporary = new ArrayList<>();
                        temporary.add(article.text()); 
                        temporary.add(article.attr("abs:href"));
                        articles.add(temporary);
//                        System.out.println("articles text  "+article.text() +" articles attr " + article.attr("abs:href"));
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
//        System.out.println("OP "+articles);
        return articles;
    }

}
