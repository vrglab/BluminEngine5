#version 430 core

in vec4 color;
in vec2 texCord;
in vec3 Normal;
in vec3 EyeView;


uniform sampler2D Texture;
uniform vec3 SunLightPos;

out vec4 outColor;


float GetDefiuseLight() {
    float distance = length(SunLightPos - EyeView);
    vec3 lightVector = normalize(SunLightPos - EyeView);
    float diffuse = max(dot(Normal, lightVector), 0.1);
    return diffuse * (1.0 / (1.0 + (0.25 * distance * distance)));
}



void main() {
    vec4 tex = vec4(texture(Texture, texCord).xyz, color.w);

    if(color.w < 0.1 || texture(Texture, texCord).w < 0.1f) {
        discard;
    } else {
        if(color.xyz == vec3(1,1,1)) {
            outColor = tex * GetDefiuseLight();
        } else{
            outColor = tex * color * GetDefiuseLight();
        }
    }
}