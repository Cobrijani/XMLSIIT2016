#Client side for Error tracker application

## Followed by John Papa Angular style guide

#Install guide:

- `npm install -g gulp-cli yo bower nodemon`


#Project structure

- app.core -> all reusable modules
	- block.common -> generic angular components that can be reused
	- block.auth -> angular authentication and authorization framework
	- block.exception -> exception configuration
	- block.router -> angular ui router configuration
	- block.logger -> logging functionality
	- block.localStorage -> local storage extension functionality
	- block.util -> factories of util functions that can be reused
- app.entities -> data model
- app.properties -> properties of application



#NOTE
Each time new module is added must be noted above
