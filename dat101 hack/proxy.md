#Proxy

Brukes som minnebuffer, feks når noe kan mellomlagres for å spare båndbredde.

```java

public class proxy {
	
	private HashMap<String,Image> p = new HashMap<String,Image>();
	//HashMap inneholder unike elementer
	public void downloadImage(String url){
		URL realurl;
		try {
		//sorroud with try catch; siden url kan være ugyldig, gi feil
			realurl = new URL(url);
			Image img = ImageIO.read(realurl); 
			//leser bilde fra url
			p.put(url, img); 
			//kartlegger bilde til url i hashmap
			getImage(url); 
			//henter bilde fra url
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public Image getImage(String url){
		if(p.containsKey(url)==false){ 
		//buffer check, sjekker om bilde i hashmap
			downloadImage(url); 
			//hvis ikke i hashmap, last ned
		}
		Image img = this.p.get(url); 
		//hvis url hentet tidligere
		return img; 
		//returner bilde
	}
}

```