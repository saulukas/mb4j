MODULES=.
MODULES=$MODULES:../../mb4j-servlet
MODULES=$MODULES:../../mb4j-component
MODULES=$MODULES:../../mb4j-brick
watch-maven-targets $MODULES class ./run.sh
