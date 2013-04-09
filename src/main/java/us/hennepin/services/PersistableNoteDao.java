package us.hennepin.services;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import us.hennepin.entities.PersistableNote;

public interface PersistableNoteDao {
	
  	List<PersistableNote> findAllNotes();
  	PersistableNote find(long id);
  	@CommitAfter
  	void save(PersistableNote note);
  	@CommitAfter
  	void delete(long noteId);
  	@CommitAfter
  	void update(PersistableNote note);

}
