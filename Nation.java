import java.util.Scanner;
/**
 * Nation is the object that includes finances and military
 * 
 * @author William Chern
 * @version 1.0
 */
public class Nation
{
    // instance field variables
    private Reserve funds;
    private Military mili;

    public Nation(int f) {
        // constructor initializes the instance fields
        funds = new Reserve(f);
        mili = new Military();
    }
    
    public boolean replenishMilitary(int force, int branch) {
        // parameter variables - force, branch
        /* use switch statement to determine which branch of the Military to replenish,
         * if there are enough funds */
        if (funds.deduct(force*1000)) {
            switch (branch) {
                case 1:
                    mili.replenishArmy(force);
                    break;
                case 2:
                    mili.replenishNavy(force);
                    break;
                case 3:
                    mili.replenishAirForce(force);
                    break;
                default:
                    break;
            }
            return true;
        }
        return false;
    }
    
    public boolean invokeMilitary(int branch, int force) {
            /* if force can be used from a certain branch, return true */
         if (branch==1 || branch==2 || branch==3) {
             if (mili.invoke(branch, force)) return true;
         }
         return false;
    }
    
    public boolean useFunds(int amt) {
        // if amt can be deducted from funds, return true
        if (funds.deduct(amt)) return true;
        return false;
    }
    
    public void depositFunds(int amt) {
        // mutator, adds amt to funds
        funds.deposit(amt);
    }
    
    public int returnFunds() {
        // accessor for amount of funds
        return funds.getFunds();
    }
    
    public int[] getMilitaryForce() {
        // returns an array of integers of the amount of force in all 3 Military branches
        // respectively: Army, Navy, Air Force
        int[] force = {mili.getArmyAmount(), mili.getNavyAmount(), mili.getAirForceAmount()};
        return force;
    }
}
