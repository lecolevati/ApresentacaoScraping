package br.com.leandrocolevati.ApresentacaoScraping.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ScrapingController {

	public ScrapingController() {
		super();
	}

	public void raspagemPura(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
		InputStream inputStream = c.getInputStream();
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(isr);
		String linha = br.readLine();
		while (linha != null) {
			System.out.println(linha);
			linha = br.readLine();
		}
		br.close();
		isr.close();
		inputStream.close();
	}
	
	public void raspagemJSoup(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		Document doc = Jsoup.parse(url, 30000);
		String esquerdaCssQuery = "html > body > div:nth-of-type(2) > main "
				+ "> main > div > div > div:first-of-type "
				+ "> div:nth-of-type(2) > table > tbody "
				+ "> tr:nth-of-type(";
		String direitaCssQuery = ") > td:nth-of-type(2)";
		for (int i = 0 ; i <= 20 ; i++) {
			Elements el = doc.select(esquerdaCssQuery+i+direitaCssQuery);
			String elStr = el.toString().replace("<td class=\"teams__points table__body__cell--gray\">", "");
			elStr = elStr.replace("</td>","");
			System.out.println(elStr);
		}
	}
	
}
