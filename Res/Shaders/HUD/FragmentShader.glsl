#version 430 core

in vec2 textureCoords;
in vec4 color;

out vec4 outColor;

uniform sampler2D guiTexture;

void main(void){

    if(color.xyz == vec3(1,1,1)) {
        outColor = texture(guiTexture, textureCoords);
    } else{
        outColor = texture(guiTexture, textureCoords) + color;
    }

}