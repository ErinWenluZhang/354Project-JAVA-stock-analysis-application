
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;

public class Log2 {
	private static final int SEARCH_HISTORY = 11;	//There is 1 extra spot for the username (place at index 0 of the array).
	private static String [] log = new String[SEARCH_HISTORY];
	private static File f = new File("log.txt");
	
	//Method that checks to see if a user has made the max number of log searches.
	public static int countSearches(){
		int searchesMade = 0;
		for (int i = 1; i < log.length; i ++){	//Skip index 0 since it is the username.
			if(log[i] == null)
				break;
			else
				searchesMade += 1;
		}
		return searchesMade;
	}
	
	//Method to write the information onto a text file.
		public static void writeToFile(String searched, String user) throws Exception, IOException, EOFException{
			String[] hold = new String[SEARCH_HISTORY];
			BufferedReader br;
			br = new BufferedReader(new FileReader("log.txt"));
			String line = "";
			int lineNumber = 0;		//lineNumber is used to identify which line to replace in the text file.
			while((line = br.readLine()) != null) {	//Finding out if the user has already made searches in the past.
				hold = line.split(",",-1);	//Putting the username and past searches into the log array.
				System.out.println(hold.length);
				
				for(int i = 0; i < hold.length; i ++){
					log[i] = hold[i];
				}
				if (hold.length != SEARCH_HISTORY){
				//If the hold array was not full, then the rest of the log array is filled with empty searches.
					for(int i = hold.length; i < SEARCH_HISTORY; i ++)
						log[i] = null;
				}
				lineNumber += 1;
			    if(log[0].equals(user)) { 	//The username is stored at index 0 of the log array.
			        br.close();
			        if (countSearches() == 10)	//If the max amount of searches has been made, the oldest one must be deleted.
			        	updateLogFile(searched, lineNumber - 1, true);
			        else	//Else simply append the search to the end of the string in the log file.
			        	updateLogFile(searched,lineNumber - 1, false);   
			        return;
			    }    
			}
			//If the user has not made searches in the past, then add the user and their first search into the log.
			setLogEmpty();
			log[0] = user;
			log[1] = searched;
			addNewUserHistory(user,searched);
			br.close();
		}
	
		
	
		//Method that displays the searches made for this particular user.
		public static void displayLogInfo(String user) throws IOException{
			System.out.println(user +" has made the past searches: ");
			for (int i = SEARCH_HISTORY - 1; i > 0 ; i	--)
			   	if(log[i] != null)
			   		System.out.println(log[i]);
		}
		
		//Method that updates the log file. 
		public static void updateLogFile(String searched, int lineNumber, boolean full) throws IOException{
			//If the search history for this particular user is not full; then we only have to add the newest search to the log.
			//Otherwise, delete the old search and insert the newest.
			String [] hold = new String[SEARCH_HISTORY];	//Used to update the log array.
			String replaceLine = "";	//replaceLine is the new string which will replace the old searches the user has made.
			for (int i = 0 ; i < log.length; i ++){	//This for loop rewrites the line without the oldest search.
				if (log[i] == null)		//If there are no more searches to add, then stop.
       				break;
       			if (i == 0){	//When i = 0; it is the spot for the username therefore no comma has to be added before it.
       				replaceLine += log[i];
       				continue;        			}
        		if (full && i == 1)	//Skipping the oldest search only when the search history has reached its maximum capacity.
        			continue;    		
        		replaceLine += "," + log[i];	//Adding the search at index i into the replaceLine string.
       		}
			replaceLine += ","+searched;
			hold = replaceLine.split(",",-1);	
			for(int i = 0; i < hold.length; i++){	//Updating the log array.
				log[i] = hold[i];
			}
			
			String line = "";
			File temp = new File("temp.txt");
			PrintWriter out = new PrintWriter(new FileWriter("temp.txt"));
			BufferedReader br = new BufferedReader(new FileReader("log.txt"));
			int currentLine = 0;
			while((line = br.readLine()) != null) {
				if (currentLine == lineNumber){
					out.println(replaceLine);
					currentLine += 1;
					continue;
				}
				out.println(line);
				currentLine += 1;
			}
			//Renaming and deleting files after rewriting the data.
			br.close();
			out.close();
			System.gc();
			f.renameTo(temp);
			f.delete();
			temp.renameTo(f);
			
			
		}
		
		//Method that adds a new user to the log file, with their first search.
		public static void addNewUserHistory(String user, String searched) throws IOException{
			try(FileWriter fw = new FileWriter("log.txt", true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				{	
				    out.println(user + "," + searched);
				    
				} catch (IOException e) {
				   
				}
		}
		//Method that finds the user in the log file and sets the log array accordingly.
		public static void setLog(String user) throws IOException{
			String[] hold;
			BufferedReader br;
			br = new BufferedReader(new FileReader("log.txt"));
			String line = "";
			while((line = br.readLine()) != null) {	//Finding out if the user has already made searches in the past.
				hold = line.split(",");
			    if(hold[0].equals(user)) { 
			        br.close();
			        System.out.println("User has been found.");
			        for (int i = 0; i < hold.length ; i ++)
			        	if(hold[i] != null)
			        	log[i] = hold[i];
			        return;
			    }	
			}
			br.close();
			System.out.println("User not found.");
		}
		
		public static int getUpperBound(){
			return SEARCH_HISTORY;
		}
		
		public static String getIndexElement(int i){
			if (log[i] == null)
				return "";
			return log[i];
		}
		
		//Method that removes all the contents of the log.
		public static void setLogEmpty(){
			for (int i = 0; i < SEARCH_HISTORY; i++){
				log[i] = null;
				System.out.println(i + log[i]);
			}
		}
}
