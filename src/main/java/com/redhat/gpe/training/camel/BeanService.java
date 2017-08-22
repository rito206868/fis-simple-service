package com.redhat.gpe.training.camel;	

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BeanService implements Processor {


    private Logger log = LoggerFactory.getLogger(BeanService.class);

    public void process(Exchange exchange) throws Exception {
    	
    	
		             //getting the request object
					 String req = (String) exchange.getIn().getBody();
		             System.out.println("Message Body from prigya's service :" + req);
		             //req = req.substring(1,req.length()-1);
		             System.out.println("req =" + req);
		             exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
			            // exchange.getOut().setBody("{\"id\":\"01\",\"name\":\"Joe\",\"age\":32}");
			         exchange.getOut().setBody(req);
				
    	
    }

}
