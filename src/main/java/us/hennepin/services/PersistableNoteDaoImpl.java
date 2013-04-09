package us.hennepin.services;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.hennepin.entities.PersistableNote;

public class PersistableNoteDaoImpl implements PersistableNoteDao  {

	Logger logger = LoggerFactory.getLogger(PersistableNoteDaoImpl.class);

	@Inject
	private Session session;

	@Override
	public List<PersistableNote> findAllNotes() {

		return session.createCriteria(PersistableNote.class).list();
	}

	@Override
	public PersistableNote find(long id) {

		return (PersistableNote) session.get(PersistableNote.class, id);

	}

	@Override
	public void save(PersistableNote note) {
			if (note.id != null) {
				PersistableNote temp = (PersistableNote) session.merge(note);
				session.saveOrUpdate(temp);
			} else {
				session.save(note);

		} 

	}

	@Override
	public void delete(long noteId) {

		try {
			session.delete(find(noteId));
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

	@Override
	@CommitAfter
	public void update(PersistableNote note) {
		//todo fix this.
	}

}
