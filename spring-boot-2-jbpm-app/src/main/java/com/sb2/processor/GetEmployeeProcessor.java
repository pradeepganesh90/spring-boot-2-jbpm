package com.sb2.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component("getEmployeeProcessor")
public class GetEmployeeProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getOut().setHeader("id", exchange.getIn().getHeader("id"));
		exchange.getOut().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		exchange.getOut().setHeader(Exchange.HTTP_METHOD, "GET");
		exchange.getOut();
	}

}
