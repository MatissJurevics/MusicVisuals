
#ifdef GL_ES
precision mediump float;
#endif

uniform vec2 u_resolution;
uniform vec2 u_mouse;
uniform float u_time;
uniform float u_circleRadius;
uniform bool u_sharp;

vec3 palette( float t ) {
    vec3 a = vec3(0.3137, 0.0, 0.0);
    vec3 b = vec3(0.0, 0.0, 0.0);
    vec3 c = vec3(0.3765, 0.0, 0.0);
    vec3 d = vec3(0.0, 0.0, 0.0);

    return a + b*cos( 6.28318*(c*t+d) );
}
void main() {

    vec2 uv = (gl_FragCoord.xy * 2.0 - u_resolution.xy) / u_resolution.y;
    vec2 uv0 = uv;
    vec3 finalColor = vec3(0.0);
    
    for (float i = 0.0; i < 4.0; i++) {
        uv = fract(uv * 1.3) - 0.5;

        float d = length(uv) * exp(-length(uv0));
        // assuming the bpm is 124 and the beat is 4/4, have the loop cycle every 4 beats

        vec3 col = palette(length(uv0) + i*.4 + u_time*1.935);

        d = sin(d*8. + u_time)/4.;
        d = abs(d);
        // if (u_sharp) d = pow(0.01 / d, 1.2);
        // d = pow(0.05 / d, 2.);
        // red ring around the circle
        
        
        finalColor += col * d;
        float o = length(uv0);
        finalColor * o;
        if (length(uv0) < u_circleRadius + 0.1) {
            finalColor = vec3(0.0);
        }
    }
    
    
       
    // finalColor = palette(d); 
    gl_FragColor = vec4(finalColor, 1.0);
}



