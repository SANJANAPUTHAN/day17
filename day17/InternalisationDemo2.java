package day17;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class InternalisationDemo2 {
public static void main(String[] args) {
	NumberFormat numberformat=NumberFormat.getCurrencyInstance(new Locale("en","IN"));
	int i=200029;
	System.out.println(String.format("%s", numberformat.format(i)));
	DateFormat dateformat=DateFormat.getDateInstance(DateFormat.SHORT,new Locale("en","US"));
	Date date=new Date();
	System.out.println(dateformat.format(date));
	
	DateTimeFormatter datetimeformat=DateTimeFormatter.ofPattern("d-MM-YYYY", new Locale("en","US"));
	String currentdate=LocalDate.now().format(datetimeformat);
	System.out.println(currentdate);
	
}
}
