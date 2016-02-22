import java.util.Scanner;
import java.util.Random;
/**
 * @author William Chern 
 * @version 1.0
 */
public class GameBrainEngine
{
    // instance field variables
    private String lastName;
    private boolean gameOver;
    private int termNumber;
    private Nation country;

    private Scanner reader;
    private Random gen;

    public GameBrainEngine() {
        // constructor initializes the instance field variables with default values, if applicable
        gameOver = false;
        termNumber = 1;
        country = new Nation(500000);
        reader = new Scanner(System.in);
        gen = new Random();
    }

    public void runIntro() {
        System.out.println("Hello! Please enter your name (first or last): ");
        lastName = reader.nextLine();

        // prints introduction and basic outline
        System.out.println("\nCongratulations on your election, President " + lastName + "!" + 
            "\n  - As the head of the Nation and leader of the free world, you will face" +
            "\n      many headwinds, both domestically and internationally." + 
            "\n  - Your job is to balance responses to crises" + 
            "\n      with the financial and military resources of the Nation." +
            "\n  - Good luck!");

        pressAnyKeyToContinue();
    }

    public void runTerm() {
        if(!gameOver) {
            switch (termNumber) {
                // print different messages to terminal, depending on the termNumber
                case 1:
                System.out.println("\nPresident " + lastName + ", welcome to your first term in office!");
                printNationalStatus();
                pressAnyKeyToContinue();
                break;
                case 2:
                System.out.println("\nPresident " + lastName + ", welcome to your second term in office!");
                printNationalStatus();
                pressAnyKeyToContinue();
                break;
                default:
                // if termNumber is not 1 or 2, set gameOver to true b/c player has reached term limit
                System.out.println("\nPresident " + lastName + ", you've reached your term limit!");
                gameOver = true;
                pressAnyKeyToContinue();
                break;
            }

            // generates array of DomesticEvents and InternationalEvents by calling their respective methods
            DomesticEvent[] dEvents = generateArrayOfDomesticEvents(5,10);
            InternationalEvent[] iEvents = generateArrayOfInternationalEvents(7,10);

            // go through the array of Events, allows the user to take action on each one
            runThroughDomesticEvents(dEvents);
            runThroughInternationalEvents(iEvents);
        }
    }

    public void runReElection() {
        if(!gameOver) {
            // if not gameOver, ask if user wishes to attempt for a second term
            System.out.println("\nPresident " + lastName + ", you've reached the end of your first term in office.");
            pressAnyKeyToContinue();
            printNationalStatus();

            // prompt user for response to question of Reelection
            boolean validResponse = false;
            int response;
            while (!validResponse) {
                System.out.println("\nWould you like to run for reelection?" +
                    "\n  1) Yes" +
                    "\n  2) No");
                response = collectStringAndParseInt();
                switch (response) {
                    case 1:
                    validResponse = true;
                    int reElectionChance = gen.nextInt(100); // gets a random number between 0 and 100
                    if (reElectionChance<=(country.returnFunds()/5000)) { 
                        // if chance is less than or equal to funds/5000, user wins reelection
                        // chance of winning is almost guaranteed if funds>500000
                        System.out.println("\nCongratulations, President " + lastName + "! You won reelection with a landslide victory!");
                        termNumber = 2;
                        pressAnyKeyToContinue();
                    }
                    else {
                        System.out.println("\nUnfortunately, your reelection campaign was unsuccessful.");
                        gameOver=true;
                        pressAnyKeyToContinue();
                    }
                    break;
                    case 2:
                    // if user chooses not to run for reelection, gameOver = true
                    validResponse = true;
                    gameOver = true;
                    break;
                    default:
                    System.out.println("Invalid response");
                    break;
                }
            }
        }
    }

    public void runConclusion() {
        // thanks the player, displays final stats of the Nation
        System.out.println("\nYou've come to the end of your presidency, President " + lastName + ".");
        pressAnyKeyToContinue();
        printNationalStatus();
        System.out.println("\nThanks for serving, President " + lastName + ".");
        reader.close();
    }

