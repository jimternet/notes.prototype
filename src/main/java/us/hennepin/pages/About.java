package us.hennepin.pages;

import java.util.HashMap;
import java.util.Map;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Mixins;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextArea;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.Zone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import us.hennepin.entities.PersistableNote;
import us.hennepin.services.PersistableNoteDao;

public class About {

	Logger logger = LoggerFactory.getLogger(About.class);
	
	@Inject
	private PersistableNoteDao persistableNoteDao;

	@Inject
	@Path("context:js/ckeditorconfig.js")
	private Asset config;

	@Inject
	@Path("context:js/ckeditorconfigwithautosave.js")
	private Asset configWithAutosave;
	
	@Environmental
	private JavaScriptSupport javaScriptSupport;
	
    @Property
    private Boolean noteHasNotBeenSaved = checkIfNoteHasBeenSaved();
    

	@Property
	PersistableNote note;
	
	@Inject
	private Request request;

	@Inject
	private Response response;

	@InjectComponent
	private Zone noteFormZone;
	
	@Component
	private Form noteForm;
	
	@Component(id="title")
	private TextField title;
	
	@Component(id="noteText", parameters={"customConfig=CKEditorParameters"}) @Mixins("tynamo/ckeditor")
	private TextArea noteText;

	public Map<String, Object> getCKEditorParameters() {
		
		Asset selectedConfig = null;
		if (noteHasNotBeenSaved){
			selectedConfig = config;
		} else {
			selectedConfig = configWithAutosave;
		}
	    javaScriptSupport.importJavaScriptLibrary(selectedConfig);

		Map<String, Object> map = new HashMap<String, Object>();
		logger.trace("###################");
		logger.trace(selectedConfig.toClientURL());

		map.put("customConfig", selectedConfig.toClientURL());
		return map;
	}
	
	void onFailure() {
		logger.error("###################### HERE IN ONSUCCESS *********");
	}


	@CommitAfter
	Object onSuccess() throws Exception {
		logger.info("############### >>> SAVUIG BRO!!!!");
		persistableNoteDao.save(note);		
		return this;

	}

	void onActivate(PersistableNote note) {
		logger.info(">>> On Activate");
		this.note = note;
		if (note!=null) {
			logger.info("here is the note : " + note.toString());
			this.noteHasNotBeenSaved = checkIfNoteHasBeenSaved();
		} 
	}
	
	
	


	PersistableNote onPassivate() {
		return note;
	}

	void onActivate() {
		if (note == null) {
			this.note = new PersistableNote();
			this.note.contents = "";

		}
	}
	
	void onValidateFromNoteForm() {
		
		for (int i = 0; i < 10; i++) {
			logger.info("onValidateFromNoteForm");
		}
	}
	
	void onSuccessFromNoteForm() {
		for (int i = 0; i < 10; i++) {
			logger.info("onSuccessFromNoteForm");
		}
	}


	
	protected void setNoteOnPage(PersistableNote note){
		this.note = note;
	}
	
	private Boolean checkIfNoteHasBeenSaved(){
		
		if (this.note == null ||
				   this.note.id == null){
			logger.info("Note has note been saved");

			return Boolean.TRUE;


		} else {
			return Boolean.FALSE;
		}
	}
	
    


}
