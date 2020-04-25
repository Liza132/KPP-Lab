package lab3;


import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class JsonDecoder implements Decoder.Text<ChatMessage> {
    private static Schema schema;

    @Override
    public ChatMessage decode(String s) {
        System.out.println(s);

        var json = new JSONObject(s);

        // Validate JSON against the schema.
        try {
            schema.validate(json);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        // Parse JSON.
        var msg = new ChatMessage();
        msg.setContent(json.getString("content"));
        if (json.has("receiver")) {
            msg.setReceiver(json.getString("receiver"));
        }

        return msg;
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        schema = SchemaLoader.load(
                new JSONObject(
                        new JSONTokener(
                                getClass().getClassLoader().getResourceAsStream("ChatMessage.schema.json")
                        )
                )
        );
    }

    @Override
    public void destroy() {
    }
}