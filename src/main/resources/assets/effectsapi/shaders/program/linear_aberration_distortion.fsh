#version 110

uniform sampler2D DiffuseSampler;

uniform float Multiplier;

varying vec2 texCoord;
varying vec2 oneTexel;

void main() {

    float r = texture2D(DiffuseSampler, texCoord.st - vec2(Multiplier, 0.0)).x;
    float g = texture2D(DiffuseSampler, texCoord.st).y;
    float b = texture2D(DiffuseSampler, texCoord.st + vec2(Multiplier, 0.0)).z;

    gl_FragColor = vec4(r, g, b, 1.0);
}

