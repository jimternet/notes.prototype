package us.hennepin.services;

import org.apache.tapestry5.alerts.AlertManager;

public interface AlertManagerTimer {
	
	void sendAlert(AlertManager alertManager);
	
	void sendAlert();


}
