package tr.edu.iyte.ceng431.question3;

public class Main {

	public static void main(String[] args) {
		
		Section paper = new Section("Paper Title");
		
		Paragraph p1 = new Paragraph(20);
		Paragraph p2 = new Paragraph(30);
		
		Figure f1 = new Figure("Figure 1.");
		Figure f2 = new Figure("Figure 2.");
		
		Table t1 = new Table("Table 1.");
		
				
		paper.addPaperEntry(p1);
		paper.addPaperEntry(p2);
		
		paper.addPaperEntry(f1);
		paper.addPaperEntry(f2);
		
		paper.addPaperEntry(t1);
		
		System.out.println("Number of paragraphs, figures and tables " + paper.numberOfPaperEntriesInTheSection());
		

	}

}
