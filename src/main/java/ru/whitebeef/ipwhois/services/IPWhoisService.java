package ru.whitebeef.ipwhois.services;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

@Service
public class IPWhoisService {
    private final static String apiLink = "http://ipwho.is/%s";

    public JSONObject getResponse(String ipAddressString) {
        try {
            return getJson(new URL(String.format(apiLink, ipAddressString)));
        } catch (IOException exception) {
            return null;
        }
    }

    public JSONObject getJson(URL url) throws IOException {
        String json = IOUtils.toString(url, Charset.forName("UTF-8"));
        return new JSONObject(json);
    }

    private byte[] textToNumericFormatV4(String src) {
        byte[] res = new byte[4];

        long tmpValue = 0;
        int currByte = 0;
        boolean newOctet = true;

        int len = src.length();
        if (len == 0 || len > 15) {
            return null;
        }

        for (int i = 0; i < len; i++) {
            char c = src.charAt(i);
            if (c == '.') {
                if (newOctet || tmpValue < 0 || tmpValue > 0xff || currByte == 3) {
                    return null;
                }
                res[currByte++] = (byte) (tmpValue & 0xff);
                tmpValue = 0;
                newOctet = true;
            } else {
                int digit = digit(c, 10);
                if (digit < 0) {
                    return null;
                }
                tmpValue *= 10;
                tmpValue += digit;
                newOctet = false;
            }
        }
        if (newOctet || tmpValue < 0 || tmpValue >= (1L << ((4 - currByte) * 8))) {
            return null;
        }
        res[currByte] = (byte) ((tmpValue >> (8 * (3 - currByte))) & 0xff);
        return res;
    }

    public boolean isIPv4LiteralAddress(String src) {
        return textToNumericFormatV4(src) != null;
    }

    private int digit(char ch, int radix) {
        return Character.digit(ch, radix);
    }

}