    // HELPER methods

    public int getRandomNumInRange(int x, int y) {
        // generates a random between a range of x and y, inclusive
        int randNum = gen.nextInt(y+1);
        while (randNum<x) randNum = gen.nextInt(y+1);
        return randNum;
    }

    public void pressAnyKeyToContinue() {
        System.out.println("\nPress any key to continue.");
        reader.next();
    }

    public boolean stringContainsAllInt(String s) {
        // checks if a string contains only numbers (ints), returns true or false (logically)
        String nonNumericalChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ !@#$%^&*()-_+=~`\\{}|[]:;'\",.<>/?œ∑´†¥¨ˆπåß∂ƒ©˙∆˚¬Ω≈ç√∫˜≤≥÷…æ“‘¡™£¢∞§¶•ªº";
        boolean containsOnlyNumbers = true;
        for (int i=0; i<nonNumericalChars.length(); i++) {
            String sub = nonNumericalChars.substring(i,i+1);
            if (s.contains(sub)) containsOnlyNumbers = false;
        }
        return containsOnlyNumbers;
    }

    public int collectStringAndParseInt() {
        /* uses Scanner to collect input from the user;
         * if the input only contains numbers, parseInt is called to take the string and convert it into an int type;
         * else, response = 0 */
        String responseString = reader.next();
        int response;
        if (stringContainsAllInt(responseString)) response = Integer.parseInt(responseString);
        else response = 0;
        return response;
    }

    public void printNationalStatus() {
        // prints to the terminal window the Nation's status, including Funds (Federal Reserve) and all 3 branches of Military
        int[] militaryForce = country.getMilitaryForce();
        System.out.println("\n--- NATIONAL STATUS ---" + "\n  Federal Reserve: $" + country.returnFunds()
            + "\n  Military:" +
            "\n    Army: " + militaryForce[0] +
            "\n    Navy: " + militaryForce[1] +
            "\n    Air Force: " + militaryForce[2]);
    }

    public String getBranchStringFromInt(int branch) {
        /* returns a String for Military branch based on corresponding int parameter variable
         * - 1 = Army
         * - 2 = Navy
         * - 3 = Air Force
         * Used for convenience purposes when printing to terminal window */
        String branchString;
        switch (branch) {
            case 1:
            branchString = "Army";
            break;
            case 2:
            branchString = "Navy";
            break;
            case 3:
            branchString = "Air Force";
            break;
            default:
            branchString = "";
            break;
        }
        return branchString;
    }

