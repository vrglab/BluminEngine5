package BluminEngine5.Behaviour;

import BluminEngine5.Componant.Component;
import BluminEngine5.Componant.Transform;
import BluminEngine5.SceneMannagement.Scene;
import BluminEngine5.Utils.EventSystem.IAction;
import BluminEngine5.Utils.Math.Vector3;
import BluminEngine5.Utils.objActionData;

import java.util.ArrayList;
import java.util.List;

/**
 * Behaviour class for all in scene Objects
 * @author Vrglab
 */
public abstract class BluminBehaviour implements IObjLogic{

        private List<Component> AttachedComponants = new ArrayList<>();
        public objActionData ActionData =  new objActionData();

        public Transform transform = new Transform(new Vector3(0,0,-1), Vector3.Zero);

        public Scene parent;

        public String name;

        public BluminBehaviour() {
                RegisterComponant(transform);
                ActionData.OnUpdate = new IAction() {
                        @Override
                        public void Run() {
                                Update();
                                for (Component comp: AttachedComponants) {
                                        comp.data.OnUpdate.Run();
                                }
                        }
                };
                ActionData.OnExit = new IAction() {
                        @Override
                        public void Run() {
                                for (Component comp: AttachedComponants) {
                                        comp.data.OnExit.Run();
                                }
                                Destroy();
                                OnExit();
                        }
                };
                ActionData.OnRender = new IAction() {
                        @Override
                        public void Run() {
                                OnRender();
                                for (Component comp: AttachedComponants) {
                                        comp.data.OnRender.Run();
                                }
                        }
                };
                ActionData.OnInit = new IAction() {
                        @Override
                        public void Run() {
                                Init();
                                for (Component comp: AttachedComponants) {
                                        comp.data.OnInit.Run();
                                }
                        }
                };
                ActionData.OnDestroy = new IAction() {
                        @Override
                        public void Run() {
                                Destroy();
                                for (Component comp: AttachedComponants) {
                                        comp.data.OnDestroy.Run();
                                }
                        }
                };
                ActionData.OnPreInit = new IAction() {
                        @Override
                        public void Run() {
                                for (Component comp: AttachedComponants) {
                                        comp.data.OnPreInit.Run();
                                }
                        }
                };
                name = getClass().getSimpleName();
        }

        public <t extends Component> t RegisterComponant(t component) {
                component.data = new objActionData();
                component.data.OnInit = new IAction() {
                        @Override
                        public void Run() {
                                component.Init();
                        }
                };
                component.data.OnRender = new IAction() {
                        @Override
                        public void Run() {
                                component.OnRender();
                        }
                };
                component.data.OnUpdate = new IAction() {
                        @Override
                        public void Run() {
                                component.Update();
                        }
                };
                component.data.OnPreInit = new IAction() {
                        @Override
                        public void Run() {
                                component.PreInit();
                        }
                };
                component.data.OnDestroy = new IAction() {
                        @Override
                        public void Run() {
                                component.Destroy();
                        }
                };
                component.data.OnExit = new IAction() {
                        @Override
                        public void Run() {
                                component.OnExit();
                        }
                };
                component.Parent = this;
                AttachedComponants.add(component);
                return component;
        }

        public void UnregisterComponant(Component component) {
                AttachedComponants.remove(AttachedComponants.lastIndexOf(component));
        }

        public <t extends Component> t getComponant(Class<t> componantType) {
                for (Component comp: AttachedComponants) {
                        if(comp.getClass().isAssignableFrom(componantType.getClass())) {
                                try{
                                        return componantType.cast(comp);
                                } catch(ClassCastException e){

                                }
                        }
                }
                return null;
        }
}
