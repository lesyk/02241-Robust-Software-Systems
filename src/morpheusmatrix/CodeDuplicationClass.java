package morpheusmatrix;

import org.eposoft.jccd.data.JCCDFile;
import org.eposoft.jccd.detectors.APipeline;
import org.eposoft.jccd.detectors.ASTDetector;

public class CodeDuplicationClass {
	public void check() {
////        File a = new File("/Users/lesyk/Dropbox/Workspace/MorpheusMatrix/plugin.xml");
//        
//        APipeline<?> detector = new ASTDetector();
        JCCDFile[] files = { 
        	new JCCDFile("/Users/lesyk/Dropbox/Workspace/MorpheusMatrix/src/morpheusmatrix/RabinKarpClass.xml"),
        	new JCCDFile("/Users/lesyk/Dropbox/Workspace/MorpheusMatrix/src/morpheusmatrix/CopyRabinKarpClass.xml") 
        };
//        detector.setSourceFiles(files);
//        APipeline.printSimilarityGroups(detector.process());
//-----------------------------------------
//        AuditListener listener = new MyAuditListener();
//        SourceFile a = new SourceFile("/Users/lesyk/Dropbox/Workspace/MorpheusMatrix/plugin.xml");
//        listener.fileProcessed(a);
//
//        Options options = new Options();
//        options.setThreshold(6);
//        options.setOption(Option.IGNORE_STRINGS, true);
//
//        Checker checker = new Checker(listener, options);

//        StreamLoader streamLoader = new StreamLoader(checker);

//        FileLoader fileLoader = new FileLoader(streamLoader);

//        for (int i = 0; i < args.length; ++i) {
//            loader.load(args[i]);
//        }

//        if (checker.check()) {
//            System.out.println("Duplicate lines were found!");
//        }
    }
}
