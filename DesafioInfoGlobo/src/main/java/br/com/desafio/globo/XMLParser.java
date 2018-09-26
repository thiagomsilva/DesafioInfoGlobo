package br.com.desafio.globo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public class XMLParser {

	static final String ITEM = "item";
	static final String TITLE = "title";
	static final String LINK = "link";
	static final String DESCRIPTION = "description";
	static final String TAG_P = "p";
	static final String TAG_DIV = "div";
	static final String TAG_IMAGE = "img";
	static final String TAG_UL = "ul";

	final URL url;

	public XMLParser(String xmlUrl) {
		try {
			this.url = new URL(xmlUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public XML readXml() {
		XML xml = null;
		try {
			boolean isFeedHeader = true;
			// Definindo valores iniciais para a string vazia

			String title = "";
			String link = "";
			String description = "";
			String ul = "";
			String p = "";
			String div = "";
			String img = "";

			// criando uma nova XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();

			// configurando um novo leitor de eventos
			InputStream in = read();

			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			// lendo itens do XML
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName().getLocalPart();
					switch (localPart) {
					case ITEM:
						if (isFeedHeader) {
							isFeedHeader = false;
							xml = new XML(title, link, description, ul, p, div, img);
						}
						event = eventReader.nextEvent();
						break;
					case TITLE:
						title = getCharacterData(event, eventReader);
						break;
					case LINK:
						link = getCharacterData(event, eventReader);
						break;
					case DESCRIPTION:
						description = getCharacterData(event, eventReader);
						break;
					case TAG_UL:
						ul = getCharacterData(event, eventReader);
						break;
					case TAG_P:
						p = getCharacterData(event, eventReader);
						break;
					case TAG_DIV:
						div = getCharacterData(event, eventReader);
						break;
					case TAG_IMAGE:
						img = getCharacterData(event, eventReader);
						break;

					}
				} else if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
						XMLMessage message = new XMLMessage();
						message.setTitle(title);
						message.setLink(link);
						message.setDescription(description);
						message.setUl(ul);
						message.setP(p);
						message.setDiv(div);
						message.setImg(img);
						xml.getMessages().add(message);
						event = eventReader.nextEvent();
						continue;
					}
				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		return xml;
	}

	// lendo as tags
	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		return result;
	}

	// saída
	private InputStream read() {
		try {
			BufferedReader rdr = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String buf = null;
			StringBuffer output = new StringBuffer();
			while ((buf = rdr.readLine()) != null) {
				output.append(buf.replaceAll("&lt;p&gt;", ""));
			}

			byte[] bytes = output.toString().getBytes("UTF-8");

			InputStream is = new ByteArrayInputStream(bytes);

			return is;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
