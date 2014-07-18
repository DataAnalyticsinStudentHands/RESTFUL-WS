'use strict';

// Declare app level module which depends on filters, and services
var ngdemoModule = angular.module('ngdemo', ['restangular',
                                             'ngRoute',
                                             'ngCookies',
                                             'ngdemo.filters',
                                             'ngdemo.services',
                                             'ngdemo.directives',
                                             'ngdemo.controllers',
                                             'ui.router']);
	
// Switched from routeProvider to stateProvider in order to work better with
// DatabaseCommunicationModule. Each child state has a controller associated with it.
// Added authentication for the states. When the app runs, you are sent to the login state.

ngdemoModule.config(['$httpProvider', '$stateProvider', '$urlRouterProvider',
    function($httpProvider, $stateProvider, $urlRouterProvider){
        
        $urlRouterProvider.otherwise("/login");
        
        $stateProvider.
            state('loggedout', {
                abstract: true,
                template: "<ui-view>"
            }).
            state('login', {
                url: "/login",
                templateUrl: "partials/login.html",
                controller: 'loginCtrl',
                authenticate: false                 // you don't need authenticaiton for this state
            });

        $stateProvider.
            state('loggedin', {
                abstract: true,
                template: "<ui-view>"
            }).
            state('secure', {
                url: "/secure",
                templateUrl: "partials/secure.html",
                controller: 'secureCtrl',
                authenticate: true              // true means you need authenticaiton for this state
            });
        
        $stateProvider.
            state('peggy', {
                abstract: true,
                template: "<ui-view>"
            }).
            state('user-list', {
                url: "/user-list",
                templateUrl: "partials/user-list.html",
                controller: 'UserListCtrl',
                authenticate: true
            }).
            state('user-detail', {
                url: "/user-detail/:id",
                templateUrl: "partials/user-detail.html",
                controller: 'UserDetailCtrl',
                authenticate: true
            }).
            state('user-creation', {
                url: "/user-creation",
                templateUrl: "partials/user-creation.html",
                controller: 'UserCreationCtrl',
                authenticate: true
            });
        
        // http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }]);
/*
ngdemoModule.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
        
    $routeProvider.when('/user-list', {
        templateUrl: 'partials/user-list.html',
        controller: 'UserListCtrl'
    });
    
    $routeProvider.when('/user-detail/:id', {
        templateUrl: 'partials/user-detail.html',
        controller: 'UserDetailCtrl'
    });
    
    $routeProvider.when('/user-creation', {
        templateUrl: 'partials/user-creation.html',
        controller: 'UserCreationCtrl'
    });
    
    $routeProvider.otherwise({redirectTo: '/user-list'});

    // http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
}]);
*/

ngdemoModule.run(['Restangular', '$rootScope', 'Auth', '$q', '$state', function(Restangular, $rootScope, Auth, $q, $state) {
    
    // Connect to server program (Eclipse is needed)
    Restangular.setBaseUrl("http://127.0.0.1:8080/RESTFUL-WS/");  // localhost IP Address
    
    $rootScope.Restangular = function() {
        return Restangular;
    }
    
    $rootScope.addAuth = function() {
        //
    }
    
    $rootScope.isAuthenticated = function() {
        //BELOW - Trying to get promises to work to verify auth
//        var deferred = $q.defer();
//        //This should be set to a work-all URL.
//        var rqPromise = Restangular.all("users").get("2").then(function(result) {
//            console.log("authed");
//            return true;
//        }, function(error) {
//            Auth.clearCredentials();
//            console.log("not-authed");
//            return false;
//        });
//        return deferred.resolve(rqPromise);
        //END
        return Auth.hasCredentials();
    }
    
    $rootScope.$on("$stateChangeStart", function(event, toState, toParams, fromState, fromParams){
      console.log("$stateChangeStart");
      console.log($rootScope.isAuthenticated());
      if (toState.authenticate && !$rootScope.isAuthenticated()){
        console.log("non-authed");
        // User isn’t authenticated
        $state.go("login");
        //What?
        event.preventDefault(); 
      } else console.log("authed");
    });
}]);