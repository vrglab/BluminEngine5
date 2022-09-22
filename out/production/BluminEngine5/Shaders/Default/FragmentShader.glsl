#version 430 core

in vec4 color;
in vec2 texCord;

uniform sampler2D Texture;

out vec4 outColor;

void main() {
    if(color.w < 0.1) {
        discard;
    } else {
        if(color.xyz == vec3(1,1,1)) {
            outColor = texture(Texture, texCord);
        } else{
            outColor = texture(Texture, texCord) + color;
        }
    }
}