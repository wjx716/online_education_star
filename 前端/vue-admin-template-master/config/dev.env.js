'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"http://localhost:9001"', // nginx port
  OSS_API: '"https://online-education-star-file-chengdu.oss-cn-chengdu.aliyuncs.com"', // OSS path
})
