/*
 * USE - UML based specification environment
 * Copyright (C) 1999-2010 Mark Richters, University of Bremen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

// $Id$

package org.tzi.use.kodkod.assl.dynamics;

import static org.tzi.use.util.StringUtil.inQuotes;

import java.io.PrintWriter;

import org.tzi.use.gen.assl.dynamics.GConfiguration;
import org.tzi.use.gen.assl.dynamics.GEvalInstruction;
import org.tzi.use.gen.assl.dynamics.GEvaluationException;
import org.tzi.use.gen.assl.dynamics.IGCaller;
import org.tzi.use.gen.assl.dynamics.IGCollector;
import org.tzi.use.gen.assl.statics.GInstrCreate_C;
import org.tzi.use.kodkod.assl.AsslTranslation;
import org.tzi.use.uml.mm.MClass;
import org.tzi.use.uml.ocl.type.ObjectType;
import org.tzi.use.uml.ocl.type.TypeFactory;
import org.tzi.use.uml.ocl.value.ObjectValue;
import org.tzi.use.uml.sys.MSystem;
import org.tzi.use.uml.sys.MSystemException;
import org.tzi.use.uml.sys.MSystemState;
import org.tzi.use.uml.sys.StatementEvaluationResult;
import org.tzi.use.uml.sys.soil.MNewObjectStatement;
import org.tzi.use.uml.sys.soil.MStatement;

/**
 * eval create
 * 
 * based on {@link GEvalInstrCreate_CKodKod}
 * 
 * @author Juergen Widdermann
 */
public class GEvalInstrCreate_CKodKod extends GEvalInstruction {
	private GInstrCreate_C fInstr;
	
	public GEvalInstrCreate_CKodKod(GInstrCreate_C instr, AsslTranslation asslTranslation ) {
        fInstr = instr;
    }

    public void eval(GConfiguration conf,
                     IGCaller caller,
                     IGCollector collector) throws GEvaluationException {
        
    	MSystemState state = conf.systemState();
    	MSystem system = state.system();
    	PrintWriter basicOutput = collector.basicPrintWriter();
    	PrintWriter detailOutput = collector.detailPrintWriter();
    	
    	detailOutput.println("evaluating " + inQuotes(fInstr));
    			
    	MClass objectClass = fInstr.cls();
    	ObjectType objectType = TypeFactory.mkObjectType(objectClass);
    	String objectName = state.uniqueObjectNameForClass(objectClass.name());
    	
    	MStatement statement = new MNewObjectStatement(
    			objectClass, 
    			objectName);
    	
    	MStatement inverseStatement;
    	
    	basicOutput.println(statement.getShellCommand());
    	try {
    		
    		StatementEvaluationResult evaluationResult = 
    			system.evaluateStatement(statement, true, false, false);
    		
    		inverseStatement = evaluationResult.getInverseStatement();
    		
		} catch (MSystemException e) {
			throw new GEvaluationException(e);
		}
		
		ObjectValue objectValue = 
			new ObjectValue(objectType, state.objectByName(objectName));
		
		detailOutput.println(inQuotes(fInstr) + " == " + objectValue);
		
		// Adding KodKod
		//asslTranslation.addObjectToLowerClassBound(objectType.cls().name(), objectName);
		
		caller.feedback(conf, objectValue, collector);
            
		if (collector.expectSubsequentReporting()) {
			collector.subsequentlyPrependStatement(statement);
		}
		
		basicOutput.println("undo: " + statement.getShellCommand());
		
		try {
			system.evaluateStatement(inverseStatement, true, false);
		} catch (MSystemException e) {
			throw new GEvaluationException(e);
		}
    }

}
