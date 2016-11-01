package WeatherReader;

import java.awt.List;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class NJWeatherReader {
  static ArrayList<String> testlist = new ArrayList<String>();
  static String time = null;
  public static void main(String [] args) {
    	//String base = "http://iwin.nws.noaa.gov/iwin/nj/hourly.html";
      String base = "http://forecast.weather.gov/product.php?site=NWS&issuedby=PHI&product=RWR&format=CI&version=1&glossary=0";
	URL u = null;
	URLConnection conn = null;
	Scanner in = null;
	try {
		u = new URL(base);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		conn = u.openConnection();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		in = new Scanner(conn.getInputStream());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	parsePage(in);
	in.close();
	System.out.println(time);
	for (String s:testlist)
	  	System.out.println(s);
     	fileOutput();
  } //end main
  static void parsePage(Scanner in) { 
	String line="";
  	while (in.hasNext()) {
	  line = in.nextLine();
	  if (time == null && line.startsWith("NATIONAL WEATHER SERVICE" ))
	      time = in.nextLine();
	  if (line.startsWith("CITY")) 
	      while ((line = in.nextLine()) != null) {
		if ( !line.startsWith("$$") && !line.startsWith(" ") && line.length()>30) 
		{  testlist.add(line); }
		else break;
	      } //end while
	  else continue;
  	} //end while

  }
  
  //This is used in the WeatherFrame Class
  static void parsePage1(Scanner in) { 
		String line="";
	  	while (in.hasNext()) {
		  line = in.nextLine();
		  if (time == null && line.startsWith("NATIONAL WEATHER SERVICE" ))
		      time = in.nextLine();
		  if (line.startsWith("CITY")) 
		      while ((line = in.nextLine()) != null) {
			if ( !line.startsWith("$$") && !line.startsWith(" ") && line.length()>30) 
			{  testlist.add(line); }
			else break;
		      } //end while
		  else continue;
	  	} //end while

	  }
  
  static void fileOutput() {
	//code here to save to a file
  }
  public static String getWeatherStatus(String city)
  {
	  	//String base = "http://iwin.nws.noaa.gov/iwin/nj/hourly.html";
      String base = "http://forecast.weather.gov/product.php?site=NWS&issuedby=PHI&product=RWR&format=CI&version=1&glossary=0";
	  	String result = null;
		URL u = null;
		URLConnection conn = null;
		Scanner in = null;
		try {
			u = new URL(base);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			conn = u.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in = new Scanner(conn.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}	
		parsePage1(in);
		in.close();
		System.out.println(time);
		for (String s:testlist)
		{
			//No need for this loop...
		  	/*for(int i = 0; i < testlist.size(); i++)
		  	{
		  		result = testlist.get(i);
		  		if(result.contains(city))
		  		{
		  			break;
		  		}
		  		System.out.println(result);
		  	}*/
			//Set s to global String.
			result = s;
			//Take the city and if it exists in the result, exit the loop
			if(result.contains(city))
	  		{
	  			break;
	  		}
		  	
		}
		System.out.println(result);
	   	fileOutput();
	   	//Returns the LAST element of the array, right when the loop exits.
	   	return result;
  	}
}

