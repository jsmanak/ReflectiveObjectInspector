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
  
  }
