package com.cybercode.crawl;

import java.util.Map;

import com.cybercode.service.CrawlTool;

public class CrawlApp {

	public static void main(String[] args) {
		CrawlTool ct = new CrawlTool(
				"https://raw.githubusercontent.com/OnAssignment/compass-interview/master/data.json");
		ct.convert();
		Map<String, Integer> statusMap = ct.getStatusStatistic();
		if (statusMap != null) {
			ct.reportStatistic(statusMap);
		}
		
		System.out.println("Done");

	}
}
