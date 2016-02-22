
/**
 * Reserve is used for the Nation's Finances.
 * 
 * @author William Chern
 * @version 1.0
 */
public class Reserve
{
    // instance field variables
    private int funds;

    public Reserve(int f) {
        // constructor initializes instance field wtih parameter variable f
        funds = f;
    }

    public boolean deduct(int amt) {
        /* if the instance field variable funds is greater than or equal to
         * the parameter variable amt, return true and deduct amt from funds.
         * else, return false */
        if (funds>=amt) {
            funds-=amt;
            return true;
        }
        else {
            return false;
        }
    }

    public void deposit(int amt) {
        // mutator which adds to the instance field funds
        funds+=amt;
    }
    
    public int getFunds() {
        // accessor
        return funds;
    }
}
