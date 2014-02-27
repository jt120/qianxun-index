package com.jt.qianxun.util;

import org.junit.Test;

/**
 * @author Liu Ze
 *
 * Create Date Feb 27, 2014 3:13:25 PM
 */

public class SearchUtilTest {
	
	@Test
	public void test01() throws Exception {
		SearchUtil s = new SearchUtil();
		String word = "知识";
		String r = s.highLight("content", word);
		System.out.println(r);
	}

}
