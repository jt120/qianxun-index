package com.jt.qianxun.util;

import java.io.File;
import java.io.IOException;

import org.ansj.lucene4.AnsjAnalysis;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.jt.qianxun.entity.Page;

/**
 * @author Liu Ze
 * 
 *         Create Date Feb 27, 2014 3:06:58 PM
 */

public class SearchUtil {
	String path = "d:/data/index/";
	Version matchVersion = Version.LUCENE_46;
	private Analyzer analyzer = null;
	private IndexWriterConfig conf = null;
	private Directory directory = null;
	private IndexReader indexReader = null;
	private IndexSearcher indexSearcher = null;
	private Page page = new Page();

	public SearchUtil() {
		try {
			analyzer = new AnsjAnalysis();
			conf = new IndexWriterConfig(matchVersion, analyzer);
			directory = FSDirectory.open(new File(path));
			indexReader = DirectoryReader.open(directory);
			indexSearcher = new IndexSearcher(indexReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void search(String word) {
		Query query = new WildcardQuery(new Term("content", word));
		try {
			TopDocs topDocs = indexSearcher.search(query, 100);
			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
				Document document = indexSearcher.doc(scoreDoc.doc);
				System.out.println("url:" + document.get("url"));
				System.out.println("title:" + document.get("title"));
				System.out.println("content:" + document.get("content"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void searchLight(String word) {

		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String highLight(String fieldName, String text) throws Exception {
		Query query = new WildcardQuery(new Term(fieldName, text));
		QueryScorer queryScorer = new QueryScorer(query);
		SimpleSpanFragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
		SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter();
		Highlighter highlighter = new Highlighter(htmlFormatter, queryScorer);
		highlighter.setTextFragmenter(fragmenter);
		String line = highlighter.getBestFragment(analyzer, fieldName, text);
		return line;
	}
}
