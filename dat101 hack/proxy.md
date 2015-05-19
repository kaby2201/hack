#Proxy

A kind of memory buffer, saves bandwidth by saving things in a cache.

*HashMap is a list of unique elements*
```java
	private HashMap<String,Image> p = new HashMap<String,Image>();
```

*Downloading an image from the Internet*
```java
	public void downloadImage(String url) {
	// Use a try/catch in case the URL is invalid
		try {
			realurl = new URL(url);

			// Read an image from the URL
			Image img = ImageIO.read(realurl); 

			// Put the image in the list
			p.put(url, img); 

			getImage(url); 
		} catch (MalformedURLException e) {
			// Runs if the URL is invalid
			e.printStackTrace();
		} catch (IOException e) {
			// Runs on an I/O error (error connecting to the Internet)
			e.printStackTrace();
		}
	}
```

*Use a buffer to save bandwith, check if the image is already in the HashMap*
```java
	public Image getImage(String url){

		// Check if the image is already in the HashMap, download and add if not 
		if(p.containsKey(url) == false) { 
			downloadImage(url);
		}

		// Get image from HashMap, return it
		Image img = this.p.get(url);
		return img; 
	}
```