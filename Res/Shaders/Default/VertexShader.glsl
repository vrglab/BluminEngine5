#version 430 core

in vec3 position;
in vec4 incolor;
in vec2 textureCord;
in vec3 normal;

uniform mat4 transform;
uniform mat4 ProjectionMatrix;
uniform mat4 ViewMatrix;

out vec4 color;
out vec2 texCord;
out vec3 Normal;
out vec3 EyeView;
out vec3 FragPos;


void main() {
    gl_Position =  ProjectionMatrix * ViewMatrix *transform * vec4(position, 1.0f) ;
    color = incolor;
    texCord = textureCord;
    Normal = mat3(transpose(inverse(transform))) * normal;
    EyeView = vec3(ProjectionMatrix * transform);
    FragPos = vec3(transform * vec4(position, 1.0));
}