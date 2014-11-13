@echo off
set MODULES=.
set MODULES=%MODULES%:../../mb4j-servlet
set MODULES=%MODULES%:../../mb4j-component
set MODULES=%MODULES%:../../mb4j-brick
watch-maven-targets %MODULES% class ./run.cmd
