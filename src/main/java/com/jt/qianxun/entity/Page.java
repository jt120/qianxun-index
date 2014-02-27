/**
 * @author Administrator
 *
 * Feb 27, 2014
 */
package com.jt.qianxun.entity;

import java.util.List;

public class Page {
	
	private String url;
	private String type;
	private String title;
	private String content;
	private List<String> urls;
	private String raw;
	
	StringBuilder sb = new StringBuilder();

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getUrls() {
		return urls;
	}
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	public String getRaw() {
		return raw;
	}
	public void setRaw(String raw) {
		this.raw = raw;
	}
	public String Urls2String(List<String> urls) {
		StringBuilder tmp = new StringBuilder();
		for(String u:urls) {
			tmp.append(u).append("\n");
		}
		return tmp.toString();
	}
	@Override
	public String toString() {
		return sb.append("url: ").append(url).append("\n")
				.append("type: ").append(type).append("\n")
				.append("title: ").append(title).append("\n")
				.append("content: ").append(content).append("\n")
				.append(Urls2String(urls)).toString();
	}

	
}
