package morpheusmatrix;

import com.harukizaemon.simian.AuditListener;
import com.harukizaemon.simian.Block;
import com.harukizaemon.simian.CheckSummary;
import com.harukizaemon.simian.Options;
import com.harukizaemon.simian.SourceFile;

public class CodeDuplicationListenerClass implements AuditListener {

	@Override
	public void block(Block arg0) {
		System.out.println("Between lines " + arg0.getStartLineNumber()
				+ " and " + arg0.getEndLineNumber() + " in "
				+ arg0.getSourceFile().getFilename());
	}

	@Override
	public void endCheck(CheckSummary arg0) {
		System.out.println("Found " + arg0.getDuplicateLineCount()
				+ " duplicate lines in " + arg0.getDuplicateBlockCount()
				+ " blocks in " + arg0.getDuplicateFileCount() + " files");
		System.out.println("Processed a total of "
				+ arg0.getTotalSignificantLineCount() + " significant ("
				+ arg0.getTotalRawLineCount() + " raw) lines in "
				+ arg0.getDuplicateFileCount() + " files");
		System.out.println("Processing time (in milliseconds):"
				+ arg0.getProcessingTime());
		System.out
				.println("The number of duplicate lines as a percentage of duplicate line count: "
						+ arg0.getDuplicateLinePercentage() + "%");
	}

	@Override
	public void endSet(String arg0) {
		System.out.println("Duplication search ended.");
	}

	@Override
	public void fileProcessed(SourceFile arg0) {
	}

	@Override
	public void startCheck(Options arg0) {
	}

	@Override
	public void startSet(int arg0) {
	}

}
