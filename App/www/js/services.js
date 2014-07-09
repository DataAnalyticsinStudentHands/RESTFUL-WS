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

var wsUrl = 'http://127.0.0.1\\:8080';

services.factory('UsersFactory', function ($resource) {
    return $resource(wsUrl + '/RESTFUL-WS/users', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});

services.factory('UserFactory', function ($resource) {
    return $resource(wsUrl + '/RESTFUL-WS/users/:id', {}, {
        show: { method: 'GET' },
        update: { method: 'POST', params: {id: '@id'} }, 
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});
