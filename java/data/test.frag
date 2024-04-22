
#ifdef GL_ES
precision mediump float;
#endif

uniform vec2 u_resolution;
uniform vec2 u_mouse;
uniform float u_time;
uniform float u_circleRadius;

vec3 palette( float t ) {
    vec3 a = vec3(0.5, 0.5, 0.5);
    vec3 b = vec3(0.5, 0.5, 0.5);
    vec3 c = vec3(0.1843, 0.0, 1.0);
    vec3 d = vec3(0.2627, 0.5176, 0.5569);

    return a + b*cos( 6.28318*(c*t+d) );
}
void main() {

    vec2 uv = (gl_FragCoord.xy * 2.0 - u_resolution.xy) / u_resolution.y;
    vec2 uv0 = uv;
    vec3 finalColor = vec3(0.0);
    
    for (float i = 0.0; i < 4.0; i++) {
        uv = fract(uv * 1.5) - 0.5;

        float d = length(uv) * exp(-length(uv0));

        vec3 col = palette(length(uv0) + i*.4 + u_time*0.8);

        d = sin(d*8. + u_time)/4.;
        d = abs(d);

        // d = pow(0.01 / d, 1.2);
        

        finalColor += col * d;
    }
    if (length(uv0) < u_circleRadius + 0.1) {
        finalColor = vec3(0.0);
    }
        
    gl_FragColor = vec4(finalColor, 1.0);
}



