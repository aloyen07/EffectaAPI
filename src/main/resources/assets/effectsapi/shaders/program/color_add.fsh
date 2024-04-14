#version 110

uniform sampler2D DiffuseSampler;

uniform float RAdd;
uniform float GAdd;
uniform float BAdd;

varying vec2 texCoord;
varying vec2 oneTexel;

void main() {
    vec4 colorIn = texture2D(DiffuseSampler, texCoord.st);

    gl_FragColor = vec4(colorIn.r + RAdd,
                        colorIn.g + GAdd,
                        colorIn.b + BAdd, 1.0);
}

