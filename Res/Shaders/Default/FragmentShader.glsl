#version 430 core

in vec4 color;
in vec2 texCord;

uniform sampler2D Texture;

out vec4 outColor;

void main() {
    vec4 tex = vec4(texture(Texture, texCord).xyz, color.w);

    if(color.w < 0.1 || texture(Texture, texCord).w < 0.1f) {
        discard;
    } else {
        if(color.xyz == vec3(1,1,1)) {
            outColor = tex;
        } else{
            outColor = tex + color;
        }
    }
}