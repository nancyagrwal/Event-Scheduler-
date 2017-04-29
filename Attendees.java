import java.util.ArrayList;
class Attendees {
	
    private	int attendeeCount;
	private String atten;
	private ArrayList<String> atteendees;
    private String name;
    private String startDate;
    private String email; 
    private String date;
    
	
    public String getAtten() {
		return String.valueOf(attendeeCount);
	}
	public void setAtten(String atten) {
		this.atten = String.valueOf(attendeeCount);
	}
	
	public int getAttendeeCount() {
		return attendeeCount;
	}
	public void setAttendeeCount(int attendeeCount) {
		this.attendeeCount = attendeeCount;
	}
	public ArrayList<String> getAtteendees() {
		return atteendees;
	}
	public void setAtteendees(ArrayList<String> atteendees) {
		this.atteendees = atteendees;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}
	
	public boolean equals(Object obj){
		if (obj == null || obj.getClass() != getClass()) { 
	        return false;
	    }

	    final Attendees other = (Attendees)obj;
	    return other.getName().equals(name);
	   } }
	
    


