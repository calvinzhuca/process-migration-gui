GUI for the PIM service. 

git clone <this project> 

cd instance-gui

npm install

npm start

## Comments from Sarah about setup for styling
# Less
A single pim-main.less pulls in patternfly, patternfly additions, sets font paths, and then pulls in custom KIE less files. After this, pim-custom is imported.

This creates a more realistic environment, as the PIM React code will eventually live inside the workbench and receive it's styling (maybe? Or does React isolate it?)