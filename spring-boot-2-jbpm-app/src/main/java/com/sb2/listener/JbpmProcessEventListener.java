package com.sb2.listener;

import org.apache.commons.lang3.StringUtils;
import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component("jbpmProcessEventListener")
@Slf4j
public class JbpmProcessEventListener implements ProcessEventListener {

	@Override
	public void beforeProcessStarted(ProcessStartedEvent event) {

	}

	@Override
	public void afterProcessStarted(ProcessStartedEvent event) {

	}

	@Override
	public void beforeProcessCompleted(ProcessCompletedEvent event) {

	}

	@Override
	public void afterProcessCompleted(ProcessCompletedEvent event) {

	}

	@Override
	public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {

	}

	@Override
	public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {

	}

	@Override
	public void beforeNodeLeft(ProcessNodeLeftEvent event) {
		if (StringUtils.equals(event.getProcessInstance().getProcessName(), "printname")
				&& StringUtils.equals(event.getNodeInstance().getNode().getName(), "Print Name")) {
			log.info("Node Name is " + event.getNodeInstance().getNode().getName());
		}
	}

	@Override
	public void afterNodeLeft(ProcessNodeLeftEvent event) {

	}

	@Override
	public void beforeVariableChanged(ProcessVariableChangedEvent event) {

	}

	@Override
	public void afterVariableChanged(ProcessVariableChangedEvent event) {

	}

}
