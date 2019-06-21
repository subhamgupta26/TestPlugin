var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'TestPlugin', 'coolMethod', [arg0]);
};

exports.methodWithMultipleArguments = function (arg0,arg1, success, error) {
    exec(success, error, 'TestPlugin', 'methodWithMultipleArguments', [arg0, arg1]);
};

exports.listBT = function (arg0, success, error) {
    exec(success, error, 'TestPlugin', 'listBT', [arg0]);
};
