
Mustache Bricks for Java
========================

Under construction... early stage...

Builds on top of jmustache with most of the nice stuff it has.

When you limit yourself in some places you get more freedom in other.
mb4j's main limitation: template file and context model .java file must
be place side by side like in wicket.apache.org.

This way you are forced to provide separate view model class for mustache 
template. This model-template pair is called a brick.

What we gain:

* effortless implementation of partials: just put one brick object 
  into the other and you are done - no custom template loading stuff

* correspondence between template and model is checked during template
  compilation 

* much nicer HTML source because of preserved indentation of partials and
  empty lines removed for null or empty sections 

