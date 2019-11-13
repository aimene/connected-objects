/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package station;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author e1801322
 */
public class Station {
 static Random random = new Random();
   
 static int RandomValue(int min,int max){
       
        return  random.nextInt(max + 1 - min) + min;
    }
     
     static void publish () throws MqttException, InterruptedException{
        int X=RandomValue(0, 10000);
            MqttClient Station = new MqttClient("tcp://localhost:1883", "station_"+X);
            Station.connect();
            MqttMessage message = new MqttMessage();
              while (true) {
                TimeUnit.SECONDS.sleep(5);
                
                message.setPayload(Integer.toString((int) (Math.random() * (20-10))).getBytes());                 
                Station.publish("/temperature/"+X, message);
                
                message.setPayload(Integer.toString(RandomValue(0,3)).getBytes());
                Station.publish("/vent", message);
             }
      }
    public static void main(String[] args) throws InterruptedException, MqttException {
      
    publish();
  

    }
    
}
