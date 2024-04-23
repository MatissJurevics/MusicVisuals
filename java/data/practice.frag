#ifdef GL_ES
precision mediump float;
#endif

uniform vec2 u_resolution;
uniform float u_time;

vec3 palette( float t ) {
    vec3 a = vec3(1.0, 0.0, 0.0);
    vec3 b = vec3(0.6824, 0.0, 1.0);
    vec3 c = vec3(1.0, 0.0, 0.0);
    vec3 d = vec3(0.0824, 0.0, 1.0);

    return a + b*cos( 6.28318*(c*t+d) );
}

void main() {
  vec2 uv = (gl_FragCoord.xy * 2. - u_resolution.xy) / u_resolution.y;
  vec2 uv0 = fract(uv * 2.) - 0.5;
  vec3 col = vec3(0., 0., 0.);

  // Draw circle with red aura
  float circ = length(uv);
  circ = step(0.2,circ);
  float dist = length(uv);

  

  dist *= sin(length(uv0)*8. * u_time)/ 8.;
  dist = 0.02 / dist;
  col += dist;

  col.x += 0.2 / (smoothstep(0.2,0.3,dist));
  col += 0.005 / (smoothstep(0.2,0.3,dist));
  col *= circ;

  gl_FragColor = vec4(col, 1.0);
}

