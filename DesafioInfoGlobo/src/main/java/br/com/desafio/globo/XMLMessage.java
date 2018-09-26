package br.com.desafio.globo;

public class XMLMessage {

	String title;
	String link;
	String description;
	String ul;
	String p;
	String div;
	String img;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUl() {
		return ul;
	}

	public void setUl(String ul) {
		this.ul = ul;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "XMLMessage [title = " + title + "link = " + link + ", description = " + description + ", tag_ul = " + ul
				+ ", tag_p = " + p + ", Tag_Div = " + div + ", img = " + img + "]";
	}

}