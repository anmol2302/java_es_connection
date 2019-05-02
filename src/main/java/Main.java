import org.apache.log4j.PropertyConfigurator;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Main {



    static {

    }
    public static void main(String[] args) throws UnknownHostException {

        Properties prop = new Properties();
        prop.setProperty("log4j.rootLogger", "WARN");
        PropertyConfigurator.configure(prop);
        TransportClient client=ConnectionManager.prepareConnection();
        client.prepareIndex("kodcucom", "article", "1")
                .setSource(putJsonDocument("ElasticSearch: Java API",
                        "ElasticSearch provides the Java API, all operations "
                                + "can be executed asynchronously using a client object.",
                        new Date(),
                        new String[]{"elasticsearch"},
                        "Hüseyin Akdoğan")).execute().actionGet();




        GetResponse getResponse = client.prepareGet("kodcucom", "article", "1").execute().actionGet();
        Map<String, Object> source = getResponse.getSource();
        System.out.println("------------------------------");
        System.out.println("Index: " + getResponse.getIndex());
        System.out.println("Type: " + getResponse.getType());
        System.out.println("Id: " + getResponse.getId());
        System.out.println("Version: " + getResponse.getVersion());
        System.out.println(source);
        System.out.println("------------------------------");


    }

    public static Map<String, Object> putJsonDocument(String title, String content, Date postDate,
                                                      String[] tags, String author){
        Map<String, Object> jsonDocument = new HashMap<String, Object>();
        jsonDocument.put("title", title);
        jsonDocument.put("conten", content);
        jsonDocument.put("postDate", postDate);
        jsonDocument.put("tags", tags);
        jsonDocument.put("author", author);
        return jsonDocument;
    }
}