    public void askForDomesticCrisisResponse(int severity) {
        // allows user to respond to a domestic crisis
        boolean validResponse = false;
        int response;
        if (severity<=85) {
            // if severity is less than or equal to 85, user can choose to spend money to attempt to solve domestic crisis
            int amount;
            int chance;
            while (!validResponse) {
                System.out.println("How would you like to respond to this domestic crisis?" +
                    "\n  1) Spend $$ on economic stimulus and/or social programs" +
                    "\n  2) Do nothing");
                response = collectStringAndParseInt();
                switch (response) {
                    case 1:
                    System.out.println("How much $$ would you like to spend?");
                    amount = collectStringAndParseInt();
                    if (amount<=0) { // (user did not enter an int only, thus collectStringAndParseInt() will return 0)
                        System.out.println("Invalid response"); // will loop again because validResponse = false
                    }
                    else if (amount<1000) {
                        System.out.println("You must spend at least $1000."); 
                        // guides the user, tells user to spend at least $1000 since everything is in 1000
                    }
                    else if (country.useFunds(amount)) {
                        validResponse = true; // will not loop again
                        chance = gen.nextInt((amount/1000)+1); // gets random number between 0 and (amount/1000)+1 [since multiples of 1000]
                        if (chance>=severity) { // if chance greater than or equal to severity, gain funds; lower severity = higher chance
                            System.out.println("Your new policies were successful!" +
                                "\n  The Nation gained $" + (severity*1000) + "!");
                            country.depositFunds(severity*1000);
                        }
                        else {
                            System.out.println("You were unsuccessful in your efforts." +
                                "\n  You used $" + amount + "."); // if unsuccessful, tell user how much $$ was spent
                        }
                    }
                    else { //if cannot use amount entered, tell user and loop again
                        System.out.println("You do not have enough funds!");
                    }
                    break;
                    case 2:
                    validResponse=true; // will not loop again
                    System.out.println("Due to inaction, the crisis cost the Nation $" + ((severity*2)*1000) + "!");
                    if (!country.useFunds((severity*2)*1000)) {
                        country.useFunds(country.returnFunds());
                    }
                    break;
                    default: // will loop back because validResponse=false
                    System.out.println("Invalid response");
                    break;
                }
            }
        }
        else {
            // if the severity is greater than 85, crisis is homeland security, requires invoking the Military
            while (!validResponse) {
                System.out.println("How would you like to respond to this domestic crisis?" +
                    "\n  1) Invoke the military for homeland protection" +
                    "\n  2) Do nothing");
                response = collectStringAndParseInt();
                switch (response) {
                    case 1:
                    validResponse=true;
                    askToInvokeMilitary(severity); // invokes military menu
                    break;
                    case 2:
                    validResponse=true;
                    // if user chooses to do nothing, deduct funds either (severity*3)*1000 OR the rest of the funds in the bank
                    System.out.println("Due to inaction, the crisis cost the Nation $" + ((severity*3)*1000) + "!");
                    if (!country.useFunds((severity*3)*1000)) {
                        country.useFunds(country.returnFunds());
                    }
                    break;
                    default:
                    System.out.println("Invalid response"); // loops back again
                    break;
                }
            }
        }
    }

    public void askForInternationalCrisisResponse(int severity, String countryInvolved) {
        int response;
        boolean validResponse = false;
        while (!validResponse) {
            System.out.println("How would you like to respond to this international crisis?" +
                "\n  1) Invoke the military" +
                "\n  2) Attend peace talks");
            response = collectStringAndParseInt(); // asks user how to respond to international crisis
            switch (response) {
                case 1:
                validResponse = true;
                askToInvokeMilitary(severity); // invokes the military
                break;
                case 2:
                validResponse = true;
                negotiatePeace(severity, countryInvolved); // peace talks
                break;
                default:
                System.out.println("Invalid response");
                break;
            }
        }
    }

    public void negotiatePeace(int severity, String countryInvolved) {
        int chance = gen.nextInt(101); // gets random number between 0 and 101
        if (chance>=severity) { // if chance >= severity; lower severity = higher chance
            System.out.println("You successfully negotiated peace with " + countryInvolved + "!" +
                "\n  The Nation gained $" + (severity*1000) + "!");
            country.depositFunds(severity*1000); // deposit funds if successful
        }
        else {
            System.out.println("Peace talks with " + countryInvolved + " were unsuccessful." +
                "\n  Due to expenses and lost time, the Nation used $" + (severity*500) + "." + //penalty for lost time
                "\n  You need to invoke the Military.");
            if (!country.useFunds(severity*500))  country.useFunds(country.returnFunds()); // either deducts severity*500 or the rest of the funds
            askToInvokeMilitary(severity); // invokes the military
        }
    }

    public void askToInvokeMilitary(int severity) {
        int branch;
        boolean validResponse = false;
        int forceResponse;
        while (!validResponse) {
            System.out.println("Which branch of the military would you like to invoke?" +
                "\n  1) Army" + 
                "\n  2) Navy" +
                "\n  3) Air Force");
            branch = collectStringAndParseInt(); // asks user which military branch to invoke
            if (branch==1 || branch==2 || branch==3) { // if response is 1,2,3 (the options), ask how much force to use
                System.out.println("How much force would you like to use? (1-100)");
                forceResponse = collectStringAndParseInt();
                validResponse = useMilitaryForce(branch, severity, forceResponse); // if useMilitaryForce is true, validResponse is also true to exit the loop
            }
            else System.out.println("Invalid response");
        }
    }

