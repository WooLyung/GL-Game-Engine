package com.example.glesgameengine.GameIO.Input;

import com.example.glesgameengine.GraphicSystem.GL.GLView;
import com.example.glesgameengine.Main.Game;
import com.example.glesgameengine.Types.Vector;

public class Input {

    public static boolean isDown = false;
    public static TOUCH_STATE touchState = TOUCH_STATE.NONE;
    public static Vector touchPos = new Vector();

    public enum TOUCH_STATE {
        UP,
        DOWN,
        STAY,
        NONE
    };

    public static void update() {
        if (Input.touchState == Input.TOUCH_STATE.NONE) {
            if (Input.isDown == true) {
                Input.touchState = Input.TOUCH_STATE.DOWN;
            }
        }
        else if (Input.touchState == Input.TOUCH_STATE.DOWN) {
            if (Input.isDown == true) {
                Input.touchState = Input.TOUCH_STATE.STAY;
            }
            else {
                Input.touchState = Input.TOUCH_STATE.UP;
            }
        }
        else if (Input.touchState == Input.TOUCH_STATE.UP) {
            if (Input.isDown == true) {
                Input.touchState = Input.TOUCH_STATE.DOWN;
            }
            else {
                Input.touchState = Input.TOUCH_STATE.NONE;
            }
        }
        else if (Input.touchState == Input.TOUCH_STATE.STAY) {
            if (Input.isDown == false) {
                Input.touchState = Input.TOUCH_STATE.UP;
            }
        }
    }

    public static TOUCH_STATE getTouchState() {
        return touchState;
    }

    public static Vector getTouchWorldPos() {
        Vector pos = new Vector();
        pos.x = 2 * (touchPos.x - Game.screenWidth / 2) * (float)GLView.nowWidth / Game.screenWidth + Game.engine.nowScene.camera.position.x;
        pos.y = 2 * (Game.screenHeight - touchPos.y - Game.screenHeight / 2) * (float)GLView.nowHeight / Game.screenHeight + Game.engine.nowScene.camera.position.y;

        return pos;
    }

    public static Vector getTouchUIPos() {
        Vector pos = new Vector();
        pos.x = 2 * (touchPos.x - Game.screenWidth / 2) * (float)GLView.defaultWidth / Game.screenWidth;
        pos.y = 2 * (Game.screenHeight - touchPos.y - Game.screenHeight / 2) * (float)GLView.defaultHeight / Game.screenHeight;

        return pos;
    }
}
