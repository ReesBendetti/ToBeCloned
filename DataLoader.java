import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
    public static ArrayList<Card> getCards() {
        ArrayList<Card> cards = new ArrayList<Card>();

        try {
			FileReader reader = new FileReader(CARDS_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray cardsJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < cardsJSON.size(); i++) {
				JSONObject cardJSON = (JSONObject)cardsJSON.get(i);
                UUID cardID = UUID.fromString((String)cardJSON.get(CARD_ID));
				String playerFirstName = (String)cardJSON.get(CARD_PLAYER_FIRST_NAME);
                String playerLastName = (String)cardJSON.get(CARD_PLAYER_LAST_NAME);
                String playerPosition = (String)cardJSON.get(CARD_PLAYER_POSITION);
                String sportsLeague = (String)cardJSON.get(CARD_SPORTS_LEAGUE);
                String playerTeamName = (String)cardJSON.get(CARD_TEAM_NAME);
                int cardNumber = ((Long)cardJSON.get(CARD_NUMBER)).intValue();
                String playerTrivia = (String)cardJSON.get(CARD_PLAYER_TRIVIA);
                String rarityType = (String)cardJSON.get(CARD_RARITY_TYPE);
                Boolean isRookie = getBoolean((String)cardJSON.get(CARD_RARITY_TYPE));
                int numCardsTotal = ((Long)cardJSON.get(CARD_NUMBER_TOTAL_CARDS)).intValue();
                int numCardsInInventory = ((Long)cardJSON.get(CARD_TOTAL_CARD_INVENTORY)).intValue();


				System.out.println("Test: " + cardID + " " + playerFirstName);
				
                if(sportsLeague.equalsIgnoreCase("baseball")){
				    cards.add(new BaseballCard(cardID, playerFirstName, playerLastName, playerPosition, sportsLeague, playerTeamName, cardNumber, playerTrivia,rarityType, isRookie, numCardsTotal, numCardsInInventory));
                }
			}
			
			return cards;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            return cards;
        }
    }

    private static boolean getBoolean(String data){
        return data.equalsIgnoreCase("true");
    }

    public static ArrayList<Account> getAccounts(){
        ArrayList<Account> accounts = new ArrayList<Account>();
 
        return accounts;
    }
   
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            FileReader reader = new FileReader(USERS_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0;i<usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject)usersJSON.get(i);
                String id = (String)userJSON.get(USER_ID);
                String username = (String)userJSON.get(USER_USERNAME);
                String password = (String)userJSON.get(USER_PASSWORD);
                String email = (String)userJSON.get(USER_EMAIL);
                String coins = (String)userJSON.get(USER_COINS);
                String cards = (String)userJSON.get(USER_CARDS);
                users.add(new User(username, password, email));
            }
            return users;
        }
        catch(Exception e) {
            e.printStackTrace();
        } finally {
            return users;
        }
    }
    public static ArrayList<TradeProposal> getProposedTrade() {
        ArrayList <TradeProposal> proposals = new ArrayList<TradeProposal>();
        return proposals;
    }

    public static void main(String[] args){
        ArrayList<Card> cards = DataLoader.getCards();

        for(Card card : cards){
            System.out.println(card);
        }
    }
}
