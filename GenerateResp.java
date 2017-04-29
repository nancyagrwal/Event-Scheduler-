import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

public class GenerateResp {
	HashMap<KeyObject,Attendees> attendeeMap=new HashMap<KeyObject,Attendees>();
	public ArrayList<Attendees> generateResp(ArrayList<RequestDto> list) throws ParseException{
		Iterator<RequestDto> itr=list.iterator();
		while(itr.hasNext()){
			RequestDto dto=itr.next();
			String dateArr[]=dto.getAvailableDates();
			String country=dto.getCountry();
			
			for(int i=0;i<dateArr.length;i++){
				KeyObject key=new KeyObject();
				key.setCountry(country);
				key.setDate(dateArr[i]);
				
				if(attendeeMap.containsKey(key)){
					Attendees attendee=attendeeMap.get(key);
					attendee.setAttendeeCount(attendee.getAttendeeCount()+1);
					attendee.setEmail(attendee.getEmail()+","+dto.getEmail());
					attendee.setDate(key.getDate());
					attendeeMap.put(key,attendee);
					}
				
				else{
					Attendees attendee=new Attendees();
					attendee.setName(country);
					attendee.setAttendeeCount(1);
					attendee.setEmail(dto.getEmail());
					attendee.setDate(key.getDate());
			    	attendeeMap.put(key,attendee);
				}}}
		
		    Collection<Attendees> students = attendeeMap.values();
		    List<Attendees> listy = new ArrayList<>(students);
		    Collections.sort(listy, new AttendeeChainedComparator(new CountryComp(), new AttendeeCountComp() , new DateComp()));
	   
//		    CommonUtil.printArrayList(listy);
	    
		    ArrayList<Attendees> finalAttendees = new ArrayList<Attendees>();
		    
		    Iterator<Attendees> it = listy.iterator();
		    	while(it.hasNext())
		    	{
		    	Attendees stdn = (Attendees) it.next();
		       	String name = stdn.getName();
		    	String date = stdn.getDate();
		    	
		    	ArrayList<Attendees> sameCountryList = new ArrayList<Attendees>();
		    	sameCountryList.add(stdn);  // adding the first entry
		    	it.remove();
		    	
		    	ArrayList<Attendees> newList = new ArrayList<Attendees>();
	    	
		    	while (it.hasNext())
		    	{
		    	Attendees stdn1 = (Attendees) it.next();
		    	if(stdn1.getName().equalsIgnoreCase(name) && !stdn1.getDate().equalsIgnoreCase(date) && it.hasNext())
		    	{
		    		sameCountryList.add(stdn1);
		       		//it.remove();
		    	}
		    	else
		    	{
		    		newList.add(stdn1);
		    	}
		    	}
		    	
//			    CommonUtil.printArrayList(sameCountryList);
			    DatesChecker datesChecker = new DatesChecker();
				String finalDate = datesChecker.countRecordsPerCountry(sameCountryList);

				Iterator<Attendees> itCountryList = sameCountryList.iterator();
			    	while(itCountryList.hasNext())
			    	{
			       	Attendees perCountry = (Attendees) itCountryList.next();
				    if(perCountry.getName().equalsIgnoreCase(name) && perCountry.getDate().equals(finalDate))
			        { finalAttendees.add(perCountry);
			       }}
//	    	    	CommonUtil.printArrayList(finalAttendees);
 //     	    	CommonUtil.printArrayList(newList);

				    listy = newList;
				    it = listy.iterator();
}	    	return finalAttendees;
	}
	
	 class AttendeeChainedComparator implements Comparator<Attendees> {
		 
	    private List<Comparator<Attendees>> listComparators;
	 
	    @SafeVarargs
	    public AttendeeChainedComparator(Comparator<Attendees>... comparators) {
	        this.listComparators = Arrays.asList(comparators);
	    }
	 
	    @Override
	    public int compare(Attendees emp1, Attendees emp2) {
	        for (Comparator<Attendees> comparator : listComparators) {
	            int result = comparator.compare(emp1, emp2);
	            if (result != 0) {
	                return result;
	            }
	        }
	        return 0;
	    }}
	
	class AttendeeCountComp implements Comparator<Attendees> {

		  @Override
		  public int compare(Attendees s1, Attendees s2) 
		  {
			    return s1.getAtten().compareTo(s2.getAtten());
		  }
	}
	
	class CountryComp implements Comparator<Attendees> {

		  @Override
		  public int compare(Attendees s1, Attendees s2) 
		  {
			    return s1.getName().compareTo(s2.getName());
		  }
	}
	
	class DateComp implements Comparator<Attendees>

	{
		public int compare(Attendees an1, Attendees an2)
		{
			return an1.getDate().compareTo(an2.getDate());
		}
	}
}
	
