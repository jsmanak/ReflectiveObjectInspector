/* 
 * CPSC 501 Assignment 2
 * Submitted by : Jessie Manak
 * 
*/

import java.util.*;
import java.lang.reflect.*;

public class Inspector {
	
	
	public Inspector() {
	}

	/*
	 * inspect will introspect on an object returning information about interfaces,
	 * methods, constructor, fields and the super class. This method also inspects recursively
	 * if true method will introspect on superclass
	 */
	public void inspect(Object obj, boolean recursive) {
		Vector objectsToInspect = new Vector();
		Class objClass = obj.getClass();

		System.out.println("inside inspector: "
				+ obj.getClass().getSimpleName() + " (recursive = " + recursive
				+ ")");
		System.out.println("Name of declaring class: "
				+ objClass.getSimpleName());
		System.out.println("Name of immediate superclass: "
				+ objClass.getSuperclass().getSimpleName());

		inspectInterfaces(obj, objClass);
		inspectMethods(obj, objClass);
		inspectConstructor(obj, objClass);
		inspectFields(obj, objClass, objectsToInspect);

		if ((objClass.getSuperclass() != null) && (objClass.getSuperclass() != Object.class)) {
			inspectSuperclass(obj, objClass, objectsToInspect);
		}

		if (recursive)
			inspectFieldClasses(obj, objClass, objectsToInspect, recursive);
	}
  	 //getMethodExceptions will return the exceptions thrown by a method
	private String getMethodExceptions(Method aMethod) {
		Class[] exceptions = aMethod.getExceptionTypes();
		String except = "";
		if (exceptions.length == 0)
			except = "none";
		else
			for (Class aException : exceptions) {
				except += aException.getSimpleName() + " ";
			}
		return except;
	}
	
		//getConstructorParameters will return a constructors list of the parameters
	private String getConstructorParameters(Constructor aConstructor) {
		Class[] parameters = aConstructor.getParameterTypes();
		String params = "";
		if (parameters.length == 0)
			params = "none";
		else
			for (Class aParam : parameters) {
				params += aParam.getSimpleName() + " ";
			}
		return params;
	}
	 //inspectInterfaces will introspect the interfaces of the object
	private void inspectInterfaces(Object obj, Class objClass) {
		System.out.println();
		System.out.println("'" + objClass.getSimpleName() + "' Interface(s):");
		Class[] interfaces = objClass.getInterfaces();
		if (interfaces.length > 0) {
			System.out.println(interfaces.length + " Interface(s) found");
			for (int i = 0; i < interfaces.length; i++) {
				System.out.println();
				System.out.println("Interface: " + interfaces[i].getName());
				System.out.println("Inspecting interface '"
						+ interfaces[i].getSimpleName() + "':");
				inspectMethods(obj, interfaces[i]);
				inspectConstructor(obj, interfaces[i]);
			}
			System.out.println("End of '" + objClass.getSimpleName()
					+ "' interfaces");
		} else {
			System.out.println("No interfaces found");
		}
	}
	
		 //printFields will print information on fields to console
	private void printFields(Object obj, Field aField) {
		try {
			if (aField.getType().isArray()) {
				System.out.println("Field: '" + aField.getName()
						+ "'\n\t-Type: " + aField.getType().getComponentType()
						+ "\n\t-Modifier: "
						+ Modifier.toString(aField.getModifiers()));
			} else {
				System.out.println("Field: '" + aField.getName() + "' = "
						+ aField.get(obj) + "\n\t-Type: " + aField.getType()
						+ "\n\t-Modifier: "
						+ Modifier.toString(aField.getModifiers()));
			}
		} catch (Exception e) {
		}
	}

	
  }
