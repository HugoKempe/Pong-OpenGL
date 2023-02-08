package com.hugo.main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;
import org.lwjgl.glfw.GLFWVidMode;

public class Driver
{
    public boolean running = false;
    public int width = 800, height = 600;
    public long window;

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
