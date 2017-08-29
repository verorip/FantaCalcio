import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class FileReader {
	String s="";
	int r=0;
	int c=0;
	char current;
	Player player;
	public ArrayList <Player> Attaccanti;
	public ArrayList <Player> Portieri;
	public ArrayList <Player> Difensori;
	public ArrayList <Player> Centrocampisti;
	
	public FileReader(){
		System.out.println("avviato filereader");
		Attaccanti= new ArrayList<>();
		Portieri= new ArrayList<>();
		Difensori= new ArrayList<>();
		Centrocampisti= new ArrayList<>();
	}

 public void fileread( String path) throws IOException{	
	 try 
	 {
		 	
	         // Get the workbook instance for XLSX file
	         XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(path)));

	         // Get first sheet from the workbook
	         XSSFSheet sheet = wb.getSheetAt(0);

	         Row row;
	         Cell cell, cell1;

	         // Iterate through each rows from first sheet
	         Iterator<Row> rowIterator = sheet.iterator();

	         while (rowIterator.hasNext()) 
	         {
	                 row = rowIterator.next();

	                 // For each row, iterate through each columns
	                 Iterator<Cell> cellIterator = row.cellIterator();
	                 c=0;
	                 while (cellIterator.hasNext()) 
	                 {
	                	 
		                 cell=cellIterator.next();
		                 if(c==1 && cell.toString().equals("A"))
		                 {
		                	 //System.out.println("attaccante");
		                	 cell=cellIterator.next();
		                	 cell1=cellIterator.next();
		                	 //System.out.println(cell.toString()+"    "+ cell1.toString());
		                	 player=new Player(cell.toString(), cell1.toString().toUpperCase());
		                	 Attaccanti.add(player);
		                 }
		                 else if(c==1 && cell.toString().equals("P")){
		                	// System.out.println("portiere");
		                	 cell=cellIterator.next();
		                	 cell1=cellIterator.next();
		                	// System.out.println(cell.toString()+"    "+ cell1.toString());
		                	 player=new Player(cell.toString(), cell1.toString().toUpperCase());
		                	 Portieri.add(player);
		                 }
		                 else if(c==1 && cell.toString().equals("C")){
		                	 //System.out.println("centrocampista");
		                	 cell=cellIterator.next();
		                	 cell1=cellIterator.next();
		                	// System.out.println(cell.toString()+"    "+ cell1.toString());
		                	 player=new Player(cell.toString(), cell1.toString().toUpperCase());
		                	 Centrocampisti.add(player);
		                 }
		                 else if(c==1 && cell.toString().equals("D")){
		                	 //System.out.println("difensore");
		                	 cell=cellIterator.next();
		                	 cell1=cellIterator.next();
		                	 //System.out.println(cell.toString()+"    "+ cell1.toString());
		                	 player=new Player(cell.toString(), cell1.toString().toUpperCase());
		                	 Difensori.add(player);
		                 }
		                	 
		                 c++;
	                 }
	                 r++;
	           }
	        sortD();
	        sortA();
	        sortC();
	        sortP();
	        /*for(Player p : Difensori)
	        	 System.out.println("Difensori----> "+p.getNome()+"  della squadra "+p.getTeam().toString());*/
	 }
	 catch (Exception e) 
	 {
	         System.err.println("Exception :" + e.getMessage());
	 }
	 System.out.println("finito");
    }
 
 	public ArrayList<Player> getAttaccanti(){
 		return Attaccanti;
 	}
 	
 	public ArrayList<Player> getPortieri(){
 		return Portieri;
 	}
 	
 	public ArrayList<Player> getCentrocampisti(){
 		return Centrocampisti;
 	}
 	
 	public ArrayList<Player> getDifensori(){
 		return Difensori;
 	}
 	public void sortD(){
 		Collections.sort(Difensori, new Comparator<Player>() {
	 	    public int compare(Player p1, Player p2) {
	 	        return p1.getNome().compareTo(p2.getNome());
	 	    }
	 	});
 	}
 	public void sortC(){
 		Collections.sort(Centrocampisti, new Comparator<Player>() {
	 	    public int compare(Player p1, Player p2) {
	 	        return p1.getNome().compareTo(p2.getNome());
	 	    }
	 	});
 	}
 	public void sortA(){
 		Collections.sort(Attaccanti, new Comparator<Player>() {
	 	    public int compare(Player p1, Player p2) {
	 	        return p1.getNome().compareTo(p2.getNome());
	 	    }
	 	});
 	}
 	public void sortP(){
 		Collections.sort(Portieri, new Comparator<Player>() {
	 	    public int compare(Player p1, Player p2) {
	 	        return p1.getNome().compareTo(p2.getNome());
	 	    }
	 	});
 	}
	 	
 	/*public void setA(ArrayList<Player> A){
 		this.Attaccanti=A;
 	}
 	public void setC(ArrayList<Player> C){
 		this.Centrocampisti=C;
 	}
 	public void setP(ArrayList<Player> P){
 		this.Portieri=P;
 	}
 	public void setD(ArrayList<Player> D){
 		this.Difensori=D;
 	}*/
}
