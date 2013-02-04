package com.example.nasarssfeed;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class IotdHandler extends DefaultHandler {
	private String url = "http://www.nasa.gov/rss/image_of_the_day.rss";
	private boolean inUrl = false;
	private boolean inTitle = false;
	private boolean inDescription = false;
	private boolean inItem = false;
	private boolean inDate = false;
	private boolean inEnclosure = false;
	private Bitmap image = null;
	private String title = null;
	private StringBuffer description = new StringBuffer();
	private String date = null;
	private XMLReader reader;
	
	public void processFeed() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			reader = parser.getXMLReader();
			reader.setContentHandler(this);
			InputStream inputStream = new URL(url).openStream();
			reader.parse(new InputSource(inputStream));
			inputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private Bitmap getBitmap(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(input);
			input.close();
			return bitmap;
		} catch (IOException ioe) {
			return null;
		}
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if (localName.startsWith("item")) {
			inItem = true;
		} else {
			inItem = false;
		}
		if (localName.equals("title")) {
			inTitle = true;
		} else {
			inTitle = false;
		}
		if (localName.equals("description")) {
			inDescription = true;
			
		} else {
			inDescription = false;
		}
		if (localName.equals("pubDate")) {
			inDate = true;
		} else {
			inDate = false;
		}
		if (localName.equals("enclosure")) {
			inEnclosure = true;
		} else if (inEnclosure && localName.equalsIgnoreCase("url")) {
			inUrl = true;
		} else {
			inUrl = false;
		}
	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		String chars = new String(ch).substring(start, start + length);
		
		if (inUrl) { 
			image = getBitmap(chars);
		}
		if (inTitle && title == null) {
			title = chars;
		}
		if (inDescription) {
			description.append(chars);
		}
		if (inDate && date == null) {
			date = chars;
		}
	}
	
	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
		super.startPrefixMapping(prefix, uri);
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}
	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		super.endPrefixMapping(prefix);
	}
	public Bitmap getImage() {
		return image;
	}
	
	public String getTitle() {
		return title;
	}
	
	public StringBuffer getDescription() {
		return description;
	}
	public String getDate() {
		return date;
	}
	
	
	
}
