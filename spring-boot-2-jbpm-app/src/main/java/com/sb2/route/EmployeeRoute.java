package com.sb2.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sb2.processor.GetEmployeeProcessor;
import com.sb2.processor.GetEmployeeResponseProcess;

@Component
public class EmployeeRoute extends RouteBuilder {

	@Autowired
	GetEmployeeProcessor getEmployeeProcessor;

	@Autowired
	GetEmployeeResponseProcess getEmployeeResponseProcess;

	@Override
	public void configure() throws Exception {

		from("direct:getEmployeeById").routeId("get_employee_by_id")
				.log("--------------------------direct:employee::employeeById--------------------")
				.process(getEmployeeProcessor)
				.to("restlet:{{resource.get.employee}}{id}?restletMethod=get&amp;throwExceptionOnFailure=false")
				.convertBodyTo(String.class).process(getEmployeeResponseProcess)
				.end();
	}

}
