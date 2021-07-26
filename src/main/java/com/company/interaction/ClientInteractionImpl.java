package com.company.interaction;

import com.company.exceptions.InvalidClientInteractionException;
import com.company.service.chatroom.ChatroomSrv;
import com.company.service.chatroom.ChatroomSrvImpl;
import com.company.service.messages.MessageSrv;
import com.company.service.messages.MessagesSrvImpl;

import java.util.Scanner;

import static com.company.util.constants.ChatroomAPI.*;

public class ClientInteractionImpl implements ClientInteractionService {

    private final Scanner scanner = new Scanner(System.in);
    ChatroomSrv chatroomService = new ChatroomSrvImpl();
    MessageSrv messageService = new MessagesSrvImpl();

    @Override
    public void initInteraction() {

        switch (choseChatroomsAction()) {
            case ADD_CHATROOM:
                chatroomService.addChatroom();
                break;

            case LIST_CHATROOMS:
                chatroomService.getChatrooms();
                break;

            case DELETE_CHATROOM:
                chatroomService.deleteChatroom();
                break;

            case UPDATE_CHATROOM_NAME:
                chatroomService.updateChatroomName();
                break;

            case VIEW_ALL_MESSAGES:
                messageService.showChatroomMessages();
                break;

            case ADD_MESSAGE:
                messageService.addChatroomMessage();
                break;

            case ADD_ADMIN:
                chatroomService.addAdmin();
                break;

            case LIST_ADMINS:
                chatroomService.listAdmins();
                break;

            case LOGOUT:
                ClientRegisterLoginImpl registerLogin = new ClientRegisterLoginImpl();
                registerLogin.initInteraction();
                break;
        }

        initInteraction();
    }

    private Integer choseChatroomsAction() {

        System.out.println(" CHATROOM MANAGEMENT ");
        System.out.println("Add chatroom - press " + ADD_CHATROOM);
        System.out.println("List chatroom - press " + LIST_CHATROOMS);
        System.out.println("Delete chatroom - press " + DELETE_CHATROOM);
        System.out.println("Update chatroom name - press " + UPDATE_CHATROOM_NAME);
        System.out.println("------------------------------------");
        System.out.println(" MESSAGE MANAGEMENT ");
        System.out.println("Add chatroom message - press " + ADD_MESSAGE);
        System.out.println("Show chatroom messages - press " + VIEW_ALL_MESSAGES);
        System.out.println("------------------------------------");
        System.out.println(" USER ");
        System.out.println("Add admin - press " + ADD_ADMIN);
        System.out.println("List admins - press " + LIST_ADMINS);
        System.out.println("------------------------------------");
        System.out.println(" LOGOUT ");
        System.out.println("For logout - press " + LOGOUT);
        System.out.println("------------------------------------");

        try {
            int action = Integer.parseInt(scanner.nextLine());
            if (action != ADD_CHATROOM && action != LIST_CHATROOMS && action != UPDATE_CHATROOM_NAME && action != DELETE_CHATROOM && action != ADD_MESSAGE &&
                    action != VIEW_ALL_MESSAGES && action != ADD_ADMIN && action != LIST_ADMINS &&
                    action != LOGOUT) {
                throw new InvalidClientInteractionException();
            }
            return action;
        } catch (Exception e) {
            System.out.println("Enter a valid number for your action!");
        }
        return choseChatroomsAction();
    }

}
