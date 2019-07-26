package com.lrh;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class CoverImgToBase64Data {

    public static void main(String[] args) throws IOException {
        String path = "/Users/lrh/Desktop/t.png";
        String txtPath = "/Users/lrh/Desktop/img2.txt";
        GenerateImage(txtPath, path);

    }

    public static void imgaeToString( String path ) throws IOException{
        File file = new File(path);
        File[] files = file.listFiles();
        BASE64Encoder encoder = new BASE64Encoder();

        for (File tmpFile : files) {
            String name = tmpFile.getName();
            if(name.indexOf(".png") > -1){
                FileInputStream fileInputStream = new FileInputStream(tmpFile);
                byte[] data  = new byte[fileInputStream.available()];
                fileInputStream.read(data);
                fileInputStream.close();
                String base64data = encoder.encode(data);
                System.out.println(base64data);
            }
        }
    }

    public static boolean GenerateImage(String txtPath ,String savepath) throws IOException {
        File file = new File(txtPath);
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer stringBuffer = new StringBuffer();
        String line = bufferedReader.readLine();
        if(line != null){
            stringBuffer.append(line);
        }
        while (line != null){
            line = bufferedReader.readLine();
            stringBuffer.append(line);
        }

        System.out.println(stringBuffer.toString());
        String base64str = stringBuffer.toString();
      //对字节数组字符串进行Base64解码并生成图片
        if (base64str == null) //图像数据为空
            return false;
        // System.out.println("开始解码");
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(base64str);
            //  System.out.println("解码完成");
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            // System.out.println("开始生成图片");
            //生成jpeg图片
            OutputStream out = new FileOutputStream(savepath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }
}
