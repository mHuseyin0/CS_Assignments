/*
 * Represent a basic card game which is completely played by computer.
 * 
 * @author Muhammed Huseyin AYDIN
 */
public class Lab05_Q2 {
    public static void main(String[] args) {
        // Variables
        String ranks = "123456789TJQK";
        String deck = "";
        String shuffledDeck = "";
        String player1Hand = "";
        String player2Hand = "";
        String player3Hand = "";
        String player4Hand = "";
        String winnerPlayer = "";
        String cardsOnTable;
        char playedCard;
        char topCard;
        final char EMPTY = '0';
        int player1point = 0;
        int player2point = 0;
        int player3point = 0;
        int player4point = 0;
        int randomInt;
        int winner;

        // Initialize and randomize the deck and print them out
        for(int i = 1; i <= ranks.length(); i++) {
            deck = deck + ranks.substring(i-1, i).repeat(4);
        }
        System.out.println();
        System.out.println("Starting the game with the following deck:");
        System.out.println(deck);

        // Shuffle the deck
        do {
            randomInt = (int)(Math.random() * deck.length());
            shuffledDeck += deck.charAt(randomInt);
            deck = deck.replaceFirst(String.valueOf(deck.charAt(randomInt)), "");
        } while (!deck.isEmpty());
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
        for (int i = 1; i <= 3; i++) {
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
            System.out.printf("Game round #%d%n", i);
            System.out.printf("\tPlayer1: %s%n", player1Hand);
            System.out.printf("\tPlayer2: %s%n", player2Hand);
            System.out.printf("\tPlayer3: %s%n", player3Hand);
            System.out.printf("\tPlayer4: %s%n%n", player4Hand);

            // 4 turns of a round
            for (int j = 0; j < 4; j++) {
                // Player1's turn
                if (!cardsOnTable.isEmpty())
                    topCard = cardsOnTable.charAt(0);
                else{
                    topCard = EMPTY;
                }

                if (player1Hand.contains(String.valueOf(topCard))){ // Fish cards
                    playedCard = topCard;
                    player1point = player1point + cardsOnTable.length() + 1;
                    cardsOnTable = "";
                }
                else{ // Add card to table
                    playedCard = player1Hand.charAt((int) (Math.random() * (4 - j)));
                    cardsOnTable = playedCard + cardsOnTable;
                }
                // Print out what happened and arrange player's hand
                System.out.printf("Player1 hand: %s  --> played: %c --> current hand: %s"
                    + "     --Cards on table: %s%n", player1Hand, playedCard, 
                    player1Hand.replaceFirst(String.valueOf(playedCard), ""), cardsOnTable);
                player1Hand = player1Hand.replaceFirst(String.valueOf(playedCard), "");


                // Player2's turn
                if (!cardsOnTable.isEmpty())
                    topCard = cardsOnTable.charAt(0);
                else{
                    topCard = EMPTY;
                }
                if (player2Hand.contains(String.valueOf(topCard))){ // Fish cards
                    playedCard = topCard;
                    player2point = player2point + cardsOnTable.length() + 1;
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
            }
        }
        // Calculate the winner and print out results
        winner = Math.max(Math.max(player1point, player2point), 
            Math.max(player3point, player4point));
        if (winner == player1point){
            winnerPlayer += "1";
        }
        if(winner == player2point){
            winnerPlayer += "2";
        }
        if(winner == player3point){
            winnerPlayer += "3";
        }
        if(winner == player4point){
            winnerPlayer += "4";
        }
        winnerPlayer  = String.valueOf(winnerPlayer.charAt(((int) (Math.random() * winnerPlayer.length()))));
        System.out.printf("Total points:%nPlayer1: %d%nPlayer2: %d%nPlayer3: %d" +
            "%nPlayer4: %d%nNumber of cards on the table: %d%n%n**** Player%s wins! ****"
            ,player1point, player2point, player3point, player4point,
            cardsOnTable.length(), winnerPlayer);
    }
}