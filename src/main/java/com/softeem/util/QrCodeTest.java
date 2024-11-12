package com.softeem.util;

public class QrCodeTest {
    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        String text = "http://www.baidu.com";
        // 嵌入二维码的图片路径
        String imgPath = "d:/icon1.png";
        // 生成的二维码的路径及名称
        String destPath = "d:/guagua/2.png";
        String no="d:/test/1.png";
        //生成带logo二维码
        QRCodeUtil.encode(text, imgPath, destPath, true);
        //生成不带logo的二维码
        //QRCodeUtil.encode(text, no);
        // 解析二维
       
        // 打印出解析出的内容
      
        
        String a="http://download.file.boxbeartv.cn/edition/programmer/android/channel-1/app.apk?t=1592019780";
        
        int uid=1;
        int gid=2;
        int bizChannel;
        
        String param="&uid="+uid+"&"+"gid="+gid+"bizChannel=-1";
        String http=a+param;
        System.out.println(http);
        
        QRCodeUtil.encode(http, imgPath, destPath, true);
        String str = QRCodeUtil.decode(destPath);
        
        System.out.println(str);
        
        
        
        

    }
}
