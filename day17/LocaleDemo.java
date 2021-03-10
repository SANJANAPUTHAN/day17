package day17;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleDemo {
public static void main(String[] args) {
	Locale.setDefault(new Locale("ta"));
	Locale locale=Locale.getDefault();
	
	System.out.println(locale);
	ResourceBundle r=ResourceBundle.getBundle("day17/Dictionary",locale);
	ResourceBundle rb=ResourceBundle.getBundle("day17.Dictionary",locale);
	System.out.println(rb.getString("hello"));
	System.out.println(r.getString("hello"));
	
	
}
}