    public boolean useMilitaryForce(int branch, int severity, int forceResponse) {
        // returns boolean value for whether force was used or not
        boolean usedForce = false;
        // assigns a value to branchString based on the branch number; for use later in the method
        String branchString = getBranchStringFromInt(branch);
        if (forceResponse<=0) { // if collectStringAndParseInt() determines String to be invalid, forceResponse=0; also catches a negative number
            System.out.println("Invalid response");
        }
        else if (country.invokeMilitary(branch, forceResponse)) { // if can invoke branch with forceResponse
            int chance = gen.nextInt(forceResponse+1); // get random number between 0 and forceResponse+1
            if (chance>=severity) { // lower severity = higher chance, though dependent on user's input
                System.out.println("Success! The Nation gained $" + (severity*1000) + "!");
                country.depositFunds(severity*1000); // deposit money if successful
            }
            else  {
                System.out.println("You were unsuccessful in your efforts." +
                    "\n  You used " + forceResponse + " force in the " + branchString + ".");
            }
            usedForce = true;
        }
        else System.out.println("You do not have enough firepower in the " + branchString + "!");
        return usedForce;
    }

    public void askToReplenishMilitary() {
        // ask if user wishes to replenish any branch of the military
        boolean validResponse = false;
        int response;
        while (!validResponse) {
            System.out.println("\nWould you like to replenish any branches of the Military at this time?" + 
                "\n If yes, please select from one of the following branches. \n If no, please select Option 4." +
                "\n  1) Army" + 
                "\n  2) Navy" +
                "\n  3) Air Force" +
                "\n  4) none");
            response = collectStringAndParseInt(); // collect input, returns an int back
            if (response==1 || response==2 || response==3) {
                validResponse = true;
                replenishBranchOfMilitary(response);
                printNationalStatus();
            }
            else if (response==4) validResponse = true;
            else System.out.println("Invalid response");
        }
    }

    public void replenishBranchOfMilitary(int branch) {
        int response;
        boolean validResponse = false;
        while (!validResponse) {
            System.out.println("How much force would you like to replenish? (1-100)");
            response = collectStringAndParseInt();
            String branchString = getBranchStringFromInt(branch);
            if (response<=0) System.out.println("Invalid response");
            else if (country.replenishMilitary(response, branch)) {
                validResponse = true;
                System.out.println("You've successfully replenished the " + branchString + " with " + response + " force." +
                    "\n  You used $" + (response*1000) +".");
            }
            else System.out.println("You do not have enough funds!");
        }
    }

    public DomesticEvent[] generateArrayOfDomesticEvents(int x, int y) {
        // generate random number of DomesticEvents in [x,y] range
        DomesticEvent[] dEvents = new DomesticEvent[getRandomNumInRange(x,y)];
        for (int i=0; i<dEvents.length; i++) dEvents[i] = new DomesticEvent(getRandomNumInRange(1,99), gen.nextBoolean());
        // each DomesticEvent gets a severity in [1,99], positivity determined by randomly selected boolean by generator
        return dEvents;
    }

