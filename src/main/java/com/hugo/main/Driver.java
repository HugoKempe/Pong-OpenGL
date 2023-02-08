package com.hugo.main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;

import com.hugo.input.KeyboardInput;
import com.hugo.input.MouseInput;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;

public class Driver
{
    public boolean running = false;
    public int width = 800, height = 600;
    public long window;

    private GLFWKeyCallback keyCallback;
    private GLFWCursorPosCallback cursorCallback;

    public void init()
    {
        this.running = true;

        if (!glfwInit())
        {
            System.err.println("GLFW initialization failed");
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        window = glfwCreateWindow(width, height, "2D Pong", NULL, NULL);

        if (window == NULL)
        {
            System.err.println("Could not create our window!");
        }

        glfwSetKeyCallback(window, keyCallback = new KeyboardInput());
        glfwSetCursorPosCallback(window, cursorCallback = new MouseInput());

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(window, 100, 100);

        glfwMakeContextCurrent(window);

        glfwShowWindow(window);
    }

    public void render()
    {
        glfwSwapBuffers(window);
    }

    public void update()
    {
        glfwPollEvents();
        if (KeyboardInput.isKeyDown(GLFW_KEY_SPACE))
        {
            System.out.println("Space");
        }
    }

    public void run()
    {
        init();
        while (running)
        {
            update();
            render();

            if (glfwWindowShouldClose(window))
            {
                running = false;
            }
        }
    }

    public static void main(String[] args)
    {
        System.out.println("2D Pong");
        Driver driver = new Driver();
        driver.run();
    }
}
