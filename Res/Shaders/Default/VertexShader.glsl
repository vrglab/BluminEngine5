

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
out vec4 WorldPos;

void main() {
    vec4 worldPos = ProjectionMatrix * vec4(position, 1.0f);
    gl_Position =  ProjectionMatrix * ViewMatrix *transform  * vec4(position, 1.0f) ;
    color = incolor;
    texCord = textureCord;
    Normal = (transform * vec4(normal,1.0)).xyz;
    WorldPos = worldPos;
}