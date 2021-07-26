package com.company.service.chatroom;

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

public class ChatroomSrvImpl implements ChatroomSrv {

    GetHost host = new GetHostImpl();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void addChatroom() {

        JSONObject jsonObject = new JSONObject();
        System.out.println(" Insert name of chatroom");
        jsonObject.put("name", scanner.nextLine());
        System.out.println(" Insert password for chatroom");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/chatrooms"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", TokenServices.getToken())
                    .body(jsonObject)
                    .asJson();
            boolean addCht = httpResponse.isSuccess();
            if (addCht) {
                System.out.println("Chatroom was created!");
            } else {
                System.out.println("Chatroom was not created!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getChatrooms() {
        try {
            HttpResponse<JsonNode> httpResponse = Unirest.get(host.getHost().concat("/getAllChatrooms"))
                    .header("Authorization", TokenServices.getToken())
                    .asJson();

            boolean getChatroom = httpResponse.isSuccess();
            if (getChatroom) {
                JSONObject chatroom = httpResponse.getBody().getObject();
                JSONArray arr = chatroom.getJSONArray("chatrooms");

                for (int i = 0; i < arr.length(); i++) {
                    String chatroomName = arr.getString(i);
                    System.out.println(" Chatroom name is: " + chatroomName);
                }
            } else {
                System.out.println(" Unsuccessful Request!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteChatroom() {
        JSONObject jsonObject = new JSONObject();
        System.out.println(" Insert name of chatroom ");
        jsonObject.put("chatroomName", scanner.nextLine());
        System.out.println(" Insert password of chatroom");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.delete(host.getHost().concat("/deleteChatrooms"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", TokenServices.getToken())
                    .body(jsonObject)
                    .asJson();
            boolean deleteChatroom = httpResponse.isSuccess();
            if (deleteChatroom) {
                System.out.println("Chatroom has been deleted!");
            } else {
                System.out.println("Chatroom has not been deleted!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateChatroomName() {
        JSONObject jsonObject = new JSONObject();
        System.out.println(" Insert name of chatroom");
        jsonObject.put("chatroomName", scanner.nextLine());
        System.out.println(" Insert new chatroom name");
        jsonObject.put("newChatroomName", scanner.nextLine());
        System.out.println(" Insert password of chatroom");
        jsonObject.put("password", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/updateChatroomName"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", TokenServices.getToken())
                    .body(jsonObject)
                    .asJson();
            boolean newChatroomName = httpResponse.isSuccess();
            if (newChatroomName) {
                System.out.println("Name of chatroom has been changed!");
            } else {
                System.out.println("Chatroom Not Renamed!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAdmin() {
        JSONObject jsonObject = new JSONObject();
        System.out.println(" Insert name of chatroom");
        jsonObject.put("chatroomName", scanner.nextLine());
        System.out.println(" Insert password of chatroom");
        jsonObject.put("password", scanner.nextLine());
        System.out.println(" Insert name of Admin ");
        jsonObject.put("adminName", scanner.nextLine());

        try {
            HttpResponse<JsonNode> httpResponse = Unirest.post(host.getHost().concat("/addAdmin"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", TokenServices.getToken())
                    .body(jsonObject)
                    .asJson();
            boolean addAdmin = httpResponse.isSuccess();
            if (addAdmin) {
                System.out.println("Admin was ad!");
            } else {
                System.out.println("Admin not added!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void listAdmins() {

    }
}
