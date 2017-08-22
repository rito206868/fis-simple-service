package com.esb.opensource.accenture.microservice;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;

public class ServiceCamelRoute extends RouteBuilder {

	@Value("${rest.host}")///mention the openshift cluster POD IP
	String host;
	@Value("${rest.port}")
	String port;
	

	/**
	 * 
	 */
	@Override
	public void configure() throws Exception {

		onException(Exception.class).handled(false).end();

		restConfiguration().component("restlet")//jetty can also be used
		.host(host).port(port)
				.bindingMode(RestBindingMode.json);

		rest("/proxy").post("/echo")
		 .outType(EmployeeDetails.class)
			.to("direct:getDetail");

		from("direct:getDetail")
		.process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getIn().setBody(null);
				EmployeeDetails employeeDetails = new EmployeeDetails();
				
				employeeDetails.setFirstName("John");
				employeeDetails.setLastName("Wick");
				employeeDetails.setId(234);
				employeeDetails.setAddress("22, Cross Street, NewYork, US");
				employeeDetails.setAge(45);
				employeeDetails.setEmail("licencetokill@aol.com");
				
				exchange.getIn().setBody(employeeDetails);
			
			}
		})
		.to("direct:result");


		from("direct:result")
				.log(LoggingLevel.INFO, "${body}")
				.process(
						exchange -> {
							String jsonResponse = exchange.getIn().getBody(
									String.class);
							exchange.getOut().setBody(jsonResponse);
						});		
	}

}
