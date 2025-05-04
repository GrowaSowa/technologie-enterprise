package lab.app;

import jakarta.json.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

public class Main {
    private static String url = "http://localhost:8080/Server-1.0-SNAPSHOT/";

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        //getComplaintStatus(client, 601L);
        //getAllComplaints(client);
        //getComplaint(client, 601L);

        getOpenComplaints(client);
        client.close();
    }

    private static void getAllComplaints(Client client) {
        String res =
                client.target(url + "api/complaints/")
                        .request(MediaType.APPLICATION_JSON)
                        .get(String.class);
        System.out.println(res);
    }

    private static void getOpenComplaints(Client client) {
        String res =
                client.target(url + "api/complaints?status=open")
                        .request(MediaType.APPLICATION_JSON)
                        .get(String.class);
        System.out.println(res);
    }

    private static void getComplaint(Client client, Long id) {
        String res =
                client.target(url + "api/complaints/" + id)
                        .request(MediaType.APPLICATION_JSON)
                        .get(String.class);
        System.out.println(res);
    }

    private static void getComplaintStatus(Client client, Long id) {
        String res =
                client.target(url + "api/complaints/" + id + "/status")
                        .request(MediaType.TEXT_PLAIN)
                        .get(String.class);
        System.out.println("Status: " + res);
    }
}
