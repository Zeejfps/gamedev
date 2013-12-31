#version 330

layout (location = 0) in vec3 position;

uniform mat4 transform;
uniform mat4 projection;

void main() {

    mat4 finalTransform = projection * transform;
    gl_Position = finalTransform * vec4(position, 1.0);

}