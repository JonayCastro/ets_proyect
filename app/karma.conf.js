const webpack = require('webpack');
const path = require('path');

module.exports = function(config) {
  config.set({
    basePath: '',

    frameworks: ['jasmine'],

    files: [
      { pattern: 'src/**/*.spec.ts', watched: false },
      { pattern: 'components/**/*.spec.ts', watched: false },
    ],

    exclude: [],

    preprocessors: {
      'src/**/*.ts': ['webpack'],
    },

    webpack: {
      mode: 'development',
      resolve: {
        extensions: ['.ts', '.js'],
      },
      module: {
        rules: [
          {
            test: /\.ts$/,
            use: 'ts-loader',
            exclude: /node_modules/,
          },
        ],
      },
      devtool: 'inline-source-map',
    },

    webpackMiddleware: {
      stats: 'errors-only',
    },
 
    reporters: ['progress'],

    port: 9876,
    colors: true,
    logLevel: config.LOG_INFO,
    autoWatch: true,
    browsers: ['Chrome'],
    singleRun: false,
    concurrency: Infinity
  });
};