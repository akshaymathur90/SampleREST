package com.akshay.webService;



import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;


@Path("/hello")
public class HelloWorld {

	  // This method is called if XML is request
	  @Path("/xmlTest")
	  @POST
	  @Consumes("application/xml")
	  @Produces("application/xml")
	  public String sayXMLHello(String xmldata) {
		  System.out.println("The name is"+xmldata);
		  String message = "";
		  DOMParser parser = new DOMParser();
		  try {
		      parser.parse(new InputSource(new java.io.StringReader(xmldata)));
		      Document doc = parser.getDocument();
		      message = doc.getDocumentElement().getTextContent();
		      System.out.println(message);
		  } catch (SAXException e) {
		      // handle SAXException 
		  } catch (IOException e) {
		  }
		  return "<?xml version=\"1.0\"?>\n" + "\t<hello> Welcome to Akshay's Amazon Cloud Webservice" + "</hello>\n\t<data>"+message+"</data>\n";

	  }

	  // This method is called if HTML is request
	  @GET
	  @Produces(MediaType.TEXT_HTML)
	  @Path("/{param}")
	  public String sayHtmlHello(@PathParam("param")String name) {
	    return "<html> " + "<title>" + "Hello Cloud" + "</title>"
	        + "<body><h1>" + "Hello " +name+ "</br>Welcome to Akshay's Amazon Cloud Webservice</body></h1>" + "</html> ";
	  }
	  
}
