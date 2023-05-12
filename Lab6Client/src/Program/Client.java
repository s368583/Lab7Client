package Program;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;


public class Client {

    private String serverHostname; //данные сервера, куда подключаться
    private int serverPort;

    private DatagramChannel clientChannel; //отправка данных
    private SocketAddress serverAddress;

    private byte[] bytes;
    private ByteBuffer buffer;

    public Client() throws IOException {
        clientChannel = DatagramChannel.open();  //канал для передачи данных в обе стороны
        clientChannel.configureBlocking( false );  //перевод в неблокирующий режим
        bytes = new byte[16384];
        buffer = ByteBuffer.wrap(bytes); //заполняется буффер
    }

    public void connect(String hostname, int port) throws IOException {
        this.serverHostname = hostname;
        this.serverPort = port;
        try {
            serverAddress = new InetSocketAddress(serverHostname, serverPort); //адресс сервера
            System.out.println("Выполнено подключение к " + getServerAddress());
            Lab5.commandReader.start();
        }
        catch(Exception e) {
            System.out.println("Сервер недоступен");
        }
    }


    public void send(String message) throws IOException {
        bytes = new byte[16384];
        buffer = ByteBuffer.wrap(message.getBytes());
        clientChannel.send(buffer, serverAddress);
        buffer = ByteBuffer.wrap(bytes);
        buffer.clear();
    }

    public String receive() throws IOException {
        for( int i = 0; i < 50; i++) {
            serverAddress = clientChannel.receive(buffer); //получам данные с сервера
            if(serverAddress!=null) break;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new IOException(e);
            }
        }

        if(serverAddress == null) {
            System.out.println("Потеряна связь с сервером");
            throw new IOException("А связи то нет!!!");
        }

        buffer.flip(); //буфер в режиме чтения, переходим в еачало данных, а не то, где остановились в прошлый раз
        bytes = new byte[buffer.limit()];
        buffer.get(bytes, 0, buffer.limit());
        buffer.clear();
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public int getServerPort() {
        return serverPort;
    }
    public String getServerHostname() {
        return serverHostname;
    }
    public DatagramChannel getClientChannel() {
        return clientChannel;
    }
    public SocketAddress getServerAddress() {
        return serverAddress;
    }
}