package com.cybercode.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cybercode.model.Site;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CrawlTool implements CrawlService {
	
	public final String GOOD_STATUS = "200";

	private Site siteLinks;
	private String source;
	private Map<String,Integer> stat;
	
	public Map<String, Integer> getStatusStatistic() {
		URL currentUrl = null;
		HttpURLConnection http = null;
		String statusCode;
		Integer counter = null;
		Map<String, Integer> counters = new HashMap<String, Integer>();
		try {
			if ( this.getSiteLinks() !=null) {
				/*
				* loops for links
				*/
				for (String url : this.getSiteLinks().links) {
					//System.out.println(url);
					currentUrl = new URL(url);
					http = (HttpURLConnection) currentUrl.openConnection();
					statusCode = String.valueOf(http.getResponseCode());
					if (!counters.containsKey(statusCode)) {
						counters.put(statusCode, 1);
					} else {
						counter = counters.get(statusCode);
						counters.put(statusCode, counter + 1);
					}
					//System.out.println(url + " status=" + statusCode);
					currentUrl = null;
					http = null;
				}
				;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return counters;
	}

	public void convert() {
		Site siteLinks = null;
		ObjectMapper mapper = new ObjectMapper();
		if (StringUtils.isNotEmpty(this.getSource())) {
			try {
				URL url = new URL(this.getSource());
				siteLinks = mapper.readValue(url, Site.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		this.setSiteLinks(siteLinks);
	}

	public void reportStatistic(Map<String, Integer> pData) {
		int allRequests = 0;
		int goodRequest = -1;
	
		if (pData != null) {
			if (pData.containsKey(this.GOOD_STATUS)) {
				goodRequest = pData.get(GOOD_STATUS).intValue();
			}
			for (Map.Entry<String, Integer> entry : pData.entrySet()) {
				allRequests = allRequests + entry.getValue().intValue();
				//System.out.println(entry.getKey() + ":" + entry.getValue());
			}
		}
		System.out.println("total number of http requests performed throughout the entire application = "+allRequests);
		System.out.println("total number of successful requests= "+goodRequest);
		System.out.println("total number of failed requests= "+(allRequests-goodRequest));
		

	}

	public Site getSiteLinks() {
		return siteLinks;
	}

	public void setSiteLinks(Site siteLinks) {
		this.siteLinks = siteLinks;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
		convert();
		stat = getStatusStatistic();
	}

	public Map<String, Integer> getStat() {
		return stat;
	}

	public void setStat(Map<String, Integer> stat) {
		this.stat = stat;
	}

}
