#version 330

layout (location = 0) in vec3 position;

uniform mat4 transform;

void main() {

    vec4 finalPos = vec4(position, 1.0);
    gl_Position = transform * finalPos;

}