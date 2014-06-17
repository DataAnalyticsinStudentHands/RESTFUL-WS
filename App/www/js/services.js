'use strict';

/* Services */

/*
 http://docs.angularjs.org/api/ngResource.$resource

 Default ngResources are defined as

 'get':    {method:'GET'},
 'save':   {method:'POST'},
 'query':  {method:'GET', isArray:true},
 'remove': {method:'DELETE'},
 'delete': {method:'DELETE'}

 */

var services = angular.module('ngdemo.services', ['ngResource']);

var wsUrl = 'http://172.27.219.161\\:8080';

services.factory('DummyFactory', function ($resource) {
    return $resource(wsUrl + '/RESTFUL-WS/rest/dummy', {}, {
        query: { method: 'GET', params: {} }
    })
});

services.factory('UsersFactory', function ($resource) {
    return $resource(wsUrl + '/RESTFUL-WS/rest/users', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});

services.factory('UserFactory', function ($resource) {
    return $resource(wsUrl + '/RESTFUL-WS/rest/users/:id', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT', params: {id: '@id'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});
