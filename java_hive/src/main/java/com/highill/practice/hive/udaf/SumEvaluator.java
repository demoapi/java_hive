package com.highill.practice.hive.udaf;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;

/**
 * https://cwiki.apache.org/confluence/display/Hive/GenericUDAFCaseStudy
 * 
 * @author
 *
 */
public class SumEvaluator extends GenericUDAFEvaluator {

	private boolean isEmpty;

	private double mySum;

	public SumEvaluator()
	{
		super();
		mySum = 0;
		isEmpty = true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public AggregationBuffer getNewAggregationBuffer() throws HiveException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void reset(AggregationBuffer agg) throws HiveException
	{
		mySum = 0;
		isEmpty = true;

	}

	@SuppressWarnings("deprecation")
	@Override
	public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException
	{
		if (parameters != null)
		{
			for (int size = 0; size < parameters.length; size++)
			{
				try
				{
					DoubleWritable doubleWritable = (DoubleWritable) parameters[size];
					mySum += doubleWritable.get();
					isEmpty = false;
				} catch (Exception e)
				{
					System.out.println("----- ----- exception: " + e.getMessage());
				}
			}
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public Object terminatePartial(AggregationBuffer agg) throws HiveException
	{
		return isEmpty ? null : new DoubleWritable(mySum);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void merge(AggregationBuffer agg, Object partial) throws HiveException
	{
		if (partial != null && partial instanceof DoubleWritable)
		{
			DoubleWritable doubleWritable = (DoubleWritable) partial;
			mySum += doubleWritable.get();
			isEmpty = false;
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public Object terminate(AggregationBuffer agg) throws HiveException
	{
		return isEmpty ? null : new DoubleWritable(mySum);
	}

}
