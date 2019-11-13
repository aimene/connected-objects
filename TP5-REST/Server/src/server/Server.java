/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import static spark.Spark.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

/**
 *
 * @author e1801322
 */
public class Server {

    private static int temporatureCourante;
    private static int temporatureChauffage=20;
    private static boolean EtatRadiateur;
    
    static String url = "http://localhost:4567/";
    static String charset = "UTF-8";
    
    public static void main(String[] args) throws MalformedURLException, IOException {
          
         // Pour traiter la requete HTTP POST http://localhost:4567/thermometre?tempCourante=5 
           post("/thermometre", (request, response) -> {
              String val=request.queryParams("tempCourante");
              temporatureCourante= Integer.parseInt(val) ;
              EtatRadiateur = temporatureChauffage>temporatureCourante;
               JSONObject obj = new JSONObject();
                    obj.put("thermometre", "thermometre");
                     obj.put("temporatureCourante", temporatureCourante);
                      obj.put("temporatureChauffage", temporatureChauffage);
                       obj.put("EtatRadiateur", EtatRadiateur);
                     return obj;
             });
      
         // Pour traiter la requete HTTP GET http://localhost:4567/Radiateur
           get("/Radiateur", (Request request, Response response) -> {
                     JSONObject obj = new JSONObject();
                          obj.put("Radiateur", "Radiateur");
                     obj.put("temporatureCourante", temporatureCourante);
                      obj.put("temporatureChauffage", temporatureChauffage);
                       obj.put("EtatRadiateur", EtatRadiateur);
                     return obj;
           });
           
           // Pour traiter la requete HTTP POST http://localhost:4567/smartphone?tempChauffage=5 
           post("/smartphone", (request, response) -> {
              String val=request.queryParams("tempChauffage");
              temporatureChauffage= Integer.parseInt(val) ;
              EtatRadiateur = temporatureChauffage>temporatureCourante;
               JSONObject obj = new JSONObject();
                                 obj.put("smartphone", "smartphone POST");
                     obj.put("temporatureCourante", temporatureCourante);
                      obj.put("temporatureChauffage", temporatureChauffage);
                       obj.put("EtatRadiateur", EtatRadiateur);
                     return obj;
             });
           
           // Pour traiter la requete HTTP GET http://localhost:4567/smartphone
           get("/smartphone", (request, response) -> {
                     JSONObject obj = new JSONObject();
                       obj.put("smartphone", "smartphone GET");
                     obj.put("temporatureCourante", temporatureCourante);
                      obj.put("temporatureChauffage", temporatureChauffage);
                       obj.put("EtatRadiateur", EtatRadiateur);
                     return obj;
               });
           
          

           
    }
    
}
