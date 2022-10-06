package BluminEngine5;

import BluminEngine5.Rendering.Display;
import BluminEngine5.Rendering.DisplayDimension;
import BluminEngine5.Rendering.DisplayMode;
import BluminEngine5.Rendering.Master.MasterRenderer;
import BluminEngine5.Rendering.PostProcessing.PostProcessingProfileBehaviour;
import BluminEngine5.Rendering.Resolution;
import BluminEngine5.Utils.Debuging.Debug;
import BluminEngine5.Utils.EventSystem.*;
import BluminEngine5.Utils.*;
import BluminEngine5.Utils.ResourceMannager.ResourceMannager;
import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import org.lwjgl.opengl.GL;
import BluminEngine5.Utils.Math.Math;
import org.lwjgl.opengl.GL11;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Application {

    public static Action<IAction> Update = new Action<>();
    public static Action<IAction> Init = new Action<>();
    public static Action<IAction> PreInit = new Action<>();
    public static Action<IAction> OnExit = new Action<>();

    private static ResourceMannager resourceManager;
    private static String ConfigFile =  "Config.ini";
    public static Metadata getMetadata() {
        return metadata;
    }
    private static Metadata metadata;
    public static Display display;

    private static MasterRenderer renderer = new MasterRenderer();
    private static DynamicsWorld dynamicsWorld;

    private static Version ActiveEngineVersion;

    private static PostProcessingProfileBehaviour postProcessingProfile = null;

    public static ResourceMannager getResourceManager() {
        return resourceManager;
    }

    public static MasterRenderer getRenderer() {
        return renderer;
    }


    private static Version EngineVersion = new Version(0,0,1,0,"DevSystem");
    public static void Run(Resolution res, DisplayMode mode, DisplayDimension dim) {
        DealWithEngineVersioning();
        resourceManager= new ResourceMannager(metadata.MainArchiveFile);
        Path tempDir;
        System.setProperty("org.lwjgl.util.Debug", "true");
        try{
            tempDir = Files.createDirectories(Paths.get("Res/Temp"));
            PreInit.Invoke();


            display = new Display();
            display.CreateWindow(getMetadata().GameName, res, mode, dim);


            GL.createCapabilities();

            Debug.log("Setting up keyboard");
            //TODO: this is a dumb way to handel inputs re-write this system
            glfwSetKeyCallback(display.getWindow(), (window, key, scancode, action, mods) -> {
                if(action == GLFW_PRESS) {
                    if(!Input.Instance().Pressed.contains(key)) {
                        Input.Instance().Pressed.add(key);
                    }
                }
                if(action == GLFW_REPEAT) {
                    if(Input.Instance().Pressed.contains(key)) {
                        Input.Instance().Pressed.remove(Input.Instance().Pressed.lastIndexOf(key));
                    }
                    if(!Input.Instance().Held.contains(key)) {
                        Input.Instance().Held.add(key);
                    }
                }
                if(action == GLFW_RELEASE) {
                    if(Input.Instance().Held.contains(key)) {
                        Input.Instance().Held.remove(Input.Instance().Held.lastIndexOf(key));
                    }
                    if(Input.Instance().Pressed.contains(key)) {
                        Input.Instance().Pressed.remove(Input.Instance().Pressed.lastIndexOf(key));
                    }
                    if(!Input.Instance().Released.contains(key)) {
                        Input.Instance().Released.add(key);
                    }
                }
            });

            GL11.glEnable(GL11.GL_DEPTH_TEST);

            Debug.log("Setting up DaynamicsWorld");
            BroadphaseInterface broadphase = new DbvtBroadphase();
            CollisionConfiguration collisionConfig = new DefaultCollisionConfiguration();
            Dispatcher dispatcher = new CollisionDispatcher(collisionConfig);
            ConstraintSolver solver = new SequentialImpulseConstraintSolver();
            dynamicsWorld = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfig);

            glEnable(GL_CULL_FACE);
            glCullFace(GL_BACK);

            Init.Invoke();
            while (!glfwWindowShouldClose(display.getWindow()) ) {
                Update.Invoke();
                renderer.Render();

                glfwSwapBuffers(display.getWindow());
                glfwPollEvents();
            }
            display.Close(OnExit);

        } catch (IOException i) {
            Utils.CrashApp(-100, "Failed to create required temp folder");
        }
    }

    private static void DealWithEngineVersioning() {
        try{
            Debug.log("Loading Metadata");
            metadata  = new Metadata(ConfigFile);
        }catch (IOException e) {
            Utils.CrashApp(-134, e);
        }
        if(Math.Equals(metadata.engineVersion, EngineVersion)) {
            ActiveEngineVersion = EngineVersion;
            Debug.log("Starting BluminEngine3 on " + EngineVersion);
        } else {
            EngineVersion = metadata.engineVersion;
            Debug.log("Attempting to load BluminEngine3 on " + metadata.engineVersion);
        }
    }

    public static DynamicsWorld getDynamicsWorld() {
        return dynamicsWorld;
    }

    public static boolean IsPostProcessingActive(){
        return metadata.PostProcessing;
    }
    public static void setPostProcessingProfile(PostProcessingProfileBehaviour postProcessingProfile) {
        Application.postProcessingProfile = postProcessingProfile;
    }
    public static PostProcessingProfileBehaviour getPostProcessingProfile() {
        return postProcessingProfile;
    }
}
