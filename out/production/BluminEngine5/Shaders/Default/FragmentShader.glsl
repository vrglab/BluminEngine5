#version 430 core

#define NR_POINT_LIGHTS 15

in vec4 color;
in vec2 texCord;
in vec3 Normal;
in vec3 EyeView;
in vec3 FragPos;



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
    sampler2D  diffuse;
    sampler2D  specular;
    float shininess;
};



uniform sampler2D Texture;
uniform Material material;
uniform PointLight light;
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

            // diffuse
            vec3 norm = normalize(Normal);
            vec3 lightDir = normalize(lightPos - FragPos);
            float diff = max(dot(norm, lightDir), 0.0);

            // specular
            vec3 viewDir = normalize(viewPos - FragPos);
            vec3 reflectDir = reflect(-lightDir, norm);
            float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);


            vec3 ambient = light.ambient * vec3(texture(material.diffuse, texCord));
            vec3 diffuse = light.diffuse * diff * vec3(texture(material.diffuse, texCord));
            vec3 specular = light.specular * spec * vec3(texture(material.specular, texCord));

            vec3 result = ambient + diffuse + specular;
            outColor = texture(Texture, texCord) + result;

        } else{
            //outColor = tex * color * GetDefiuseLight(lightdata[i]);
        }
    }
}