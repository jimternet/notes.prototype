package us.hennepin.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import us.hennepin.entities.PersistableNote;
import us.hennepin.services.PersistableNoteDao;

public class Contact {
	
	
	Logger logger = LoggerFactory.getLogger(Contact.class);



    public List<PersistableNote> getNotes() { return persistableNoteDao.findAllNotes(); }

	@Inject
	private PersistableNoteDao persistableNoteDao;

	@Property
	private PersistableNote note;

	@Property
	private BeanModel<PersistableNote> persistableNoteModel;

	@Inject
	private BeanModelSource beanModelSource;

	@Inject
	private Messages messages;

	void setupRender() {

		persistableNoteModel = beanModelSource.createDisplayModel(PersistableNote.class,
				messages);
		persistableNoteModel.add("action", null);
		persistableNoteModel.include("id", "contents", "title");
		persistableNoteModel.get("contents").sortable(false);
		persistableNoteModel.get("title").label("Note Title");
//		notes = persistableNoteDao.findAllNotes();
//		notes = session.createCriteria(PersistableNote.class).list();
	}

	@CommitAfter
	void onActionFromDelete(long noteId) {
		persistableNoteDao.delete(noteId);

		// session.delete("PersistableNote", notes.get((int) userId));
	}

}
