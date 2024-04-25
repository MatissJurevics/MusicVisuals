# Music Visualiser Project

Name: Karl Negrillo  
Student Number: C22386123

Name: Matiss Jurevics  
Student Number: C22501743

# Description of the assignment
In this assignment, we have decided to do one visual each and code it using Java Processing. The visualisation are based on the music track
called "RATATA" by Skrillex, Missy Elliot & Mr. Oizo. For Karls visual, the audio level directly influences the movement speed of the cubes, making the visualization dynamically respond to the music's volume and intensity. For Matiss's Visual, the audio-visual interaction provides a dynamic and responsive experience where audio inputs directly influence visual outputs (Shaders and Animation). 

# Instructions
- Compile and Run Main.java from ie.tudublin package
- Press Space Bar to Start and Reset
- **Visualisation**: Arrow keys will switch back or forward from each scene
- Order of Apperance: Karl's Visual, then Matiss's Visual

# How it works
- Each memeber has its own directory containing the visualisation
- We have an aggregate file called Controller.java in ie.main. This is used to run the visualisation using a controller, draw the sketch, 
and able to switch to different scenes. This file is made in ie.main package for convenience.

## Karls Visual
![An image](images/karlsvisual.png)

## Matiss's Visual
![An image](images/matissvisual.png)

# How does it work

## Matiss' Visual
This scene is made up of 2 main components, a background frame which is created using a glsl shader and a an eye which is contained in an eye object. The glsl shader uses some basic triganometry to create a pattern and the eye object is used to work with the offsets used for the shaking pupil.

# What Im proud of
## Karl
- I got a better understanding of OOP concepts and how to use it in coding
- Adjusting the speed of the cubes based off the audio levels
- Learning how to extend other files to go into my Main visual file so it doesnt need to cramp up into one full file


