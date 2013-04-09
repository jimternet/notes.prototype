package us.hennepin.pages;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.hennepin.entities.PersistableNote;
import us.hennepin.services.PersistableNoteDao;

public class NotePersister {

	@Property
	PersistableNote note;

	@Inject
	private PersistableNoteDao persistableNoteDao;

	@Inject
	private Request request;

	@Inject
	private Response response;

	@Property
	private String content;
	
	@InjectPage
	private About aboutPage;


	private static final Logger logger = LoggerFactory
			.getLogger(NotePersister.class);

	public void onActivate() throws Exception {
		PrintWriter pw = response.getPrintWriter("text/xml;charset=UTF-8");

		try {
			// RequestLogger.logRequest(request);

			content = request.getParameter("content");
			
			if (logger.isInfoEnabled()) {
				logger.info(content);}

			if ((request.getHeader("Referer")) != null) {
				// Referer : http://localhost:8080/notes.prototype/about/7

				String referer = request.getHeader("Referer");
				String regex = "[0-9]{1,}$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(referer);


				if (matcher.find()) {
					String noteId = matcher.group();
					logger.debug("Found IT find()!");
					logger.debug(noteId);
					this.note = persistableNoteDao.find(Long.parseLong(noteId));
					autoSave();


					setResponse200(pw);
				
				} else {
					
					setResponse404(pw);
					//TODO need to figure how to return to the page with the id.
					
				}

			}



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pw.close();

		}

	}

	private void setResponse200(PrintWriter pw) {
		response.setHeader("Expires", new Long(
				System.currentTimeMillis() - 10000000).toString());
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setStatus(200);
		pw.println("<result status=\"ok\" />");
	}
	
	private void setResponse404(PrintWriter pw) {
		response.setHeader("Expires", new Long(
				System.currentTimeMillis() - 10000000).toString());
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setStatus(404);
		pw.println("<result status=\"Need to Save manually before autosave will work\" />");
	}

	@CommitAfter
	void autoSave() throws Exception {
		if (note != null) {
			logger.info("Here is the note");
			logger.info(note.toString());
		}
		if (note == null) {
			note = new PersistableNote();
		}
		note.setContents(content);
		persistableNoteDao.save(note);
	}

}
