import java.util.Scanner;

/**
 * Represent a basic card game which a player involved in.
 * 
 * @author Muhammed Huseyin AYDIN
 */
public class Lab05_Q3 {
    public static void main(String[] args) {
        // Variables
        Scanner scanner = new Scanner(System.in);
        String ranks = "123456789TJQK";
        String deck = "";
        String tempDeck;
        String shuffledDeck = "";
        String player1Hand = "";
        String player2Hand = "";
        String player3Hand = "";
        String player4Hand = "";
        String winnerPlayer = "";
        String cardsOnTable;
        String inputCard;
        char playedCard;
        char topCard;
        final char EMPTY = '0';
        int player1point = 0;
        int player2point = 0;
        int player3point = 0;
        int player4point = 0;
        int roundCounter;
        int randomInt;
        boolean foundWinner = false;
        boolean validInput;

        // Initialize and randomize the deck and print them out
        for(int i = 1; i <= ranks.length(); i++) {
            deck = deck + ranks.substring(i-1, i).repeat(4);
        }
        System.out.println();
        System.out.println("Starting the game with the following deck:");
        System.out.println(deck);

        do { // Redistribution of cards in case nobody reaches 35 points
            
            // Shuffle the deck
            tempDeck = deck;
            do {
                randomInt = (int)(Math.random() * tempDeck.length());
                shuffledDeck += tempDeck.charAt(randomInt);
                tempDeck = tempDeck.replaceFirst(String.valueOf(tempDeck.charAt(randomInt)), "");
            } while (!tempDeck.isEmpty());
            System.out.println();
            System.out.println("Let's shuffle the deck:");
            System.out.println(shuffledDeck);
    
            // Initialize the game by putting 4 cards to the table
            System.out.println();
            System.out.println("Put first 4 cards on table...");
            cardsOnTable = shuffledDeck.substring(0, 4);
            System.out.printf("Cards on table: %s%n", cardsOnTable);
            shuffledDeck = shuffledDeck.substring(4);

            // 3 rounds of the game
            roundCounter = 1;
            do{
                // Deal 4 cards to each player amd print everyone's hand
                System.out.println();
                for(int j = 0; j < 4; j++) {
                    player1Hand = player1Hand + shuffledDeck.charAt(0);
                    player2Hand = player2Hand + shuffledDeck.charAt(1);
                    player3Hand = player3Hand + shuffledDeck.charAt(2);
                    player4Hand = player4Hand + shuffledDeck.charAt(3);
                    shuffledDeck = shuffledDeck.substring(4);
                }
                System.out.println("_".repeat(100));
                System.out.printf("Game round #%d%n", roundCounter);
                System.out.printf("\tPlayer1: %s%n", player1Hand);
                System.out.printf("\tPlayer2: %s%n", player2Hand);
                System.out.printf("\tPlayer3: %s%n", player3Hand);
                System.out.printf("\tPlayer4: %s%n%n", player4Hand);
    
                // 4 turns of a round
                for (int j = 0; j < 4; j++) {
                    // Player's round
                    do {
                        validInput = false;
                        System.out.printf("Now your turn, your hand: %s, select a card to play: ", player1Hand);
                        inputCard = scanner.nextLine();
                        if (inputCard.length() == 1 && player1Hand.contains(inputCard)){
                            validInput = true;
                        }
                    } while (!validInput);
                    
                    if (!cardsOnTable.isEmpty())
                        topCard = cardsOnTable.charAt(0);
                    else{
                        topCard = EMPTY;
                    }
                    if (inputCard.equals(String.valueOf(topCard))){ // Fish cards
                        player1point = player1point + cardsOnTable.length() + 1;
                        if (!(cardsOnTable.contains("J") || cardsOnTable.contains("Q") || cardsOnTable.contains("K"))){
                            player1point += 10;
                        }
                        cardsOnTable = "";
                    }
                    else{ // Add card to table
                        cardsOnTable = inputCard + cardsOnTable;
                    }
                    // Print out what happened and arrange player's hand
                    System.out.printf("Player1 hand: %s  --> played: %s --> current hand: %s"
                        + "     --Cards on table: %s%n", player1Hand, inputCard, 
                        player1Hand.replaceFirst(inputCard, ""), cardsOnTable);
                    player1Hand = player1Hand.replaceFirst(inputCard, "");
    
    
                    // Player2's turn
                    if (!cardsOnTable.isEmpty())
                        topCard = cardsOnTable.charAt(0);
                    else{
                        topCard = EMPTY;
                    }
                    if (player2Hand.contains(String.valueOf(topCard))){ // Fish cards
                        playedCard = topCard;
                        player2point = player2point + cardsOnTable.length() + 1;
                        if (!(cardsOnTable.contains("J") || cardsOnTable.contains("Q") || cardsOnTable.contains("K"))){
                            player2point += 10;
                        }
                        cardsOnTable = "";
                    }
                    else{ // Add card to table
                        playedCard = player2Hand.charAt((int) (Math.random() * (4-j)));
                        cardsOnTable = playedCard + cardsOnTable;
                    }
                    // Print out what happened and arrange player's hand
                    System.out.printf("Player2 hand: %s  --> played: %c --> current hand: %s"
                        + "     --Cards on table: %s%n", player2Hand, playedCard, 
                        player2Hand.replaceFirst(String.valueOf(playedCard), ""), cardsOnTable);
                    player2Hand = player2Hand.replaceFirst(String.valueOf(playedCard), "");
    
    
                    // Player3's turn
                    if (!cardsOnTable.isEmpty())
                        topCard = cardsOnTable.charAt(0);
                    else{
                        topCard = EMPTY;
                    }
                    if (player3Hand.contains(String.valueOf(topCard))){ // Fish cards
                        playedCard = topCard;
                        player3point = player3point + cardsOnTable.length() + 1;
                        if (!(cardsOnTable.contains("J") || cardsOnTable.contains("Q") || cardsOnTable.contains("K"))){
                            player3point += 10;
                        }
                        cardsOnTable = "";
                    }
                    else{ // Add card to table
                        playedCard = player3Hand.charAt((int) (Math.random() * (4-j)));
                        cardsOnTable = playedCard + cardsOnTable;
                    }
                    // Print out what happened and arrange player's hand
                    System.out.printf("Player3 hand: %s  --> played: %c --> current hand: %s"
                        + "     --Cards on table: %s%n", player3Hand, playedCard, 
                        player3Hand.replaceFirst(String.valueOf(playedCard), ""), cardsOnTable);
                    player3Hand = player3Hand.replaceFirst(String.valueOf(playedCard), "");
    
    
                    // Player4's turn
                    if (!cardsOnTable.isEmpty())
                        topCard = cardsOnTable.charAt(0);
                    else{
                        topCard = EMPTY;
                    }
                    if (player4Hand.contains(String.valueOf(topCard))){ // Fish cards
                        playedCard = topCard;
                        player4point = player4point + cardsOnTable.length() + 1;
                        if (!(cardsOnTable.contains("J") || cardsOnTable.contains("Q") || cardsOnTable.contains("K"))){
                            player4point += 10;
                        }
                        cardsOnTable = "";
                    }
                    else{ // Add card to table
                        playedCard = player4Hand.charAt((int) (Math.random() * (4-j)));
                        cardsOnTable = playedCard + cardsOnTable;
                    }
                    // Print out what happened and arrange player's hand
                    System.out.printf("Player4 hand: %s  --> played: %c --> current hand: %s"
                        + "     --Cards on table: %s%n", player4Hand, playedCard, 
                        player4Hand.replaceFirst(String.valueOf(playedCard), ""), cardsOnTable);
                    player4Hand = player4Hand.replaceFirst(String.valueOf(playedCard), "");
    
                    // Print out current points
                    System.out.printf("Points *** Player1: %d. Player2: %d. Player3: %d."
                        +" Player4: %d.***%n%n", player1point, player2point, player3point,
                        player4point);
                        
                    // Calculate if there is a winner
                    if (35 <= player1point){
                        winnerPlayer += "1";
                        foundWinner = true;
                    }
                    if(35 <= player2point){
                        winnerPlayer += "2";
                        foundWinner = true;
                    }
                    if(35 <= player3point){
                        winnerPlayer += "3";
                        foundWinner = true;
                    }
                    if(35 <= player4point){
                        winnerPlayer += "4";
                        foundWinner = true;
                    }
                
                }
                roundCounter++;
            } while(roundCounter <= 3 && !foundWinner);
            if (!foundWinner){
                System.out.println("---- No one can reach/pass 35 points, cards are redistributed ----");
            }
        } while (!foundWinner);
    
        winnerPlayer  = String.valueOf(winnerPlayer.charAt(((int) (Math.random() * winnerPlayer.length()))));
        System.out.printf("Total points:%nPlayer1: %d%nPlayer2: %d%nPlayer3: %d" +
            "%nPlayer4: %d%nNumber of cards on the table: %d%n%n**** Player%s"+
            " exceeds 35 points and wins! ****", player1point, player2point,
            player3point, player4point, cardsOnTable.length(), winnerPlayer);
    }
}
