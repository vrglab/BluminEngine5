package BluminEngine5.Utils.EventSystem;

public interface IActionArgBased<t> {
    void Run(t args);
    void Run();
}
