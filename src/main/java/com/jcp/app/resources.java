package com.jcp.app;

public class resources {
	public static String getres()
	{
		String res="/v1/pricing-authoring/products/{id}";
		return res;
		
	}
	///v1/pricing-authoring/rateTables
	public static String getpostresourceforgraph()
	{
		String res="/api/v1/pricing-graph/items";
		return res;
		
	}
	public static String GetRforratetablecreation()
	{
		String res="/v1/pricing-authoring/rateTables";
		return res;
	}
	public static String getassociateratetable()
	{
		String res="/v1/pricing-authoring/serviceLevels/{id}";
		return res;
	}
	public static String getmarketinglabelresouce()
	{
		String res="/v1/pricing-authoring/marketinglabels";
		return res;
		
	}
	
}
