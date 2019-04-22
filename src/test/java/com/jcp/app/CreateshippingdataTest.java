package com.jcp.app;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ff.model.Mainmodel;
import com.ff.model.ShipmentRateRange;
import com.google.gson.*;
import com.google.gson.JsonParser;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateshippingdataTest {
	private static Logger log=LogManager.getLogger(CreateshippingdataTest.class.getName());
	Properties prop=new Properties();
	public static String jsonAsString;
	
	
	@BeforeTest
	public void getdata() throws IOException
	{
		
		FileInputStream fis=new FileInputStream("C:\\maven\\delphi-restassured\\src\\main\\java\\com\\jcp\\app\\env.properties");
		prop.load(fis);
		
	}
	
	 @DataProvider(name = "ValueRatetablecreationTestdata")
	 
	  public static Object[][] ratetabletestdata() {
	 
	        return new Object[][] { 
	        	{ "99", "8.95","0","0","A-Standard-continental shipment ratetable","ValueRateTable","0","Continental - Standard","STANDARD","CONTINENTAL"},
                { "99", "8.96","0","0","A-Standard-Noncontinental shipment ratetable","ValueRateTable","0","Non-Continental - Standard","STANDARD","NON_CONTINENTAL"},
	        	{ "99", "8.96","0","0","A-Standard-Apo-fpo shipment ratetable","ValueRateTable","0","APO_FPO - Standard","STANDARD","APO_FPO"},
	        	
                { "24.99", "3.96","0","0","A-Willcall shipment ratetable","ValueRateTable","0","Will Call Continental Non-Truck","WILL_CALL","CONTINENTAL"},
	        	{ "24.99", "3.95","0","0","A-Willcall shipment ratetable","ValueRateTable","0","Will Call Non-Continental Non-Truck","WILL_CALL","NON_CONTINENTAL"},
	        	
                { "24.99", "0","0","0","A-Bopus shipment ratetable","ValueRateTable","0","BOPIS Continental Service","BOPUS","CONTINENTAL"},
	        	{ "24.99", "0","0","0","A-Bopus shipment ratetable","ValueRateTable","0","BOPIS Non-Continental Service","BOPUS","NON_CONTINENTAL"},
	        	
	        	{ "98.99", "15","149.99","25","A-Express economy shipment ratetable","ValueRateTable","34","Continental - Express","EXPRESS_ECONOMY","CONTINENTAL"},
	        	{ "98.99", "14","149.99","24","A-Express economy shipment ratetable","ValueRateTable","35","Non-Continental - Express","EXPRESS_ECONOMY","NON_CONTINENTAL"},
	        	
	        	{ "98.99", "15","149.99","25","A-Expedited shipment ratetable","ValueRateTable","34","Continental - Expedited","EXPRESS","CONTINENTAL"},
	        	{ "98.99", "14","149.99","24","A-Expedited economy shipment ratetable","ValueRateTable","35","Non_Continental - Expedited","EXPRESS","NON_CONTINENTAL"},
	        	
	        	{ "98.99", "9.95","0","0","A-Expedited shipment ratetable","ValueRateTable","4.95","Continental - Premium","GROUND3DAY","CONTINENTAL"},
	        	{ "98.99", "9.96","0","0","A-Expedited economy shipment ratetable","ValueRateTable","4.96","Non_Continental - Premium","GROUND3DAY","NON_CONTINENTAL"}
                                
	        	};
	 
	  }
	 @DataProvider(name = "WeightRatetablecreationTestdata")
	 
	  public static Object[][] weightratetabletestdata() {
	 
	        return new Object[][]{ 
	        	{"A-Standard-continental shipment ratetable","WeightRateTable","125","Continental - Standard","STANDARD","CONTINENTAL"},
	        	{"A-Standard-noncontinental shipment ratetable","WeightRateTable","124","Non-Continental - Standard","STANDARD","NON_CONTINENTAL"},
	        	
	        	{ "A-Willcall shipment ratetable","WeightRateTable","124","Will Call Continental","WILL_CALL","CONTINENTAL"},
		        { "A-Willcall shipment ratetable","WeightRateTable","125","Will Call Non-Continental","WILL_CALL","NON_CONTINENTAL"}
	        	
	        };
	 
	  }
	 
	 
	 
	 
	 
	  // Here we are calling the Data Provider object with its Name
	 @Before
	 public void headersetup()
	 {
		 given().
			
			header("Content-Type","application/json").
			header("Origin",prop.getProperty("HOST")).
			header("authorization",prop.getProperty("NoAuthToken"));
		 
	 }
	 
//@Test(priority = 0)
public void GetLotbyLotid()
 {
		// TODO Auto-generated method stub
		RestAssured.baseURI=prop.getProperty("HOST");
		Response res=
				given().
		pathParam("id", "573-0105").
		//header("Content-Type","application/json").
		//header("Origin",prop.getProperty("HOST")).
		//header("authorization",prop.getProperty("NoAuthToken")).
		when().
		get(resources.getres()).
		then().assertThat().statusCode(200).
		body("[0].productid",equalTo("573-0105")).
		extract().response();
		
		jsonAsString= res.asString();
		JsonPath js= new JsonPath(jsonAsString);
		System.out.println(jsonAsString);
		String str=js.get("[0].productid");
		String sku=js.getString("[0].skuList.skuNumber");
		String skucount=js.get("[0].skuCount");
		System.out.println(str);
		System.out.println(js);
		System.out.println(sku+""+skucount);
		 Assert.assertEquals(str, "573-0105","both message is matching");
		
		
		
		

	}
//@Test(dataProvider = "ValueRatetablecreationTestdata",priority = 12)
public void CreateValueRatetable(String range1, String range1v,String Range2,String Range2V,String rateTableName,String rateType,String maxShipmentRate,String servicelevelname,String serviceLevel,String region)
{
	RestAssured.baseURI=prop.getProperty("HOST");
	
	JsonObject ship = new JsonObject();
	JsonObject shipmentrange = new JsonObject();
	JsonObject Servicelevel = new JsonObject();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();
	
	
	
	if(Range2.contains("0"))
    {
		shipmentrange.addProperty(range1, range1v);
    }
    else
    {
    	shipmentrange.addProperty(range1, range1v);
    	shipmentrange.addProperty(Range2, Range2V);
 	   
 	   
    }
	 ship.add("shipmentRateRange", shipmentrange);
     ship.addProperty("rateTableName", rateTableName);
     ship.addProperty("rateType", rateType);
     ship.addProperty("effectiveDateDisplay", "2018-09-27 00:00:00");
     ship.addProperty("endDateDisplay", "2098-01-25 23:59:59");
     ship.addProperty("createdDateDisplay", dateFormat.format(date));
     ship.addProperty("maxShipmentRate", maxShipmentRate);
     Response resp=given().
     header("Content-Type","application/json").
	 header("Origin",prop.getProperty("HOST")).
	 header("authorization",prop.getProperty("NoAuthToken")).
	 request().body(ship.toString()).
	 when().post(resources.GetRforratetablecreation()).
	 then().assertThat().statusCode(201).extract().response();
     jsonAsString=resp.asString();
     JsonPath js= new JsonPath(jsonAsString);
     String ratetablenumber=js.get("rateTableNumber");
     System.out.println(ratetablenumber);
     
     RestAssured.baseURI=prop.getProperty("HOST");
     String level=servicelevelname;
     Servicelevel.addProperty("rateTableNumber",ratetablenumber);
     Servicelevel.addProperty("rateTableName", rateTableName);
     Servicelevel.addProperty("serviceLevel", serviceLevel);
     Servicelevel.addProperty("serviceLevelName", level);
     Servicelevel.addProperty("region",region);
     Servicelevel.addProperty("isTruckable", false);
     Response respa=given().log().uri().log().body().log().headers().
	 header("Content-Type","application/json").
	 header("Origin",prop.getProperty("HOST")).
	 pathParam("id",level).
	 header("authorization",prop.getProperty("NoAuthToken")).
	 request().body(Servicelevel.toString()).
	 when().
	 put(resources.getassociateratetable()).then().assertThat().statusCode(200).extract().response();
     
     
      
	 
	 
     
}
//@Test
public void UpdateServicelevel()
{
	            RestAssured.baseURI=prop.getProperty("HOST");
	            JsonObject Servicelevel = new JsonObject(); 
	            String level="Continental - Standard";
	            Servicelevel.addProperty("rateTableNumber","1268");
	            Servicelevel.addProperty("rateTableName", "Standard-continental shipment ratetable");
	            Servicelevel.addProperty("serviceLevel", "STANDARD");
	            Servicelevel.addProperty("serviceLevelName", level);
	            Servicelevel.addProperty("region","CONTINENTAL");
	            Servicelevel.addProperty("isTruckable", false);
	            Response respa=given().log().uri().log().body().log().headers().
			    header("Content-Type","application/json").
				header("Origin",prop.getProperty("HOST")).
				pathParam("id",level).
				header("authorization",prop.getProperty("NoAuthToken")).
				request().body(Servicelevel.toString()).
				when().
				put(resources.getassociateratetable()).then().extract().response();
			
	   
}
//@Test
public void deletemarketinglable()
{
	RestAssured.baseURI=prop.getProperty("HOST");
	Response res=given().
			log().all().
	        header("Origin",prop.getProperty("HOST")).
	        queryParam("marketingLabelDesc", "testing").
	when().
	        delete(resources.getmarketinglabelresouce()).
	        then().
	        log().all().
	        assertThat().statusCode(200).
	        body("status",equalTo("success")).
	        extract().response();

	
	
}
//@Test(dataProvider = "WeightRatetablecreationTestdata",priority = 12)
public void CreateWeightRatetable(String rateTableName,String rateType,String maxShipmentRate,String servicelevelname,String serviceLevel,String region)
{
	RestAssured.baseURI=prop.getProperty("HOST");
	
	JsonObject ship = new JsonObject();
	JsonObject shipmentrange = new JsonObject();
	JsonObject Servicelevel = new JsonObject();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();
	
	
	shipmentrange.addProperty("","");
    
   
	 ship.add("shipmentRateRange", shipmentrange.remove(";"));
     ship.addProperty("rateTableName", rateTableName);
     ship.addProperty("rateType", rateType);
     ship.addProperty("effectiveDateDisplay", "2018-09-27 00:00:00");
     ship.addProperty("endDateDisplay", "2098-01-25 23:59:59");
     ship.addProperty("createdDateDisplay", dateFormat.format(date));
     ship.addProperty("maxShipmentRate", maxShipmentRate);
     Response resp=given().log().all().
     header("Content-Type","application/json").
	 header("Origin",prop.getProperty("HOST")).
	 header("authorization",prop.getProperty("NoAuthToken")).
	 request().body(ship.toString()).
	 when().post(resources.GetRforratetablecreation()).
	 then().assertThat().statusCode(201).extract().response();
     jsonAsString=resp.asString();
     JsonPath js= new JsonPath(jsonAsString);
     String ratetablenumber=js.get("rateTableNumber");
     System.out.println(ratetablenumber);
     
     RestAssured.baseURI=prop.getProperty("HOST");
     String level=servicelevelname;
     Servicelevel.addProperty("rateTableNumber",ratetablenumber);
     Servicelevel.addProperty("rateTableName", rateTableName);
     Servicelevel.addProperty("serviceLevel", serviceLevel);
     Servicelevel.addProperty("serviceLevelName", level);
     Servicelevel.addProperty("region",region);
     Servicelevel.addProperty("isTruckable", true);
     Response respa=given().log().uri().log().body().log().headers().
	 header("Content-Type","application/json").
	 header("Origin",prop.getProperty("HOST")).
	 pathParam("id",level).
	 header("authorization",prop.getProperty("NoAuthToken")).
	 request().body(Servicelevel.toString()).
	 when().
	 put(resources.getassociateratetable()).then().assertThat().statusCode(200).extract().response();
     
     
      
	 
	 
     
}

@Test(dataProvider = "ValueRatetablecreationTestdata",priority = 12)
public void CreateValueRatetable1234(String range1, String range1v,String Range2,String Range2V,String rateTableName,String rateType,String maxShipmentRate,String servicelevelname,String serviceLevel,String region)
{
	RestAssured.baseURI=prop.getProperty("HOST");
	
	Mainmodel mainobj=new Mainmodel();
	ShipmentRateRange shiprange=new ShipmentRateRange();
	
	
	
	JsonObject ship = new JsonObject();
	JsonObject shipmentrange = new JsonObject();
	JsonObject Servicelevel = new JsonObject();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();
	
	
	
	if(Range2.contains("0"))
    {
		shipmentrange.addProperty(range1, range1v);
		//mainobj.setShipmentRateRange(shipmentRateRange);
		shiprange.se
		
		shiprange.setAdditionalProperty(name, value);
		//shiprange.setAdditionalProperty(range1, range1v);
    }
    else
    {
    	shipmentrange.addProperty(range1, range1v);
    	shipmentrange.addProperty(Range2, Range2V);
 	   
 	   
    }
	 ship.add("shipmentRateRange", shipmentrange);
     ship.addProperty("rateTableName", rateTableName);
     ship.addProperty("rateType", rateType);
     ship.addProperty("effectiveDateDisplay", "2018-09-27 00:00:00");
     ship.addProperty("endDateDisplay", "2098-01-25 23:59:59");
     ship.addProperty("createdDateDisplay", dateFormat.format(date));
     ship.addProperty("maxShipmentRate", maxShipmentRate);
     Response resp=given().
     header("Content-Type","application/json").
	 header("Origin",prop.getProperty("HOST")).
	 header("authorization",prop.getProperty("NoAuthToken")).
	 request().body(ship.toString()).
	 when().post(resources.GetRforratetablecreation()).
	 then().assertThat().statusCode(201).extract().response();
     jsonAsString=resp.asString();
     JsonPath js= new JsonPath(jsonAsString);
     String ratetablenumber=js.get("rateTableNumber");
     System.out.println(ratetablenumber);
     
     RestAssured.baseURI=prop.getProperty("HOST");
     String level=servicelevelname;
     Servicelevel.addProperty("rateTableNumber",ratetablenumber);
     Servicelevel.addProperty("rateTableName", rateTableName);
     Servicelevel.addProperty("serviceLevel", serviceLevel);
     Servicelevel.addProperty("serviceLevelName", level);
     Servicelevel.addProperty("region",region);
     Servicelevel.addProperty("isTruckable", false);
     Response respa=given().log().uri().log().body().log().headers().
	 header("Content-Type","application/json").
	 header("Origin",prop.getProperty("HOST")).
	 pathParam("id",level).
	 header("authorization",prop.getProperty("NoAuthToken")).
	 request().body(Servicelevel.toString()).
	 when().
	 put(resources.getassociateratetable()).then().assertThat().statusCode(200).extract().response();
     
     
      
	 
	 
     
}



}
