package morpheusmatrix;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.apache.commons.io.IOUtils;

public class LinesOfCodeCountingClass {
	public Repository initRepo(String location) throws Exception {
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
//		Repository repository = builder.setGitDir(new File("/Users/lesyk/Dropbox/runtime-EclipseApplication/test/.git"))
        Repository repository = builder.setGitDir(new File(location +"/.git"))
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();
        return repository;
	}
	
	public void count() throws Exception {
		FileIOClass fileIOObj = new FileIOClass();
		List<String> projects = fileIOObj.getProjects();
		List<String> files = new ArrayList<String>();
		
		//get all projects
		for(String project : projects){
			System.out.println("Project: "+project);
			
			//getting all files from project
			files.addAll(fileIOObj.listFilesForFolder(project));
			
			Repository repository = initRepo(project+"/");
			
			//getting all commits for project
	        Iterable<RevCommit> logs = new Git(repository).log()
	                .all()
	                .call();
	        int count = 0;
	        for (RevCommit rev : logs) {
	            System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
	            count++;
	        }
	        System.out.println("Had " + count + " commits overall on current branch");
			
	        //getting all commits for each file
			for(String file : files){
				System.out.println("File: " + file + ":");
				System.out.print(fileIOObj.count(file));
		        
				String gitLocation = file;
		        gitLocation = gitLocation.replace(project+"/","");
		        
		        logs = new Git(repository).log()
		                // for all log.all()
		                .addPath(gitLocation)
		                .call();
		        count = 0;
		        for (RevCommit rev : logs) {
		        	
		            // find the HEAD
		            ObjectId lastCommitId = repository.resolve(rev.getId().getName());

		            // a RevWalk allows to walk over commits based on some filtering that is defined
		            RevWalk revWalk = new RevWalk(repository);
		            RevCommit commit = revWalk.parseCommit(lastCommitId);
		            // and using commit's tree find the path
		            RevTree tree = commit.getTree();
//		            System.out.println("Having tree: " + tree);

		            // now try to find a specific file
		            TreeWalk treeWalk = new TreeWalk(repository);
		            treeWalk.addTree(tree);
		            treeWalk.setRecursive(true);
		            treeWalk.setFilter(PathFilter.create(gitLocation));
		            if (!treeWalk.next()) {
		                throw new IllegalStateException("Did not find expected file '"+file+"'");
		            }

		            ObjectId objectId = treeWalk.getObjectId(0);
		            ObjectLoader loader = repository.open(objectId);

		            // and then one can the loader to read the file
//		            loader.copyTo(System.out);
		            
		            ByteArrayOutputStream stream = new ByteArrayOutputStream();
		            // and then one can the loader to read the file
		            loader.copyTo(stream);

		            System.out.print(" " + IOUtils.readLines(new ByteArrayInputStream(stream.toByteArray())).size());
		        	
//		            System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
		            count++;
		        }
		        System.out.println("");
		        System.out.println("Had " + count + " commits on '"+file+"'");
			}
			
	        repository.close();
	        
	        files.clear();
		}
    }
	
//	public void count() throws Exception {
//		
//		get all projects
//		for(String project : projects){
//			System.out.println("Project: "+project);
//			
//			getting all files from project
//			
//			init repositor
//			
//			getting all commits for project
//			
//	        getting all commits for each file
//			for(String file : files){
//				System.out.println("File: " + file + ":");
//				System.out.print(fileIOObj.count(file));
//	
//				get all commits for file
//	
//		        for each commit
//		        	count lines of code
//				end
//			}
//			
//	        close repository
//	        
//    }
}