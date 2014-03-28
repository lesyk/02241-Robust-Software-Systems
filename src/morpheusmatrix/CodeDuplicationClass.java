package morpheusmatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.harukizaemon.simian.AuditListener;
import com.harukizaemon.simian.Checker;
import com.harukizaemon.simian.FileLoader;
import com.harukizaemon.simian.Option;
import com.harukizaemon.simian.Options;
import com.harukizaemon.simian.StreamLoader;

public class CodeDuplicationClass {
	
	public void check() throws IOException {
        AuditListener listener = new CodeDuplicationListenerClass();        
        Options options = new Options();
        options.setThreshold(6);
        options.setOption(Option.IGNORE_STRINGS, true);
        options.setOption(Option.FAIL_ON_DUPLICATION, true);
        options.setOption(Option.IGNORE_CHARACTER_CASE, true);
        options.setOption(Option.IGNORE_CURLY_BRACES, true);
        options.setOption(Option.IGNORE_IDENTIFIER_CASE, true);
        options.setOption(Option.IGNORE_MODIFIERS, true);
        options.setOption(Option.IGNORE_STRING_CASE, true);

        Checker checker = new Checker(listener, options);
        StreamLoader streamLoader = new StreamLoader(checker);
        FileLoader fileLoader = new FileLoader(streamLoader);
        
//        File a = new File("/Users/lesyk/Dropbox/Workspace/MorpheusMatrix/test/RabinKarpClass.java");
//        File b = new File("/Users/lesyk/Dropbox/Workspace/MorpheusMatrix/test/CopyOfRabinKarpClass.java");
        
//        add files form projects
        FileIOClass fileIOObj = new FileIOClass();
		List<String> projects = fileIOObj.getProjects();
		List<String> files = new ArrayList<String>();
		
		for(String project : projects){
			System.out.println("Project: "+project);
			files.addAll(fileIOObj.listFilesForFolder(project));
		}
		
		for(String file : files){
			try {
				fileLoader.load(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        
        if (checker.check()) {
        	System.out.println("There are no duplications.");
        }else{
        	System.out.println("Duplicate lines were found!");
        }
    }
}