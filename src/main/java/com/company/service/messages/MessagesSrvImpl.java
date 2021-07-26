package com.company.service.messages;

import com.company.token.TokenServices;
import com.company.util.host.GetHost;
import com.company.util.host.GetHostImpl;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.Scanner;

public class MessagesSrvImpl implements MessageSrv{
    GetHost host = new GetHostImpl();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void addChatroomMessage() {
        JSONObject jsonObject = new JSONObject();
        System.out.println(" Insert name of chatroom ");
        jsonObject.put("chatroomName", scanner.nextLine());
        System.out.println(" Insert message --");
        jsonObject.put("message", scanner.nextLine());
        System.out.println(" Insert password of chatroom");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/addChatroomMessage"))
                    .header("Authorization", TokenServices.getToken())
                    .header("Content-Type", "application/json")
                    .body(jsonObject)
                    .asJson();
            boolean status = httpResponse.isSuccess();
            if (status) {
                System.out.println("Send!");
            } else {
                System.out.println("Message not send!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showChatroomMessages() {

        JSONObject jsonObject = new JSONObject();
        System.out.println(" Insert name of chatroom");
        jsonObject.put("chatroomName", scanner.nextLine());
        System.out.println("-- Insert password of chatroom");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/getChatroomMessages"))
                    .header("Authorization", TokenServices.getToken())
                    .header("Content-Type", "application/json")
                    .body(jsonObject)
                    .asJson();

            boolean status = httpResponse.isSuccess();
            if (status) {
                JSONObject msg = httpResponse.getBody().getObject();

                JSONArray arr = msg.getJSONArray("chatroomMessages");

                for (int i = 0; i < arr.length(); i++) {
                    String timestamp = arr.getJSONObject(i).getString("timestamp");
                    String username = arr.getJSONObject(i).getString("username");
                    String message = arr.getJSONObject(i).getString("message");

                    System.out.println("Date: " + timestamp + " Sender: " + username + " Message: " + message);
                }

            } else {
                System.out.println("Unsuccessful request!");
            }


        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
