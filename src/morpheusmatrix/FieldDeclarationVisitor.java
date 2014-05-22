package morpheusmatrix;

import java.util.HashMap;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleType;

public class FieldDeclarationVisitor extends ASTVisitor {
	private HashMap<String, Double> depList = null;

	public FieldDeclarationVisitor(HashMap<String, Double> depList) {
		this.depList = depList;
	}

	public boolean visit(SimpleType type) {
		System.out.println("Field +1: " + type.getName().getFullyQualifiedName());
		String typeName = type.getName().getFullyQualifiedName();
		if (depList.containsKey(typeName)) {
			Double currDepLevel = depList.get(typeName);
			depList.put(typeName, currDepLevel + 1.0);
		} else {
			depList.put(typeName, 1.0);
		}
		return true;
	}
}
