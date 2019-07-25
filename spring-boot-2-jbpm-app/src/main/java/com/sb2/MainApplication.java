package com.sb2;

import java.util.Collection;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.model.ProcessDefinition;
import org.kie.api.runtime.query.QueryContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan(basePackages = "com.sb2")
@Slf4j
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Value("${org.jbpm.var.log.length}")
	String instanceVarLength;

	@Bean
	CommandLineRunner deployAndValidate() {
		return new CommandLineRunner() {

			@Autowired
			private DeploymentService deploymentService;

			@Autowired
			private RuntimeDataService runtimeDataService;

			@Override
			public void run(String... strings) throws Exception {
				System.getProperties().setProperty("org.jbpm.var.log.length", instanceVarLength);
				KModuleDeploymentUnit unit = null;
				if (strings.length > 0) {
					String arg = strings[0];
					log.info("About to deploy : {}", arg);

					String[] gav = arg.split(":");

					unit = new KModuleDeploymentUnit(gav[0], gav[1], gav[2]);
					deploymentService.deploy(unit);
					log.info("{} successfully deployed", arg);
				}
				log.info("Available processes:");
				Collection<ProcessDefinition> processes = runtimeDataService.getProcesses(new QueryContext());
				for (ProcessDefinition def : processes) {
					log.info("\t{} (with id '{})", def.getName(), def.getId());
				}

				if (unit != null && !processes.isEmpty()) {
					String processId = processes.iterator().next().getId();
					log.info("About to start process with id {}", processId);
				}
				log.info("========= Verification completed successfully =========");

			}
		};
	}
}