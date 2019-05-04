package com.example.glesgameengine.Main;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.example.glesgameengine.GL.GLRenderer;
import com.example.glesgameengine.GL.GLView;

public class Game extends AppCompatActivity
{
    GLView view;
    GLRenderer renderer;
    Thread thread;
    public static Engine engine;
    public static int screenWidth;
    public static int screenHeight;
    public static double screenDiagonal;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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

        Log.i("height", GLView.nowHeight + "");
        Log.i("width", GLView.nowWidth + "");

        // 엔진의 베이스를 이루는 객체를 생성
        renderer = new GLRenderer(this); // 엔진의 모든 렌더링을 담당
        view = new GLView(this, renderer); // 렌더가 된 이미지를 출력하는 뷰
        thread = new Thread(new MainLoop()); // 게임이 돌아가는 스레드
        engine = new Engine(); // 게임의 전반적인 부분을 총괄하는 엔진

        setContentView(view);
        thread.start(); // 게임 스레드 시작
    }
}