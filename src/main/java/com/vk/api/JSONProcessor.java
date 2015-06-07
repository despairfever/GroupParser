package com.vk.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.api.domain.WallPost;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Денис on 19.05.2015. Ave furby!
 */

public class JSONProcessor {

    public enum AttachmentType {PHOTO, VIDEO, LINK, OTHER}

    public ArrayList<WallPost> getPosts(APIRequest request, int count) throws IOException {
        ArrayList<WallPost> ar = new ArrayList<WallPost>();
        ObjectMapper om = new ObjectMapper();
        om.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        om.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        URL url = new URL(request.getURl());
        JsonNode items = om.readTree(url).path("response").path("items");

        for (int i=0; i<count; i++) {
            WallPost wp = new WallPost();
            JsonNode current = items.get(i);
            wp.setDate(current.get("date").toString());
            wp.setText(current.get("text").textValue());
            if (current.has("attachments")) {
                JsonNode attached = current.path("attachments");
                wp.setAttached(this.parseAttachments(attached));
            }
            ar.add(wp);
        }

        return ar;
    }

    private ArrayList<String> parseAttachments(JsonNode current) {
        ArrayList<String> ar = new ArrayList<String>();

        int j = 0;
        while (current.has(j)) {
            String _type = current.get(j).get("type").textValue();
            AttachmentType type = this.getType(_type);
            switch (type) {
                case PHOTO:
                    JsonNode photo = current.get(j).path("photo");
                    if (photo.has("photo_2560")) {
                        ar.add(photo.get("photo_2560").textValue());
                        break;
                    }
                    if (photo.has("photo_1280")) {
                        ar.add(photo.get("photo_1280").textValue());
                        break;
                    }
                    if (photo.has("photo_807")) {
                        ar.add(photo.get("photo_807").textValue());
                        break;
                    }
                    if (photo.has("photo_604")) {
                        ar.add(photo.get("photo_604").textValue());
                        break;
                    }
                    if (photo.has("photo_130")) {
                        ar.add(photo.get("photo_130").textValue());
                        break;
                    }
                    if (photo.has("photo_75")) {
                        ar.add(photo.get("photo_75").textValue());
                        break;
                    }
                    break;
                case VIDEO:
                    StringBuilder sb = new StringBuilder();
                    sb.append("http://vk.com/video");
                    sb.append(current.get(j).path("video").get("id").toString());
                    sb.append("_");
                    sb.append(current.get(j).path("video").get("owner_id").toString());
                    ar.add(sb.toString());
                    break;
                case LINK:
                    ar.add(current.get(j).path("link").get("url").textValue());
                    break;
                case OTHER:
                    break;
            }
            j++;
        }

        return ar;
    }

    private AttachmentType getType(String _type) {
        if (_type.equals("photo")) {return AttachmentType.PHOTO;}
        if (_type.equals("video")) {return AttachmentType.VIDEO;}
        if (_type.equals("link")) {return AttachmentType.LINK;}
        return AttachmentType.OTHER;
    }

}
