import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SiteMapReader {

	private static final String OUTPUT_PATH = "./ressources/sitemap/extracted_sitemap.xml";
	private static final String INPUT_PATH = "./ressources/sitemap/sitemap.xml";

	public static void main(String argv[]) {

		
		// source for filewriting : http://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
		
		
		try(FileWriter fw = new FileWriter(OUTPUT_PATH, true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw)) {

//			 out.println("the text");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();


			DefaultHandler handler = new DefaultHandler() {

				boolean bid = false;
				boolean blocale = false;
				boolean blastmod = false;
				boolean bloc = false;
				boolean bchangefreq = false;
				boolean buserGroup = false;
				boolean bview = false;

				boolean bfaqs = false;
				boolean blanguage = false;
				boolean buserGroups = false;
				boolean bviews = false;

				String cView;
				String cViews;

				String cUserGroup;
				String cUserGroups;

				String currentID = "";
				
				String content="";

				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {

//					System.out.println("Start Element :" + qName);

					if (qName.equalsIgnoreCase("FAQS")) {
						bfaqs = true;
					}

					if (qName.equalsIgnoreCase("LANGUAGE")) {

						bview = true;
					}

					if (qName.equalsIgnoreCase("ID")) {
						bid = true;
					}

					if (qName.equalsIgnoreCase("LOCALE")) {
						blocale = true;
					}

					if (qName.equalsIgnoreCase("LASTMOD")) {
						blastmod = true;
					}

					if (qName.equalsIgnoreCase("LOC")) {
						bloc = true;
					}

					if (qName.equalsIgnoreCase("CHANGEFREQ")) {
						bchangefreq = true;
					}

					if (qName.equalsIgnoreCase("USERGROUP")) {
						buserGroup = true;
					}

					if (qName.equalsIgnoreCase("VIEW")) {
						bview = true;
					}

					if (qName.equalsIgnoreCase("USERGROUPS")) {
						cUserGroups = "";
						buserGroups = true;
					}

					if (qName.equalsIgnoreCase("VIEWS")) {
						cViews = "";
						bviews = true;
					}

				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {

//					System.out.println("End Element :" + qName);

					if (qName.equalsIgnoreCase("USERGROUPS")) {
						// System.out.println(cUserGroups.substring(0,
						// cUserGroups.length()-1));
						buserGroups = false;
					}

					if (qName.equalsIgnoreCase("VIEWS")) {
						// System.out.println(cViews.substring(0,
						// cViews.length()-1));
						bviews = false;
					}

					if (qName.equalsIgnoreCase("ID")) {
						bid = false;
					}

					if (qName.equalsIgnoreCase("LOCALE")) {
						blocale = false;

					}

					if (qName.equalsIgnoreCase("LASTMOD")) {
						blastmod = false;
					}

					if (qName.equalsIgnoreCase("CHANGEFREQ")) {
						bchangefreq = false;
					}

					if (qName.equalsIgnoreCase("USERGROUP")) {
						buserGroup = false;
					}

					if (qName.equalsIgnoreCase("VIEW")) {
						bview = false;
					}

					if (qName.equalsIgnoreCase("LOC")) {
						// System.out.println(cViews.substring(0,
						// cViews.length()-1));
						content+=";\n";
						bloc = false;
					}

					if (qName.equalsIgnoreCase("LANGUAGE")) {
						// listOfFAQs.add(currentFAQ);
						try {
							out.println(content);
							content="";
						} catch (Exception e) {
							throw new RuntimeException(e);
						}

						blanguage = false;
					}

				}

				public void characters(char ch[], int start, int length)
						throws SAXException {

					if (bid) {
						currentID = new String(ch, start, length);
						// System.out.println("FAQ : " + currentID);
						content+=currentID + ":";
					}

					if (blocale) {
						// System.out.println("locale : " +
						// currentFAQ.getLocale());
					}

					if (blastmod) {
					}

					if (bloc) {
						// currentFAQ.setLoc(new String(ch, start, length));
						// System.out.println("loc : " + currentFAQ.getLoc());
						content+=new String(ch, start, length);
						// bloc = false;
					}

					if (bchangefreq) {

						// System.out.println("changefreq : " +
						// currentFAQ.getChangefreq());
					}

					if (buserGroup) {
//						cUserGroup = new String(ch, start, length);
						// System.out.println("userGroup : " + cUserGroup);
//						cUserGroups += cUserGroup + ",";
					}

					if (bview) {
//						cView = new String(ch, start, length);
						// System.out.println("view : " + cView);
//						cViews += cView + ",";
					}

				}

			};

			saxParser.parse(INPUT_PATH, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
