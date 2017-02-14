const conf = require('./gulp.conf');
const proxy = require('proxy-middleware');
const url = require('url');


var proxyOptions = url.parse(conf.browsersync.backend);
proxyOptions.route = conf.browsersync.route;

module.exports = function () {
  return {
    server: {
      baseDir: [
        conf.paths.tmp,
        conf.paths.src
      ],
      middleware: [proxy(proxyOptions)],
      routes: {
        '/bower_components': 'bower_components'
      }
    },
    open: true
  };
};
