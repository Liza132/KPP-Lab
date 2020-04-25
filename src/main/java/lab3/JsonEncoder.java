package lab3;

import com.google.gson.Gson;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class JsonEncoder implements Encoder.Text<ChatMessage> {
    private static Gson gson = new Gson();

    @Override
    public String encode(ChatMessage chatMessage) {
        return gson.toJson(chatMessage);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
