package morpheusmatrix;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class CompilationUnitVisitor extends ASTVisitor {
	
	Map<String, HashMap<String, Double>> dependencies = new HashMap<String, HashMap<String,Double>>();
	
	public Map<String, HashMap<String, Double>> startVisit(CompilationUnit cu) {
		cu.accept(this);
		return dependencies;
	}

	/*
	 * public boolean visit(VariableDeclarationFragment node) {
	 * System.out.println(node.getName().getIdentifier()); return true; }
	 */
	/*
	 * public boolean visit(MethodDeclaration method){ method.accept(new
	 * MethodDeclarationVisitor(dependencies)); return true; } /* public boolean
	 * visit(SimpleType type){
	 * System.out.println(type.getName().getFullyQualifiedName()); return true;
	 * }
	 */
	/*
	 * public boolean visit(FieldDeclaration field) {
	 * //System.out.println("FieldDeclaration type: " + field.; field.accept(new
	 * FieldDeclarationVisitor()); return true; }
	 */
	public boolean visit(TypeDeclaration node) {
		System.out.println("TypeDeclaration " + node.getName().getIdentifier()
				+ "depends on:");
		System.out.println("Parent class +5: " + node.getSuperclassType());
		HashMap<String, Double> depList = new HashMap<String, Double>();
		// dependencies.put(type.getElementName(), new HashMap<String,
		// Double>());
		if (node.getSuperclassType() != null)
			depList.put(node.getSuperclassType().toString(), 5.0);
		node.accept(new FieldDeclarationVisitor(depList));
		node.accept(new MethodDeclarationVisitor(depList));
		dependencies.put(node.getName().getIdentifier(), depList);
		return true;
	}
	
}
