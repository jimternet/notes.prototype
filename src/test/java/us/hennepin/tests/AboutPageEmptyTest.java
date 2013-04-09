package us.hennepin.tests;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.test.PageTester;
import org.easymock.EasyMock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.formos.tapestry.testify.core.ForComponents;

import us.hennepin.entities.PersistableNote;
import us.hennepin.services.PersistableNoteDao;

public class AboutPageEmptyTest extends AbstractNotesTest
{
	@ForComponents
	private PersistableNoteDao persistableNoteDao;
	
	private String appPackage;
	private String appName;
	private PageTester tester;
	
	
   public void doSetUp() {
	   persistableNoteDao = EasyMock.createMock(PersistableNoteDao.class);
   }
	
    @BeforeClass
    public void oneTimeSetUp() throws Exception {
    	this.appPackage = "us.hennepin";
    	this.appName = "Development";
    	this.tester = new PageTester(appPackage, appName, "src/main/webapp");
    	
    }


	@Test
	public void savePerson(){
    	PersistableNote note = new PersistableNote();
    	note.setContents("doof");

    	this.persistableNoteDao.save(note);
	}
	

}
