#version 460 core

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
out int ActiveLightIndexes;

void main() {
    gl_Position =  ProjectionMatrix * ViewMatrix *transform * vec4(position, 1.0f) ;
    color = incolor;
    texCord = textureCord;
    Normal = normal;
    EyeView = vec3(ProjectionMatrix * transform);
    FragPos = vec3(ViewMatrix * vec4(position, 1.0));
    ActiveLightIndexes = 16;
}