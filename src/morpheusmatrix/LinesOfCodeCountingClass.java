package morpheusmatrix;

import java.io.IOException;
import java.util.List;

public class LinesOfCodeCountingClass {
	public void count() throws IOException {
		FileIOClass file = new FileIOClass();
//		file.listFilesForFolder("/Users/lesyk/Dropbox/Workspace/MorpheusMatrix");
		List<String> projects = file.getProjects();
		
		for(String project : projects){
			System.out.println(project);
			file.listFilesForFolder(project);
		}
	}
}
