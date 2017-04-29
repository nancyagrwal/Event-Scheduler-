import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jsptags.navigation.pager.parser.ParseException;

public class RequestResponse {

	private final static String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		//ReadRequest read = new ReadRequest();
		RequestResponse http = new RequestResponse();
		System.out.println("Testing 1 - Send Http GET request");
		ParseJsonRequest req=new ParseJsonRequest();
		ArrayList<RequestDto> list=req.parseJsonPopulte(http.sendGet().toString());
		//ArrayList<RequestDto> list=req.parseJsonPopulte(read.req);
		GenerateResp generateResp = new GenerateResp();
		ArrayList<Attendees> finalList = new ArrayList<>();
		finalList = generateResp.generateResp(list);
		CommonUtil.printArrayList(finalList);
		System.out.println("\nTesting 2 - Send Http POST request");
		responseGenerate(finalList);
	}

	   private StringBuffer sendGet() throws Exception {
		String url = ""; // get url
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result.toString());
		return result;
	}
	
	public static void responseGenerate(ArrayList<Attendees> set) throws ClientProtocolException, IOException, JSONException, ClassNotFoundException, ParseException, org.json.simple.parser.ParseException{
		Iterator<Attendees> iter=set.iterator();
		JSONArray araay=new JSONArray();
		
		while(iter.hasNext()){
			Attendees attendees=iter.next();
			JSONObject obj=new JSONObject();
			obj.put("attendeeCount",attendees.getAttendeeCount());
			String[] mails=attendees.getEmail().split(",");
			JSONArray mailList = new JSONArray();
			for(int i=0;i<mails.length;i++){
				mailList.put(mails[i]);
			}
			obj.put("attendees",mailList);
			obj.put("name", attendees.getName());
			obj.put("startDate", attendees.getDate());
			araay.put(obj);
		}
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("countries", araay);
		sendPost(jsonObj);
		}
	
	public static void sendPost(JSONObject obj) throws ClientProtocolException, IOException{
		String url = ""; // post url
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		post.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result.toString());
	}
	
	
	
	
}