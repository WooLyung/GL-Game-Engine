package com.example.glesgameengine.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.example.glesgameengine.GL.GLRenderer;
import com.example.glesgameengine.GL.GLView;
import com.example.glesgameengine.SocketIO.SocketIOBuilder;

import java.net.URISyntaxException;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Game extends AppCompatActivity
{
    Socket socket;
    GLView view;
    GLRenderer renderer;
    Thread thread;
    String TAG = "MainActivity";
    public static Engine engine;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState); // 조명 항상켜기
        try { // socketio로 서버 연결
            socket = new SocketIOBuilder("http://omok-server.run.goorm.io").getSocket();
        } catch (URISyntaxException e) { // 서버 주소 문법 오류시
            e.printStackTrace();
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        renderer = new GLRenderer(this);
        view = new GLView(this, renderer);
        thread = new Thread(new MainLoop());
        engine = new Engine();

        setContentView(view);
        thread.start();
    }

    private void startSocketCommunication() {
        try { // socketio로 서버 연결
            socket = new SocketIOBuilder("http://omok-server.run.goorm.io").getSocket();
        } catch (URISyntaxException e) { // 서버 주소 문법 오류시
            e.printStackTrace();
        }


/*        JSONObject jsonObject;
        try { // json 만드는 코드
            jsonObject = new JSONObject("{\"data\": \"message data\"}");
        } catch (JSONException e) { // json 문법 오류시
            throw new Error(e);
        }
        socket.emit("messageName", jsonObject); //이벤트네임, 데이터(JSONObject - json 포멧 데이터)*/
    }
}