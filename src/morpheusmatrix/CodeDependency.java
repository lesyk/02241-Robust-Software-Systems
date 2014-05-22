package morpheusmatrix;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class CodeDependency {

	double	WEAK = 0.1;
	double	MEDIUM = 1;
	double	HARD = 5;
		
	Map<String, HashMap<String, Double>> dependencies = new HashMap<String, HashMap<String,Double>>();
	
	IWorkspace justaworkspace = null;
	
	public void count()
	{
		analyzeProjects(ResourcesPlugin.getWorkspace().getRoot());
		System.out.println("# of Classes " + dependencies.size());
		
		printMap(dependencies);
	}
	
	private void printMap(Map<String, HashMap<String, Double>> mp) {
		
		for(Map.Entry<String, HashMap<String, Double>> entry : mp.entrySet()){
			if(entry.getValue().entrySet().size() != 0){
				System.out.println("Type " + entry.getKey() + " dependes on:");
				for(Map.Entry<String, Double> dep: entry.getValue().entrySet())
				{
					System.out.println("\t" + dep.getKey() + " with level " + dep.getValue());
				}
			}
		}
	}
	
	private void analyzeProjects(IWorkspaceRoot root)
	{
		IProject[] projects = root.getProjects();
		for (IProject project : projects) {
			analyzeProject(project);
		}
	}
	/*
	private void analyzeProject(IProject project)
	{
		if(project.isOpen())
		{
			System.out.println("Current project: " + project.getName());
			IPackageFragment[] packages;
			try {
				packages = JavaCore.create(project).getPackageFragments();

				for(IPackageFragment fragment : packages)
				{
					if (fragment.getKind() == IPackageFragmentRoot.K_SOURCE){
						for (ICompilationUnit unit : fragment.getCompilationUnits()) {
							analyzeCompilationUnit(unit);
						}
					}
				}
			}
			catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	*/
	
	private void analyzeProject(IProject project)
	{
		if(project.isOpen())
		{
			//System.out.println("Current project: " + project.getName());
			IPackageFragment[] packages;
			try {
				packages = JavaCore.create(project).getPackageFragments();

				for(IPackageFragment fragment : packages)
				{
					if (fragment.getKind() == IPackageFragmentRoot.K_SOURCE){
						for (ICompilationUnit unit : fragment.getCompilationUnits()) {
							CompilationUnit cu = parse(unit);
							CompilationUnitVisitor visitor = new CompilationUnitVisitor();
							dependencies.putAll(visitor.startVisit(cu));
							//System.out.println("---------------------------------");
						}
					}
				}
			}
			catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
/*
	private void analyzeCompilationUnit(ICompilationUnit cu)
	{
		
		System.out.println("\tCurrent file: " + cu.getElementName());
		try{
			IType[] types = cu.getAllTypes();
			for(IType type: types)
			{
				analyzeType(type);
			}
		}
		catch(JavaModelException e){
			e.printStackTrace();
		}
	}
	
	private void analyzeType(IType type)
	{
		System.out.println("\tCurrent class:" + type.getElementName());
		dependencies.put(type.getElementName(), new HashMap<String, Double>());
		analyzeFields(type);
		analyzeMethods(type);

	}
	
	private void analyzeMethods(IType type) {
		try{
		IMethod[] methods = type.getMethods();
		System.out.print("\t\tMethods: ");
		for(IMethod method: methods)
		{
			System.out.print(method.getElementName() + ":");
			System.out.print(Signature.toString(method.getReturnType()) + ", ");
			addDependency(type.getElementName(), Signature.toString(method.getReturnType()), WEAK);
			analyzeMethodParameters(method);
		}			
		System.out.println();
		}catch(JavaModelException e)
		{
			e.printStackTrace();
		}
	}
	
	private void analyzeMethodParameters(IMethod method)
	{
		String[] paramTypes = method.getParameterTypes();
		System.out.print("\t\t\tParameters :");
		for(String type: paramTypes)
		{
			//System.out.print(type+ ", ");
			System.out.print(Signature.toString(type) + ", ");
		}
		System.out.println();
	}
	
	private void analyzeMethodBody()
	{
		
	}
	
	private void analyzeFields(IType type) {
		try{
			System.out.print("\t\tFields: ");
			IField[] children =  type.getFields();
			for(IField field: children)
			{
				System.out.print(field.getElementName() + ":");
				System.out.print(Signature.toString(field.getTypeSignature()) + ", ");
			}
			System.out.println();
		}catch(JavaModelException e){
			e.printStackTrace();
		}
	}
	
	private void addDependency(String cuName, String dependendsOn, Double level)
	{
		Map<String, Double> deps = dependencies.get(cuName);
		if(deps.containsKey(dependendsOn))
		{
			Double currentLevel = deps.get(dependendsOn);
			Double newLevel = new Double(currentLevel.doubleValue()+level);
			deps.put(cuName, newLevel);
		}
		else
		{
			deps.put(cuName, level);
		}
	}
*/
	private static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(false); //it was true
		return (CompilationUnit) parser.createAST(null); // parse
	}

}