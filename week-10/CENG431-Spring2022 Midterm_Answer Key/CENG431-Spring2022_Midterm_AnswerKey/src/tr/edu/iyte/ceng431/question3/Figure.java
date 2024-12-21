package tr.edu.iyte.ceng431.question3;

public class Figure extends PaperEntry {
	

	private String figureCaption;
	
	public Figure(String figureCaption) {
		setSize();
		setFigureCaption(figureCaption);
	}


	@Override
	public void setSize() {
		this.size = "Tabloid";
		
	}


	public String getFigureCaption() {
		return figureCaption;
	}


	public void setFigureCaption(String figureCaption) {
		this.figureCaption = figureCaption;
	}

}
