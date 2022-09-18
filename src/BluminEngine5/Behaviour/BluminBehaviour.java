package BluminEngine5.Behaviour;

import BluminEngine5.Componant.IComponent;
import BluminEngine5.Componant.Transform;
import BluminEngine5.Utils.EventSystem.IAction;
import BluminEngine5.Utils.Math.Vector3;
import BluminEngine5.Utils.objActionData;

import java.util.ArrayList;
import java.util.List;

public abstract class BluminBehaviour implements IObjLogic{

        private List<IComponent> AttachedComponants = new ArrayList<>();
        public objActionData ActionData =  new objActionData();

        public Transform transform = new Transform(new Vector3(0,0,-1), Vector3.Zero);

        public BluminBehaviour() {
                RegisterComponant(transform, this);
                ActionData.OnUpdate = new IAction() {
                        @Override
                        public void Run() {
                                Update();
                                for (IComponent comp: AttachedComponants) {
                                        comp.data.OnUpdate.Run();
                                }
                        }
                };
                ActionData.OnExit = new IAction() {
                        @Override
                        public void Run() {
                                for (IComponent comp: AttachedComponants) {
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
                                for (IComponent comp: AttachedComponants) {
                                        comp.data.OnRender.Run();
                                }
                        }
                };
                ActionData.OnInit = new IAction() {
                        @Override
                        public void Run() {
                                Init();
                                for (IComponent comp: AttachedComponants) {
                                        comp.data.OnInit.Run();
                                }
                        }
                };
                ActionData.OnDestroy = new IAction() {
                        @Override
                        public void Run() {
                                Destroy();
                                for (IComponent comp: AttachedComponants) {
                                        comp.data.OnDestroy.Run();
                                }
                        }
                };
                ActionData.OnPreInit = new IAction() {
                        @Override
                        public void Run() {
                                for (IComponent comp: AttachedComponants) {
                                        comp.data.OnPreInit.Run();
                                }
                        }
                };
        }

        public void RegisterComponant(IComponent component, BluminBehaviour bb) {
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
                component.Parent = bb;
                AttachedComponants.add(component);
        }

        public void UnregisterComponant(IComponent component) {
                AttachedComponants.remove(AttachedComponants.lastIndexOf(component));
        }

}
