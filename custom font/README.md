
# Instructions:

Put these in a utility package and add the package declaration to the top of each class. Next initialize them on startup, example below.

```java
// put these in the class not in the startup method
    static CustomFontRenderer arial8;
	static CustomFontRenderer arial16;
	static CustomFontRenderer arial24;
	static CustomFontRenderer arial32;
	static CustomFontRenderer arial40;
	static CustomFontRenderer arial48;
	
	public static void start() {
		// initialize custom font, put these in startup method
		CustomFontRenderer arial8 = new CustomFontRenderer(new Font("Arial", Font.PLAIN, 8));
		CustomFontRenderer arial16 = new CustomFontRenderer(new Font("Arial", Font.PLAIN, 16));
		CustomFontRenderer arial24 = new CustomFontRenderer(new Font("Arial", Font.PLAIN, 24));
		CustomFontRenderer arial32 = new CustomFontRenderer(new Font("Arial", Font.PLAIN, 32));
		CustomFontRenderer arial40 = new CustomFontRenderer(new Font("Arial", Font.PLAIN, 40));
		CustomFontRenderer arial48 = new CustomFontRenderer(new Font("Arial", Font.PLAIN, 48));
	}
```
