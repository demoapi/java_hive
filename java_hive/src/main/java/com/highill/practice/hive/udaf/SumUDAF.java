package com.highill.practice.hive.udaf;

import org.apache.hadoop.hive.ql.exec.NumericUDAF;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFParameterInfo;

/**
 * <a href="https://cwiki.apache.org/confluence/display/Hive/LanguageManual+UDF">UDF LanguageManual</a>
 *
 *<a href="https://cwiki.apache.org/confluence/display/Hive/DeveloperGuide#DeveloperGuide-UDFsandUDAFs">DeveloperGuide UDFs and UDAFs</a>
 */
public class SumUDAF extends NumericUDAF {

	/**
	 * public GenericUDAFEvaluator getEvaluator(TypeInfo[] parameters)
	 * {
	 * return new SumEvaluator();
	 * }
	 */

	public GenericUDAFEvaluator getEvaluator(GenericUDAFParameterInfo info)
	{
		// TypeInfo[] parameters = info.getParameters();
		System.out.println("----- ----- ObjectInspectors array=" + info.getParameterObjectInspectors());
		return new SumEvaluator();
	}

}
