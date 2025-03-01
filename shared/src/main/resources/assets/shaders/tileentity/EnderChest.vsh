#version 330
precision highp float;

uniform mat4 projection;
uniform mat4 modelView;
uniform vec3 offset;

uniform float alpha;
uniform float partialTicks;

layout(location = 0) in vec3 modelPosition;
layout(location = 1) in vec2 vertUV;
layout(location = 2) in vec3 vertNormal;
layout(location = 3) in int vertGroupID;


layout(location = 4) in vec3 renderPosition;
layout(location = 5) in vec2 vertLightMapUV;

layout(location = 6) in int rotationY;
layout(location = 7) in float prevLidAngle;
layout(location = 8) in float lidAngle;

out vec2 uv;
flat out vec3 normal;
out vec2 lightMapUV;

const vec3 rotationPointOffset = vec3(0.0, 0.5625, -0.4375);

#import /assets/shaders/util/Mat3Rotation.glsl

void main() {
    vec3 position = modelPosition;
    normal = vertNormal;

    if (vertGroupID != 0) {
        float renderLidAngle = mix(prevLidAngle, lidAngle, partialTicks);
        renderLidAngle = 1.0 - renderLidAngle;
        renderLidAngle = 1.0 - renderLidAngle * renderLidAngle * renderLidAngle;

        mat3 lidMatrix = mat3(1.0);
        lidMatrix = rotateX90(lidMatrix, renderLidAngle);

        position -= rotationPointOffset;
        position = position * lidMatrix;
        position += rotationPointOffset;

        normal = normal * lidMatrix;
    }

    mat3 rotationMatrix = mat3(1.0);
    rotationMatrix = rotateY90(rotationMatrix, rotationY);

    gl_Position = projection * modelView * vec4(position * rotationMatrix + (renderPosition + offset), 1.0);
    uv = vertUV;
    normal *= rotationMatrix;
    lightMapUV = vertLightMapUV * 0.99609375 + 0.03125;
}