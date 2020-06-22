package code;
import java.util.ArrayList;
import java.util.List;

public class NovelWriter {
	public NovelWriter() {};
	private String strForTesting = "Test";
	
	public String writeNovel() {
		return "Print from \'writeNovel\'";
    }
	
	private String checkPrivateString() {
		String finalString = null;
		String checkString = "Test";
		if (strForTesting.contains(checkString)) {
			finalString = "OK";
		} else {
			finalString = "NOT";
		}
		return finalString;
	}
	
    private String shout(String s) {
        return s.toUpperCase().replaceAll("\\.", "!");
    }
 
    private List<Integer> countLetters(List<String> words) {
        List<Integer> out = new ArrayList<Integer>();
        for (String word : words) {
            out.add( word.replaceAll("[^A-Za-z]+","").length() );
        }
        return out;
    }
    public static void main(String[] args) {
    	NovelWriter novelWriter = new NovelWriter();
    	
    	System.out.println(novelWriter.shout("this is reflection usage."));
    	System.out.println(novelWriter.writeNovel());
    }
}
