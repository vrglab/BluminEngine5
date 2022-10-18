package BluminEngine5;

import BluminEngine5.Rendering.*;
import BluminEngine5.Rendering.Master.MasterRenderer;
import BluminEngine5.Rendering.PostProcessing.PostProcessingProfileBehaviour;
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
import de.fabmax.physxjni.Loader;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import physx.PxTopLevelFunctions;
import physx.common.PxDefaultErrorCallback;
import physx.common.PxFoundation;
import physx.common.PxTolerancesScale;
import physx.extensions.PxDefaultAllocator;
import physx.physics.PxPhysics;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Application {

    public static Action<IAction> Update = new Action<>();
    public static Action<IAction> Init = new Action<>();
    public static Action<IAction> Awake = new Action<>();
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


    private static Version EngineVersion = new Version(0,0,1,0,"Alpha");

    public static void Run(Resolution res, DisplayMode mode, DisplayDimension dim) {
        DealWithEngineVersioning();
        resourceManager= new ResourceMannager(metadata.MainArchiveFile);
        Path tempDir;

        try{
            InvokeBeforeWindowCreation();
            display = new Display();
            display.CreateWindow(getMetadata().GameName, res, mode, dim);

            InvokeAfterWindowCreation(true);

            while (!glfwWindowShouldClose(display.getWindow()) ) {
                Update.Invoke();
                renderer.Render();

                glfwSwapBuffers(display.getWindow());
                glfwPollEvents();
            }
            display.Close(OnExit);

        } catch (Exception i) {
            Utils.CrashApp(-100, i);
        }
    }

    public static void InvokeBeforeWindowCreation(){
        try {
            Files.createDirectories(Paths.get(metadata.ResourceFolder+"/Temp"));
        } catch(Exception e) {

        }

        PreInit.Invoke();


    }

    public static void InvokeAfterWindowCreation(boolean Debugs) {

        GL.createCapabilities();

        if(Debugs) {
            Debug.log("Setting up keyboard");
        }

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

        if(Debugs) {
            Debug.log("Setting up DaynamicsWorld");
        }

        //Physics should be implemented here

        Awake.Invoke();

        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);

        Init.Invoke();
    }

    private static void DealWithEngineVersioning() {
        try{
            Debug.log("Loading Metadata");
            metadata  = new Metadata(ConfigFile);
            Debug.log("Loading BluminEngine5" + EngineVersion);
        }catch (IOException e) {
            Utils.CrashApp(-134, e);
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

    @Deprecated
    public static PostProcessingProfileBehaviour getPostProcessingProfile() {
        return postProcessingProfile;
    }
}
