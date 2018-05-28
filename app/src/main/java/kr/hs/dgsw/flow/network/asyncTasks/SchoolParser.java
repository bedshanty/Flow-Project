/*
package kr.hs.dgsw.flow.network.asyncTasks;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

*/
/**
 * Created by JiHeon on 2018-03-20.
 *//*



public class SchoolParser extends AsyncTask<String, Void, String> {

    public interface SchoolResponse {
        void processFinish(String output);
    }

    public SchoolResponse delegate = null;

    private final String API_URL = "https://www.meatwatch.go.kr/biz/bm/sel/schoolListPopup.do";

    @Override
    protected String doInBackground(String... keywords) {
        */
/*String encodedKeyword = Utils.urlEncoder(keywords[0]);
        String dataForm = "pageIndex=1&bsnmNm=";
        String encodedDataForm = Utils.urlEncoder(dataForm + encodedKeyword);
        String param = "criteria=" + encodedDataForm;

        HttpURLConnection urlConn = null;
        final StringBuilder sb = new StringBuilder();
        BufferedReader br;
        try {
            final URL url = new URL(API_URL);
            urlConn = (HttpURLConnection) url.openConnection();

            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConn.setReadTimeout(10000);
            urlConn.setConnectTimeout(15000);
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);

            final String stringJson = param.toString();
            final OutputStream os = urlConn.getOutputStream();
            os.write(stringJson.getBytes("utf-8"));
            os.flush();

            switch (urlConn.getResponseCode()) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                    break;

                default:
                    Log.e("Parsing Failed", String.valueOf(urlConn.getResponseCode()));
                    //br = new BufferedReader(new InputStreamReader(urlConn.getErrorStream()));
                    return "Error";
            }

            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            return sb.toString();
        } catch (
                MalformedURLException e)

        {
            e.printStackTrace();
            return "MalformedURLException";
        } catch (
                FileNotFoundException e)

        {
            e.printStackTrace();
            return "FileNotFoundException";
        } catch (
                IOException e)

        {//인터넷 연결이 안되거나 호스트 주소가 잘못됨
            e.printStackTrace();
            return "IOException";
        } finally

        {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }*//*

        Document doc = null;

        try {
            Connection.Response response = Jsoup.connect("https://www.meatwatch.go.kr/biz/bm/sel/schoolListPopup.do")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .method(Connection.Method.POST)
                    .data("criteria", "pageIndex%3D1%26bsnmNm%3D%25EC%25A3%25BC")
                    .execute();


            doc = response.parse();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return doc.html();

    }

    @Override
    protected void onPostExecute(String output) {
        delegate.processFinish(output);
    }
}*/
