/*global module, global, require, process*/
module.exports = function(grunt) {
	var buildDir = './build';
    var sourceDir = './src/main/js';
	var config = {
		// configuration from package.json
		pkg: grunt.file.readJSON('package.json'),

		// clean up task
		clean: {
			all: [buildDir]
		},

		// build project structure
		sync: {
			all: {
				files: [{
					src: ['**'],
					dest: buildDir,
					cwd: sourceDir
				}]
			}
		},

		concat: {
			src: {
				options: {
					process: function(src, filepath) {
						var wrappedSrc = '//' + filepath + '\n';
						wrappedSrc += src;
						wrappedSrc += '\n';
						return wrappedSrc;
					}
				},
				files: [{
					src: [
						sourceDir + '/sjl.js', sourceDir + '/core/*.js', sourceDir + '/component/**/*.js',
						'!' + sourceDir + '/bootstrap.js', '!' + sourceDir + '/config/*.js'
					],
					dest: buildDir + '/sjl-debug.js'
				}]
			}
		},

		uglify: {
            src: {
                options: {
                    mangle: true,
                    compress: {
                        pure_funcs: [ 'console.log' ]
                    }
                },
                files: [{
                    src: buildDir + '/sjl-debug.js',
                    dest: buildDir + '/sjl.js'
                }]
            }
		}
	};

	grunt.initConfig(config);

	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-sync');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-uglify');

	// Default task(s).
	grunt.registerTask('install', ['clean:all', 'sync:all', 'concat:src', 'uglify:src']);
};