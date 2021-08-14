package splash.com.runner;

import org.json.JSONObject;
import splash.com.db.DbConnection;
import splash.com.db.DbHandler;
import splash.com.db.SmsModel;
import splash.com.modal.ResponseJson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.util.*;

import static splash.com.runner.Application.getInstance;

public class SmsSenderThread implements  Runnable{


    @Override
    public void run() {
       DbConnection connection= getInstance().dbConnection;
       DbHandler handler =new DbHandler(connection);
       List<SmsModel> list=new ArrayList<>();
       Map<String,String> map =new TreeMap<>();
        try {
            System.out.println("check");
           list=handler.fetchUnsendSms();

           if(!list.isEmpty()){
                for (SmsModel sms:list
                     ) {
                    String phoneno=sms.getPhoneno();
                   phoneno= phoneno.replace("+","");
                    phoneno= phoneno.replace("-","");
                    phoneno= phoneno.replace(" ","");
                    if(phoneno.substring(0,1).equals("92")){
                      phoneno=  phoneno.replaceFirst("92","0");
                    }
                    map.put("hash",Constants.ApiKey);
                    map.put("receivenum",sms.getPhoneno());
                    map.put("sendernum",Constants.Phone);
                    map.put("textmessage",sms.getMsg());
                    System.out.println(sms);

                    URL url= new URL(getParamsString(map)) ;
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                    con.setDoOutput(true);
//                    DataOutputStream out = new DataOutputStream(con.getOutputStream());
//                    out.writeBytes(getParamsString(map));
//                    out.flush();
//                    out.close();


                    con.connect();

                    int status = con.getResponseCode();

                    String respone="";
                    switch (status) {
                        case 200:
                        case 201:
                            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            StringBuilder sb = new StringBuilder();
                            String line;
                            while ((line = br.readLine()) != null) {
                                sb.append(line+"\n");
                            }
                            br.close();
                            respone=sb.toString();
                            System.out.println(sb.toString());
                    }

                    JSONObject jsonObject=new JSONObject(respone);
                    String statusresponse= jsonObject.getString("status");
                    System.out.println(statusresponse);
                    if(statusresponse.equals("ACCEPTED")){
                        handler.updateSmsSender(sms.getId(),"Y",respone);
                    }else {
                        handler.updateSmsSender(sms.getId(),"E",respone);
                    }


                }

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

        public  String getText(String url) throws Exception {
            URL website = new URL(url);
//            URLConnection connection = website.openConnection();
            HttpURLConnection connection = (HttpURLConnection) website.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));

            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);

            in.close();

            return response.toString();
        }
    public  String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        result.append(Constants.VeevoUrl);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        System.out.println(resultString);
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
