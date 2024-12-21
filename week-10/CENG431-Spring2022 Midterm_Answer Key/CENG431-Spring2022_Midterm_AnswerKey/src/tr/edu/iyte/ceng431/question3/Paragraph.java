package tr.edu.iyte.ceng431.question3;

public class Paragraph extends PaperEntry {
	
	private int numberOfLines;
	
	public Paragraph(int numberOfLines) {
		setSize();
		setNumberOfLines(numberOfLines);
	}

	@Override
	public void setSize() {
		this.size = "A3";
		
	}

	public int getNumberOfLines() {
		return numberOfLines;
	}

	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

}
