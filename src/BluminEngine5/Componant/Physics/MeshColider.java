package BluminEngine5.Componant.Physics;

import BluminEngine5.Application;
import BluminEngine5.Componant.IComponent;
import BluminEngine5.Componant.Rendering.MeshRenderer;
import BluminEngine5.Rendering.ThreeD.Master.Mesh;
import BluminEngine5.Rendering.ThreeD.Vertex;
import BluminEngine5.Utils.Debuging.Debug;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.narrowphase.ManifoldPoint;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.shapes.ConvexHullShape;
import com.bulletphysics.collision.shapes.ShapeHull;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.util.ObjectArrayList;

import javax.vecmath.Vector3f;

public class MeshColider extends IComponent {

    private MeshRenderer renderer;
    private ConvexHullShape collisionHullShape;

    public MeshColider(MeshRenderer r) {
        renderer = r;
    }

    public MeshColider(Mesh r) {
        renderer = new MeshRenderer(r);
    }

    @Override
    public void Update() {
        Dispatcher dispatcher = Application.getDynamicsWorld().getDispatcher();
        int manifoldCount = dispatcher.getNumManifolds();
        for(int i = 0; i < manifoldCount; i ++){
            Debug.log(" is running");
            PersistentManifold manifold = dispatcher.getManifoldByIndexInternal(i);
            RigidBody object1 = (RigidBody)manifold.getBody0();
            RigidBody object2 = (RigidBody)manifold.getBody1();

            CollisionObject physicsObject1 = (CollisionObject)object1.getUserPointer();
            CollisionObject physicsObject2 = (CollisionObject)object2.getUserPointer();
            boolean contact = false;
            for (int j = 0; j < manifold.getNumContacts(); j++) {
                Debug.log("Colision is running");
                ManifoldPoint contactPoint = manifold.getContactPoint(j);
                if (contactPoint.getDistance() < 0.0f) {
                    contact = true;

                    break;
                }
            }
            if (contact) {
                System.out.println("hit");
            }
        }
    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        ObjectArrayList<Vector3f> vertices = new ObjectArrayList<javax.vecmath.Vector3f>();
        for(Vertex vertex:renderer.mesh.getVertecies()){
            javax.vecmath.Vector3f v = new javax.vecmath.Vector3f(vertex.getPosition().x, vertex.getPosition().y, vertex.getPosition().z);
            vertices.add(v);
        }
        ConvexHullShape shape = new ConvexHullShape(vertices);
        ShapeHull hull = new ShapeHull(shape);
        hull.buildHull(shape.getMargin());
        collisionHullShape = new ConvexHullShape(hull.getVertexPointer());
    }

    @Override
    public void PreInit() {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void SceneLoad() {

    }

    @Override
    public void Destroy() {

    }
}
