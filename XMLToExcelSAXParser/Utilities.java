package XMLToExcelSAXParser;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities 
{
	public static void main(String[] args) throws Exception
	{
		Object obj=new Utilities();
		obj=Proxy.newProxyInstance(Utilities.class.getClassLoader(), new Class[] {XMLConverterComponent.class}, new MyInvocationHandler(new Object[] {new XMLConverter()}));
		XMLConverterComponent xml=(XMLConverterComponent)obj;
		xml.convert();
	
	}
}
class MyInvocationHandler implements InvocationHandler
{
	Object ob[];
	public MyInvocationHandler(Object ob[]) {
		this.ob=ob;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		Object role=null;
		for(Object o:ob)
		{
			Method m[]=o.getClass().getDeclaredMethods();
			for(Method mm:m)
			{
				mm.setAccessible(true);
				role=mm.invoke(o, args);
			}
		}
		return role;
	}
}
interface XMLConverterComponent
{
	public void convert() throws Exception;
}
class XMLConverter implements XMLConverterComponent
{	
	@Override
	public void convert() throws Exception
	{	
		XSSFWorkbook workbook=new XSSFWorkbook();		
		XSSFSheet sheet=workbook.createSheet("CustomerBill");
		int rownum=0;
		Row row=sheet.createRow(rownum);
		Invoice invoice=Invoice.getInvoice();
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		    XSSFFont font = sheet.getWorkbook().createFont();
		    font.setBold(true);
		    font.setFontHeightInPoints((short) 8);
		    cellStyle.setFont(font);
		 
		    Cell cellTitle = row.createCell(rownum);
		 
		    cellTitle.setCellStyle(cellStyle);
		    cellTitle.setCellValue("INVOICENUMBER");
		 
		    Cell cellAuthor = row.createCell(1);
		    cellAuthor.setCellStyle(cellStyle);
		    cellAuthor.setCellValue("INVOICEDATE");
		 
		    Cell cellPrice = row.createCell(2);
		    cellPrice.setCellStyle(cellStyle);
		    cellPrice.setCellValue("CUSTOMERNAME");
		    rownum++;
		//createHeaderRow(sheet);
		    row = sheet.createRow(rownum++);
			Cell cell = row.createCell(0);
		    cell.setCellValue(invoice.getInvno());
		 
		    cell = row.createCell(1);
		    cell.setCellValue(invoice.getInvdate());
		 
		    cell = row.createCell(2);
		    cell.setCellValue(invoice.getCutomername());
		//writeCustomerDetails(invoice);
		    cellStyle = sheet.getWorkbook().createCellStyle();
		    font = sheet.getWorkbook().createFont();
		    font.setBold(true);
		    font.setFontHeightInPoints((short) 8);
		    cellStyle.setFont(font);
		 
		    row = sheet.createRow(rownum++);
		    cellTitle = row.createCell(0);
		 
		    cellTitle.setCellStyle(cellStyle);
		    cellTitle.setCellValue("ITEMNUMBER");
		 
		    cellAuthor = row.createCell(1);
		    cellAuthor.setCellStyle(cellStyle);
		    cellAuthor.setCellValue("ITEMNAME");
		 
		    cellPrice = row.createCell(2);
		    cellPrice.setCellStyle(cellStyle);
		    cellPrice.setCellValue("ITEMPRICE");
		    
		    Cell cellquantity = row.createCell(3);
		    cellquantity.setCellStyle(cellStyle);
		    cellquantity.setCellValue("ITEMQUANTITY");
		//createItemHeader();
		    List<Items> item=invoice.item;
			ListIterator<Items> list=item.listIterator();
			Map < Integer,String[] > prodinfo = new TreeMap <Integer, String[] >();
			int i=1;		
			while(list.hasNext())
			{
				prodinfo.put(i++,list.next().toString().split(" "));
			}
			Set<Integer> keyid = prodinfo.keySet();
		    for (Integer key : keyid) {
		         row = sheet.createRow(rownum++);
		         String [] objectArr = prodinfo.get(key);
	             int cellid = 0;
		         
		         for (Object obj : objectArr){
		            cell = row.createCell(cellid++);
		            cell.setCellValue((String)obj);
		         }
		    }	
			
		//writeItemDetails(invoice);	
		    row=sheet.createRow(rownum++);
			row.createCell(0).setCellValue("GST");
			row.createCell(1).setCellValue(invoice.getGst());
			row=sheet.createRow(rownum++);
			row.createCell(0).setCellValue("NET");
			row.createCell(1).setCellValue(invoice.getNet());	
		//writeTaxDetails(invoice);
		try (FileOutputStream outputStream = new FileOutputStream("CustomerBill.xlsx")) 
		{
            workbook.write(outputStream);
        }
		
	}
	
}