package Program;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;


public class Client {

    private String serverHostname; //данные сервера, куда подключаться
    private int serverPort;

    private DatagramChannel clientChannel; //отправка данных
    private SocketAddress serverAddress;

    private byte[] bytes;
    private ByteBuffer buffer; //это поток!!

    public Client() throws IOException {
        clientChannel = DatagramChannel.open();  //канал для передачи данных в обе стороны
        bytes = new byte[16384];
        buffer = ByteBuffer.wrap(bytes); //создается буфер (nio)
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

    public void disconnect() throws IOException {
        clientChannel.close();
        System.out.println("Выполнено отключение от сервера");
    }

    public void send(String message) throws IOException {
        bytes = new byte[16384];
        buffer = ByteBuffer.wrap(message.getBytes()); //заполнение буффера
        clientChannel.send(buffer, serverAddress);
        buffer = ByteBuffer.wrap(bytes);
        buffer.clear();
    }

    public String receive() throws IOException {
        serverAddress = clientChannel.receive(buffer); //получам данные с сервера
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