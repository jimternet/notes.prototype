package us.hennepin.tests;
import us.hennepin.services.DevelopmentModule;

import com.formos.tapestry.testify.core.TapestryTester;
import com.formos.tapestry.testify.testng.TapestryTest;

public abstract class AbstractNotesTest extends TapestryTest{

	
	   private static final TapestryTester SHARED_TESTER =
		        new TapestryTester("us.hennepin", DevelopmentModule.class);

		    public AbstractNotesTest() {
		        super(SHARED_TESTER);
		    }  
}
