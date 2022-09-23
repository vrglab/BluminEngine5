#version 460 core

in vec4 color;
in vec2 texCord;
in vec3 Normal;
in vec3 EyeView;
in vec3 FragPos;
in int ActiveLightIndexes;

#define NR_POINT_LIGHTS 100


struct PointLight {
    vec3 position;

    float constant;
    float linear;
    float quadratic;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

struct DirLight {
    vec3 direction;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

struct Material {
    sampler2D Texture;
    vec3 ambient;
    sampler2D  diffuse;
    sampler2D  specular;
    float shininess;
};

uniform Material material;
uniform PointLight light;
uniform vec3 lightPos;
uniform vec4 lightColor;
uniform vec3 viewPos;
uniform PointLight pointLights[NR_POINT_LIGHTS];
uniform DirLight dirLight;

out vec4 outColor;

vec4 TexWithLighting() {
    // diffuse
    vec3 norm = normalize(Normal);
    vec3 lightDir = normalize(lightPos + FragPos);
    float diff = max(dot(norm, lightDir), 0.1);

    // specular
    vec3 viewDir = normalize(viewPos - FragPos);
    vec3 reflectDir = reflect(-lightDir, norm);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);


    vec3 ambient = light.ambient * texture(material.Texture, texCord).xyz;
    vec3 diffuse = light.diffuse * diff * vec3(texture(material.diffuse, texCord));
    vec3 specular = light.specular * spec * vec3(texture(material.specular, texCord));

    vec3 result =  ambient + diffuse + specular;

    return vec4(result,1.0);
}



vec3 CalcPointLight(PointLight light, vec3 normal, vec3 fragPos, vec3 viewDir)
{
    vec3 lightDir = normalize(light.position - fragPos);
    // diffuse shading
    float diff = max(dot(normal, lightDir), 0.0);
    // specular shading
    vec3 reflectDir = reflect(-lightDir, normal);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    // attenuation
    float distance    = length(light.position - fragPos);
    float attenuation = 1.0 / (light.constant + light.linear * distance +
    light.quadratic * (distance * distance));
    // combine results
    vec3 ambient  = light.ambient  * vec3(texture(material.diffuse, texCord));
    vec3 diffuse  = light.diffuse  * diff * vec3(texture(material.diffuse, texCord));
    vec3 specular = light.specular * spec * vec3(texture(material.specular, texCord));
    ambient  *= attenuation;
    diffuse  *= attenuation;
    specular *= attenuation;
    return (ambient + diffuse + specular);
}

vec3 CalcDirLight(DirLight light, vec3 normal, vec3 viewDir)
{
    vec3 lightDir = normalize(-light.direction);
    // diffuse shading
    float diff = max(dot(normal, lightDir), 0.0);
    // specular shading
    vec3 reflectDir = reflect(-lightDir, normal);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    // combine results
    vec3 ambient  = light.ambient  * vec3(texture(material.Texture, texCord));
    vec3 diffuse  = light.diffuse  * diff * vec3(texture(material.diffuse, texCord));
    vec3 specular = light.specular * spec * vec3(texture(material.specular, texCord));
    return (ambient + diffuse + specular);
}

void main() {
    vec3 norm = normalize(Normal);
    vec3 viewDir = normalize(viewPos - FragPos);

    // phase 1: Directional lighting
    vec3 result = CalcDirLight(dirLight, norm, viewDir);
    // phase 2: Point lights
    for(int i = 0; i < NR_POINT_LIGHTS; i++)
    result += CalcPointLight(pointLights[i], norm, FragPos, viewDir);
    // phase 3: Spot light
    //result += CalcSpotLight(spotLight, norm, FragPos, viewDir);

    //outColor = vec4(result, 1.0);
    outColor = texture(material.Texture, texCord);
}