package com.highill.practice.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class HelloUDF extends UDF {

	public String evaluate(String inputStr)
	{
		if (inputStr == null || inputStr.trim().isEmpty())
		{
			return "Hello, Empty!!";
		} else
		{
			return "Hello, " + inputStr.trim();
		}
	}

}
