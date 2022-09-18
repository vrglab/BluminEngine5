package BluminEngine5.Utils;

import org.json.JSONObject;

public class Version {
    public int r;
    public int a;
    public int b;
    public int p;
    public String ext;

    public Version() {
    }
    public Version(int r, int a, int b, int p, String ext) {

        this.a = a;
        this.b = b;
        this.p = p;
        this.r = r;
        this.ext = ext;
    }

    public String Json() {
        JSONObject obj = new JSONObject();
        obj.put("r", r);
        obj.put("a", a);
        obj.put("b", b);
        obj.put("p", p);
        obj.put("ext", ext);
        return obj.toString();
    }
    public static Version FromJson(JSONObject obj) {
        return new Version(
                obj.getInt("r"),
                obj.getInt("a"),
                obj.getInt("b"),
                obj.getInt("p"),
                obj.getString("ext")
        );
    }
    public static Version FromJson(String file) {
        String data = Utils.LoadFile(file);
        JSONObject obj = new JSONObject(data);
        return new Version(
                obj.getJSONObject("Version").getInt("r"),
                obj.getJSONObject("Version").getInt("a"),
                obj.getJSONObject("Version").getInt("b"),
                obj.getJSONObject("Version").getInt("p"),
                obj.getJSONObject("Version").getString("ext")
        );
    }

    @Override
    public String toString() {
        return "Version " + r + "." + a +"." + b +"."+ p + "_" + ext;
    }
}
