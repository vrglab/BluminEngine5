#version 430 core

in vec3 position;
in vec3 incolor;
in vec2 textureCord;

uniform mat4 transform;


out vec3 color;
out vec2 texCord;

void main() {
    gl_Position =  transform * vec4(position, 1.0f) ;
    color = incolor;
    texCord = textureCord;
}