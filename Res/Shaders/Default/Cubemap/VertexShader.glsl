in vec3 position;

uniform mat4 transform;
uniform mat4 ProjectionMatrix;
uniform mat4 ViewMatrix;

void main() {
    gl_Position =  ProjectionMatrix * ViewMatrix * transform  * vec4(position, 1.0f) ;
}