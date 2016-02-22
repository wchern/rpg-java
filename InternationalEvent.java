
/**
 * InternationalEvent
 * 
 * @author William Chern
 * @version 1.0
 */
public class InternationalEvent
{
    // public final instance field variables, do not need accessor methods to access
    // these instance field variables never change
    public final int severity;
    public final boolean influence;
    public final String countryInvolved;

    public InternationalEvent(int s, boolean i, String c) {
        // instance field variables are assigned a value from their respective parameter variables
        severity = s;
        influence = i;
        countryInvolved = c;
    }
    
    public String toString() {
        return "InternationalEvent [Influence: " + influence + ", Severity: " + severity + ", CountryInvolved: " + countryInvolved;
    }
}
