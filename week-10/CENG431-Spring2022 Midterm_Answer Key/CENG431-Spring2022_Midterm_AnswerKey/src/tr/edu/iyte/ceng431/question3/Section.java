package tr.edu.iyte.ceng431.question3;

import java.util.LinkedList;
import java.util.List;

public class Section extends PaperEntry {
	
	private String title;
	private List<PaperEntry> paperEntryList;
	
	public Section(String title) {
		setSize();
		setTitle(title);
		paperEntryList = new LinkedList<PaperEntry>();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<PaperEntry> getPaperEntryList() {
		return paperEntryList;
	}

	public void addPaperEntry(PaperEntry paperEntry) {
		paperEntryList.add(paperEntry);
	}
	
	public void removePaperEntry(PaperEntry paperEntry) {
		paperEntryList.remove(paperEntry);
	}
	
	public int numberOfPaperEntriesInTheSection() {
		return paperEntryList.size();
	}

	@Override
	public void setSize() {
		this.size = "A4";
		
	}



}
