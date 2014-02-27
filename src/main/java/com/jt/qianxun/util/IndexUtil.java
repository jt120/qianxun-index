package com.jt.qianxun.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.ansj.lucene4.AnsjAnalysis;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.jt.qianxun.entity.Page;

/**
 * @author Liu Ze
 *
 * Create Date Feb 27, 2014 2:34:23 PM
 */

public class IndexUtil {
	String path = "d:/data/index/";
	Version matchVersion = Version.LUCENE_46;
	private Analyzer analyzer = null;
	private IndexWriterConfig conf = null;
	private Directory directory = null;
	private IndexWriter indexWriter = null;
	private Page page = new Page();
	public IndexUtil() {

		try {
			analyzer = new AnsjAnalysis();
			conf = new IndexWriterConfig(matchVersion, analyzer);
			directory = FSDirectory.open(new File(path));
			indexWriter = new IndexWriter(directory, conf );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void index(String pagePath) {
		readPage(pagePath );
		Document doc = page2Document();
		try {
			deleteAll();
			indexWriter.addDocument(doc);
			indexWriter.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				indexWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteAll() throws IOException {
		indexWriter.deleteAll();
	}
	
	public void readPage(String path) {
		File file = new File(path);
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(is));
			String line = "";
			
			while((line=br.readLine())!=null) {
				if(line.startsWith("url")) {
					line = line.substring("url:".length());
					page.setUrl(line);
				} else if(line.startsWith("title")) {
					line = line.substring("title:".length());
					page.setTitle(line);
				} else if(line.startsWith("content")) {
					line = line.substring("content:".length());
					page.setContent(line);
				} else {
					//todo
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is!=null) is.close();
				if(br!=null) br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Document page2Document() {
		Document document = new Document();
		document.add(new StringField("url", page.getUrl(), Store.YES));
		document.add(new TextField("title", page.getTitle(), Store.YES));
		document.add(new TextField("content", page.getContent(), Store.YES));
		return document;
	}

}
