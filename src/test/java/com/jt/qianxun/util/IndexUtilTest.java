package com.jt.qianxun.util;

import org.junit.Test;

/**
 * @author Liu Ze
 *
 * Create Date Feb 27, 2014 3:03:19 PM
 */

public class IndexUtilTest {
	
	private String pagePath = "d:/data/page/parse/20140227150526";
	
	@Test
	public void test01() {
		IndexUtil in = new IndexUtil();
		in.index(pagePath);
	}
	
	@Test
	public void test02() {
		
	}

}
