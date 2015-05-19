#Kakeoppskrift

##For-each loop
```java
	for (Object s : noe) {
		m.addElement(s);
    }
```

##Collections 

*ArrayList - Like an array but dynamic size, add/remove elements at will*
```java
	ArrayList<Integer> a = new ArrayList<Integer>();

	// Add/remove elements
	a.add(14)
	a.remove(0) // Use with index
```

Sets are lists that only have unique elements

*HashSet*
Elements are unique
```java
	HashSet<Integer> s = new HashSet<Integer>();
```

*TreeSet*
Elements are unique and sorted automatically
```java
	TreeSet<Integer> s = new TreeSet<Integer>();
```

*LinkedHashSet*
Elements are unique and sorted in the same order they were added
```java
	LinkedHashSet<Integer> s = new LinkedHashSet<Integer>();
```

*HashMap*
A list that has a key and a corresponding value (like a dictionary in Python)
```java
	HashMap<String, String> h = new HashMap<String, String>();

	// Add element using put
	h.put("key", "value");
	h.remove("key");
```

##Update GUI with lists
```java
	// Pass the JList that is used in the GUI and the ArrayList you want to make a  
	// list from
	public void updateGUI(JList l, Object[] a) {
		DefaultListModel m = new DefaultListModel();
		for (Object s : a) {
			// Copy contents of ArrayList into the list model
			m.addElement(s)
		}

		// Update the JList with our new model
		l.setModel(m);
	}
```