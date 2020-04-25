package lab3;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Pattern;

@ServerEndpoint(value = "/chat/{username}", decoders = JsonDecoder.class, encoders = JsonEncoder.class)
public class ChatServerEndpoint {
    private static final Set<ChatServerEndpoint> endpoints = new CopyOnWriteArraySet<>();
    private static final HashMap<String, String> users = new HashMap<>();
    //private static final HashMap<String, Session> sessions = new HashMap<>();
    //private static final Pattern userMentionsPattern = Pattern.compile("\\b@\\w+\\b");

    private Session session;

    @OnOpen
    public void connectUser(Session session, @PathParam("username") String username) {
        this.session = session;

        endpoints.add(this);
        users.put(session.getId(), username);

        var msg = new ChatMessage();
        msg.setSender(username);
        msg.setContent("<joined the chat>");

        broadcast(msg);
    }

    @OnMessage
    public void sendMessage(Session session, ChatMessage msg) {
        msg.setSender(users.get(session.getId()));

        broadcast(msg);
    }

    @OnClose
    public void closeSession(Session session) {
        endpoints.remove(this);

        var msg = new ChatMessage();
        msg.setSender(users.get(session.getId()));
        msg.setContent("<left the chat>");

        broadcast(msg);
    }

    @OnError
    public void handleError(Session session, Throwable throwable) {
    }

    private static void broadcast(ChatMessage msg) {
        //var matcher = userMentionsPattern.matcher(msg.getContent());
        //while (matcher.find()) {
        //    var session = // get session
        //}
        endpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote().sendObject(msg);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}