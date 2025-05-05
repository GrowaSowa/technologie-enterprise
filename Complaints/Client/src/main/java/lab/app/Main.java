package lab.app;

import jakarta.json.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lab.entities.Complaint;

import java.io.StringReader;
import java.util.Set;

public class Main {
    private static String url = "http://localhost:8080/Server-1.0-SNAPSHOT/";

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        //getComplaintStatus(client, 601L);
        //getAllComplaints(client);
        printComplaint(getComplaint(client, 604L));

        //closeComplaint(client, 604L);
        //openComplaint(client, 604L);
        //getOpenComplaints(client);
        client.close();
    }

    private static void getAllComplaints(Client client) {
        String res =
                client.target(url + "api/complaints/")
                        .request(MediaType.APPLICATION_JSON)
                        .get(String.class);
        JsonStructure j = Json.createReader(new StringReader(res)).read();
        printJsonArray(j.asJsonArray());
    }

    private static void getOpenComplaints(Client client) {
        String res =
                client.target(url + "api/complaints")
                        .queryParam("status", "open")
                        .request(MediaType.APPLICATION_JSON)
                        .get(String.class);
        JsonStructure j = Json.createReader(new StringReader(res)).read();
        printJsonArray(j.asJsonArray());
    }

    private static Complaint getComplaint(Client client, Long id) {
        Complaint complaint =
                client.target(url + "api/complaints/")
                        .path("{id}").resolveTemplate("id", id)
                        .request(MediaType.APPLICATION_JSON)
                        .get(Complaint.class);
        return complaint;
    }

    private static void getComplaintStatus(Client client, Long id) {
        String res =
                client.target(url + "api/complaints/" + id + "/status")
                        .request(MediaType.TEXT_PLAIN)
                        .get(String.class);
        System.out.println("status: " + res);
    }

    private static void closeComplaint(Client client, Long id) {
        Complaint complaint = getComplaint(client, id);
        complaint.setStatus("closed");

        Response response = client.target(url + "api/complaints")
                .path("{id}").resolveTemplate("id", id)
                .request().put(Entity.entity(complaint, MediaType.APPLICATION_JSON));
        System.out.println("request status: " + response.getStatus());
    }

    private static void openComplaint(Client client, Long id) {
        Complaint complaint = getComplaint(client, id);
        complaint.setStatus("open");

        Response response = client.target(url + "api/complaints")
                .path("{id}").resolveTemplate("id", id)
                .request().put(Entity.entity(complaint, MediaType.APPLICATION_JSON));
        System.out.println("request status: " + response.getStatus());
    }

    private static void printJsonArray(JsonArray arr) {
        int i = 0;
        for(JsonValue val : arr) {
            System.out.println(i + ":");
            printJsonObject(val.asJsonObject());
            System.out.println();
            i++;
        }
    }

    private static void printJsonObject(JsonObject obj) {
        Set<String> keys = obj.keySet();
        for(String key : keys) {
            System.out.println(key + ": " + obj.get(key));
        }
    }

    private static void printComplaint(Complaint complaint) {
        System.out.println("id: " + complaint.getId());
        System.out.println("date: " + complaint.getComplaintDate());
        System.out.println("text: " + complaint.getComplaintText());
        System.out.println("author: " + complaint.getAuthor());
        System.out.println("status: " + complaint.getStatus());
    }
}
