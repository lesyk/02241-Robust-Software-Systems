package morpheusmatrix;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleType;

public class MethodDeclarationVisitor extends ASTVisitor {

	private HashMap<String, Double> depList = null;

	public MethodDeclarationVisitor(HashMap<String, Double> depList)
	{
		this.depList = depList;
	}

	public boolean visit(SimpleType type) {
		System.out.println("Method +0.1: "
				+ type.getName().getFullyQualifiedName());
		String typeName = type.getName().getFullyQualifiedName();
		if (depList.containsKey(typeName)) {
			Double currDepLevel = depList.get(typeName);
			depList.put(typeName, currDepLevel + 0.5);
		} else {
			depList.put(typeName, 0.5);
		}
		return true;
	}
}
