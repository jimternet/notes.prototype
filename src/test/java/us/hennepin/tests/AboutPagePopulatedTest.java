package us.hennepin.tests;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.test.PageTester;
import org.easymock.EasyMock;
import us.hennepin.entities.PersistableNote;
import us.hennepin.services.PersistableNoteDao;

public class AboutPagePopulatedTest extends AbstractNotesTest {

	private static final String TEST_NOTE_CONTENTS = "test contents";
	private static final String TEST_NOTE_TITLE = "test title";

	private String appPackage;
	private String appName;
	private PageTester tester;
	private Long noteId;
	private PersistableNoteDao persistableNoteDao;

	public void doSetUp() {
		persistableNoteDao = EasyMock.createMock(PersistableNoteDao.class);
	}


	// @BeforeClass
	public void oneTimeSetUp()  {




		// this.appName = "";
		this.tester = new PageTester(appPackage, appName, "src/main/webapp");

		PersistableNote note = new PersistableNote();
		note.setContents(TEST_NOTE_CONTENTS);
		note.setTitle(TEST_NOTE_TITLE);
		persistableNoteDao.save(note);
		this.noteId = note.getId();

	}

	// @Test
	public void pageNotNull() {
		Document doc = this.tester.renderPage("About/" + this.noteId);
		
		// assertEquals(doc.getElementById("id1").getChildText(), "hello");

	}

	// @Test
	public void editorAvailable() {

		Document doc = this.tester.renderPage("About/" + this.noteId);
		// assertEquals(doc.getElementById("id1").getChildText(), "hello");
//		assertNotNull(doc.getElementById("noteText"));

	}

	// @Test
	public void editorAvailableWithNoText() {

		Document doc = this.tester.renderPage("About/" + this.noteId);
		// assertEquals(doc.getElementById("id1").getChildText(), "hello");
//		assertFalse(doc.getElementById("noteText").isEmpty());

	}
}
