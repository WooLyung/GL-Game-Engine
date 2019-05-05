package com.example.glesgameengine.Main;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.example.glesgameengine.GraphicSystem.GLRenderer;
import com.example.glesgameengine.GraphicSystem.GLView;
import com.example.glesgameengine.SocketIO.SocketIOBuilder;

import java.net.URISyntaxException;

import io.socket.client.Socket;

public class Game extends AppCompatActivity
{
    Socket socket;
    GLView view;
    GLRenderer renderer;
    Thread thread;
    String TAG = "MainActivity";

    public static long preTime;

    public static Engine engine;
    public static int screenWidth;
    public static int screenHeight;
    public static float deltaTime;
    public static double screenDiagonal;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        // 시간 초기화
        preTime = System.currentTimeMillis();

        try { // socketio로 서버 연결
            socket = new SocketIOBuilder("http://omok-server.run.goorm.io").getSocket();
        } catch (URISyntaxException e) { // 서버 주소 문법 오류시
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState); // 조명 항상켜기
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // 화면의 크기 값을 저장
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        screenWidth = point.x;
        screenHeight = point.y;
        screenDiagonal = Math.sqrt(point.x * point.x + point.y * point.y);
        GLView.defaultHeight = 10 * screenHeight / screenDiagonal;
        GLView.defaultWidth = 10 * screenWidth / screenDiagonal;
        GLView.nowHeight = GLView.defaultHeight;
        GLView.nowWidth = GLView.defaultWidth;

        // 엔진의 베이스를 이루는 객체를 생성
        renderer = new GLRenderer(this); // 엔진의 모든 렌더링을 담당
        view = new GLView(this, renderer); // 렌더가 된 이미지를 출력하는 뷰
        thread = new Thread(new MainLoop()); // 게임이 돌아가는 스레드
        engine = new Engine(); // 게임의 전반적인 부분을 총괄하는 엔진

        setContentView(view);
        thread.start(); // 게임 스레드 시작
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