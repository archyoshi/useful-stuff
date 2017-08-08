package com.tutorialspoint.lucene;

import java.io.IOException;

import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {
	
	private IndexWriter writer;
	
	public Indexer(String indexDirectoryPath) throws IOException{
		// this directory will contain indexes
		Directory indexDirectory = FSDirectory.open(Paths.get(indexDirectoryPath));
		
		// creating the indexer
		
		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
		writer = new IndexWriter(indexDirectory, config);
	}

}
