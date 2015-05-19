#Krypteringsprogram

##String magic

*Create an array of a string*
```java
    StringBuilder tekstarr = new StringBuilder(tekst);
```

*Set a char at index i in array*  
```java
    tekstarr.setCharAt(i, 'y');
```

*Return array as string*  
```java
    return tekstarr.toString();
```

##Testing

*Setup function, creating from original GUI*  
```java
    @Before
    public void setUp(){  
        gui = new GUI();  
    }  

```

*Test function, use assertEquals to test if a given value returns the correct value*
```java
    @Test
    public void testAlgoritme2() {
    	// Checks if gui.algoritme2 returns åøe when given øæy
		assertEquals("åøæ", gui.algoritme2("øæy"));
	}
```

*For numbers, pass a margin of error (delta) as the third argument to assertEquals*
```java
	@Test
    public void testKonverter2() throws Exception {
        assertEquals(319.34, Oblig16.konverter(5000, 2, 0), 0.01);
    }
```