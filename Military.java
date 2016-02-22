
/**
 * Military is used for Nation's Military/Defense
 * 
 * @author William Chern 
 * @version 1.0
 */
public class Military
{
    // instance field variables
    private int army;
    private int navy;
    private int airForce;

    public Military() {
        // constructor assigns all instance fields with a default value of 100
        army = 100;
        navy = 100;
        airForce = 100;
    }

    public boolean invoke(int branch, int force) {
        // utilizes switch statement for branch of military, if force can be used, return true
        // else (if there is not enough force in that branch), do nothing and return false
        switch (branch) {
            case 1:
            if (force<=army) {
                army-=force;
                return true;
            }
            return false;
            case 2:
            if (force<=navy) {
                navy-=force;
                return true;
            }
            return false;
            case 3:
            if (force<=airForce) {
                airForce-=force;
                return true;
            }
            return false;
            default:
            return false;
        }
    }

    public void replenishArmy(int amt) {
        // mutator
        army+=amt;
    }

    public int getArmyAmount() {
        // accessor
        return army;
    }

    public void replenishNavy(int amt) {
        // mutator
        navy+=amt;
    }

    public int getNavyAmount() {
        // accessor
        return navy;
    }

    public void replenishAirForce(int amt) {
        // mutator
        airForce+=amt;
    }

    public int getAirForceAmount() {
        // accessor
        return airForce;
    }
}
