package us.hennepin.services;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertManagerTimerImpl implements AlertManagerTimer {

	
	@Inject
	private AlertManager alertManager;

    private static final Logger log = LoggerFactory.getLogger(AlertManagerTimerImpl.class);
    
	@Override
	public void sendAlert(AlertManager alertManager) {

	}
	
	
	@Override
	public void sendAlert() {
		alertManager.info("cron job run");
        log.info("just created the alert");

	}

}
