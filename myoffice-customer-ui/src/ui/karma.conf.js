// Karma configuration file, see link for more information
// https://karma-runner.github.io/1.0/config/configuration-file.html

const path = require('path');

module.exports = function (config) {
    config.set({
        basePath: '',
        frameworks: ['jasmine', '@angular/cli'],
        plugins: [
            require('karma-jasmine'),
            require('karma-phantomjs-launcher'),
            require('karma-chrome-launcher'),
            require('karma-jasmine-html-reporter'),
            require('karma-coverage-istanbul-reporter'),
            require('karma-istanbul-threshold'),
            require('karma-junit-reporter'),
            require('@angular/cli/plugins/karma')
        ],
        client: {
            clearContext: false // leave Jasmine Spec Runner output visible in browser
        },
        junitReporter: {
            outputDir: path.join(__dirname, '/coverage/junit'), // results will be saved as $outputDir/$browserName.xml
            useBrowserName: true, // add browser name to report and classes names
            properties: {}, // key value pair of properties to add to the <properties> section of the report
            xmlVersion: null // use '1' if reporting to be per SonarQube 6.2 XML format
        },
        coverageIstanbulReporter: {
            reports: ['html', 'lcovonly', 'json'],
            dir: path.join(__dirname, '/coverage'),
            fixWebpackSourcePaths: true,
            skipFilesWithNoCoverage: true,
            'report-config': {
                html: {
                    subdir: 'html'
                }
            }
        },
        istanbulThresholdReporter: {
            src: 'coverage/coverage-final.json',
            reporters: ['text'],
            thresholds: {
                global: {
                    statements: 90,
                    branches: 90,
                    lines: 70,
                    functions: 90,
                },
                each: {
                    statements: 80,
                    branches: 80,
                    lines: 60,
                    functions: 80,
                },
            }
        },
        angularCli: {
            environment: 'dev'
        },
        reporters: config.angularCli && config.angularCli.codeCoverage ? ['progress', 'kjhtml', 'junit', 'coverage-istanbul', 'istanbul-threshold'] : ['progress', 'kjhtml', 'junit'],
        port: 9876,
        colors: true,
        logLevel: config.LOG_INFO,
        autoWatch: true,
        browsers: ['Chrome'],
        singleRun: false
    });
};
