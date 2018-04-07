package com.sunshine.publicserver.service.markup.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class HttpClientUtil
{

    public HttpClientUtil()
    {
        readTimeout = Integer.valueOf(35000);
        connectTimeout = Integer.valueOf(5000);
    }


    public String doPost(String urlArr, String param)
    {
        HttpURLConnection conn;
        BufferedReader reader;
        DataOutputStream out;
        StringBuffer content;
        int code;
        conn = null;
        reader = null;
        out = null;
        content = new StringBuffer();
        code = 0;
        try
        {
            URL url = new URL(urlArr);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "binary/octet-stream");
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(getConnectTimeout().intValue());
            conn.setReadTimeout(getReadTimeout().intValue());
            conn.connect();
            out = new DataOutputStream(conn.getOutputStream());
            if(param != null)
                out.write(param.getBytes("utf-8"));
            code = conn.getResponseCode();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            if(out != null)
            {
                out.flush();
                out.close();
            }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        Exception exception;
        try
        {
            if(out != null)
            {
                out.flush();
                out.close();
            }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        try
        {
            if(out != null)
            {
                out.flush();
                out.close();
            }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        InputStream in = null;
        try
        {
            in = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in, "utf-8"), 5242880);
            for(String line = null; (line = reader.readLine()) != null;)
                content.append(line);

        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        try
        {
            if(conn != null)
                conn.disconnect();
            if(in != null)
                in.close();
            if(reader != null)
                reader.close();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        Exception exception1;
        try
        {
            if(conn != null)
                conn.disconnect();
            if(in != null)
                in.close();
            if(reader != null)
                reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            if(conn != null)
                conn.disconnect();
            if(in != null)
                in.close();
            if(reader != null)
                reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        String str = content.toString();
        content.setLength(0);
        return str;
    }

    public String doPost(String urlArr, String param, String coding)
    {
        HttpURLConnection conn;
        BufferedReader reader;
        DataOutputStream out;
        StringBuffer content;
        int code;
        conn = null;
        reader = null;
        out = null;
        content = new StringBuffer();
        code = 0;
        try
        {
            URL url = new URL(urlArr);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "binary/octet-stream");
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(getConnectTimeout().intValue());
            conn.setReadTimeout(getReadTimeout().intValue());
            conn.connect();
            out = new DataOutputStream(conn.getOutputStream());
            if(param != null)
                out.write(param.getBytes(coding));
            code = conn.getResponseCode();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        try
        {
            if(out != null)
            {
                out.flush();
                out.close();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Exception exception;
        try
        {
            if(out != null)
            {
                out.flush();
                out.close();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            if(out != null)
            {
                out.flush();
                out.close();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        InputStream in = null;
        try
        {
            in = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in, coding));
            for(String line = null; (line = reader.readLine()) != null;)
                content.append(line);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            if(conn != null)
                conn.disconnect();
            if(in != null)
                in.close();
            if(reader != null)
                reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Exception exception1;
        try
        {
            if(conn != null)
                conn.disconnect();
            if(in != null)
                in.close();
            if(reader != null)
                reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            if(conn != null)
                conn.disconnect();
            if(in != null)
                in.close();
            if(reader != null)
                reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        String str = content.toString();
        content.setLength(0);
        return str;
    }

    public String doPost(String urlArr, String param, Map propertyMap)
    {
        HttpURLConnection conn;
        BufferedReader reader;
        DataOutputStream out;
        StringBuffer content;
        int code;
        conn = null;
        reader = null;
        out = null;
        content = new StringBuffer();
        code = 0;
        try
        {
            URL url = new URL(urlArr);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(getConnectTimeout().intValue());
            conn.setReadTimeout(getReadTimeout().intValue());
            if(propertyMap != null)
            {
                String key;
                String value;
                for(Iterator iterator = propertyMap.entrySet().iterator(); iterator.hasNext(); conn.setRequestProperty(key, value))
                {
                    Map.Entry entry = (Map.Entry)iterator.next();
                    key = (String)entry.getKey();
                    value = (String)entry.getValue();
                }

            }
            conn.setRequestMethod("POST");
            conn.connect();
            out = new DataOutputStream(conn.getOutputStream());
            if(param != null)
                out.write(param.getBytes("utf-8"));
            code = conn.getResponseCode();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            if(out != null)
            {
                out.flush();
                out.close();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Exception exception1;
        try
        {
            if(out != null)
            {
                out.flush();
                out.close();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            if(out != null)
            {
                out.flush();
                out.close();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        InputStream in = null;
        try
        {
            in = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            for(String line = null; (line = reader.readLine()) != null;)
                content.append(line);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            if(conn != null)
                conn.disconnect();
            if(in != null)
                in.close();
            if(reader != null)
                reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Exception exception;
        try
        {
            if(conn != null)
                conn.disconnect();
            if(in != null)
                in.close();
            if(reader != null)
                reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            if(conn != null)
                conn.disconnect();
            if(in != null)
                in.close();
            if(reader != null)
                reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        String str = content.toString();
        content.setLength(0);
        return str;
    }

    public String doPostByForm(String urlArr, String param)
    {
        HttpURLConnection conn;
        BufferedReader reader;
        DataOutputStream out;
        StringBuffer content;
        int code;
        conn = null;
        reader = null;
        out = null;
        content = new StringBuffer();
        code = 0;
        try
        {
            URL url = new URL(urlArr);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(getConnectTimeout().intValue());
            conn.setReadTimeout(getReadTimeout().intValue());
            conn.connect();
            out = new DataOutputStream(conn.getOutputStream());
            if(param != null)
                out.write(param.getBytes("utf-8"));
            code = conn.getResponseCode();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        try
        {
            if(out != null)
            {
                out.flush();
                out.close();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Exception exception;
        try
        {
            if(out != null)
            {
                out.flush();
                out.close();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            if(out != null)
            {
                out.flush();
                out.close();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        InputStream in = null;
        try
        {
            in = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in, "utf-8"), 5242880);
            for(String line = null; (line = reader.readLine()) != null;)
                content.append(line);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            if(conn != null)
                conn.disconnect();
            if(in != null)
                in.close();
            if(reader != null)
                reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Exception exception1;
        try
        {
            if(conn != null)
                conn.disconnect();
            if(in != null)
                in.close();
            if(reader != null)
                reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            if(conn != null)
                conn.disconnect();
            if(in != null)
                in.close();
            if(reader != null)
                reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        String str = content.toString();
        content.setLength(0);
        return str;
    }

    public Integer getReadTimeout()
    {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout)
    {
        this.readTimeout = readTimeout;
    }

    public Integer getConnectTimeout()
    {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout)
    {
        this.connectTimeout = connectTimeout;
    }

    private static final boolean LOGTAG = false;
    private Integer readTimeout;
    private Integer connectTimeout;

}