import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import com.jsptags.navigation.pager.parser.ParseException;

public class CommonUtil {
	public static void printArrayList(List<Attendees> listy)
	{
		for (Iterator<Attendees> it = listy.iterator(); it.hasNext();) {
		 Attendees stdn = (Attendees) it.next();
		 System.out.println("Country " + stdn.getName());
		 System.out.println("Start Date " + stdn.getDate());
		 System.out.println("Attendees " + stdn.getEmail());
		 System.out.println("Attendee count " + stdn.getAttendeeCount());
	 }}

	public static void printHashMap(HashMap<KeyObject,Attendees> mapp)
	{
	for (KeyObject name: mapp.keySet()){

        String country = name.getCountry();
        String date = name.getDate();
        String a  = mapp.get(name).toString();  
        System.out.println(country + " " + date + " " + a);  
}}
	
	public static void printRequestArrayList(ArrayList<RequestDto> listy)
	{
		for (Iterator<RequestDto> it = listy.iterator(); it.hasNext();) {
			RequestDto stdn = (RequestDto) it.next();
		 System.out.println("Country " + stdn.getCountry());
		 System.out.println("EMAIL " + stdn.getEmail());
		 System.out.println("first name " + stdn.getFirstName());
		 System.out.println("last name " + stdn.getLastName());
		 System.out.println("available dates " + stdn.getAvailableDates().length);
		 for(int i = 0;i<stdn.getAvailableDates().length;i++)
		 {
			 System.out.println(stdn.getAvailableDates()[i]);
		 }
		}}
	
	
	public ArrayList<Attendees> chalao() throws ParseException, java.text.ParseException, ClassNotFoundException, JSONException, org.json.simple.parser.ParseException
	{
		ParseJsonRequest pr = new ParseJsonRequest();
		GenerateResp rsp = new GenerateResp();
		ReadRequest r = new ReadRequest();
		ArrayList<RequestDto> ls = pr.parseJsonPopulte(r.req);
		return rsp.generateResp(ls);
	}
	
	public static String formatDate(int valDate)
	{
		String nd = "";
		if(valDate >= 1 && valDate<= 9)
			nd = "0"+valDate;
		else
			nd = String.valueOf(valDate);
		return nd;
	}
	
	public static void printStringArrayList(List<String> listy)
	{
		for (Iterator<String> it = listy.iterator(); it.hasNext();) {
		 String stdn = (String) it.next();
		 System.out.println(stdn + "," );
	 }}
	}


