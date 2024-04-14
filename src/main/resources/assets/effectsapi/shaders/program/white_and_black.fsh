#version 110

uniform sampler2D DiffuseSampler;

uniform float Multiplier;

varying vec2 texCoord;
varying vec2 oneTexel;

void main() {
    float mul = 1.0 - Multiplier;
    vec4 colorIn = texture2D(DiffuseSampler, texCoord.st);

    float ave = (colorIn.r + colorIn.g + colorIn.b) / 3.0;

    vec3 color = vec3(ave - (ave-colorIn.r)*mul,
                      ave - (ave-colorIn.g)*mul,
                      ave - (ave-colorIn.b)*mul);

    gl_FragColor = vec4(color, 1.0);
}

