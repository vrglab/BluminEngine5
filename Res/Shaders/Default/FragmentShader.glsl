#version 460 core

in vec3 color;
in vec2 texCord;

uniform sampler2D Texture;

out vec4 outColor;

void main() {
    outColor = texture(Texture, texCord);
}