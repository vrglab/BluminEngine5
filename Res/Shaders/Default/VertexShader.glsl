#version 430 core

in vec3 position;
in vec4 incolor;
in vec2 textureCord;

uniform mat4 transform;
uniform mat4 ProjectionMatrix;
uniform mat4 ViewMatrix;

out vec4 color;
out vec2 texCord;

void main() {
    gl_Position =  ProjectionMatrix * ViewMatrix *transform * vec4(position, 1.0f) ;
    color = incolor;
    texCord = textureCord;
}