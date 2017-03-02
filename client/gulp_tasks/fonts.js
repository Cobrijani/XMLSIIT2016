/**
 * Created by SBratic on 1/29/2017.
 */

const gulp = require('gulp');
const cfg = require('./../conf/gulp.conf');

gulp.task('fonts', fonts);

function fonts() {
  return gulp.src([
    'app/bower_components/font-awesome/fonts/fontawesome-webfont.*'])
    .pipe(gulp.dest(conf.path.dest('/fonts/**')));
}
