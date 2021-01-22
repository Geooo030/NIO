package com.github.geooo030.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * @author zhaoqi.wang
 * @date 2021/1/6
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9001);
        // 向服务端发送数据
        socket.getOutputStream().write("hello Server".getBytes());
        socket.getOutputStream().flush();
        System.out.println("给服务端发送信息成功！");
        byte[] bytes = new byte[1024];
        int read = socket.getInputStream().read(bytes);
        System.out.println("接收服务端的信息：" + new String(bytes, 0, read));

    }
}
