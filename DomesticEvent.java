
/**
 * DomesticEvent
 * 
 * @author William Chern
 * @version 1.0
 */
public class DomesticEvent
{
    // final instance field variables, do not need accessor methods to access
    // these instance field variables never change
    public final int severity;
    public final boolean influence;

    public DomesticEvent(int s, boolean i) {
        // instance field variables are assigned a value from their respective parameter variables
        severity = s;
        influence = i;
    }
}
