package BluminEngine5.Physics;

import BluminEngine5.Componant.Component;
import BluminEngine5.Componant.Rendering.MeshRenderer;
import BluminEngine5.Physics.Colision.Collider;

import java.util.ArrayList;
import java.util.List;

public class Rigidbody extends Component {

    List<Collider> colliders = new ArrayList<>();

    @Override
    public void Update() {

    }

    @Override
    public void OnRender() {

    }

    @Override
    public void Init() {
        colliders = Parent.getComponant(MeshRenderer.class).model.getColliders();
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
