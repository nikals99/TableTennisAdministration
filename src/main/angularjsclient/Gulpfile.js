var gulp = require('gulp');
var sass = require('gulp-sass');
var sassOptions = {
    errLogToConsole: true,
    outputStyle: 'compressed'
};

var browserify = require('browserify');
var source = require('vinyl-source-stream');
var jshint = require('gulp-jshint');
var uglify = require('gulp-uglify');
var concat = require('gulp-concat');
var del = require('del');

gulp.task('browserify', function() {
    // Grabs the app.js file
    return browserify('src/js/app.js')
    // bundles it and creates a file called main.js
        .bundle()
        .pipe(source('main.js'))
        .pipe(gulp.dest('dist/js/'));
});

gulp.task('lint', function() {
    return gulp.src('src/js/**/*.js')
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});

gulp.task('scripts', function(){
    return gulp.src(['src/js/**/*.js'])
        .pipe(uglify())
        .pipe(concat('client.min.js'))
        .pipe(gulp.dest('dist/js'));
});

gulp.task('copy', ['browserify','styles'], function() {
    gulp.src(['src/**/*.html'])
        .pipe(gulp.dest('dist'))
});

gulp.task('build',['clean','lint', 'styles', 'copy','scripts']);

gulp.task('styles', function () {
    gulp.src('src/scss/**/*.scss').pipe(sass(sassOptions).on('error', sass.logError)).pipe(gulp.dest('dist/css/'));
});

gulp.task('clean',function () {
   return del('dist/**', {force: true});
});

gulp.task('watch', function(){
    gulp.watch("src/**/*.*", ["build"]);

});