// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js and so on 
var starterModule = angular.module('starter', ['ionic',
                           'restangular',
                           'ngCookies',
                           'ui.router',
                           'starter.controllers',
                           'starter.services',
                           'starter.filters',
                           'starter.directives']);

starterModule.run(function($ionicPlatform) {
  
	$ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if(window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if(window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
  });

	starterModule.run(['Restangular', '$rootScope', 'Auth', '$q', '$state', function(Restangular, $rootScope, Auth, $q, $state) {
	    
	    // Connect to server program (Eclipse is needed)
	    Restangular.setBaseUrl("http://127.0.0.1:8080/RESTFUL-WS/");  // localhost IP Address
	    
	    $rootScope.Restangular = function() {
	        return Restangular;
	    }
	    
	   	    
	    $rootScope.isAuthenticated = function() {
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
  

})

starterModule.config(function($stateProvider, $urlRouterProvider) {

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider

  
    // setup an abstract state for the tabs directive
    .state('tab', {
      url: "/tab",
      abstract: true,
      templateUrl: "partials/tabs.html"
    })

    // Each tab has its own nav history stack:

    .state('tab.dash', {
      url: '/dash',
      views: {
        'tab-dash': {
          templateUrl: 'partials/tab-dash.html',
          controller: 'loginCtrl'
        }
      }
    })

    .state('tab.users', {
      url: '/users',
      views: {
        'tab-users': {
          templateUrl: 'partials/tab-users.html',
          controller: 'FriendsCtrl'
        }
      }
    })
    .state('tab.user-detail', {
      url: '/user/:friendId',
      views: {
        'tab-users': {
          templateUrl: 'partials/user-detail.html',
          controller: 'FriendDetailCtrl'
        }
      }
    })

    .state('tab.account', {
      url: '/account',
      views: {
        'tab-account': {
          templateUrl: 'partials/tab-account.html',
          controller: 'AccountCtrl'
        }
      }
    })

  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/tab/dash');

});

