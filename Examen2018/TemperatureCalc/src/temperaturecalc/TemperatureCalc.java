/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temperaturecalc;

import java.util.concurrent.TimeUnit;
import static javafx.scene.input.KeyCode.X;
import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author e1801322
 */
public class TemperatureCalc {

    static String Station1 = null;
    static String Station2 = null;
    static String Station3 = null;

    static int Station1Temp;
    static int Station2Temp;
    static int Station3Temp;
    
    static int AverageTemp;

    
     static void publishAverageTemp (int averageTemp , String topic) throws MqttException, InterruptedException{
     
            MqttClient Station = new MqttClient("tcp://localhost:1883", "Average");
            Station.connect();
            MqttMessage message = new MqttMessage();
           
            message.setPayload(Integer.toString(averageTemp).getBytes());                 
            Station.publish(topic, message);
             
      }

    static void TemperatureCalcSUB() throws MqttException {

        MqttClient TempCalc = new MqttClient("tcp://localhost:1883", "TemperatureCalc");

        TempCalc.setCallback(new MqttCallback() {
            int value;

            @Override
            public void connectionLost(Throwable cause) {
            }
            // Callback pour la reception des messages

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                
                    TimeUnit.SECONDS.sleep(5);

                    if (topic.contains("/temperature/")) {
                        if (Station1 == null) {
                            Station1 = topic;
                        } else {
                            if (Station2 == null) {
                                Station2 = topic;
                            } else {
                                Station3 = topic;
                            }
                        }

                        if (Station1 == topic) {
                            Station1Temp = Integer.parseInt(new String(message.getPayload()));

                        }
                        if (Station2 == topic) {
                            Station2Temp = Integer.parseInt(new String(message.getPayload()));

                        }
                        if (Station3 == topic) {
                            Station3Temp = Integer.parseInt(new String(message.getPayload()));
                          

                        }

                      //  System.out.println("moyenne = (" +Station1Temp+"+"+Station2Temp+"+"+Station3Temp+")"+((Station1Temp + Station2Temp + Station3Temp) / 3));
                        AverageTemp =(Station1Temp + Station2Temp + Station3Temp) / 3;
                        publishAverageTemp(AverageTemp, "/moyennetemp");
                        
                    }

                

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });
        TempCalc.connect();
        TempCalc.subscribe("/temperature/#");
        // TempCalc.subscribe("house/button");
        // AlarmSirenSUB.disconnect();

    }

    public static void main(String[] args) throws MqttException {
        TemperatureCalcSUB();
    }

}
