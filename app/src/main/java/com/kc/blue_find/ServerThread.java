package com.kc.blue_find;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class ServerThread extends Thread {
    public BluetoothAdapter bluetoothAdapter;
    public Context context;
    private BluetoothServerSocket mservierSocket;
    private BluetoothSocket socket;
    private InputStream inputStream;

    public ServerThread(BluetoothAdapter bluetoothAdapter, Context context) {
        this.bluetoothAdapter = bluetoothAdapter;
        this.context = context;
    }


    @Override
    public void run() {
        try {
            mservierSocket = bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord("btspp", UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            socket = mservierSocket.accept();

            byte[] buffer = new byte[1024];
            int bytes;
            inputStream = socket.getInputStream();
            while (true){
                if ((bytes = inputStream.read(buffer)) > 0) {
                    byte[] buf_data = new byte[bytes];
                    for (int i = 0; i < bytes; i++) {
                        buf_data[i] = buffer[i];
                    }
                    String s = new String(buf_data);
                    Log.i("server",s + "server is in");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}