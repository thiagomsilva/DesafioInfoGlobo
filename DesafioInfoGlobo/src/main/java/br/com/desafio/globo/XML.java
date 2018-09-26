package br.com.desafio.globo;

import java.util.ArrayList;
import java.util.List;

public class XML {

	final String title;
	final String link;
	final String description;
	final String body;
	final String p;
	final String div;
	final String img;

	final List<XMLMessage> entries = new ArrayList<XMLMessage>();

	public XML(String title, String link, String description, String body, String p, String div, String img) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.body = body;
		this.p = p;
		this.div = div;
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public String getBody() {
		return body;
	}

	public String getP() {
		return p;
	}

	public String getDiv() {
		return div;
	}

	public String getImg() {
		return img;
	}

	public List<XMLMessage> getMessages() {
		return entries;
	}

	@Override
	public String toString() {
		return "Xml [title = " + title + ", link = " + link + ", description = " + description + ", body = " + body
				+ ", tag_p = " + p + ", Tag_Div = " + div + ", img = " + img + "]";
	}

}