    public InternationalEvent[] generateArrayOfInternationalEvents(int x, int y) {
        // generate random number of InternationalEvents in [x,y] range
        InternationalEvent[] iEvents = new InternationalEvent[getRandomNumInRange(x,y)];
        String[] countries = {"the United Kingdom", "Canada", "Mexico", "China", "Japan", "France", 
                "Germany", "South Korea", "Brazil", "Egypt", "Saudi Arabia", "Argentina", "Peru",
                "Yemen", "Kazakhstan", "Mongolia", "Vietnam", "Philippines", "Thailand", "Australia",
                "Spain", "Portugal", "Ireland", "Sweden", "Norway", "Netherlands", "Poland", "Ukraine",
                "Chile", "Ecuador", "Egypt", "Libya", "Morocco", "Monaco", "Greece", "Iraq", "Iran",
                "Austria", "India", "Pakistan", "Russia", "Indonesia", "New Zealand", "Finland", "Denmark", 
                "Belgium", "Estonia", "Latvia", "Lithuania", "Turkey", "Afghanistan", "Pakistan", 
                "Turkmenistan", "Uzbekistan", "Bangladesh", "Nepal", "Myanmar"};
        for (int i=0; i<iEvents.length; i++) {
            iEvents[i] = new InternationalEvent(getRandomNumInRange(1,99), gen.nextBoolean(), countries[gen.nextInt(countries.length)]);
        }
        // each InternationalEvent has severity in [1,99], positivity determined by random generator, 
        // countryInvolved determined by random generator, which indexes into String array of countries above
        return iEvents;
    }

    public void runThroughDomesticEvents(DomesticEvent[] dEvents) {
        for (int i=0; i<dEvents.length; i++) {
            if (dEvents[i].influence) { // if positive influence, add funds to Nation's Reserve
                System.out.println("\nPresident " + lastName + ", the economy is booming, and citizens are prospering!" + 
                    "\n  Stocks have soared, and the people are happy!" +
                    "\n  The Nation gained $" + (dEvents[i].severity*1000) +"!");
                country.depositFunds(dEvents[i].severity*1000);
                pressAnyKeyToContinue();
                printNationalStatus();
            }
            else { // negative influence,  print different messages based on the severity value
                if (dEvents[i].severity<=25) {
                    System.out.println("\nOh no, President " + lastName + "!" +
                        "\n  A drought afflicts the Nation.");
                    askForDomesticCrisisResponse(dEvents[i].severity);
                }
                else if (dEvents[i].severity<=50) {
                    System.out.println("\nOh no, President " + lastName + "!" +
                        "\n  Due to a drought and severe weather, a major famine afflicts the Nation.");
                    askForDomesticCrisisResponse(dEvents[i].severity);
                }
                else if (dEvents[i].severity<=85) {
                    System.out.println("\nOh no, President " + lastName + "!" +
                        "\n  The stock market has crashed, and companies have collapsed!");
                    askForDomesticCrisisResponse(dEvents[i].severity);
                }
                else { // severity greater than 85
                    System.out.println("\nOh no, President " + lastName + "!" +
                        "\n  The homeland security of the Nation has been threatened with imminent violence and terrorism!");
                    askForDomesticCrisisResponse(dEvents[i].severity);
                }
                printNationalStatus();
            }
        }
    }

    public void runThroughInternationalEvents(InternationalEvent[] iEvents) {
        for (int i=0; i<iEvents.length; i++) {
            if (iEvents[i].influence) { // positive influence, the Nation gains funds
                System.out.println("\nPresident " + lastName + ", your brilliant advisors have negotiated a breakthrough partnership"
                    + " with " + iEvents[i].countryInvolved + ". "
                    + "\n  As a result, the Nation gained $" + (iEvents[i].severity*1000) + "!");
                country.depositFunds(iEvents[i].severity*1000);
                pressAnyKeyToContinue();
                printNationalStatus();
                askToReplenishMilitary();
            }
            else { // negative influence, calls askForInternationalCrisisResponse; after each event, asks if user wishes to replenish Military
                System.out.println("\nOh no, President " + lastName + "!" + 
                    "\n  An international conflict has broken out with " + iEvents[i].countryInvolved + ".");
                askForInternationalCrisisResponse(iEvents[i].severity, iEvents[i].countryInvolved);
                printNationalStatus();
                askToReplenishMilitary();
            }
        }
    }
}
