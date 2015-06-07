package com.vk.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Денис on 19.05.2015. Ave furby!
 */
public class APIRequest {

    private String domain;
    private String method;
    private HashMap<String,String> params;

    public APIRequest() {
        params = new HashMap<String, String>();
    }

    public String fire() {
        try {
            URLConnection connection = new URL(this.getURl()).openConnection();
            InputStream response = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(response));
            String line = "";
            String out = "";
            while ((line = br.readLine()) != null) {
                out += line;
            }
            return out;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String getURl() {
        StringBuilder sb = new StringBuilder();
        sb.append(domain).append(method).append("?");
        boolean first = true;
        for (Map.Entry<String, String> cursor : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            sb.append(cursor.getKey()).append("=").append(cursor.getValue());
        }
        return sb.toString();
    }

    public void addParam(String name, String value) {
        params.put(name, value);
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
}
