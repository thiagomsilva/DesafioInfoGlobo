package br.com.desafio.globo;

import com.google.gson.Gson;

public class GeradorJson {

	public void gerador() {

		XMLParser parser = new XMLParser("https://revistaautoesporte.globo.com/rss/ultimas/feed.xml");

		XML xml = parser.readXml();

		// itera sobre cada objeto do array
		for (XMLMessage message : xml.getMessages()) {

			// instanciando o objeto Gson
			Gson gson = new Gson();

			// conversão da string no formato Json
			String jsonStr = gson.toJson(message);

			System.out.println(jsonStr);

		}

	}

}
