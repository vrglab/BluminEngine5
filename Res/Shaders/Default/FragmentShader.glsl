#version 430 core

#define NR_POINT_LIGHTS 15

in vec4 color;
in vec2 texCord;
in vec3 Normal;
in vec3 EyeView;
in vec3 FragPos;


struct DirLight {
    vec3 direction;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

struct PointLight {
    vec3 position;

    float constant;
    float linear;
    float quadratic;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

struct Material {
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
    float shininess;
};



uniform sampler2D Texture;
uniform Material material;
uniform DirLight dirLight;
uniform PointLight pointLights[NR_POINT_LIGHTS];
uniform vec3 lightPos;
uniform vec4 lightColor;
uniform vec3 viewPos;


out vec4 outColor;


void main() {
    vec4 tex = vec4(texture(Texture, texCord).xyz, color.w);

    if(color.w < 0.1 || texture(Texture, texCord).w < 0.1f) {
        discard;
    } else {
        if(color.xyz == vec3(1,1,1)) {
            float ambientStrength = 0.1;
            vec3 ambient = ambientStrength * lightColor.xyz;

            vec3 norm = normalize(Normal);
            vec3 lightDir = normalize(lightPos - FragPos);
            float diff = max(dot(norm, lightDir), 0.0);
            vec3 diffuse = diff * lightColor.xyz;

            float specularStrength = 0.5;
            vec3 viewDir = normalize(viewPos - FragPos);
            vec3 reflectDir = reflect(-lightDir, norm);

            float spec = pow(max(dot(viewDir, reflectDir), 0.0), 32);
            vec3 specular = specularStrength * spec * lightColor.xyz;

            vec3 result = (ambient + diffuse + specular) * color.xyz;
            outColor = texture(Texture, texCord) + vec4(result, 1.0);

        } else{
            //outColor = tex * color * GetDefiuseLight(lightdata[i]);
        }
    }
}