import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jsptags.navigation.pager.parser.ParseException;

public class ParseJsonRequest {
	
	JSONParser parser=new JSONParser();
	public ArrayList<RequestDto> parseJsonPopulte(String req) throws ParseException, ClassNotFoundException, JSONException, 
	org.json.simple.parser.ParseException{
			
		    ArrayList<RequestDto> reqList=new ArrayList<RequestDto>();
			JSONObject obj = new JSONObject(req);
			JSONArray arr = obj.getJSONArray("partners");
			for (int i = 0; i < arr.length(); i++)
			{
				RequestDto request=new RequestDto();
			    request.setFirstName(arr.getJSONObject(i).getString("firstName"));
			    request.setLastName(arr.getJSONObject(i).getString("lastName"));
			    request.setEmail(arr.getJSONObject(i).getString("email"));
			    request.setCountry(arr.getJSONObject(i).getString("country"));
			    JSONArray j = (JSONArray) arr.getJSONObject(i).get("availableDates");
			    int length = j.length();
			    String[] dates = new String[length];
			    for(int y=0;y<length;y++)
			    {dates[y] = j.get(y).toString();}
			    
			    request.setAvailableDates(dates);
			    reqList.add(request);			
			}
			
		return reqList;
	}
}


