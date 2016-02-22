
/**
 * RPG Starter
 * 
 * @author William Chern 
 * @version 1.0
 */
public class RPGStarter
{
    public static void main(String[]args) {
        System.out.println("RPG – William Chern \n");
        
        // declare and initialize a new instance of the GameBrainEngine
        GameBrainEngine rpg = new GameBrainEngine();
        
        rpg.runIntro();
        rpg.runTerm();
        rpg.runReElection();
        rpg.runTerm();
        rpg.runConclusion();
    }
}
