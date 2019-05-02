import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConnectionManager {


    public static TransportClient client;


    public static  TransportClient prepareConnection() throws UnknownHostException {

        Settings settings = Settings.builder()
                .put("cluster.name", "myClusterName")
                .put("client.transport.sniff", false)
                .put("client.transport.ignore_cluster_name", true).build();
         client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        return client;

    }
}
