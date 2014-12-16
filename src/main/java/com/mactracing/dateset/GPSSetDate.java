package com.mactracing.dateset;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
public class GPSSetDate {

	static Enumeration<CommPortIdentifier> lista;
	static CommPort serialPort;
	static InputStream input;
	public static void main(String[] args) throws UnsupportedCommOperationException, PortInUseException, IOException {

		 lista = CommPortIdentifier.getPortIdentifiers();
		 while (lista.hasMoreElements())
		 {
		 CommPortIdentifier comPort = lista.nextElement();
		 if(comPort.getName().contains("/dev/ttyUSB0"))
		 {
			 serialPort = comPort.open(comPort.getName(),9600);
			 input = serialPort.getInputStream();
			 char character;
			 String linea="";
			 do
			 {
		     character = (char)input.read();
		     
		     if ((int)character!=10)
		     {
			 linea = linea+character;
		     }
		     else
		     {
		    	 if (linea.contains("$GPGLL"))
		    	 {
		    	 System.out.println(linea);
		    	 }
		    	 	if (linea.contains("$GPRMC"))
		    	 	{
		    	 		String[] campos= linea.split(",");
		    	 		System.out.println(linea);
		    	 			if (campos.length == 13 && campos[1].isEmpty()==false && campos[9].isEmpty()==false )
		    	 			{
		    	 				final String hora = campos[1].replace(".00", "").substring(0, 2)+":"+campos[1].replace(".00", "").substring(2, 4)+":"+campos[1].replace(".00", "").substring(4, 6);
		    	 				final String fecha = "20"+campos[9].substring(4, 6)+"-"+campos[9].substring(2, 4)+"-"+campos[9].substring(0, 2);
		    	 				final String comandoAjuste = "date --set \""+fecha+" "+hora+"\"";
		    	 				
		    	 			}
		    	 
		    	 	}
		         
		    	 linea ="";
		     }
		    	 
		    	 
		     }
			 while(true);
			 //input.close();
		 }
		
		 
		 }
		 
		 
	}

}
