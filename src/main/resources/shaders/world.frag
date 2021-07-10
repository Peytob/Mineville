#version 330 core

uniform sampler2D ut_diffuseAtlas;

in VS_OUT {
    vec2 texture;
} VSO;

out vec4 fsout_color;

uniform sampler2D textureSampler;

void main() {
    fsout_color = texture(textureSampler, VSO.texture);
}
