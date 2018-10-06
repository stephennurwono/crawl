package com.cybercode.crawl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.cybercode.model.Site;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Crawler
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            Site siteLinks = mapper.readValue(new URL("https://raw.githubusercontent.com/OnAssignment/compass-interview/master/data.json"), Site.class);
            URL currentUrl = null;
            HttpURLConnection http = null;
            String statusCode;
            Integer counter = null;
            Map<String,Integer> counters = new HashMap<String,Integer>();
            /*
             * loops for links
             */
            for (String url: siteLinks.links) {
            	System.out.println(url);
            	currentUrl = new URL(url);
            	http = (HttpURLConnection)currentUrl.openConnection();
                statusCode = String.valueOf(http.getResponseCode());
                if (!counters.containsKey(statusCode)) {
                	counters.put(statusCode, 1);
                } else {
                	counter = counters.get(statusCode);
                	counters.put(statusCode, counter+1);
                }
                System.out.println(url+" status="+statusCode);
                currentUrl = null;
                http = null;
            }
            for (Map.Entry<String, Integer> entry : counters.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
           
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
