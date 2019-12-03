/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radiateur;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;



/**
 *
 * @author e1801322
 */
public class Radiateur {
static String url = "http://localhost:4567/Radiateur";
static String charset = "UTF-8";

    public static void main(String[] args) throws MalformedURLException, IOException {
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = connection.getInputStream();
        
        try (Scanner scanner = new Scanner(response)) 
        {
	// le delimitateur "\A" pour le debut d'une String
	String etat = scanner.useDelimiter("\\A").next();
	// Affiche le resultat sur la console
	System.out.println(etat);
        }


    }
    
}
