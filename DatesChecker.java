import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DatesChecker {
	
	public String countRecordsPerCountry(List<Attendees> listy) throws ParseException
	{
		ArrayList<String> arrj = new ArrayList<String>();
		for (Iterator<Attendees> it = listy.iterator(); it.hasNext();) {
	    	Attendees stdn = (Attendees) it.next();
	    	    arrj.add(stdn.getDate());
		}
		//CommonUtil.printStringArrayList(arrj);
		  return checkDates(arrj,listy);
		}
	
	public String checkDates (List<String> list, List<Attendees> countryList) throws ParseException
	{
	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		
		// when only 2 dates, return the least of the consecutive date, else return null.
			if(list.size()==2)
		{
			 String[] dates1 = findPlusMinusDates(list.get(0)).split(",");
			 Date mindate = formatter.parse(dates1[0]);
	         Date maxDate = formatter.parse(dates1[1]);
	         Date input = formatter.parse(list.get(0));
	        if(list.get(1).equalsIgnoreCase(dates1[0]) )
			 return least (mindate,input).toString() ;
			if(list.get(1).equalsIgnoreCase(dates1[1]))
				 return least (maxDate,input).toString() ;
			else
				return null;
		}
			
			else if(list.size() >2){
			int count = countryList.get(countryList.size()-1).getAttendeeCount(); // last count would be the greatest count
			
			if(count == 1){
			Iterator<String> itk = list.iterator();
		    while(itk.hasNext())
	     	{
    	    String dateExtracted = itk.next(); // first date
    	    String[] dates1 = findPlusMinusDates(dateExtracted).split(",");
            //String nextDate = itk.next();
    	    //if(nextDate.equalsIgnoreCase(dates1[0]) || nextDate.equalsIgnoreCase(dates1[1])){
	        //	 System.out.println("dates1[0] and dates1[1]" + dates1[0] + "..." + dates1[1]);
            if(list.contains(dates1[1]))
            {
			   return dateExtracted;}
	     	}}
			
		    else
		    {
			// last count is not 1:
			String date = countryList.get(countryList.size()-1).getDate();  // date with highest number of participants
			String[] dates = findPlusMinusDates(date).split(",");
			String minDate = dates[0];
    		String maxDate = dates[1];
	
			int countmin =0;
			int countmax=0;
	        Iterator<Attendees> it = countryList.iterator();
			while(it.hasNext())
			{
	    	Attendees stdn = (Attendees) it.next();
	    	if(stdn.getDate().equalsIgnoreCase(minDate))
	    	{
	    		 countmin = stdn.getAttendeeCount();
	       	}
	    	if(stdn.getDate().equalsIgnoreCase(maxDate))
	    	{
	    		 countmax = stdn.getAttendeeCount();
	       	}
		}
	    	if(countmin==0 && countmax==0)
	    		return null;
	    	if(countmin>countmax || countmin == countmax)
	    	  return minDate;
	       	else return maxDate;
	    	}}
			return null;}

	       	
	public String findPlusMinusDates(String input) throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = dateFormat.parse(input);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(myDate);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(myDate);
		cal1.add(Calendar.DAY_OF_YEAR, -1);
		cal2.add(Calendar.DAY_OF_YEAR, +1);
		Date previousDate = cal1.getTime();
		Date nextDate = cal2.getTime();
		
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date date = (Date)formatter.parse(previousDate.toString());
		Date date1 = (Date)formatter.parse(nextDate.toString());
				
		cal1.setTime(date);
		String prevMonth = CommonUtil.formatDate(cal1.get(Calendar.MONTH) + 1);
		String prevDate = CommonUtil.formatDate(cal1.get(Calendar.DATE));
		String formatedDate = String.valueOf(cal1.get(Calendar.YEAR)) + "-" + prevMonth + "-" +  prevDate;
		
		cal2.setTime(date1);
		String nextMonth = CommonUtil.formatDate(cal2.get(Calendar.MONTH) + 1);
		String nextdate = CommonUtil.formatDate(cal2.get(Calendar.DATE));
		String formatedDate1 = String.valueOf(cal2.get(Calendar.YEAR)) + "-" + nextMonth + "-" +  nextdate;
		
		return formatedDate.toString() + "," + formatedDate1.toString();
	}
	
	public static Date least(Date a, Date b) {
	    return a == null ? b : (b == null ? a : (a.before(b) ? a : b));
	}
}


