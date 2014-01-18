package com.ravi.torrentz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ravi.common.Constants;
import com.ravi.common.Movie;
import com.ravi.common.Utils;

public class TorrentzHtmlParser {

	public static Map<String, Movie> parseHtmlForTorrentLinkz(String searchLink) {
		Map<String, Movie> torrentLikz = new HashMap<String, Movie>();
		List<String> pages = new ArrayList<String>();
		pages.add(searchLink);
		pages.add(searchLink + "&/p=1");
		pages.add(searchLink + "&/p=2");
//		pages.add(searchLink + "&/p=3");
		try {
			for (String page : pages) {
				Document htmlDoc = Jsoup.connect(page).get();
				Elements elements = htmlDoc.select("a[href]");
				for (Element element : elements) {
					String link = element.attr("href");

					if (!Utils.isBlankString(link)
							&& !link.startsWith("/search")) {
						// Hardcoding the value as all the result always returns
						// the same string length
						if (41 == link.length()) {
							String movie = element.text().split(
									"[0-9][0-9][0-9][0-9]")[0].trim();
							Set<String> links = new HashSet<String>();
							links.add(element.attr("abs:href").trim());
							// System.out.println(movie +" and link is:"+
							// element.attr("abs:href"));
							if (torrentLikz.containsKey(movie)) {
								Movie m = torrentLikz.get(movie);
								links.addAll(m.getLinks());
								m.setLinks(links);
								torrentLikz.put(movie, m);
							} else {
								torrentLikz.put(movie, new Movie(movie, links));
							}

						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception while parsing the search results"
					+ e.getMessage());
			e.printStackTrace();
		}
		return torrentLikz;
	}

	public static Map<String,String> getTorrentProviderLink(Movie m) throws IOException {
		Map<String, String> providersMap = new HashMap<String, String>();
		if (null != m) {
			Set<String> providers = m.getLinks();
			for (String link : providers) {
				Document providerDoc = Jsoup.connect(link).get();
				Elements providerElements = providerDoc.select("a[href]");
				for (Element provider : providerElements) {
					String p = provider.text().split(" ")[0];
					if (Constants.SUPPORTED_SITES.contains(p.trim()))
					{
						System.out.println("Text: " + p);
					    System.out.println("Link:" + provider.attr("href"));
					    providersMap.put(p,provider.attr("href"));
				    }
			}
		}
		}
		
		return providersMap;

	}

	public static void main(String[] args) throws IOException {
		Map<String, Movie> linkz = parseHtmlForTorrentLinkz("http://torrentz.in/verified?f=games+2013");
		// for(String m : linkz.keySet())
		// {
		// System.out.println(linkz.get(m).toString());
		// }
		System.out.println(linkz.containsKey("Far Cry 3 Blood Dragon"));
		getTorrentProviderLink(linkz.get("Far Cry 3 Blood Dragon"));
	}

}
