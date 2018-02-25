# angularportal

## Synopsis
This project is actually composed of 3 distinct subprojects designed to show
how 2 angular apps can coexist in a 'container' page.
The 3 projects are:
1.  portal - contains a single HTML file which loads the 2 other apps using jQuery
2.  portlet1 - an app generated using angular-cli with a few modifications to
               overcome the issue of 'Zone Already Loaded'
3.  portlet2 - same in nature as portlet1 except that this loads a different sample domain

## portal
This is nothing but a single HTML file which loads the portlet1 and portlet2
apps using jQuery.  Nothing fancy here.

## portlet1
An app generated using angular-cli with the following files modified
1.  .angular-cli.json - added the "deployUrl" so that URIs for resources are properly prefixed
2.  main.ts - added logic for 'dynamically' importing zone.js.  It checks if there is an
              existing one before trying to import it.
3.  app.module.ts - added {useHash : true} to router configuration
4.  tsconfig.app.json - set "module" value to "esnext"
5.  index.html - 
    *  changed the app-root element name to 'app-root1' to avoid collisions
             with other angular apps on the same page
    *  removed <BASE> element
6.  app.component.ts - updated 'selector' value to 'app-root1' to match element root
                name specified in item #5
