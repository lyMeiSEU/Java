import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class dirfilter implements FilenameFilter{
	private Pattern pattern;
	public dirfilter(String regex)
	{
		pattern=Pattern.compile(regex);
	}
	
	public boolean accept(File dir,String name) {
		return pattern.matcher(new File(name).getName()).matches();
	}
}
