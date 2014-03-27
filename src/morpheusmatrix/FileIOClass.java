package morpheusmatrix;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

public class FileIOClass {
	public List<String> getProjects(){
		List<String> projectsList = new ArrayList<String>();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		// Get all projects in the workspace
		IProject[] projects = root.getProjects();
		// Loop over all projects
		for (IProject project : projects) {
			projectsList.add(project.getLocation().toString());
		}
		
		return projectsList;
	}
	
	public void listFilesForFolder(String path) throws IOException {
		File root = new File(path);
		File[] list = root.listFiles();

        if(list == null) return;

        for(File f : list) {
            if (f.isDirectory()) {
            	listFilesForFolder(f.getAbsolutePath());
//                System.out.println("Dir:" + f.getAbsoluteFile());
            }else{
            	System.out.println("File:" + f.getAbsoluteFile());
            	System.out.println(count(f.getAbsoluteFile().toString()));
            }
        }
	}
	
	public int count(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}
}