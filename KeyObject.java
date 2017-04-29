
public class KeyObject {
	
	private String country;
	private String date;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public boolean equals(Object obj){
		if (obj == null || obj.getClass() != getClass()) { 
	        return false;
	    }
	    final KeyObject other = (KeyObject)obj;
	    return other.getCountry().equals(this.country) && other.getDate().equals(this.date);
	 }
	
	public int hashCode(){
	    return this.country.hashCode()*3 + this.date.hashCode();
	}
}
	
	 