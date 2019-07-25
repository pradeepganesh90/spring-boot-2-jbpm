package com.sb2.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb2.bo.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Component("getEmployeeResponseProcess")
@Slf4j
public class GetEmployeeResponseProcess implements Processor{

	@Autowired
    private ObjectMapper mapper;

    @Override
    public void process(final Exchange exchange) throws Exception {
        final String response = exchange.getIn().getBody().toString();
        final int responseCode = exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
        log.debug("OrderResponseProcess -> process responseCode : {}", responseCode);

        if ((responseCode >= HttpStatus.OK_200) && (responseCode <= HttpStatus.RESET_CONTENT_205)) {
            exchange.getOut().setBody(response);
        } else {
            final ApiErrorResponse errorResponse = mapper.readValue(response, ApiErrorResponse.class);
            errorResponse.setResponseCode(HttpStatus.UNPROCESSABLE_ENTITY_422);
            errorResponse.setResponseText(HttpStatus.getMessage(HttpStatus.UNPROCESSABLE_ENTITY_422));
            exchange.getOut().setBody(errorResponse);
        }
    }

}
