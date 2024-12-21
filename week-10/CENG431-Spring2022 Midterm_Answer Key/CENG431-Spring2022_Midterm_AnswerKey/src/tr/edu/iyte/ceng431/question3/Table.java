package tr.edu.iyte.ceng431.question3;

public class Table extends PaperEntry {
	
	private String tableCaption;
	
	public Table(String tableCaption) {
		setSize();
		setTableCaption(tableCaption);
	}

	@Override
	public void setSize() {
		this.size = "Letter";
		
	}

	public String getTableCaption() {
		return tableCaption;
	}

	public void setTableCaption(String tableCaption) {
		this.tableCaption = tableCaption;
	}

}
