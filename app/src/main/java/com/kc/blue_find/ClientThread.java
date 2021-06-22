package com.kc.blue_find;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 【类功能】
 *
 * @author pengli
 * @version 2021/6/22
 */
public class ClientThread extends Thread{

    private final BluetoothDevice device;
    private final Context context;
    private BluetoothSocket socket;

    public ClientThread(BluetoothDevice device, Context context) {
        this.device = device;
        this.context = context;
    }

    @Override
    public void run() {

        try {
            socket = device.createInsecureRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));

            try {
                socket.connect();
            }catch (IOException e){
                socket.close();
            }
            if (socket == null) {
                Toast.makeText(context,"连接失败",Toast.LENGTH_SHORT).show();
                return;
            }
            while (true) {
                try {
                    String msg = "hello world";
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(msg.getBytes());
                }catch (IOException e){



                }

            }
        } catch (IOException e) {
            
            e.printStackTrace();
        }

    }
}
