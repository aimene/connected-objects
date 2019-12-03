/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thermometre;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 *
 * @author e1801322
 */
public class Thermometre {

    static String url = "http://localhost:4567/thermometre";
    static String charset = "UTF-8";
    
    public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException, IOException {
        int tempCourante = 10;
        String query = String.format( 
		"tempCourante=%d",
		tempCourante);
        
        
            // Envoie de la requete  HTTP POST
        URLConnection  connection = new URL(url).openConnection();

        // Pour specifier que la requete est de type HTTP POST
        connection.setDoOutput(true); 

        // Pour le codage des caractères
        connection.setRequestProperty("Accept-Charset", charset);

        // Pour le format des donnees envoyees par la requete HTTP POST
        connection.setRequestProperty("Content-Type", 
            "application/x-www-form-urlencoded;charset=" + charset);

        // Pour envoyer les parametres de la requete
        try (OutputStream output = connection.getOutputStream()) {
                    output.write(query.getBytes(charset));
        }
        // Recuperation du flux pour lire la reponse de la requete
        InputStream response = connection.getInputStream();

        // Lecture de la reponse de la requete
        try (Scanner scanner = new Scanner(response)) 
        {
                 // le delimitateur "\A" pour le debut d'une String
                String responseBody = scanner.useDelimiter("\\A").next();
                // Affiche le resultat sur la console
                System.out.println(responseBody);
        }


        

    }
    
}
