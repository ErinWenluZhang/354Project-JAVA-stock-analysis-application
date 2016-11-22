import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class login {
	String name;
	String password;
	
	public login(String name, String password){
		this.name = name;
		this.password = password;
	}
	public boolean verify(){
		try{
			String line;
			String login[]=new String[2];
			//FileReader fr = new FileReader("users.txt");
			//BufferedReader br = new BufferedReader(fr);
		//	InputStream fr = this.getClass().getResourceAsStream("users.txt");
		//	BufferedReader br = new BufferedReader(new InputStreamReader(fr,"UTF-8"));
			BufferedReader br;
			br = new BufferedReader(new FileReader("users.txt"));
			while((line = br.readLine()) != null){
				login = line.split(",");
				if(name.equals(login[0]) && password.equals(login[1])){
					br.close();
					return true;
				}
			}
			br.close();
		}catch(FileNotFoundException e1){
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return false;
	}
}
