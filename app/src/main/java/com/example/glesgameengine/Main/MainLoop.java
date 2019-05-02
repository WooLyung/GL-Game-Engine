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
                Thread.sleep(50);
                Game.engine.update();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
