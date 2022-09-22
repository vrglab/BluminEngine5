#version 430 core

in vec2 textureCoords;
in vec4 color;

out vec4 outColor;

uniform sampler2D guiTexture;

void main(){
    if(color.w < 0.1) {
        discard;
    } else {
        if(color.xyz == vec3(1,1,1)) {
            outColor = texture(guiTexture, textureCoords);
        } else{
            vec4 col = texture(guiTexture, textureCoords) + color.w * (1 - color.w) + color;
            outColor = texture(guiTexture, textureCoords) + col;
        }
    }
}