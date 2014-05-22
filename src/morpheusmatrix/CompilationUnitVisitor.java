package morpheusmatrix;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

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
		System.out.print("TypeDeclaration " + node.getName().getIdentifier() + " depends on: ");
		if(node.getSuperclassType() != null){
			System.out.println("Parent class +5: " + node.getSuperclassType());
		}else{
			System.out.println("None.");
		}
		
		HashMap<String, Double> depList = new HashMap<String, Double>();
		// dependencies.put(type.getElementName(), new HashMap<String,
		// Double>());
		if (node.getSuperclassType() != null)
			depList.put(node.getSuperclassType().toString(), 5.0);
		node.accept(new FieldDeclarationVisitor(depList));
		dependencies.put(node.getName().getIdentifier(), depList);
		return true;
	}
	
}
