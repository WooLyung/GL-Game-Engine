package com.example.glesgameengine.Main;

public class MainLoop implements Runnable
{
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(50); // 최대 프레임 제한
                Game.engine.update(); // 엔진을 업데이트
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
