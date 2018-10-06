package com.cybercode.crawl;

import com.cybercode.service.CrawlTool;

/**
 * Crawler
 *
 */
public class App {
	public static void main(String[] args) {
		

		CrawlTool ct = new CrawlTool(
				"https://raw.githubusercontent.com/OnAssignment/compass-interview/master/data.json");
		if (ct.getStat() != null) {
			ct.reportStatistic(ct.getStat());
		}

		System.out.println("Done");
	}
}
