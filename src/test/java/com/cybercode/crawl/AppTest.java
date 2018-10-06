package com.cybercode.crawl;

import com.cybercode.service.CrawlTool;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
		CrawlTool ct = new CrawlTool();
		String[] testUrls = { //"https://raw.githubusercontent.com/stephennurwono/crawl/master/data.json",
				//,"https://raw.githubusercontent.com/stephennurwono/crawl/master/data1.json"
				//,"https://raw.githubusercontent.com/stephennurwono/crawl/master/data3.json"
				"https://raw.githubusercontent.com/stephennurwono/crawl/master/error.json"
				//,"https://raw.githubusercontent.com/stephennurwono/crawl/master/error1.json"
				//,"https://raw.githubusercontent.com/stephennurwono/crawl/master/error2.json" 
				};
		for (String testUrl : testUrls) {
			ct.setSource(testUrl);
			if (ct.getStat() != null) {
				ct.reportStatistic(ct.getStat());
			}

		}

	}
}
