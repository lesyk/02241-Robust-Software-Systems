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
		System.out.println("Field +1: "
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
