package morpheusmatrix;

import java.util.List;

public class LinesOfCodeCountingClass {
	public void count() throws Exception {
		FileIOClass file = new FileIOClass();
//		file.listFilesForFolder("/Users/lesyk/Dropbox/Workspace/MorpheusMatrix");
		List<String> projects = file.getProjects();
		
		for(String project : projects){
			System.out.println(project);
			file.listFilesForFolder(project);
		}
		
//		String a = "/Users/lesyk/Dropbox/Workspace/MorpheusMatrix/.git";
//		File gitDir = new File(a);		
//	    Git git = Git.open(gitDir);
//	    Status status=git.status().call();
//	    String oldHash = "393234c07e74be8ca89bd57c780f665564f04653";
//	 
//	    ObjectId headId = git.getRepository().resolve("HEAD^{tree}");
//	    ObjectId oldId = git.getRepository().resolve(oldHash + "^{tree}");
//	 
//	    ObjectReader reader = git.getRepository().newObjectReader();
//	     
//	    CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
//	    oldTreeIter.reset(reader, oldId);
//	    CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
//	    newTreeIter.reset(reader, headId);
//	 
//	    List<DiffEntry> diffs= git.diff()
//	            .setNewTree(newTreeIter)
//	            .setOldTree(oldTreeIter)
//	            .call();
//	     
//	    ByteArrayOutputStream out = new ByteArrayOutputStream();
//	    DiffFormatter df = new DiffFormatter(out);
//	    df.setRepository(git.getRepository());
//	 
//	    for(DiffEntry diff : diffs)
//	    {
//	      df.setContext(0);
//	      df.format(diff);
//	      diff.getOldId();
//	      String diffText = out.toString("UTF-8");
//	      System.out.println(diffText);
//	      out.reset();
//	    }
	}
}
