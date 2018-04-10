package com.company.recaptcha;




import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VerifyUtils {

    public static final String SITE_VERIFY_URL =
                "https://www.google.com/recaptcha/api/siteverify";

    public static boolean verify(String gRecaptchaResponse){
        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0){
            return false;
        }

        try {

            URL verifyUrl = new URL(SITE_VERIFY_URL);

            // ������� ���������� (Connection) � URL ����.
            HttpURLConnection conn = (HttpsURLConnection)verifyUrl.openConnection();

            // �������� ���������� Header � Request, ����� ����������� �������� � server.
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User_Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            // ������ ����� ���������� �� Server.
            String postParams = "secret="+MyConstants.SECRET_KEY
                    + "&response="+gRecaptchaResponse;

            // Send Request
            conn.setDoOutput(true);

            // �������� Output Stream (�������� �����) ���������� � Server.
            // �������� ������ � Output Stream, ������ ��������� ���������� �� Server.
            OutputStream outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes());

            outStream.flush();
            outStream.close();

            // �������� ��� ���������� �� Server.
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode="+responseCode);

            // �������� Input Stream (������� �����) Connection
            // ����� ��������� ������ ������������ �� Server.
            InputStream is = conn.getInputStream();

            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            // ==> {"success": true}
            System.out.println("Response: "+jsonObject);

            boolean success = jsonObject.getBoolean("success");
            return success;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
