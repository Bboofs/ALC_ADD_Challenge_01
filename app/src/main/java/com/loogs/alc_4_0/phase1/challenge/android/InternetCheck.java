package com.loogs.alc_4_0.phase1.challenge.android;

import android.os.AsyncTask;

import androidx.core.util.Consumer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class InternetCheck extends AsyncTask <Void, Void, Boolean> {
    private Consumer mConsumer;
    public interface Consumer {
        void accept(boolean internet);
    }

    public InternetCheck(Consumer consumer) {
        mConsumer = consumer;
        execute();
    }

    // ICMP
    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    protected Boolean doInBackground(Void... voids){
        try {
            Socket sock = new Socket();
            // TCP/HTTP/DNS (depending on the port, 53=DNS, 80=HTTP, etc.)
            sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
            sock.close();
            return  true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected  void onPostExecute(Boolean internet) {
        mConsumer.accept(internet);
    }
}
