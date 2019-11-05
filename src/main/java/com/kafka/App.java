package com.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{


    public static void main( String[] args )
    {

        KafkaService kf=new KafkaService();
        ElasticService es = new ElasticService();

        //send message
        for(int i=1;i<=5;i++){
            kf.send(new CustomObject(String.valueOf(i),"testName " + i,(25+i)));
        }



        //consume and process consumed message
        final Consumer<String, CustomObject> consumer =kf.consume("msg");

        final int giveUp = 100;   int noRecordsCount = 0;


        while (true) {
            final ConsumerRecords<String, CustomObject> consumerRecords =
                    consumer.poll(1000);
            if (consumerRecords.count()==0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }
            consumerRecords.forEach(record -> {
                es.send(convertObjectIntoJson(record.value()));
            });
            consumer.commitAsync();
        }
        consumer.close();


    }

    private static String convertObjectIntoJson(CustomObject object){
        // Creating Object of ObjectMapper define in Jakson Api
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = "";
        try {

            // get Custom object as a json string
            jsonStr = Obj.writeValueAsString(object);

            // Displaying JSON String
            System.out.println("elastic: "+jsonStr);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
}
