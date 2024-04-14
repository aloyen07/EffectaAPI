#version 110

uniform sampler2D DiffuseSampler;

uniform float RMultiplier;
uniform float GMultiplier;
uniform float BMultiplier;

varying vec2 texCoord;
varying vec2 oneTexel;

void main() {
    vec4 colorIn = texture2D(DiffuseSampler, texCoord.st);

    gl_FragColor = vec4(colorIn.r * RMultiplier,
                        colorIn.g * GMultiplier,
                        colorIn.b * BMultiplier, 1.0);
}

