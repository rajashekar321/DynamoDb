package com.DynamoDbPractice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;

@SpringBootApplication
public class MovieRatings1Application {

	public static void main(String[] args) {
		SpringApplication.run(MovieRatings1Application.class, args);
//		String s = "07/16/2024";
//		DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//		LocalDate dt = LocalDate.parse(s,df);
//		System.out.println(dt);
		

		AmazonDynamoDB db = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(
						new BasicAWSCredentials("YourAccessKey", "YourSecretAccesskey")))
				.withRegion("Your Region").build();// need to give your aws credentials
		// AwsBasicCredentials awsbasicCredentials=AwsBasicCredentials.create();
		// db.getItem(null)
		// aTable table=db.gett
		DynamoDB db1 = new DynamoDB(db);//DynamoDb object have more simplify methods to use crud operations
		Table table = db1.getTable("Student");//mention your tableName
		String partitionKey = "Student_Id";
		String rangeKey = "College_Joined_Date";//sortkey or rangekey
		Map<String, AttributeValue> map = new HashMap<>(); //need to create a map for fetching details with primarykey and sortkey
		map.put("Student_Id", new AttributeValue().withS("15190-CM-017"));
		map.put("College_Joined_Date", new AttributeValue().withS("1721128481"));
//		Map<String, AttributeValue> insertingElements = new HashMap<>();
//		insertingElements.put("Student_Id", new AttributeValue().withS("15984-CM-019"));
//		insertingElements.put("College_Joined_Date", new AttributeValue().withS("1721193203"));
		GetItemSpec spec = new GetItemSpec().withPrimaryKey(partitionKey, "15824-CM-017", rangeKey, "1721128481");//for fetching you can mention directly
		Item item = new Item().withPrimaryKey("Student_Id", "15184-CM-019")
				.withString("College_Joined_Date", "1721193203")
				.withString("Department", "Computer Science and Engineering");
		PutItemSpec pSpec = new PutItemSpec().withItem(item);//just creare one item that you want to create in table in ddb so you can directly give using with item(item type)
		// GetItemRequest gir = new
		// GetItemRequest().withTableName("Student").withKey(map);
		// PutItemRequest pir=PutItemRequest.builder();
		ScanSpec sc = new ScanSpec();//this specification is used for scan expression
		try {
//			ItemCollection<ScanOutcome> so = table.scan(sc);//when you want to scan all items in the table 
//			Iterator<Item> itr = so.iterator();
//			while (itr.hasNext()) {
//				System.out.println(itr.next());//printing all the items
//			}
			// Item itm = table.getItem(spec);
			table.putItem(item);//inserting /updating single item
			// GetItemResult giresult = db.getItem(gir);
			// System.out.println(giresult.toString());
			// System.out.println(itm);
		} catch (Exception e) {
			System.out.println(e);

		}
		// GetItemRequest gir=GetItemRequest.
		// GetItemRequest gir = GetItemRequest.builder().withTableName("Student")
		// .key(Collections.singletonMap("15190-CM-017", "1721128481")).build();
		// Item itm = table.getItem("15190-CM-017", "1721128481");
		// System.out.println(itm);

	}

}
