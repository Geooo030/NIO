package com.github.geooo030.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 普通SocketIO模型（服务端单线程去接收客户端的请求）
 * @author zhaoqi.wang
 * @date 2021/1/6
 */
public class SocketServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(9001);
        while(true) {
            System.out.println("服务端端口:" + serverSocket.getLocalPort() +"等待连接");
            // 阻塞等待连接
            final Socket socket = serverSocket.accept();
            System.out.println("收到客户端连接");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        handler(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            Thread.sleep(50);
        }
    }

    public static void handler(Socket socket) throws IOException {
        System.out.println("客户端连接端口:" + socket.getPort() + ", 处理线程ID:" + Thread.currentThread().getId());
        byte[] bytes = new byte[1024];

        System.out.println("准备read...");
        // 接收客户端的数据，阻塞方法，没有数据可读时就阻塞
        int read = socket.getInputStream().read(bytes);
        System.out.println("read客户端数据完成...");
        if (read != -1) {
            System.out.println("接收客户端的数据: " + new String(bytes, 0, read));
            System.out.println("thread id = " + Thread.currentThread().getId());
            System.out.println(" ");
        }
        socket.getOutputStream().write("Hello Client!".getBytes());
        socket.getOutputStream().flush();
    }
}
