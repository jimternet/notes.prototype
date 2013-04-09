package us.hennepin.pages;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;

public class BasePage {
	
    @Inject
    private AlertManager alertManager;
	
    @InjectComponent
    private Zone zone;

}
